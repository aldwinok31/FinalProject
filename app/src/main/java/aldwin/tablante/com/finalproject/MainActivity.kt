package aldwin.tablante.com.finalproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import aldwin.tablante.com.finalproject.R.id.editText
import android.view.KeyEvent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class MainActivity : AppCompatActivity() {
    var urlal = ""
    var searchvalue = ""
    var searching: EditText?=null
    var albumlist : ArrayList<Album> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializer()

    }




    inner class albumloader : AsyncTask<Void,String,Void>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg p0: Void?): Void {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
        }
    }



    fun initializer(){
        searching = findViewById<EditText>(R.id.editText)
        searching!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override
            fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                   searchvalue= searching!!.text.toString()
                    urlal = StringUrl(searchvalue)
                    var process=albumloader()
                    process.execute()

                    return true
                }
                return false
            }
        })

    }


    fun StringUrl ( stringer:String): String {
        var url = URL("http://ws.audioscrobbler.com/2.0/?method=album.search&album=Destruction"+stringer.capitalize()
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




