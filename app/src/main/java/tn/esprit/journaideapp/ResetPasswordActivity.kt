package tn.esprit.journaideapp


import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaide.api.ApiInterface
import tn.esprit.journaide.models.modelResponse.LoginResponse

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var apiInterface: ApiInterface
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSubmit: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_reset_password)

        supportActionBar?.hide()



//        btnSubmit.setOnClickListener {
  //          val email = etEmail.text.toString().trim()

//            if (email.isNotEmpty()) {
  //              GlobalScope.launch(Dispatchers.Main) {
    //                resetPassword(email)
      //          }
        //    } else {
          //      Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show()
            //}
//        }

//    }

  //  private suspend fun resetPassword(email: String) {
    //    val requestBody = "{\"email\":\"$email\"}".toRequestBody("application/json".toMediaType())

      //  try {
        //    val response = apiInterface.resetPassword(requestBody)
          //  if (response.isSuccessful) {
                // Handle successful password reset
          //      Toast.makeText(this@ResetPasswordActivity, "Password reset successful", Toast.LENGTH_SHORT).show()
       //     } else {
                // Handle password reset failure
     //           Toast.makeText(this@ResetPasswordActivity, "Password reset failed", Toast.LENGTH_SHORT).show()
       //     }
 //       } catch (e: Exception) {
            // Handle password reset failure
   //         Toast.makeText(this@ResetPasswordActivity, "Password reset failed", Toast.LENGTH_SHORT).show()
     //   }
   }

}