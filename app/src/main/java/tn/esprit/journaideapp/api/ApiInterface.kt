package tn.esprit.journaide.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.journaide.models.modelResponse.LoginResponse
import tn.esprit.journaide.models.modelResponse.SignupResponse
import tn.esprit.journaideapp.Interview
import tn.esprit.journaideapp.models.MESinterv

interface ApiInterface {

    @Headers("Content-Type:application/json")
    @POST("login")
    fun executelogin(@Body map: String, password: String): Call<LoginResponse>?

    @Headers("Content-Type:application/json")
    @POST("api/register")
    fun executeSignup(@Body map: HashMap<String?, String?>): Call<SignupResponse>?

    @POST("api/interview/add")
    suspend fun add(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("api/interview")
    fun getAll(): Call<List<Interview>>

    @HTTP(method = "DELETE", path = "api/interview/delete", hasBody = true)
    suspend fun Delete(@Body requestBody: RequestBody): Response<ResponseBody>

    @PUT("api/interview/update")
    suspend fun Update(@Body requestBody: RequestBody): Response<ResponseBody>


    @GET("api/interview/show")
    suspend fun Getinter(@Body Interview: MESinterv): Response<MESinterv>
}