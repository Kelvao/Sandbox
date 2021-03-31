package digital.gok.sandbox.data.remote.model

import digital.gok.sandbox.data.repository.ProductsModel
import java.io.Serializable

data class ProductsRestModel(
    var spotlight: List<SpotlightRestModel>? = emptyList(),
    var products: List<ProductRestModel>? = emptyList(),
    var cash: CashRestModel? = null
) : Serializable {
    data class SpotlightRestModel(
        var name: String? = "",
        var bannerURL: String? = "",
        var description: String? = ""
    ) : Serializable

    data class ProductRestModel(
        var name: String? = "",
        var imageURL: String? = "",
        var description: String? = ""
    ) : Serializable

    data class CashRestModel(
        var title: String? = "",
        var bannerURL: String? = "",
        var description: String? = ""
    ) : Serializable

    fun toModel() = ProductsModel(this)
}
