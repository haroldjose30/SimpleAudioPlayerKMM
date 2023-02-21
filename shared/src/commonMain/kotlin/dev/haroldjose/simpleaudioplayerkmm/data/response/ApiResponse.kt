package dev.haroldjose.simpleaudioplayerkmm.data.response

import kotlinx.serialization.Serializable

/**
 * Wrapper object for all api requests
 */
@Serializable
internal data class ApiResponse<T>(val data: T?)