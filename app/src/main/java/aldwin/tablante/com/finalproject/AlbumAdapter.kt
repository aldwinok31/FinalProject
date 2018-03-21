package aldwin.tablante.com.finalproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Bobby on 21/03/2018.
 */
class AlbumAdapter (list : ArrayList<Album>,context: Context):RecyclerView.Adapter<AlbumAdapter.MusicHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MusicHolder{
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.songlist, parent, false)


        return MusicHolder(view)     }
var data :ArrayList<Album> = list
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder:MusicHolder ?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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