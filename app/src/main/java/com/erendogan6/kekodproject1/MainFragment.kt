package com.erendogan6.kekodproject1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        // Set up observers and listeners
        setupObservers()
        setupSwitchListeners()
    }

    private fun setupObservers() =
        with(viewModel) {
            isEgoSwitchOn.observe(viewLifecycleOwner) { isOn ->
                binding.switchEgo.isChecked = isOn
                setSwitchesEnabled(!isOn)
                (activity as? MainActivity)?.setBottomNavigationVisibility(!isOn)
            }

            switchModels.observe(viewLifecycleOwner) { switches ->
                binding.run {
                    listOf(switch1, switch2, switch3, switch4, switch5).forEachIndexed { index, switch ->
                        val switchModel = switches[index]
                        switch.text = switchModel.name
                        switch.isChecked = switchModel.isChecked
                    }
                }
            }
        }

    private fun setupSwitchListeners() =
        binding.run {
            // Listener for Ego switch
            switchEgo.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onEgoSwitchChanged(isChecked)
            }

            // Listeners for other switches
            listOf(switch1, switch2, switch3, switch4, switch5).forEachIndexed { index, switch ->
                switch.setOnCheckedChangeListener { _, isChecked ->
                    viewModel.onSwitchChanged(index + 1, isChecked)
                }
            }
        }

    private fun setSwitchesEnabled(enabled: Boolean) =
        binding.run {
            // Enable or disable all switches except Ego switch
            listOf(switch1, switch2, switch3, switch4, switch5).forEach { it.isEnabled = enabled }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding to avoid memory leaks
    }
}
