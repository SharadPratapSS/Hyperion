package org.sharad.emify.core.util

import kotlinx.datetime.*
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun getTodayDate(): LocalDate {
    val nowInstant = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    return nowInstant.toLocalDateTime(zone).date
}

@OptIn(ExperimentalTime::class)
fun getTodayMillis(): Long {
    val today = getTodayDate()
    val zone = TimeZone.currentSystemDefault()
    return today.atStartOfDayIn(zone).toEpochMilliseconds()
}


fun formatDateParts(date: LocalDate): Pair<String, String> {
    val year = date.year.toString()
    val dateFormatter = LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED); char(','); char(' ')
        day(padding = Padding.ZERO)
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
    }

    val formattedDate = date.format(dateFormatter)
    return year to formattedDate
}


@OptIn(ExperimentalTime::class)
fun formatMillisDateParts(millis: Long?, timeZone: TimeZone = TimeZone.currentSystemDefault()): Pair<String, String>? {
    if (millis == null) return null

    val instant = Instant.fromEpochMilliseconds(millis)
    val localDate = instant.toLocalDateTime(timeZone).date

    val year = localDate.year.toString()
    val dateFormatter = LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED); char(','); char(' ')
        day(padding = Padding.ZERO)
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
    }

    val formattedDate = localDate.format(dateFormatter)
    return year to formattedDate
}

@OptIn(ExperimentalTime::class)
fun formatMillisToSlashDate(
    millis: Long?,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String? {
    if (millis == null) return null

    val instant = Instant.fromEpochMilliseconds(millis)
    val localDate = instant.toLocalDateTime(timeZone).date

    // Ensure day and month are always 2 digits
    val day = localDate.day.toString().padStart(2, '0')
    val month = localDate.month.number.toString().padStart(2, '0')
    val year = localDate.year.toString()
    val time= instant.toLocalDateTime(timeZone).time


    return "$day/$month/$year"
}

