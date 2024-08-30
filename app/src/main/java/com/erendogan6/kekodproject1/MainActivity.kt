package com.erendogan6.kekodproject1

import android.os.Bundle
import android.view.Menu
import android.view.View
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
        viewModel.isEgoSwitchOn.observe(
            this,
            Observer { isOn ->
                binding.switchEgo.isChecked = isOn
                setSwitchesEnabled(!isOn) // Disable/Enable other switches based on Ego state
            },
        )

        // Observe the BottomNavigationView visibility
        viewModel.isBottomNavVisible.observe(
            this,
            Observer { isVisible ->
                binding.bottomNavigationView.visibility = if (isVisible) View.VISIBLE else View.GONE
            },
        )

        // Observe the state of other switches
        viewModel.switchStates.observe(
            this,
            Observer { states ->
                binding.switch1.isChecked = states[1] ?: false
                binding.switch2.isChecked = states[2] ?: false
                binding.switch3.isChecked = states[3] ?: false
                binding.switch4.isChecked = states[4] ?: false
                binding.switch5.isChecked = states[5] ?: false
            },
        )

        // Observe the active menu items and update BottomNavigationView
        viewModel.activeMenuItems.observe(
            this,
            Observer { menuItems ->
                updateBottomNavigationMenu(menuItems)
            },
        )

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

    // Method to update the BottomNavigationView menu dynamically
    private fun updateBottomNavigationMenu(menuItems: List<Int>) {
        val menu = binding.bottomNavigationView.menu
        menu.clear() // Clear existing items

        // Add the main screen item
        menu.add(0, R.id.nav_main_screen, Menu.NONE, "Main Screen").setIcon(R.drawable.ic_home)

        // Add dynamic items based on the active switches
        menuItems.forEach { switchId ->
            if (switchId != R.id.nav_main_screen) { // Do not re-add the main screen
                val title =
                    when (switchId) {
                        1 -> "Switch 1"
                        2 -> "Switch 2"
                        3 -> "Switch 3"
                        4 -> "Switch 4"
                        5 -> "Switch 5"
                        else -> ""
                    }
                val iconRes =
                    when (switchId) {
                        1 -> R.drawable.ic_happy
                        2 -> R.drawable.ic_money
                        3 -> R.drawable.ic_peace
                        4 -> R.drawable.ic_friend
                        5 -> R.drawable.ic_evolution
                        else -> R.drawable.ic_home
                    }
                menu.add(0, switchId, Menu.NONE, title).setIcon(iconRes)
            }
        }
    }
}
