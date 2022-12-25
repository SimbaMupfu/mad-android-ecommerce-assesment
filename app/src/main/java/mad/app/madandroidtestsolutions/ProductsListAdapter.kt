package mad.app.madandroidtestsolutions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mad.app.madandroidtestsolutions.databinding.ProductItemBinding

class ProductsListAdapter(private val productList: List<CategoryQuery.Item?>) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    var onEndOfListReached: (() -> Unit)? = null
    var onItemClicked: ((CategoryQuery.Item?) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        with(holder.binding){
            productBrand.text = product?.productListFragment?.brand
            productName.text = product?.productListFragment?.name
            productPrice.text = "R${product?.productListFragment?.price_range?.priceRangeFragment?.minimum_price?.final_price?.value}"
//            productImg.load(product?.productListFragment?.mp_label_data?.first()?.label_image)
        }
//        holder.binding.site.text = product.site ?: ""
//        holder.binding.missionName.text = product.mission?.name
//        holder.binding.missionPatch.load(product.mission?.missionPatch) {
//            placeholder(R.drawable.ic_placeholder)
//        }

        if (position == productList.size - 1) {
            onEndOfListReached?.invoke()
        }

        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(product)
        }
    }
}