package com.anushka.navdemo5


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.anushka.navdemo5.databinding.FragmentEmailBinding
import com.anushka.navdemo5.databinding.FragmentNameBinding
import kotlinx.android.synthetic.main.fragment_email.*

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            submitButton.setOnClickListener {
                if(email_edit_text.text.trim().toString() != ""){
                    val bundle = bundleOf(
                        "name" to arguments?.getString("name").toString(),
                        "email" to emailEditText.text.toString()
                    )
                    findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment,bundle)
                }else{
                    Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
