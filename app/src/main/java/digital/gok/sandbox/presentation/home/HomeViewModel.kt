package digital.gok.sandbox.presentation.home

import androidx.lifecycle.ViewModel
import digital.gok.sandbox.data.repository.ProductsModel
import digital.gok.sandbox.data.repository.SandboxRepository
import digital.gok.sandbox.mechanism.coroutine.ExecutorCoroutineScope
import digital.gok.sandbox.mechanism.coroutine.getCoroutineScope
import digital.gok.sandbox.mechanism.livedata.MutableLiveDataResource
import digital.gok.sandbox.mechanism.livedata.Resource

class HomeViewModel(
    private val repository: SandboxRepository
) : ViewModel(), ExecutorCoroutineScope by getCoroutineScope() {

    val productsLiveData = MutableLiveDataResource<ProductsModel>()

    fun getProducts() = runCoroutine {
        productsLiveData.postValue(Resource.loading())
        productsLiveData.postValue(Resource.success(repository.getProducts()))
    } onError {
        productsLiveData.postValue(Resource.error(it))
    }

}