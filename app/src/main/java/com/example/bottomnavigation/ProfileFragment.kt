package com.example.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Ambil data dari arguments
        val name = arguments?.getString("name") ?: "Unknown"
        val nim = arguments?.getString("nim") ?: "Unknown"

        // Set teks di TextViews
        view.findViewById<TextView>(R.id.nameTextView).text = "Name: $name"
        view.findViewById<TextView>(R.id.nimTextView).text = "NIM: $nim"

        return view
    }

    companion object {
        // Fungsi untuk membuat instance baru dari fragment ini dengan arguments
        fun newInstance(name: String, nim: String) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString("name", name)
                putString("nim", nim)
            }
        }
    }
}
