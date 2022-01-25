package com.example.myapplication.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.LoginActivity
import com.example.myapplication.PasswordChangeActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var buttonLogout: Button
    private lateinit var buttonChangePassword: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogout = view.findViewById(R.id.buttonLogout)
        buttonChangePassword = view.findViewById(R.id.buttonChangePassword)
        registerListeners()
    }

    private fun registerListeners() {
        buttonChangePassword.setOnClickListener {
            startActivity(Intent(requireContext(), PasswordChangeActivity::class.java))
        }
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }
}