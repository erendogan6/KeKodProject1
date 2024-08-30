package com.erendogan6.kekodproject1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.erendogan6.kekodproject1.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        // Set up observers for ViewModel data
        setupObservers()

        // Set listener for Ego switch
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
        }

        // Set listeners for other switches
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchChanged(1, isChecked)
        }
        binding.switch2.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchChanged(2, isChecked)
        }
        binding.switch3.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchChanged(3, isChecked)
        }
        binding.switch4.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchChanged(4, isChecked)
        }
        binding.switch5.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchChanged(5, isChecked)
        }
    }

    private fun setupObservers() {
        // Observe the Ego switch state
        viewModel.isEgoSwitchOn.observe(
            viewLifecycleOwner,
            Observer { isOn ->
                binding.switchEgo.isChecked = isOn
                setSwitchesEnabled(!isOn) // Disable/Enable other switches based on Ego state

                // Show/hide BottomNavigationView based on Ego switch state
                (activity as? MainActivity)?.setBottomNavigationVisibility(!isOn)
            },
        )

        // Observe the state of other switches
        viewModel.switchStates.observe(
            viewLifecycleOwner,
            Observer { states ->
                binding.switch1.isChecked = states[1] ?: false
                binding.switch2.isChecked = states[2] ?: false
                binding.switch3.isChecked = states[3] ?: false
                binding.switch4.isChecked = states[4] ?: false
                binding.switch5.isChecked = states[5] ?: false
            },
        )
    }

    private fun setSwitchesEnabled(enabled: Boolean) {
        // Enable or disable all switches except Ego switch
        binding.switch1.isEnabled = enabled
        binding.switch2.isEnabled = enabled
        binding.switch3.isEnabled = enabled
        binding.switch4.isEnabled = enabled
        binding.switch5.isEnabled = enabled
    }
}
