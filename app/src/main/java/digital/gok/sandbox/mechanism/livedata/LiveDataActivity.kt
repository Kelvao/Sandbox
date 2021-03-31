package digital.gok.sandbox.mechanism.livedata

import androidx.appcompat.app.AppCompatActivity
import digital.gok.sandbox.data.repository.ProductsModel
import digital.gok.sandbox.data.repository.base.ErrorCode
import digital.gok.sandbox.data.repository.base.SandboxException

abstract class LiveDataActivity : AppCompatActivity() {

    fun observeChanges(productsRestModelLiveData: MutableLiveDataResource<ProductsModel>) {
        productsRestModelLiveData.observe(this, { resource ->
            when (resource.status) {
                Status.LOADING -> setLoaderVisibility(true)
                Status.SUCCESS -> {
                    setLoaderVisibility(false)
                    resource.data?.let { onSuccess(it) }
                        ?: onError(SandboxException(ErrorCode.GENERIC_ERROR))
                }
                Status.ERROR -> {
                    setLoaderVisibility(false)
                    onError(resource.error)
                }
            }
        })
    }

    abstract fun onSuccess(productsModel: ProductsModel)

    abstract fun setLoaderVisibility(isLoading: Boolean)

    abstract fun onError(error: SandboxException)
}