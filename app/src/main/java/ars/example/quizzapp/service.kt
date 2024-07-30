package ars.example.quizzapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface service {

    @GET("api/questions")
    fun get_questions(@Query("categories")category:String ,@Query("limit")limit:String,@Query("difficulty")diff:String)
    : Call<Questions>
}