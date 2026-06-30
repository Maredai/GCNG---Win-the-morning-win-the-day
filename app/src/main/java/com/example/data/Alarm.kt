package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val time: String,
    val amPm: String,
    val label: String,
    val isEnabled: Boolean,
    val days: String = "",
    val isScheduled: Boolean = true
)
