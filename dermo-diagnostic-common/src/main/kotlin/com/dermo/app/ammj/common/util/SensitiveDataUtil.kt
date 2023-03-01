package com.dermo.app.ammj.common.util

import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.util.Date

object SensitiveDataUtil {

    private val logger = LoggerFactory.getLogger(SensitiveDataUtil::class.java)

    private val CLASS = SensitiveDataUtil::class.simpleName

    private const val ERROR = "***OBFUSCATION_ERROR***"

    enum class SensitiveDataRegex(val regex: Regex) {
        // Capture Group 1: first 4 chars - public email chars, it should be public on logs
        // Capture Group 2: all chars until @ - to be hidden and replaced with *
        // Capture Group 3: rest of the string - to remain as it is, it should be public on logs
        EMAIL_SENSITIVE_POSITION_GROUPS(Regex("(^.{4})(.*)(?=@)(.*)")),

        // Capture only digits, to exclude date separator, used to be replaced with * on date and to hide number fields
        ONLY_DIGITS_REGEX(Regex("\\d")),

        // Capture any word character. Used to be replaced with * on text fields
        ONLY_CHARACTERS_REGEX(Regex("\\w"))
    }

    /**
     * This implementation supports numbers longer than 7 digits
     */
    fun hideNumber(number: Long): String {
        val showFirstAndLastChars = 3
        return try {
            val numberStr = number.toString()
            val hiddenChars =
                numberStr.substring(showFirstAndLastChars, number.toString().length - showFirstAndLastChars)
                    .replace(SensitiveDataRegex.ONLY_DIGITS_REGEX.regex, "*")
            numberStr.replaceRange(
                showFirstAndLastChars,
                number.toString().length - showFirstAndLastChars,
                hiddenChars
            )
        } catch (ex: Exception) {
            logger.error("--$CLASS:hideNumber --Exception --cause[{}]", ex.cause)
            logger.error("--$CLASS:hideNumber --Exception --message[{}]", ex.message)
            ERROR
        }
    }

    /**
     * This implementation supports strings longer than 5 digits
     */
    fun hideText(text: String): String {
        val showFirstAndLastChars = 2
        return try {
            val hiddenChars = text.substring(showFirstAndLastChars, text.length - showFirstAndLastChars)
                .replace(SensitiveDataRegex.ONLY_CHARACTERS_REGEX.regex, "*")
            text.replaceRange(showFirstAndLastChars, text.length - showFirstAndLastChars, hiddenChars)
        } catch (ex: Exception) {
            logger.error("--$CLASS:hideText --Exception --cause[{}]", ex.cause)
            logger.error("--$CLASS:hideText --Exception --message[{}]", ex.message)
            ERROR
        }
    }

    fun hideDate(date: String): String {
        return try {
            date.replace(SensitiveDataRegex.ONLY_DIGITS_REGEX.regex, "*")
        } catch (ex: Exception) {
            logger.error("--$CLASS:hideDate --Exception --cause[{}]", ex.cause)
            logger.error("--$CLASS:hideDate --Exception --message[{}]", ex.message)
            ERROR
        }
    }

    fun hideDate(date: Date, pattern: String = "MM/dd/yyyy"): String {
        return try {
            DateUtil.convertDateToString(date, pattern).replace(SensitiveDataRegex.ONLY_DIGITS_REGEX.regex, "*")
        } catch (ex: Exception) {
            logger.error("--$CLASS:hideDate --Exception --cause[{}]", ex.cause)
            logger.error("--$CLASS:hideDate --Exception --message[{}]", ex.message)
            ERROR
        }
    }

    fun hideDate(date: LocalDate, pattern: String = "MM/dd/yyyy"): String {
        return try {
            DateUtil.convertDateToString(date, pattern).replace(SensitiveDataRegex.ONLY_DIGITS_REGEX.regex, "*")
        } catch (ex: Exception) {
            logger.error("--$CLASS:hideDate --Exception --cause[{}]", ex.cause)
            logger.error("--$CLASS:hideDate --Exception --message[{}]", ex.message)
            ERROR
        }
    }

    fun hideEmail(email: String): String {
        return try {
            val matchedValues = SensitiveDataRegex.EMAIL_SENSITIVE_POSITION_GROUPS.regex.find(email)?.groupValues!!
            val publicChars = matchedValues[1]
            val hiddenChars = matchedValues[2].replace(Regex("."), "*")
            val domainName = matchedValues[3]
            publicChars + hiddenChars + domainName
        } catch (ex: Exception) {
            logger.error("--$CLASS:hideEmail --Exception --cause[{}]", ex.cause)
            logger.error("--$CLASS:hideEmail --Exception --message[{}]", ex.message)
            ERROR
        }
    }
}
