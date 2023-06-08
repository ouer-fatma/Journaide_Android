package tn.esprit.journaideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import tn.esprit.journaide.api.ApiInterface
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaideapp.view.activity.NavActivity
import java.net.URLEncoder
import java.nio.charset.Charset

class LoginActivity : AppCompatActivity() {


    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private lateinit var btnLogin: Button
    private lateinit var tvregister: TextView

    val apiInterface = RetrofitBuilder.create( ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail= findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        btnLogin = findViewById(R.id.btn_login)
         tvregister = findViewById(R.id.tv_register)
        btnLogin.setOnClickListener {
            doLogin()
            val intent = Intent(this, NavActivity::class.java)
            finish()
            startActivity(intent)

        }
        val buttonClick3 = findViewById<Button>(R.id.btn_login)
        buttonClick3.setOnClickListener {
            doLogin()
            val intent = Intent(this, NavActivity::class.java)
            finish()
            startActivity(intent)
        }

        val buttonClick4 = findViewById<TextView>(R.id.tv_register)
        buttonClick4.setOnClickListener {
            doLogin()
            val intent = Intent(this, NavActivity::class.java)
            finish()
            startActivity(intent)
        }
    }


    private fun doLogin() {
        btnLogin.isEnabled = false

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.16:3000/api/login"

        val requestBody =
            "email=" + URLEncoder.encode(
                etEmail.text.toString(),
                "UTF-8"
            ) + "&password=" +  etEmail.text
        val stringReq: StringRequest =
            object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    btnLogin.isEnabled = true
                    Log.i("mylog", response)
                },
                Response.ErrorListener { error ->
                    Log.i("myLog", "error = " + error)
                }
            ) {
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
        queue.add(stringReq)
    }
}
