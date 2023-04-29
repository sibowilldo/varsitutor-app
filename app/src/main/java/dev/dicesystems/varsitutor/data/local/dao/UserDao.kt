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
    fun findById(userId: Int): UserEntity

    @Query("SELECT COUNT(*) FROM User WHERE id = (:token)")
    fun userExists(token: String): Int

    @Query("SELECT * FROM User WHERE id = (:token)")
    fun findByToken(token: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

}