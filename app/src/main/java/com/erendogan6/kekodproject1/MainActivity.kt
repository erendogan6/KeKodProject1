package com.erendogan6.kekodproject1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.erendogan6.kekodproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe the Ego switch state
        viewModel.isEgoSwitchOn.observe(this, Observer { isOn ->
            binding.switchEgo.isChecked = isOn
            setSwitchesEnabled(!isOn) // Disable/Enable other switches based on Ego state
        })

        // Observe the state of other switches
        viewModel.switchStates.observe(this, Observer { states ->
            binding.switch1.isChecked = states[1] ?: false
            binding.switch2.isChecked = states[2] ?: false
            binding.switch3.isChecked = states[3] ?: false
            binding.switch4.isChecked = states[4] ?: false
            binding.switch5.isChecked = states[5] ?: false
        })

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

    // Helper method to enable/disable other switches
    private fun setSwitchesEnabled(enabled: Boolean) {
        binding.switch1.isEnabled = enabled
        binding.switch2.isEnabled = enabled
        binding.switch3.isEnabled = enabled
        binding.switch4.isEnabled = enabled
        binding.switch5.isEnabled = enabled
    }
}
