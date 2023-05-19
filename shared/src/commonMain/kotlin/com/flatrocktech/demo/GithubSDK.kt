package com.flatrocktech.demo

class GithubSDK {
    private val api = GithubApi()

    @Throws(Exception::class)
    suspend fun getRepos(): List<GithubRepositoryModel> {
        return api.getPublicRepos("nmgalo")
    }
}