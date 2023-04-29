package dev.dicesystems.varsitutor.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dicesystems.varsitutor.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE id = (:userId)")
    suspend fun findById(userId: Int): UserEntity

    @Query("SELECT COUNT(*) FROM User WHERE token = (:token)")
    suspend fun userExists(token: String): Int

    @Query("SELECT * FROM User WHERE token = (:token)")
    suspend fun findByToken(token: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

}