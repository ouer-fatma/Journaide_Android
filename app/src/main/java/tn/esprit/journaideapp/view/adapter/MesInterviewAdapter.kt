package tn.esprit.journaideapp.models

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
import tn.esprit.journaideapp.R
import tn.esprit.journaideapp.view.fragment.MesInterviewFragment
import tn.esprit.journaideapp.view.fragment.UpdateFragment



class MesInterviewAdapter(val InterviewList: MutableList<Interview>):
    RecyclerView.Adapter<MesInterviewAdapter.InterviewViewHolder>() {
    private lateinit var  context: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mes_intervew, parent, false)

        return InterviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterviewViewHolder, position: Int) {
       val currentInterview =InterviewList[position]

        holder.nom_interviewer.text = currentInterview.nom_interviewer
        holder.nom_interviee.text = currentInterview.nom_interview
        holder.question.text = currentInterview.question
        holder.reponse.text = currentInterview.reponse

        holder.supprimer.setOnClickListener{
            deleteiem(currentInterview.id!!)
            changeFragment(MesInterviewFragment(),context)
        }
        holder.modifer.setOnClickListener{
            val transaction: FragmentTransaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment,UpdateFragment.newInstance(currentInterview.id!!))
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun getItemCount(): Int {
  return InterviewList.size
    }

    class InterviewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nom_interviewer=itemView.findViewById<TextView>(R.id.Name)
        val nom_interviee=itemView.findViewById<TextView>(R.id.OtherName)
        val question=itemView.findViewById<TextView>(R.id.question)
        val reponse=itemView.findViewById<TextView>(R.id.reponse)
        val supprimer=itemView.findViewById<Button>(R.id.button)
        val modifer=itemView.findViewById<Button>(R.id.button2)

    }
    fun changeFragment(newFragment: Fragment?, context: Context) {
        val transaction: FragmentTransaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, newFragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun deleteiem(ID: String) : Boolean{
        val retrofit: retrofit2.Retrofit = RetrofitBuilder.getInstance()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("interviewId",ID)
        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.Delete(requestBody)
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(JsonParser.parseString(response.body()?.string()))
                        Log.d("Pretty Printed JSON :", prettyJson)
                        return@withContext true
                    } else {
                        return@withContext false
                    }
                }
            } catch (e: Exception) {
                println(e.printStackTrace())
                println("Error")
            }
        }
        return false




    }
}