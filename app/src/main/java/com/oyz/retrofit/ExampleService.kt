package com.oyz.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ExampleService {
    //接口地址是静态的

    //@Headers("User-Agent: okhttp", "Cache-Control: max-age=0")   //静态指定头部信息
    /*
    @GET("get_data.json")
    //动态指定头部信息
    fun getData(@Header("User-Agent") userAgent: String, @Header("Cache-Control")cacheControl: String): Call<Data>
    */

    //动态地址
    /*
    @GET("{page}/get_data.json")
    fun getData(@Path("page")page: Int): Call<Data>
    */

    //参数处理  u=<user> & t=<token>
    /*@GET("get_data.json")
    fun getData(@Query("u")user: String, @Query("t")token: String): Call<Data>
    */
    //发送delete请求    ResponseBody:表示可以接受任意响应数据，且不进行解析
    @DELETE("data/{id}")
    fun deleteData(@Path("id")id: String): Call<ResponseBody>

    //使用POST提交数据 ，用Body注解存放数据
    @POST("data/create")
    fun createData(@Body data: Data): Call<ResponseBody>














}