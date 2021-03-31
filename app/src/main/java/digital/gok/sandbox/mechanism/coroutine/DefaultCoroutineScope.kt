package digital.gok.sandbox.mechanism.coroutine

import digital.gok.sandbox.data.repository.base.SandboxException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DefaultCoroutineScope : ExecutorCoroutineScope, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + Job()

    override fun cancelJobs() {
        coroutineContext.cancel()
    }

    override infix fun ConcurrencyContinuation.onError(onError: (SandboxException) -> Unit) {
        initCoroutine(this.action, onError)
    }

    private fun initCoroutine(run: suspend () -> Unit, onError: (SandboxException) -> Unit = {}) =
        launch {
            try {
                run()
            } catch (e: SandboxException) {
                onError(e)
            }
        }
}

fun getCoroutineScope(): DefaultCoroutineScope =
    DefaultCoroutineScope()