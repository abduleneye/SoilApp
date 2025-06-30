package com.eneye.soilapp.domain.model

data class SensorParameterModel(
    val channel: Channel,
    val feeds: List<Feed>
)