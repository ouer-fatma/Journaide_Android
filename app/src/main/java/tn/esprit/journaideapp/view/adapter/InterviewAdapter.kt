package tn.esprit.journaideapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Interview(@SerializedName("_id") @Expose val id: String?,
                     @SerializedName("nom_interviewer") @Expose val nom_interviewer: String?,
                     @SerializedName("nom_interviewee") @Expose val nom_interview: String?,
                     @SerializedName("question") @Expose val question: String?,
                     @SerializedName("reponse") @Expose val reponse: String?,
)

class InterviewAdapter(val InterviewList: MutableList<Interview>):
    RecyclerView.Adapter<InterviewAdapter.InterviewViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_interview, parent, false)

        return InterviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterviewViewHolder, position: Int) {
       val currentInterview =InterviewList[position]

        holder.nom_interviewer.text = currentInterview.nom_interviewer
        holder.nom_interviee.text = currentInterview.nom_interview
        holder.question.text = currentInterview.question
        holder.reponse.text = currentInterview.reponse



    }

    override fun getItemCount(): Int {
  return InterviewList.size
    }

    class InterviewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nom_interviewer=itemView.findViewById<TextView>(R.id.Name)
        val nom_interviee=itemView.findViewById<TextView>(R.id.OtherName)
        val question=itemView.findViewById<TextView>(R.id.question)
        val reponse=itemView.findViewById<TextView>(R.id.reponse)

    }
    fun setInterviewList(newList: List<Interview>) {
        InterviewList.clear()
        InterviewList.addAll(newList)
        notifyDataSetChanged()
    }

    fun filterByNomInterviewer(searchTerm: String) {
        val filteredList = InterviewList.filter {
            it.nom_interviewer?.contains(searchTerm, ignoreCase = true) == true
        }.toMutableList()

        setInterviewList(filteredList)
    }

}