package com.erendogan6.kekodproject1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.erendogan6.kekodproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        replaceFragment(MainFragment())
        setupBottomNavigation()
        observeViewModel()
    }

    // Handle navigation item clicks
    private fun setupBottomNavigation() =
        binding.bottomNavigationView.setOnItemSelectedListener {
            handleNavigation(it)
            true
        }

    // Observe active menu items from ViewModel
    private fun observeViewModel() {
        // Observe active menu items from ViewModel
        viewModel.activeMenuItems.observe(this) { menuItems ->
            updateBottomNavigationMenu(menuItems)
        }
    }

    fun setBottomNavigationVisibility(visible: Boolean) {
        binding.bottomNavigationView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun updateBottomNavigationMenu(menuItems: List<Int>) =
        binding.bottomNavigationView.menu.apply {
            clear() // Clear existing items

            menuItems.forEach { itemId ->
                val (title, iconRes) =
                    when (itemId) {
                        1 -> "Happy" to R.drawable.ic_happy
                        2 -> "Money" to R.drawable.ic_money
                        3 -> "Peace" to R.drawable.ic_peace
                        4 -> "Friend" to R.drawable.ic_friend
                        5 -> "Evolution" to R.drawable.ic_evolution
                        else -> "Main Screen" to R.drawable.ic_home
                    }
                add(0, itemId, Menu.NONE, title).setIcon(iconRes)
            }
        }

    // Handle navigation between fragments
    private fun handleNavigation(menuItem: MenuItem) {
        val fragment: Fragment =
            when (menuItem.itemId) {
                R.id.nav_main_screen -> MainFragment()
                1, 2, 3, 4, 5 -> SwitchFragment.newInstance(menuItem.itemId)
                else -> return
            }
        replaceFragment(fragment)
    }

    // Helper method to replace fragments
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // Avoid memory leaks by clearing the binding reference
    }
}
