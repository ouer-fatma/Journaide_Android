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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import tn.esprit.journaide.models.modelResponse.ResetPasswordResponse


interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login")
    fun executelogin( @Field("Username") username: String, @Field("Password") password: String): Call<LoginResponse>?
    @Headers("Content-Type:application/json")
    @POST("api/register")
    fun executeSignup(@Body map: HashMap<String?, String?>): Call<SignupResponse>?

    @POST("api/password/reset")
    suspend fun resetPassword(@Body requestBody: RequestBody): Response<ResponseBody>

    @POST("api/password/new/random")
    suspend fun generateRandomPassword(@Body requestBody: RequestBody): Response<ResponseBody>

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

    @FormUrlEncoded
    @POST("api/password/forgot")
    fun forgotPassword(@Field("email") email: String): Call<ResetPasswordResponse>

    @FormUrlEncoded
    @POST("api/password/reset")
    fun resetPassword(@Field("email") email: String, @Field("codeForget") codeForget: String, @Field("password") password: String): Call<ResetPasswordResponse>

}