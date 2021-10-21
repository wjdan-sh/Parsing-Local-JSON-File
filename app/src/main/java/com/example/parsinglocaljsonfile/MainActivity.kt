package com.example.parsinglocaljsonfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var images: ArrayList<Image>
    lateinit var RV : RecyclerView
    private lateinit var rvAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        images = arrayListOf()
        RV = findViewById(R.id.rvMain)


        rvAdapter = MyAdapter(this, images)
        RV.adapter = rvAdapter
        RV.layoutManager = LinearLayoutManager(this)

        val jason =getJsonDataFromAsset(this@MainActivity ,"data.json")
        showPhotos(jason)

    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String {

        var jsonString = " "
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return jsonString
    }

    private fun showPhotos(data: String){
            val jsonObj = JSONArray(data)
            for(i in 0 until jsonObj.length()){
                val title = jsonObj.getJSONObject(i).getString("title")
                val url = jsonObj.getJSONObject(i).getString("url")

                images.add(Image(title, url))
            }
            rvAdapter.notifyDataSetChanged()
    }


}
