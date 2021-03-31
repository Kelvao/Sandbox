package digital.gok.sandbox.data.repository

import digital.gok.sandbox.data.remote.datasource.SandboxDataSource
import digital.gok.sandbox.data.remote.model.ProductsRestModel
import digital.gok.sandbox.data.repository.base.performRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SandboxRepository(private val dataSource: SandboxDataSource) {

    suspend fun getProducts() = withContext(Dispatchers.IO) {
        return@withContext (performRequest(
            dataSource.getProducts().execute()
        ) as ProductsRestModel).toModel()
    }
}