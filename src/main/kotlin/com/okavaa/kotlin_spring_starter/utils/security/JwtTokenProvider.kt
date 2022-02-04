package com.okavaa.kotlin_spring_starter.utils.security

import io.jsonwebtoken.*

import com.okavaa.kotlin_spring_starter.models.User
import io.jsonwebtoken.SignatureAlgorithm
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.rsocket.RSocketSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashSet

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.expiresIn}")
    private val jwtExpirationInMs = 0
    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)
        val grantedAuthorities: MutableSet<GrantedAuthority> = HashSet()
        for (role in userPrincipal.authorities) {
            grantedAuthorities.add(SimpleGrantedAuthority(role.authority))
        }
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        val authUser: User = modelMapper.map(userPrincipal, User::class.java)
        println(authUser)
        return Jwts.builder().setId(authUser.id.toString()).setSubject(userPrincipal.id.toString() + "")
            .claim("user", authUser).claim("authorities", grantedAuthorities)
            .setIssuedAt(Date(System.currentTimeMillis())).setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun getUserIdFromToken(token: String?): Long {
        val claims: Claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody()
        return claims.subject.toLong()
    }

    fun validateToken(authToken: String?): Boolean {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature", ex)
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token", ex)
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token$ex")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token$ex")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty $ex")
        }
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }
}