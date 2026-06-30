package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.Alarm
import com.example.data.AlarmDao
import com.example.data.AppDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class AlarmDatabaseTest {

    private lateinit var alarmDao: AlarmDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        alarmDao = db.alarmDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndReadAlarm() = runBlocking {
        val alarm = Alarm(id = "test_id", time = "08:00", amPm = "AM", label = "Test Alarm", isEnabled = true, days = "", isScheduled = true)
        alarmDao.insertAlarm(alarm)

        val alarms = alarmDao.getAllAlarms().first()
        assertEquals(1, alarms.size)
        assertEquals("Test Alarm", alarms[0].label)
    }

    @Test
    fun deleteAlarm() = runBlocking {
        val alarm = Alarm(id = "test_id_2", time = "09:00", amPm = "AM", label = "Delete Test", isEnabled = true, days = "", isScheduled = true)
        alarmDao.insertAlarm(alarm)
        
        var alarms = alarmDao.getAllAlarms().first()
        assertEquals(1, alarms.size)

        alarmDao.deleteAlarmById("test_id_2")
        alarms = alarmDao.getAllAlarms().first()
        assertEquals(0, alarms.size)
    }
}
