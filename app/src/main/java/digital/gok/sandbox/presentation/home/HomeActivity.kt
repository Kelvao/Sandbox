package digital.gok.sandbox.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import digital.gok.sandbox.R
import digital.gok.sandbox.data.repository.ProductsModel
import digital.gok.sandbox.data.repository.base.SandboxException
import digital.gok.sandbox.mechanism.imageloader.GlideApp
import digital.gok.sandbox.mechanism.livedata.LiveDataActivity
import digital.gok.sandbox.presentation.home.SandboxAdapter.ProductType
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : LiveDataActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var sandboxSpotlightAdapter: SandboxAdapter
    private lateinit var sandboxProductAdapter: SandboxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupRecyclerViews()
        observeChanges(homeViewModel.productsLiveData)
        homeViewModel.getProducts()
    }

    private fun setupRecyclerViews() {
        sandboxSpotlightAdapter = SandboxAdapter(ProductType.SPOTLIGHT)
        sandboxProductAdapter = SandboxAdapter(ProductType.PRODUCT)

        spotlightList.run {
            adapter = sandboxSpotlightAdapter
            layoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        productsList.run {
            adapter = sandboxProductAdapter
            layoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    override fun onSuccess(productsModel: ProductsModel) {
        sandboxSpotlightAdapter.data =
            productsModel.spotlight as MutableList<ProductsModel.ProductModel>
        sandboxProductAdapter.data =
            productsModel.products as MutableList<ProductsModel.ProductModel>

        GlideApp.with(this)
            .load(productsModel.cash.bannerURL)
            .into(cashImage)
    }

    override fun setLoaderVisibility(isLoading: Boolean) {
        loader.visibility = if (isLoading) View.VISIBLE else View.GONE
        allViews.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    override fun onError(error: SandboxException) {
        Toast.makeText(
            this,
            error.errorCode.value + " " + error.errorMessage, Toast.LENGTH_LONG
        ).show()
    }
}
