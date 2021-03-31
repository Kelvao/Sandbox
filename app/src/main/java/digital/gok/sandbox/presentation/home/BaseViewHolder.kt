package digital.gok.sandbox.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import digital.gok.sandbox.data.repository.ProductsModel

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(model: ProductsModel.ProductModel)
}