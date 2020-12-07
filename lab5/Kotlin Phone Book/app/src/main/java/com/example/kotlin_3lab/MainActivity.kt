package com.example.kotlin_3lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_3lab.fragments.AboutFragment
import com.example.kotlin_3lab.fragments.DisplayUserInfoFragment
import com.example.kotlin_3lab.fragments.MainFragment
import com.example.kotlin_3lab.fragments.TermsFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(), Communicator{
    lateinit var fragment: Fragment
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            val fragmentMain = MainFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentMain).commit()
        }

        Timber.i("Called ViewModelProviders!")

    }

    override fun passData(username: String, phoneNumber: String, email: String, goTo: String) {
        val bundle = Bundle()
        viewModelFactory = MainViewModelFactory(username)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.setDataInfo(username, phoneNumber, email, goTo)
        bundle.putString("username", viewModel.user_name.value)
        bundle.putString("phoneNumber", viewModel.phone_number.value)
        bundle.putString("email", viewModel.emailM.value)
        bundle.putString("goTo", viewModel.go_to.value)

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

}