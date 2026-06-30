package com.example.data

import kotlinx.coroutines.flow.Flow

class AlarmRepository(private val alarmDao: AlarmDao) {
    val allAlarms: Flow<List<Alarm>> = alarmDao.getAllAlarms()

    suspend fun insert(alarm: Alarm) = alarmDao.insertAlarm(alarm)

    suspend fun deleteById(id: String) = alarmDao.deleteAlarmById(id)
    
    suspend fun toggleStatus(id: String, isEnabled: Boolean) = alarmDao.updateAlarmStatus(id, isEnabled)
}
