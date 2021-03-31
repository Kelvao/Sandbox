package digital.gok.sandbox.data.repository.base

enum class ErrorCode (val value: String){
    ASYNC_TASK_ERROR("Async Task Error"),
    GENERIC_ERROR("Generic Error"),
    INTERNET_CONNECTION_UNAVAILABLE("INTERNET_CONNECTION_UNAVAILABLE"),
    NOT_FOUND("Not Found"),
    INVALID("Invalid");

    companion object {
        fun fromString(value: String?) = values().find { it.value == value } ?: INVALID
    }

}