package tn.esprit.journaideapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import tn.esprit.journaideapp.R

class ProfileFragment : Fragment() {

    private lateinit var usernameTV: TextView
    private lateinit var emailTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val switch: Switch = view.findViewById(R.id.theme)

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

        usernameTV = view.findViewById(R.id.usernameTV)
        emailTV = view.findViewById(R.id.emailTV)

        usernameTV.text = "Username : " + requireActivity().intent?.getStringExtra("user_name")
        emailTV.text = "Email : " + requireActivity().intent?.getStringExtra("email")

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your other initialization code goes here
    }
}
