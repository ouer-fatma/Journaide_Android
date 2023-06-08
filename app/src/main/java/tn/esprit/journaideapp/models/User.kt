package tn.esprit.journaide.models



import com.google.gson.annotations.SerializedName

data class User(

    var id: Int,
    @SerializedName("Username")
    var Username: String,
    @SerializedName("Email")
    var Email: String,
    @SerializedName("Password")
    var Password: String,
    @SerializedName("vPassword")
    var vPassword: String,
    @SerializedName("Image")
    var Image: String ="fsdfsdfsd",
    var __v: Int




    )