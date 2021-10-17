package com.efronnypardede.parrotchat.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    @JvmStatic fun formatToDisplayTimestamp(timestamp: Long?): String {
        timestamp ?: return ""
        val date = Date(timestamp)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
        return formatter.format(date)
    }
}