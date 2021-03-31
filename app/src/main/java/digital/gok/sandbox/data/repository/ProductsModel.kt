package digital.gok.sandbox.data.repository

import digital.gok.sandbox.data.remote.model.ProductsRestModel

data class ProductsModel(
    var spotlight: List<ProductModel> = emptyList(),
    var products: List<ProductModel> = emptyList(),
    var cash: ProductModel = ProductModel()
) {
    data class ProductModel(
        var name: String = "",
        var bannerURL: String = "",
        var description: String = ""
    ) {
        constructor(spotlightRestModel: ProductsRestModel.SpotlightRestModel): this(
            spotlightRestModel.name.orEmpty(),
            spotlightRestModel.bannerURL.orEmpty(),
            spotlightRestModel.description.orEmpty()
        )

        constructor(productRestModel: ProductsRestModel.ProductRestModel): this(
            productRestModel.name.orEmpty(),
            productRestModel.imageURL.orEmpty(),
            productRestModel.description.orEmpty()
        )

        constructor(cashRestModel: ProductsRestModel.CashRestModel): this(
            cashRestModel.title.orEmpty(),
            cashRestModel.bannerURL.orEmpty(),
            cashRestModel.description.orEmpty()
        )
    }

    constructor(productsRestModel: ProductsRestModel) : this (
        productsRestModel.spotlight?.map { ProductModel(it) }?.toList().orEmpty(),
        productsRestModel.products?.map { ProductModel(it) }?.toList().orEmpty(),
        productsRestModel.cash?.let { ProductModel(it) } ?: ProductModel()
    )
}
