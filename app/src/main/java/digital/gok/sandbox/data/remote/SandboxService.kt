package digital.gok.sandbox.data.remote

import digital.gok.sandbox.data.remote.model.ProductsRestModel
import retrofit2.Call
import retrofit2.http.GET

interface SandboxService {

    @GET("products")
    fun getProducts() : Call<ProductsRestModel>
}