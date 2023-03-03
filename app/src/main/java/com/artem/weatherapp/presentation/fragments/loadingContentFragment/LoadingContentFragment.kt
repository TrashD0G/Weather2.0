package com.artem.weatherapp.presentation.fragments.loadingContentFragment

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.artem.weatherapp.R
import com.artem.weatherapp.databinding.FragmentLoadingContentBinding
import com.artem.weatherapp.domain.usecase.ToastDisplayErrorUseCase
import com.artem.weatherapp.presentation.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoadingContentFragment : Fragment() {

    private var _binding: FragmentLoadingContentBinding? = null
    private val binding get() = _binding!!

    private val loadingContentVM: LoadingContentVM by activityViewModels()

    @Inject
    lateinit var toastDisplayErrorUseCase: ToastDisplayErrorUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingContentVM.startLoadingContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadingContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Debug", "LoadingContentFragment: Fragment create!")

        val sharedViewModel: SharedViewModel by activityViewModels()
        loadingContentVM.cityData.observe(viewLifecycleOwner) {
            sharedViewModel.shareData(it)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loadingContentVM.uiState.collect { currentUiState ->

                    when (currentUiState) {
                        is LoadingContentUiState.Loading -> loadingIndicatorDisplay(currentUiState.isLoading)
                        is LoadingContentUiState.Success -> navigate(
                            destination = "toCurrentCity",
                            view = view
                        )
                        is LoadingContentUiState.Error -> navigate(
                            destination = "toSearchCity",
                            view = view
                        )
                    }
                }
            }
        }
    }


    private fun navigate(destination: String, view: View) {
        val navController = view.findNavController()
        val toSearchCity =
            LoadingContentFragmentDirections.actionLoadingContentFragmentToSearchCityFragment()
        val toCurrentCity =
            LoadingContentFragmentDirections.actionLoadingContentFragmentToCurrentCityFragment()


        when (destination) {
            "toCurrentCity" -> navController.navigate(toCurrentCity)
            "toSearchCity" -> navController.navigate(toSearchCity)
        }

    }


    private fun loadingIndicatorDisplay(isLoading: Boolean) {
        if (isLoading) {

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}