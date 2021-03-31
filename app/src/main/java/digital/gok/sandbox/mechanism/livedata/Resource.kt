package digital.gok.sandbox.mechanism.livedata

import androidx.lifecycle.MutableLiveData
import digital.gok.sandbox.data.repository.base.ErrorCode
import digital.gok.sandbox.data.repository.base.SandboxException

data class Resource<out DataType>(
    val status: Status,
    val data: DataType? = null,
    val error: SandboxException = SandboxException(ErrorCode.GENERIC_ERROR)
) {

    companion object {

        fun <DataType> loading(): Resource<DataType> = Resource(Status.LOADING)

        fun <DataType> success(data: DataType) = Resource(Status.SUCCESS, data)

        fun <DataType> error(
            exception: SandboxException,
            data: DataType? = null
        ): Resource<DataType> = Resource(Status.ERROR, data, exception)
    }
}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
}

typealias MutableLiveDataResource<DataType> = MutableLiveData<Resource<DataType>>
