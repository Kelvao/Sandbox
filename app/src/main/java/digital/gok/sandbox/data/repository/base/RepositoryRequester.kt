package digital.gok.sandbox.data.repository.base

import com.google.gson.Gson
import digital.gok.sandbox.data.remote.model.ErrorMessage
import okhttp3.ResponseBody
import retrofit2.Response

fun <T : Any> performRequest(response: Response<T>, verifyBody: Boolean = true): Any {
    try {
        return if (response.isSuccessful) {
            if (verifyBody) {
                response.body() ?: treatError(response.errorBody())
            } else true
        } else {
            treatError(response.errorBody())
        }
    } catch (e: Exception) {
        if (e is SandboxException) {
            throw SandboxException(e.errorCode)
        } else {
            throw SandboxException(ErrorCode.GENERIC_ERROR, e.localizedMessage)
        }
    }
}

fun treatError(errorBody: ResponseBody? = null) {
    errorBody?.string()?.let { error ->
        Gson().fromJson(error, ErrorMessage::class.java)?.let { wsError ->
            throw SandboxException(
                ErrorCode.fromString(wsError.name)
            )
        }
    } ?: throw SandboxException(ErrorCode.GENERIC_ERROR)
}