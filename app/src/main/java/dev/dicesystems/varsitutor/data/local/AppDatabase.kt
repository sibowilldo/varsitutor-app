package dev.dicesystems.varsitutor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dicesystems.varsitutor.data.local.dao.UserDao
import dev.dicesystems.varsitutor.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}