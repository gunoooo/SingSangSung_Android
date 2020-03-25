package com.gunwoo.karaoke.data.database

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import com.gunwoo.karaoke.data.database.dao.*
import com.gunwoo.karaoke.data.database.entity.*

@Database(entities = [RecordEntity::class, DownloadEntity::class,
    HidingEntity::class, FavoritesEntity::class, RecentEntity::class,
    PlaylistEntity::class, SearchEntity::class],
    version = 1, exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun recordDao(): RecordDao
    abstract fun downloadDao(): DownloadDao
    abstract fun hidingDao(): HidingDao
    abstract fun favoritesDao(): FavoritesDao
    abstract fun recentDao(): RecentDao
    abstract fun playlistsDao(): PlaylistDao
    abstract fun searchDao(): SearchDao

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
