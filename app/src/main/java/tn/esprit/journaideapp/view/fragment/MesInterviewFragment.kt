package tn.esprit.journaideapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.journaide.api.ApiInterface
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaideapp.Interview
import tn.esprit.journaideapp.InterviewAdapter
import tn.esprit.journaideapp.R
import tn.esprit.journaideapp.databinding.FragmentLesInterviewBinding
import tn.esprit.journaideapp.databinding.FragmentMesInterviewBinding
import tn.esprit.journaideapp.models.MesInterviewAdapter

class MesInterviewFragment : Fragment() {
    lateinit var recylceAdapter: MesInterviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentMesInterviewBinding.inflate(inflater, container, false)
        val view = binding.root

        var list: ArrayList<Interview> = ArrayList()
        val retrofit: retrofit2.Retrofit = RetrofitBuilder.getInstance()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)


        val call: Call<List<Interview>> = service.getAll()

        call.enqueue(object : Callback<List<Interview>> {
            override fun onResponse(
                call: Call<List<Interview>>,
                response: Response<List<Interview>>
            ) {

                list = ArrayList<Interview>(response.body()!!)
                recylceAdapter = MesInterviewAdapter(list)
                binding.RecyclerView.adapter =  recylceAdapter
                binding.RecyclerView.layoutManager = LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL ,false)
            }

            override fun onFailure(call: Call<List<Interview>>, t: Throwable) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }

        })




        return view
    }


}