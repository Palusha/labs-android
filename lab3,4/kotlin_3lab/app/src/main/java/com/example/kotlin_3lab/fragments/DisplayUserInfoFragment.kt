package com.example.kotlin_3lab.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.example.kotlin_3lab.Communicator
import com.example.kotlin_3lab.R
import kotlinx.android.synthetic.main.fragment_display_user_info.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import timber.log.Timber

class DisplayUserInfoFragment : Fragment() {

    private lateinit var communicator: Communicator

    var username: String? = ""
    var phone_number: String? = ""
    var email: String? = ""
    lateinit var checkEmail: CheckBox
    lateinit var home_button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display_user_info, container, false)

        username = arguments?.getString("username")
        phone_number = arguments?.getString("phoneNumber")
        email = arguments?.getString("email")
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

        view.shareButton.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, username)
            intent.putExtra(Intent.EXTRA_TEXT, email)
            intent.putExtra(Intent.EXTRA_TEXT, phone_number)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share to: "))
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")

    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

}