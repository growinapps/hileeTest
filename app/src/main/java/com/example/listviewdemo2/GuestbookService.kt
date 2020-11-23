package com.example.listviewdemo2

import retrofit2.Call
import retrofit2.http.GET

interface GuestbookService {
    @GET("/guestbook/list")
    fun getList():Call<List<Guestbook>>


}