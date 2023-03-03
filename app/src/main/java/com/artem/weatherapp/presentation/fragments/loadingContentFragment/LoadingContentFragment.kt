package com.artem.weatherapp.presentation.fragments.loadingContentFragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
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
                    obtainEvent(uiState = currentUiState, view = view)
                }
            }
        }
    }


    private fun obtainEvent(uiState: LoadingContentUiState, view: View) {
        when (uiState) {
            is LoadingContentUiState.Loading -> loadingIndicatorDisplay(uiState.isLoading)
            is LoadingContentUiState.Success -> navigate(uiState = uiState, view = view)
            is LoadingContentUiState.Error -> navigate(uiState = uiState, view = view)
        }
    }


    private fun navigate(uiState: LoadingContentUiState, view: View) {
        val navController = view.findNavController()
        val toSearchCity =
            LoadingContentFragmentDirections.actionLoadingContentFragmentToSearchCityFragment()
        val toCurrentCity =
            LoadingContentFragmentDirections.actionLoadingContentFragmentToCurrentCityFragment()


        when (uiState) {
            is LoadingContentUiState.Success -> navController.navigate(toCurrentCity)
            is LoadingContentUiState.Error -> navController.navigate(toSearchCity)
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