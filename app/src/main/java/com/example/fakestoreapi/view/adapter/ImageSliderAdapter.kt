import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestoreapi.R
import com.example.fakestoreapi.utils.load

/**
 * RecyclerView Adapter for displaying a horizontal image slider.
 *
 * This adapter takes a list of image URLs and binds them to ImageView items
 * within a RecyclerView, enabling efficient image sliding/carousel functionality.
 *
 * Usage:
 * - Provide a list of image URLs (String).
 * - Set this adapter to a RecyclerView with a horizontal LinearLayoutManager.
 * - Images are loaded asynchronously using Coil (or Glide).
 *
 * @param images List of image URLs to display in the slider.
 */
class ImageSliderAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    /**
     * ViewHolder class for the image slider item.
     *
     * Holds the reference to the ImageView which displays the image.
     *
     * @param itemView The inflated layout view for a single slider item.
     */
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivImage)
    }

    /**
     * Inflates the layout for a single image slider item.
     *
     * @param parent The ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View (not used here).
     * @return A new ImageViewHolder instance containing the inflated layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_slider, parent, false)
        return ImageViewHolder(view)
    }

    /**
     * Returns the total number of images in the slider.
     *
     * @return The size of the image list.
     */
    override fun getItemCount(): Int = images.size

    /**
     * Binds the image URL at the given position to the ImageView in the ViewHolder.
     *
     * Uses an image loading library (e.g., Coil or Glide) to asynchronously load
     * the image from the URL into the ImageView.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the image in the list.
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        holder.imageView.load(url) // Use Coil, Glide or other image loading library
    }
}
