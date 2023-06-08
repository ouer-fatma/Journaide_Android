package tn.esprit.journaideapp.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import tn.esprit.journaide.api.ApiInterface
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaideapp.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private var retrofitInterface: ApiInterface? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        fun AddInterview(nom: String, non2: String,question: String,reponse: String) {
            val retrofit: retrofit2.Retrofit = RetrofitBuilder.getInstance()
            val service: ApiInterface = retrofit.create(ApiInterface::class.java)

            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("nom_interviewer",nom)
            jsonObject.put("nom_interviewee", non2)
            jsonObject.put("question",question)
            jsonObject.put("reponse", reponse)
            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()
            // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            CoroutineScope(Dispatchers.IO).launch {
                // Do the POST request and get response
                val response =  service.add(requestBody)
                try {
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            val prettyJson = gson.toJson(JsonParser.parseString(response.body()?.string()))
                            Log.d("Pretty Printed JSON :", prettyJson)
                            Toast.makeText(context, "successfully added", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    println(e.printStackTrace())
                    println("Error")
                }
            }
        }


binding.btnRegister.setOnClickListener(){
    if(binding.etUsername.text.toString().isNullOrEmpty() || binding.etEmail.text.toString().isNullOrEmpty()   ||binding.etPassword.text.toString().isNullOrEmpty() ||binding.etVpassword.text.toString().isNullOrEmpty() ){
        Toast.makeText(context, "empty filds", Toast.LENGTH_SHORT).show()
    }else{
        AddInterview(binding.etUsername.text.toString(),binding.etEmail.text.toString(),binding.etPassword.text.toString(),binding.etVpassword.text.toString())

    }
}




        return view
    }

}