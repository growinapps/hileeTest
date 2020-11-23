package com.example.listviewdemo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter:BaseAdapter() {

    private var itemList =ArrayList<Guestbook>()
    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): Any {
        return itemList[position]  //해당위치 데이터값
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //데이터를 화면에 출력할때
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var context = parent?.context  //현재 작동중인 액티비티

        //xml문서 읽어서 textView과 같은 위젯을 실제 객체로 전환하는 과정
        if(view ==null){
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listview_item,parent, false)
        }

        //각각 위젯에 대한 참조를 가져와야 한다. - listview_item.xml파일로 부터
        var textView = view?.findViewById(R.id.textView) as TextView
        var textView2 = view?.findViewById(R.id.textView2) as TextView
        var textView3 = view?.findViewById(R.id.textView3) as TextView
        var textView4 = view?.findViewById(R.id.textView4) as TextView

        //선택된 항목을 가져오자
        var guestbook = itemList[position]

        //view에 항목을 출력한다.
        textView.text = guestbook.id.toString()
        textView2.text = guestbook.title
        textView3.text = guestbook.writer
        textView4.text = guestbook.contents

        return view
    }

    fun addItem(data:Guestbook){
        itemList.add(data)
    }

    fun addItem(id: Int, title: String, writer: String, contents: String) {
        val item = Guestbook()
        item.id = id
        item.title = title
        item.writer = writer
        item.contents = contents
        itemList.add(item)
    }



}