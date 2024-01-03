package tn.esprit.journaideapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.journaide.api.ApiInterface
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaide.models.modelRequest.LoginRequest
import tn.esprit.journaide.models.modelResponse.LoginResponse
import tn.esprit.journaideapp.view.activity.NavActivity
import tn.esprit.journaideapp.view.fragment.ProfileFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvRegister: TextView
    private lateinit var MySharedPref: SharedPreferences

    private val apiInterface = Retrofit.Builder()
        .baseUrl(RetrofitBuilder.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        etUsername = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
        tvForgotPassword = findViewById(R.id.tv_forgot_password)

        btnLogin.setOnClickListener {
            doLogin()
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvForgotPassword.setOnClickListener {
            val intent = Intent(this@LoginActivity, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doLogin() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            // Handle empty fields
            return
        }

        val loginRequest = LoginRequest(username, password)
        val call: Call<LoginResponse>? = apiInterface.executelogin(loginRequest.Username, loginRequest.Password)

        call!!.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                    val loginResponse = response.body()
                    val message = loginResponse?.message ?: "Login successful!"
                    val token = loginResponse?.token ?: ""

                    // Extract the username from the login response (replace 'usernameField' with the actual field name in your response)
                    val username = loginResponse?.usernameField ?: ""

                    // Pass the username to NavActivity
                    val intent = Intent(this@LoginActivity, NavActivity::class.java)
                    intent.putExtra("user_name", loginResponse?.user?.Username)
                    intent.putExtra("email", loginResponse?.user?.Email)
                    finish()
                    startActivity(intent)

                } else {
                    // Handle unsuccessful login
                    Log.e("LoginActivity", "Login failed")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle failure
                Log.e("LoginActivity", "Login failed", t)
            }
        })
    }



}



