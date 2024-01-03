package tn.esprit.journaideapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.journaide.api.ApiInterface
import tn.esprit.journaide.api.RetrofitBuilder
import tn.esprit.journaideapp.Interview
import tn.esprit.journaideapp.InterviewAdapter
import tn.esprit.journaideapp.databinding.FragmentLesInterviewBinding
import tn.esprit.journaideapp.models.MesInterviewAdapter

class LesInterviewFragment : Fragment() {
    lateinit var recylceAdapter: InterviewAdapter
    private lateinit var searchBox: TextInputEditText
    private val interviewList = ArrayList<Interview>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLesInterviewBinding.inflate(inflater, container, false)
        val view = binding.root

        // Reference to your search box
        searchBox = binding.editTextSearch

        // Initialize RecyclerView and Adapter
        recylceAdapter = InterviewAdapter(interviewList)
        binding.RecyclerView.adapter = recylceAdapter
        binding.RecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Load initial data (you might want to call this without search term initially)
        loadData(null)

        // Handle search button click event
        binding.buttonSearch.setOnClickListener {
            val searchTerm = searchBox.text.toString().trim()
            recylceAdapter.filterByNomInterviewer(searchTerm)
        }

        return view
    }

    private fun loadData(searchTerm: String?) {
        val retrofit: retrofit2.Retrofit = RetrofitBuilder.getInstance()
        val service: ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<List<Interview>> = service.getAll()

        call.enqueue(object : Callback<List<Interview>> {
            override fun onResponse(
                call: Call<List<Interview>>,
                response: Response<List<Interview>>
            ) {
                if (response.isSuccessful) {
                    interviewList.clear()
                    interviewList.addAll(response.body() ?: emptyList())
                    recylceAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Interview>>, t: Throwable) {
                Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun performSearch(searchTerm: String) {
        // Implement your search logic here
    }
}
