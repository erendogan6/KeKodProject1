package com.erendogan6.kekodproject1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.erendogan6.kekodproject1.R
import com.erendogan6.kekodproject1.databinding.FragmentMainBinding
import com.erendogan6.kekodproject1.model.SwitchModel
import com.erendogan6.kekodproject1.ui.activity.MainActivity
import com.erendogan6.kekodproject1.viewmodel.MainViewModel

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

    override fun onResume() {
        super.onResume()
        updateTexts()
    }

    private fun updateTexts() {
        val switchModels =
            listOf(
                SwitchModel(
                    1,
                    getString(R.string.switch_happy),
                    R.drawable.ic_happy,
                ),
                SwitchModel(
                    2,
                    getString(R.string.switch_money),
                    R.drawable.ic_money,
                ),
                SwitchModel(
                    3,
                    getString(R.string.switch_peace),
                    R.drawable.ic_peace,
                ),
                SwitchModel(
                    4,
                    getString(R.string.switch_friend),
                    R.drawable.ic_friend,
                ),
                SwitchModel(
                    5,
                    getString(R.string.switch_evolution),
                    R.drawable.ic_evolution,
                ),
            )

        // ViewModel'e güncellenmiş verileri geç
        viewModel.updateSwitchModels(switchModels)
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
