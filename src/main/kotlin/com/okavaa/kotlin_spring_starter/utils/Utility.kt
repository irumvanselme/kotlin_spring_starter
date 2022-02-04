package com.okavaa.kotlin_spring_starter.utils

import com.okavaa.kotlin_spring_starter.utils.exceptions.BadRequestException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object Utility {
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
    fun from(page: Int, limit: Int): Pageable {
        return PageRequest.of(page, limit, Sort.Direction.ASC, "id")
    }

    fun encode(password: String?): String {
        return passwordEncoder.encode(password)
    }

    fun matches(raw: String?, encoded: String?): Boolean {
        return passwordEncoder.matches(raw, encoded)
    }

    fun saveFile(document: MultipartFile, directory: String?): String {
        return try {
            val path = Paths.get(directory)
            val filename: String = UUID.randomUUID().toString() + "_" + document.getOriginalFilename()
            Files.copy(document.getInputStream(), path.resolve(Objects.requireNonNull(filename)))
            filename
        } catch (e: Exception) {
            e.printStackTrace()
            throw BadRequestException(e.message)
        }
    }

    fun removeFile(profileImage: String?, directory: String?) {
        try {
            var path = Paths.get(directory)
            path = path.resolve(profileImage)
            Files.delete(path)
        } catch (e: Exception) {
            throw BadRequestException("Failed to remove the file : " + e.message)
        }
    }

    @Throws(Exception::class)
    fun isValidExtension(fileName: String, extensions: String): Boolean {
        var fileExtension = getFileExtension(fileName)
            ?: throw Exception("No File Extension")
        fileExtension = fileExtension.lowercase(Locale.getDefault())
        for (validExtension in extensions.split(",".toRegex()).toTypedArray()) {
            if (fileExtension == validExtension) {
                return true
            }
        }
        return false
    }

    private fun getFileExtension(fileName: String): String? {
        val dotIndex = fileName.lastIndexOf(".")
        return if (dotIndex < 0) {
            null
        } else fileName.substring(dotIndex + 1)
    }
}