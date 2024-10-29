package com.example.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bottomnavigation.databinding.FragmentCheckoutBinding
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            edtJnsTiket.setOnClickListener {
                val action = CheckoutFragmentDirections.actionCheckoutFragmentToTicketTypeFragment3()
                findNavController().navigate(action)
            }

            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("address")
                ?.observe(viewLifecycleOwner) { result ->
                    edtJnsTiket.setText(result)
                }

            btnDone.setOnClickListener {
                val ticketType = edtJnsTiket.text.toString()

                if (ticketType.isEmpty()) {
                    Toast.makeText(requireContext(), "Pilih tipe tiket", Toast.LENGTH_SHORT).show()
                } else {
                    val currentTime = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                    Toast.makeText(
                        requireContext(),
                        "Tiket dengan tipe $ticketType berhasil dipesan pada $currentTime",
                        Toast.LENGTH_LONG
                    ).show()

                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
