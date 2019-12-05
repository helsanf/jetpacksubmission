package com.helsanf.jetpacksubmision.data.remote

class ApiResponse<out T>(val statusResponse: StatusResponse, val body: T?, val message: String?) {

    companion object {
        fun <T> success(body : T?) : ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS,body,null)
        fun <T> empty(message : String,body : T?) : ApiResponse<T> = ApiResponse(StatusResponse.EMPTY,body,message)
        fun <T> error(message : String,body : T?) : ApiResponse<T> = ApiResponse(StatusResponse.ERROR,body,message)
    }
}