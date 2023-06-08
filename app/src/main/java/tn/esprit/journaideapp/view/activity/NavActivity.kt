package tn.esprit.journaideapp.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tn.esprit.journaideapp.R
import tn.esprit.journaideapp.view.fragment.AddFragment
import tn.esprit.journaideapp.view.fragment.LesInterviewFragment
import tn.esprit.journaideapp.view.fragment.MesInterviewFragment

class NavActivity : AppCompatActivity() {

    private lateinit var bottom_navigation : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        bottom_navigation = findViewById(R.id.intervew_nav)




        bottom_navigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.AddFragment -> { loadFragment(AddFragment())
                }
                R.id.list -> {
                    loadFragment(LesInterviewFragment())
                }
                R.id.mes -> {
                    loadFragment(MesInterviewFragment())
                }
                R.id.logout -> {
                    this@NavActivity.finish()
                }
            }





            true

        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment,fragment)
        transaction.addToBackStack("")
        transaction.setReorderingAllowed(true).commit()
    }

}