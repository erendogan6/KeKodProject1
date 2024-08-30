package com.erendogan6.kekodproject1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.erendogan6.kekodproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(MainFragment())

        // Handle navigation item clicks
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            handleNavigation(menuItem)
            true
        }

        // Observe active menu items from ViewModel
        viewModel.activeMenuItems.observe(
            this,
            Observer { menuItems ->
                updateBottomNavigationMenu(menuItems)
            },
        )
    }

    fun setBottomNavigationVisibility(visible: Boolean) {
        binding.bottomNavigationView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun updateBottomNavigationMenu(menuItems: List<Int>) {
        val menu = binding.bottomNavigationView.menu
        menu.clear() // Clear existing items

        menuItems.forEach { itemId ->
            val title =
                when (itemId) {
                    1 -> "Happy"
                    2 -> "Money"
                    3 -> "Peace"
                    4 -> "Friend"
                    5 -> "Evolution"
                    else -> "Main Screen"
                }
            val iconRes =
                when (itemId) {
                    1 -> R.drawable.ic_happy
                    2 -> R.drawable.ic_money
                    3 -> R.drawable.ic_peace
                    4 -> R.drawable.ic_friend
                    5 -> R.drawable.ic_evolution
                    else -> R.drawable.ic_home
                }
            menu.add(0, itemId, Menu.NONE, title).setIcon(iconRes)
        }
    }

    // Handle navigation between fragments
    fun handleNavigation(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_main_screen -> replaceFragment(MainFragment())
            1, 2, 3, 4, 5 -> replaceFragment(SwitchFragment.newInstance(menuItem.itemId))
        }
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
