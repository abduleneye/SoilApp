package com.eneye.soilapp.domain.repo

import com.eneye.soilapp.domain.model.Feed
import com.eneye.soilapp.domain.model.SensorParameterModel
import com.voyatek.tripapp.features.trips.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SensorParameterApiRepo{
    fun getSensorParameters(): Flow<Resource<List<Feed>>>
}