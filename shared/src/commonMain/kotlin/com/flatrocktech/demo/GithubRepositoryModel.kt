package com.flatrocktech.demo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositoryModel(
    val name: String,
    @SerialName("full_name")
    val fullName: String,
)
