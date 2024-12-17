package com.berkayyaman.footballapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.berkayyaman.footballapp.data.remote.dto.FixtureInfo
import com.berkayyaman.footballapp.domain.model.TeamUiModel

/**
 * Created by berkayyaman on 22,October,2024
 */

@Database(
    entities = [TeamUiModel::class,FixtureInfo::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(FootballTypeConverter::class)
abstract class FootballDatabase: RoomDatabase() {
    abstract val footballDao: FootballDao
}