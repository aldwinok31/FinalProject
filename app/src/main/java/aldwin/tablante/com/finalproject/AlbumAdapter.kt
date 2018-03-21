package aldwin.tablante.com.finalproject

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.net.URL

/**
 * Created by Bobby on 21/03/2018.
 */
class AlbumAdapter (list : ArrayList<Album>,context: Context):RecyclerView.Adapter<AlbumAdapter.MusicHolder> (){
val mContext = context
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MusicHolder{
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.songlist, parent, false)


        return MusicHolder(view)     }
var data :ArrayList<Album> = list
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder:MusicHolder ?, position: Int) {
        val pmon = data[position]


        val name = pmon.name
        val artist = pmon.artist
        val url = pmon.image
if(url != "" || url != null) {
    val ins = URL(url).openStream()


    var mIcon = BitmapFactory.decodeStream(ins)
    holder!!.imageView.setImageBitmap(mIcon)
    holder!!.descript.setText(name + " \n" + artist)
}
    }



    class MusicHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageView: ImageView
        val descript: TextView


        init {


            imageView = itemView.findViewById<ImageView>(R.id.image)
            descript = itemView.findViewById<TextView>(R.id.description)


        }
    }
}