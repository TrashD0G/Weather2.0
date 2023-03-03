package com.artem.weatherapp.presentation.fragments.searchCityFragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.artem.weatherapp.domain.usecase.ToastDisplayErrorUseCase
import com.artem.weatherapp.databinding.FragmentSearchCityBinding
import com.artem.weatherapp.presentation.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchCityFragment : Fragment() {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var toastDisplayErrorUseCase: ToastDisplayErrorUseCase

    private val searchCityVM: SearchCityVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedViewModel: SharedViewModel by activityViewModels()


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchCityVM.uiState.collect { currentUiState ->
                    obtainEvent(uiState = currentUiState, view = view)
                }
            }
        }


        searchCityVM.cityData.observe(viewLifecycleOwner) {
            println("Data is fetch!")
            Log.d("Debug", "SearchCityFragment: Data is fetch!")

            sharedViewModel.shareData(it)
        }


        binding.searchCityButton.setOnClickListener {
            val cityName = binding.inputText.text.toString()
            searchCityVM.remoteGetCityWeather(cityName)
        }
    }


    private fun obtainEvent(uiState: SearchCityUiState, view: View){
        when (uiState) {
            is SearchCityUiState.Loading -> loadingIndicatorDisplay(uiState.isLoading)
            is SearchCityUiState.Success -> navigate(view = view)
            is SearchCityUiState.Error -> errorMessageDisplay(uiState.exception)
        }
    }


    private fun navigate(view: View) {
        val navController = view.findNavController()
        val toCurrentCity =
            SearchCityFragmentDirections.actionSearchCityFragmentToCurrentCityFragment()

        navController.navigate(toCurrentCity)
    }


    private fun errorMessageDisplay(stringMessage: String) {
        Log.d("Debug", "SearchCItyFragment: stringMessage - $stringMessage")
        toastDisplayErrorUseCase.invoke(stringMessage)
        searchCityVM.errorMessageShown()
    }


    private fun loadingIndicatorDisplay(isLoading: Boolean) {
        if (isLoading) {
            Log.d("Debug", "SearchCItyFragment: Download data!")
            toastDisplayErrorUseCase.invoke("Получение данных!")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}