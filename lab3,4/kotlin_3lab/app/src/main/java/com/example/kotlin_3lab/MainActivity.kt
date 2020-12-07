package com.example.kotlin_3lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin_3lab.fragments.AboutFragment
import com.example.kotlin_3lab.fragments.DisplayUserInfoFragment
import com.example.kotlin_3lab.fragments.MainFragment
import com.example.kotlin_3lab.fragments.TermsFragment
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), Communicator{
    lateinit var fragment: Fragment
    private lateinit var dessertTimer: DessertTimer
    private var allTime: Long = 0
    private var revenue = 0
    var user_name: String = ""
    var phone_number: String = ""
    var emailM: String = ""
    var go_to: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allTime = System.nanoTime()
        dessertTimer = DessertTimer(this.lifecycle)

        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            val fragmentMain = MainFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentMain).commit()
        }


        if(savedInstanceState != null){
            user_name = savedInstanceState.getString("username").toString()
            phone_number = savedInstanceState.getString("phoneNumber").toString()
            emailM = savedInstanceState.getString("email").toString()
            go_to = savedInstanceState.getString("goTo").toString()
        }
    }

    override fun passData(username: String, phoneNumber: String, email: String, goTo: String) {
        val bundle = Bundle()
        user_name = username
        phone_number = phoneNumber
        emailM = email
        go_to = goTo
        bundle.putString("username", user_name)
        bundle.putString("phoneNumber", phone_number)
        bundle.putString("email", emailM)
        bundle.putString("goTo", go_to)

        val transaction = this.supportFragmentManager.beginTransaction()
        if(goTo == "2") {
            fragment = DisplayUserInfoFragment()
        }
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        val transaction = this.supportFragmentManager.beginTransaction()

        return when (item.itemId) {
            R.id.mainFragment -> {
                fragment = MainFragment()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
                true
            }
            R.id.aboutFragment -> {
                fragment = AboutFragment()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
                true
            }
            R.id.termsFragment -> {
                fragment = TermsFragment()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("username", user_name)
        outState.putString("email", emailM)
        outState.putString("phoneNumber", phone_number)
        outState.putString("goTo", go_to)
        Timber.i("onSaveInstanceState called")
    }

    override fun onStart() {
        super.onStart()
        Timber.i( "onStart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        val workTime: Long = (System.nanoTime() - allTime ) / 1000000000
        val focus_time: Float = dessertTimer.secondsCount.toFloat() / workTime.toFloat()  * 100
        Timber.i("onDestroy called")
        Timber.i("Focus percent ".plus(focus_time.toInt()).plus("%"))

    }

    override fun onStop() {
        super.onStop()
    }
}