package tn.esprit.journaideapp

    import android.os.Bundle
    import android.util.Log
    import android.widget.Button
    import android.widget.EditText
    import androidx.appcompat.app.AppCompatActivity
    import com.android.volley.Response
    import com.android.volley.toolbox.StringRequest
    import com.android.volley.toolbox.Volley
    import tn.esprit.journaide.api.ApiInterface
    import tn.esprit.journaide.api.RetrofitBuilder
    import java.net.URLEncoder
    import java.nio.charset.Charset

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etvPassword: EditText
    private lateinit var etImage: EditText
    private lateinit var btnRegister: Button
    val apiInterface = RetrofitBuilder.create( ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUsername= findViewById(R.id.et_Username)
        etEmail= findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etvPassword = findViewById(R.id.et_vpassword)
        etImage = findViewById(R.id.et_image)
        btnRegister = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            doRegister()
        }
    }

    private fun doRegister() {
        btnRegister.isEnabled = false

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.220.165:3000/register"

        val requestBody =
            "username=" + etUsername.text + "&email=" + URLEncoder.encode(etEmail.text.toString(), "UTF-8") + "&password=" + etPassword.text
        val stringReq: StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    btnRegister.isEnabled = true
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