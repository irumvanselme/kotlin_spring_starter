package com.okavaa.kotlin_spring_starter.utils.security

import com.okavaa.kotlin_spring_starter.utils.security.JwtAuthenticationFilter
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private val tokenProvider: JwtTokenProvider? = null

    @Autowired
    private val customUserDetailsService: CustomUserDetailsService? = null
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = getJwtFromRequest(httpServletRequest)
            if (StringUtils.hasText(jwt) && tokenProvider!!.validateToken(jwt)) {
                val userID = tokenProvider.getUserIdFromToken(jwt)
                val userDetails = customUserDetailsService!!.loadByUserId(userID)
                Companion.logger.info(userDetails.authorities.toString())
                val authenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        } catch (e: Exception) {
            Companion.logger.error("Could not set user authentication in security context :" + e.message)
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    }
}