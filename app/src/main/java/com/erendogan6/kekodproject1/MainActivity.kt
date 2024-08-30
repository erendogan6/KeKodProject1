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
    private var lastSelectedItemId: Int = R.id.nav_main_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }

        replaceFragment(MainFragment(), false)
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
                val switch = viewModel.switchModels.value?.find { it.id == itemId }
                val title = switch?.name ?: "Main Screen"
                val iconRes = switch?.iconRes ?: R.drawable.ic_home
                add(0, itemId, Menu.NONE, title).setIcon(iconRes)
            }
        }

    // Handle navigation between fragments
    private fun handleNavigation(menuItem: MenuItem) {
        val newFragment: Fragment =
            when (menuItem.itemId) {
                R.id.nav_main_screen -> MainFragment()
                1, 2, 3, 4, 5 -> SwitchFragment.newInstance(menuItem.itemId)
                else -> return
            }

        val isMovingRight = menuItem.itemId > lastSelectedItemId // Determine direction
        replaceFragment(newFragment, isMovingRight)

        lastSelectedItemId = menuItem.itemId // Update the last selected item
    }

    // Helper method to replace fragments with dynamic transition effects based on direction
    private fun replaceFragment(
        fragment: Fragment,
        isMovingRight: Boolean,
    ) {
        val transaction = supportFragmentManager.beginTransaction()

        // Set custom animations based on navigation direction
        if (isMovingRight) {
            transaction.setCustomAnimations(
                R.anim.slide_in_right, // Enter from right
                R.anim.slide_out_left, // Exit to left
            )
        } else {
            transaction.setCustomAnimations(
                R.anim.slide_in_left, // Enter from left
                R.anim.slide_out_right, // Exit to right
            )
        }

        // Replace the fragment
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // Avoid memory leaks by clearing the binding reference
    }
}
