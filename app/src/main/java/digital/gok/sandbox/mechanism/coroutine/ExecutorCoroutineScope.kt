package digital.gok.sandbox.mechanism.coroutine

import digital.gok.sandbox.data.repository.base.SandboxException

interface ExecutorCoroutineScope {
    fun cancelJobs()
    fun runCoroutine(run: suspend () -> Unit): ConcurrencyContinuation =
        ConcurrencyContinuation(run)

    infix fun ConcurrencyContinuation.onError(onError: (SandboxException) -> Unit)
}

inline class ConcurrencyContinuation(val action: suspend () -> Unit)