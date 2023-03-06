package com.artem.weatherapp.presentation.fragments.currentCityFragment


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.work.*
import com.artem.weatherapp.databinding.FragmentCurrentCityBinding
import com.artem.weatherapp.presentation.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val DEBUG_TAG = "NetworkStatusDebug"

@AndroidEntryPoint
class CurrentCityFragment : Fragment() {

    private var _binding: FragmentCurrentCityBinding? = null
    private val binding get() = _binding!!

    private val currentCityVM: CurrentCityVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedViewModel: SharedViewModel by activityViewModels()

        sharedViewModel.sharedData.observe(viewLifecycleOwner) {
            binding.apply {
                textViewCityName.text = it.cityName
                textViewTempValue.text = it.temp
                textViewTempFeelsLikeValue.text = it.feelsLike
                textViewWindSpeedValue.text = it.windSpeed
                textViewHumidityValue.text = it.humidity
                textViewPressureValue.text = it.pressure
            }

            Log.d("Debug", "DATA CHANGE!")
            currentCityVM.localSaveCityWeather(param = it)
        }


        startWorker(ctx = requireContext())


        val nw = NetworkStatusTracker(requireContext())

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                nw.networkStatus.collect { nwStatus ->
                    when (nwStatus) {
                        is NetworkStatus.Available -> Log.d(DEBUG_TAG, "Network Available")
                        is NetworkStatus.Unavailable -> Log.d(DEBUG_TAG, "Network Unavailable")
                        is NetworkStatus.Lost -> Log.d(DEBUG_TAG, "Network Lost")
                        is NetworkStatus.NotConnected -> Log.d(DEBUG_TAG, "Network NotConnected")
                    }
                }
            }
        }


        binding.SearchFragmentButton.setOnClickListener {
            navigateToSearchCity(this)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun navigateToSearchCity(fragment: Fragment) {
        val navController = NavHostFragment.findNavController(fragment)
        navController.navigate(CurrentCityFragmentDirections.actionCurrentCityFragmentToSearchCityFragment())
    }

    private fun startWorker(ctx: Context){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val updateWeatherWorkRequest =
            PeriodicWorkRequestBuilder<UpdateWeatherWorker>(
                15,
                TimeUnit.MINUTES
            ).addTag("First worker")
                .setConstraints(constraints)
                .build()


        WorkManager.getInstance(ctx).enqueueUniquePeriodicWork(
            "my_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            updateWeatherWorkRequest
        )
    }
}