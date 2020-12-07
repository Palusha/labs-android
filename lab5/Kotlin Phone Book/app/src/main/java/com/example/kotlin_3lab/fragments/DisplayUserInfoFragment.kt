package com.example.kotlin_3lab.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_3lab.*
import kotlinx.android.synthetic.main.fragment_display_user_info.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import timber.log.Timber

class DisplayUserInfoFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var communicator: Communicator

    var username: String = ""
    var phone_number: String = ""
    var email: String = ""
    lateinit var checkEmail: CheckBox
    lateinit var home_button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_user_info, container, false)
        username = arguments?.getString("username").toString()
        phone_number = arguments?.getString("phoneNumber").toString()
        email = arguments?.getString("email").toString()
        checkEmail = view.emailCheckBox
        view.showUserName.text = username
        view.showPhoneNumber.text = phone_number
        view.showEmail.text = email

        checkEmail.setOnClickListener{
            if (checkEmail.isChecked)
            {
                view.showEmail.visibility = View.VISIBLE
            }
            else{
                view.showEmail.visibility = View.GONE
            }
        }

        communicator = activity as Communicator
        view.homeButton.setOnClickListener{
            val fragmentManager = (activity as FragmentActivity).supportFragmentManager
            val fr = fragmentManager.beginTransaction()
            fr.replace(R.id.fragment_container, MainFragment())
            fr.commit()
        }
        viewModelFactory = MainViewModelFactory(username)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        view.saveUserButton.setOnClickListener {
            viewModel.saveUser()
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.eventUserSaved.observe(viewLifecycleOwner, Observer { hasSaved ->
            if(hasSaved){
                userSaved()
                viewModel.onSaveUserComplete()
            }
        })

        viewModel.eventBuzz.observe(viewLifecycleOwner, Observer { buzzType ->
            if (buzzType != BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })


        return view
    }

    fun userSaved(){
        Toast.makeText(this.activity, "User saved", Toast.LENGTH_SHORT).show()
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
}