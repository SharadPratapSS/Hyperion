package org.sharad.emify.core.data.local.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TestInterfaceDao {
    @Query("SELECT * FROM TestEntity")
    fun query(): Flow<List<TestEntity>>

    @Query("DELETE FROM TestEntity")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(testEntity: TestEntity)


}