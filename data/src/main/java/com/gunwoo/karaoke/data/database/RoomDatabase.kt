package com.gunwoo.karaoke.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import com.gunwoo.karaoke.data.database.dao.RecordDao
import com.gunwoo.karaoke.data.database.entity.RecordEntity

@Database(entities = [RecordEntity::class],
    version = 1, exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {

        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        RoomDatabase::class.java, "singsangsung_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}
