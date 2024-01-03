package tn.esprit.journaideapp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Retrofit
import tn.esprit.journaide.api.ApiInterface

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.graphics.Color
import android.os.Build


import tn.esprit.journaide.models.modelResponse.SignupResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaideapp.view.activity.NavActivity

class MainActivity : AppCompatActivity() {
    private var retrofit: Retrofit? = null
    private var retrofitInterface: ApiInterface? = null
    private val BASE_URL = "http://192.168.1.12:3000/"
    val apiInterface = RetrofitBuilder.create( ApiInterface::class.java)


    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Make the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_main)





        supportActionBar?.hide()


        lateinit var btn:Button
        lateinit var btn1:TextView


        val JU=findViewById<TextInputEditText>(R.id.et_Username)
        val JE=findViewById<TextInputEditText>(R.id.et_email)
        val JP=findViewById<TextInputEditText>(R.id.et_password)
        val JvP=findViewById<TextInputEditText>(R.id.et_vpassword)
        val JI=findViewById<TextInputEditText>(R.id.et_image)
        val role="User"


        fun isValidPassword(etPassword: String,JvP: String): Boolean {
            if (etPassword.length < 8) return false

            return true
        }
        fun isValidEmail(etEmail: String): Boolean {
            if (etEmail.contains("@gmail.com")) return true
            return false
        }
        fun isValidName(etName: String): Boolean {
            if (etName.length < 3) return false
            return true
        }


        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface = retrofit!!.create(ApiInterface::class.java)


        /* navigation Signup */
       btn = findViewById(R.id.btn_register)
       btn1 = findViewById(R.id.tv_login)



        btn.setOnClickListener(View.OnClickListener {
            val map = HashMap<String?, String?>()
            if(isValidName(JU.text.toString())){
                map["Username"] = JU.text.toString()
            }else{
                JU.setError("Username is not valid")
                JU.requestFocus()
                return@OnClickListener           }
            if (isValidEmail(JE.text.toString())) {
                map["Email"] = JE.text.toString()
            } else {
                JE.setError("Email is not valid")
                JE.requestFocus()
                return@OnClickListener
            }
            if (isValidPassword(JP.text.toString(),JvP.text.toString())) {
                map["Password"] = JP.text.toString()
            } else {
                JP.setError("Password must be at least 8 characters")
                JP.requestFocus()
                return@OnClickListener
            }
            map["Image"]=""


            if (JU.text.toString().isNotEmpty() && JE.text.toString().isNotEmpty() && JP.text.toString().isNotEmpty() && JvP.text.toString().isNotEmpty()) {
                val call: Call<SignupResponse>? = retrofitInterface!!.executeSignup(map)
                call!!.enqueue(object : Callback<SignupResponse?> {
                    override fun onResponse(
                        call: Call<SignupResponse?>?,
                        response: Response<SignupResponse?>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                this@MainActivity,
                                "successfuly registred",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            finish()
                            startActivity(intent)
                        } else if (response.code() == 400) {
                            Toast.makeText(
                                this@MainActivity,
                                "Email already exists",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse?>?, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        })

        btn1.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        })
    }


}