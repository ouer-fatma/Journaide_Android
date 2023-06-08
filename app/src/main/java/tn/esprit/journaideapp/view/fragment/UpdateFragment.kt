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
import tn.esprit.journaideapp.Interview
import tn.esprit.journaideapp.databinding.FragmentUpdateBinding
import tn.esprit.journaideapp.models.MESinterv

private const val ARG_PARAM1 = "param1"


class UpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
private  var inter = MESinterv()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        inter.setId(param1)
        println(inter.getId())
        val retrofit: retrofit2.Retrofit = RetrofitBuilder.getInstance()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)

























        fun UpdateInterview(nom: String, non2: String,question: String,reponse: String) {


            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("interviewId",param1)
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
                val response =  service.Update(requestBody)
                try {
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            val gson = GsonBuilder().setPrettyPrinting().create()
                            val prettyJson = gson.toJson(JsonParser.parseString(response.body()?.string()))
                            Log.d("Pretty Printed JSON :", prettyJson)
                            Toast.makeText(context, "successfully updated", Toast.LENGTH_SHORT).show()
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

            UpdateInterview(binding.etUsername.text.toString(),binding.etEmail.text.toString(),binding.etPassword.text.toString(),binding.etVpassword.text.toString())


        }






















        return view
    }

    companion object {

        fun newInstance(param1: String) =
            UpdateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}