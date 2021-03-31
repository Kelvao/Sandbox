package digital.gok.sandbox.data.remote.datasource

import digital.gok.sandbox.data.remote.SandboxService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SandboxDataSource (private val sandboxService: SandboxService) {

    suspend fun getProducts() = withContext(Dispatchers.IO) { sandboxService.getProducts() }

}