package dev.haroldjose.simpleaudioplayerkmm.data.repository.remote

import dev.haroldjose.simpleaudioplayerkmm.data.response.ApiResponse
import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AudioRemoteRepository: IAudioRemoteRepository {

    private val apiUrl = "https://nomad5.com/data/skoove/manifest.json"

    //TODO: Add HttpClient to DI
    private val client = HttpClient() {
        defaultRequest {
            url(apiUrl)
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getAll(): List<AudioEntryDTO> {

        val httpResponse = client.get {  }

        if (httpResponse.status.value in 200..299) {
            val apiResponse = httpResponse.body<ApiResponse<List<AudioEntryDTO>>>()
            return apiResponse.data ?: arrayListOf()
        }

        //TODO: Handle error
        return arrayListOf()
    }
}