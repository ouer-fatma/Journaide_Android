package tn.esprit.journaideapp.view.activity

import android.app.UiModeManager.MODE_NIGHT_NO
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tn.esprit.journaideapp.R
import android.graphics.Color
import android.os.Build
import android.view.View
import android.content.SharedPreferences

import android.preference.PreferenceManager
import android.widget.Switch

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import tn.esprit.journaideapp.view.fragment.*


class NavActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var bottom_navigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        supportActionBar?.hide()

        // Make the status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }







        bottom_navigation = findViewById(R.id.intervew_nav)
        bottom_navigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.AddFragment -> { loadFragment(AddFragment())
                }
                R.id.list -> {
                    loadFragment(LesInterviewFragment())
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
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