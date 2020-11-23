package com.example.listviewdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView:ListView
        val adapter:ListViewAdapter = ListViewAdapter()

        listView = findViewById(R.id.listView)
        listView.adapter = adapter

//수정수정tnwjdtnwjd
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var selectItem = parent.getItemAtPosition(position) as Guestbook
            Toast.makeText(this,selectItem.title, Toast.LENGTH_LONG).show()
        }

        /*
        adapter.addItem(Guestbook(1,"제목1", "작성자1", "내용1"))
        adapter.addItem(Guestbook(2,"제목2", "작성자2", "내용2"))
        adapter.addItem(Guestbook(3,"제목3", "작성자3", "내용3"))
        adapter.addItem(Guestbook(4,"제목4", "작성자4", "내용4"))
        adapter.addItem(Guestbook(5,"제목5", "작성자5", "내용5"))
        adapter.addItem(Guestbook(6,"제목6", "작성자6", "내용6"))
        adapter.addItem(Guestbook(7,"제목7", "작성자7", "내용7"))
         */
        //네트워크를 통해서 데이터를 불러와서 추가하기
        var gson = GsonBuilder()
            .setLenient()
            .create()
        var retrofit = Retrofit.Builder()
            .baseUrl("http://172.30.1.60:9000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        var guestbookService: GuestbookService = retrofit.create(GuestbookService::class.java)

        guestbookService.getList()
            .enqueue(object : Callback<List<Guestbook>> {
                override fun onFailure(call: Call<List<Guestbook>>, t: Throwable) { //실패할 경우
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG ).show()
                }

                override fun onResponse(call: Call<List<Guestbook>>, response: Response<List<Guestbook>>?) {
                    //정상응답이 올경우
                    val dataList: List<Guestbook>? = response?.body()

                    Toast.makeText(this@MainActivity, "success", Toast.LENGTH_LONG ).show()
                    if (dataList != null) {
                        for(item in dataList){
                            Log.i("Test", item.title.toString())
                            adapter.addItem(item)
                            adapter.notifyDataSetChanged()
                        }
                    }

                }
            })



    }
}