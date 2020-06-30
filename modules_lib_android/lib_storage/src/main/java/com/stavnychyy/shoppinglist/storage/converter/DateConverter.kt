package com.stavnychyy.shoppinglist.storage.converter

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


object DateConverter {
    @TypeConverter
    @JvmStatic
    fun stringToDate(str: String): LocalDate? = LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE)

    @TypeConverter
    @JvmStatic
    fun dateToString(dateTime: LocalDate): String? = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)
}
