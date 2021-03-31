package digital.gok.sandbox.data.repository.base

class SandboxException(var errorCode: ErrorCode, var errorMessage : String? = "") : RuntimeException()
