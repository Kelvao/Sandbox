package digital.gok.sandbox.presentation.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import digital.gok.sandbox.R
import digital.gok.sandbox.data.repository.ProductsModel
import digital.gok.sandbox.mechanism.imageloader.GlideApp
import digital.gok.sandbox.presentation.home.SandboxAdapter.SandboxViewHolder
import kotlinx.android.synthetic.main.product_item.view.*
import kotlinx.android.synthetic.main.spotlight_item.view.*

class SandboxAdapter(private var productType: ProductType = ProductType.SPOTLIGHT) :
    RecyclerView.Adapter<SandboxViewHolder>() {

    var data: MutableList<ProductsModel.ProductModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SandboxViewHolder(
            ProductType.fromType(viewType),
            LayoutInflater
                .from(parent.context)
                .inflate(ProductType.fromType(viewType).layout, parent, false)
        )

    override fun onBindViewHolder(holder: SandboxViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = productType.type

    enum class ProductType(var type: Int, var layout: Int) {
        SPOTLIGHT(0, R.layout.spotlight_item), PRODUCT(1, R.layout.product_item);

        companion object {
            fun fromType(type: Int) = values().find { it.type == type } ?: SPOTLIGHT
        }
    }

    class SandboxViewHolder(
        private val type: ProductType,
        private val itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: ProductsModel.ProductModel) {
            itemView.apply {
                GlideApp.with(this)
                    .load(model.bannerURL)
                    .error(R.drawable.ic_broken_image)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            getImageViewByType().scaleType = ScaleType.FIT_CENTER
                            if (type == ProductType.SPOTLIGHT) spotlightLoader.visibility = View.GONE
                            else productLoader.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            getImageViewByType().scaleType = ScaleType.CENTER_CROP
                            return false
                        }
                    })
                    .into(getImageViewByType())
            }
        }

        private fun getImageViewByType(): ImageView {
            itemView.run {
                return if (type == ProductType.SPOTLIGHT) spotlightImage
                else productImage
            }
        }
    }
}