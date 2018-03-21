package aldwin.tablante.com.finalproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import aldwin.tablante.com.finalproject.R.id.editText
import android.os.StrictMode
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var prog1 : ProgressBar? = null
    var urlal = ""
    var process1 : albumloader?=null
    var searchvalue = ""
    var clear:Button?=null
    var searching: EditText?=null
    var albumlist : ArrayList<Album> = ArrayList()
    var album =Album("","","")
    var adapter :AlbumAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        initializer()
        var bool =1
        button.setOnClickListener {
            var process : albumloader = albumloader()
            searchvalue = searching!!.text.toString()
            if(searchvalue != "" && adapter ==null && bool ==1) {

                urlal = StringUrl(searchvalue)

                process.execute()
               process1 = process
bool =0
            }
            else{
                if(process1 != null) {
                    process1!!.cancel(true)
                    prog1!!.visibility = View.GONE
                    button2.isEnabled = true
                }
                    process1 = null

                    adapter = null
                    bool = 1


            }

        }
        button2.setOnClickListener {
        adapter = null
        }

    }




    inner class albumloader : AsyncTask<Void,String,Void>(){
        override fun onPreExecute() {
            super.onPreExecute()
            albumlist.clear()
            prog1!!.visibility = View.VISIBLE
            button2.isEnabled = false

        }


        override fun doInBackground(vararg p0: Void?): Void? {
    try {
        var data = StringUrl(searchvalue)
        var limi = JSONObject(data).getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
        var count = 0
while(count !=  limi.length() && count<= 50) {

    var data = StringUrl(searchvalue)
    var JO = JSONObject(data).getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
            .getJSONObject(count).getJSONArray("image").getJSONObject(3)

    var JA = JSONObject(data).getJSONObject("results").getJSONObject("albummatches").getJSONArray("album").getJSONObject(count)
    var image = JO.getString("#text")
    var name = JA.getString("name")
    var artist = JA.getString("artist")


    albumlist.add(Album(image,name,artist))
    adapter = AlbumAdapter(albumlist,applicationContext)
    publishProgress()

    count++

}




    }
    catch (e:InterruptedException){
        e.printStackTrace()
        prog1!!.visibility = View.GONE
        button2.isEnabled = true
        adapter = null


    }
            return null
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)


            var layout_manager = LinearLayoutManager(applicationContext)
            recyclerView.layoutManager = layout_manager
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            prog1!!.visibility = View.GONE
            button2.isEnabled = true

        }
    }



    fun initializer(){
        searching = findViewById<EditText>(R.id.editText)
        prog1 = findViewById(R.id.progressBar)

        prog1!!.visibility = View.GONE
    }


    fun StringUrl ( stringer:String): String {
        var url = URL("http://ws.audioscrobbler.com/2.0/?method=album.search&album="+stringer.capitalize()
                +
                "&api_key=6672f4014a94fc7befe86d6ba8ca1057&format=json")
        var httpURLConnection = url.openConnection()
        var inputStream = httpURLConnection.getInputStream()
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = ""
        var data :String =""
        while (line != null) {
            line = bufferedReader.readLine()
            data += line
        }
        return  data
    }

}




