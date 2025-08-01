package org.sharad.emify.core.data.local.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
