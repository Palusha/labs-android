package com.example.kotlin_3lab.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example.kotlin_3lab.Communicator
import com.example.kotlin_3lab.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import timber.log.Timber

class MainFragment : Fragment() {

    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        communicator = activity as Communicator
        view.addUser.setOnClickListener{
            val username = view.userNameEdit.text.toString()
            val phoneNumber = view.phoneNumberEdit.text.toString()
            val email = view.emailEdit.text.toString()
            val goTo = "2"
            communicator.passData(username, phoneNumber, email, goTo)
        }

        view.finish.setOnClickListener {
            (activity as FragmentActivity).finish()
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