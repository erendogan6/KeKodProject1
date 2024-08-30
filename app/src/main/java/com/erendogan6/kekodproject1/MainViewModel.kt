package com.erendogan6.kekodproject1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // LiveData to hold the state of switches
    private val _isEgoSwitchOn = MutableLiveData<Boolean>(true)
    val isEgoSwitchOn: LiveData<Boolean> = _isEgoSwitchOn

    private val _switchStates = MutableLiveData<Map<Int, Boolean>>()
    val switchStates: LiveData<Map<Int, Boolean>> = _switchStates

    // Active Menu Items to be shown in BottomNavigationView (mutable list with the main screen initially)
    private val _activeMenuItems = MutableLiveData<MutableList<Int>>(mutableListOf(R.id.nav_main_screen))
    val activeMenuItems: LiveData<MutableList<Int>> = _activeMenuItems

    // List to keep track of the switches in the order they were turned ON
    private val _switchHistory = mutableListOf<Int>()

    init {
        // Initialize all switches to be off, except the "Ego" switch
        _switchStates.value =
            mapOf(
                1 to false,
                2 to false,
                3 to false,
                4 to false,
                5 to false,
            )
    }

    // Method to handle changes in "Ego" switch
    fun onEgoSwitchChanged(isOn: Boolean) {
        _isEgoSwitchOn.value = isOn
        if (isOn) {
            // If Ego switch is on, disable all other switches
            _switchStates.value = _switchStates.value?.mapValues { false }
            _switchHistory.clear() // Clear history when "Ego" switch is turned ON
            _activeMenuItems.value = mutableListOf(R.id.nav_main_screen) // Reset to main screen only
        }
    }

    // Method to handle changes in other switches
    fun onSwitchChanged(
        switchId: Int,
        isOn: Boolean,
    ) {
        if (_isEgoSwitchOn.value == true) return // Do nothing if Ego switch is on

        _switchStates.value =
            _switchStates.value?.toMutableMap()?.apply {
                this[switchId] = isOn
            }

        // Update the active menu items
        if (isOn) {
            // Add switch to history list if it's not already there
            if (!_switchHistory.contains(switchId) && _switchHistory.size < 4) {
                _switchHistory.add(switchId)
                updateActiveMenuItems()
            }
        } else {
            // Remove switch from history list and active menu if turned OFF
            _switchHistory.remove(switchId)
            if (_switchHistory.size < 4) {
                updateActiveMenuItems()
            }
        }
    }

    private fun updateActiveMenuItems() {
        val menuItems = mutableListOf<Int>()
        menuItems.add(R.id.nav_main_screen) // Main screen is always first

        // Add the first 4 switches in the history, excluding the most recent one (last opened)
        val switchCount = _switchHistory.size
        if (switchCount > 0) {
            val switchesToAdd = _switchHistory.take(4)
            menuItems.addAll(switchesToAdd)
        }

        _activeMenuItems.value = menuItems
    }
}
