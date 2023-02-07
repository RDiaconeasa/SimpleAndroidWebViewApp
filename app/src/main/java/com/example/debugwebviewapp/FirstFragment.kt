package com.example.debugwebviewapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.debugwebviewapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.launchButton.setOnClickListener {
            val launchUrlBasePath: String = binding.launchUrlEditText.text.toString()
            val launchUrlOperatorId: String = binding.launchOperatorIdEditText.text.toString()
            val launchUrlToken: String = binding.launchTokenEditText.text.toString()
            val launchUrl: String
            if (launchUrlBasePath.indexOf("game_id") != -1) {
                launchUrl = "$launchUrlBasePath&operatorId=$launchUrlOperatorId&token=$launchUrlToken"
            } else {
                launchUrl = "$launchUrlBasePath/auth/?operatorId=$launchUrlOperatorId&token=$launchUrlToken"
            }


            print(launchUrl)
            Log.d("URL", launchUrl)
            val bundle = bundleOf("launchUrl" to launchUrl)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}