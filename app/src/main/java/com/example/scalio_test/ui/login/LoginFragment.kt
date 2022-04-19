package com.example.scalio_test.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.scalio_test.R
import com.example.scalio_test.databinding.FragmentLoginBinding
import com.example.scalio_test.presentation.viewmodel.LoginViewModel
import com.example.scalio_test.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginEditText = binding.loginEditText
        val loginButton = binding.loginButton
        val loadingProgressBar = binding.loading
        loginButton.setOnClickListener {
            val q = loginEditText.text.toString()
            if (q.isNotEmpty()){
                login(q)
            }else{
                Toast.makeText(requireContext(), "Please input a login keyword", Toast.LENGTH_SHORT).show()
            }
        }

        handleLoginResponse()
    }


private fun login(q:String){
    viewModel.login(q)
}

    private fun handleLoginResponse(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginResponse.collectLatest {networkResponse ->
                    when(networkResponse){
                        is NetworkResult.Failure -> {
                            binding.loading.visibility = INVISIBLE
                            Toast.makeText(requireContext(), "${networkResponse.errorText}", Toast.LENGTH_SHORT).show()
                        }
                        is NetworkResult.Loading -> binding.loading.visibility = VISIBLE
                        is NetworkResult.Success -> {
                            binding.loading.visibility = VISIBLE
                            findNavController().navigate(R.id.listFragment)
                        }
                    }
                }

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}