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

    init {
        // Initialize all switches to be off, except the "Ego" switch
        _switchStates.value = mapOf(
            1 to false,  // Switch 1
            2 to false,  // Switch 2
            3 to false,  // Switch 3
            4 to false,  // Switch 4
            5 to false   // Switch 5
        )
    }

    // Method to handle changes in "Ego" switch
    fun onEgoSwitchChanged(isOn: Boolean) {
        _isEgoSwitchOn.value = isOn
        if (isOn) {
            // If Ego switch is on, disable all other switches
            _switchStates.value = _switchStates.value?.mapValues { false }
        }
    }

    // Method to handle changes in other switches
    fun onSwitchChanged(switchId: Int, isOn: Boolean) {
        if (_isEgoSwitchOn.value == true) return  // Do nothing if Ego switch is on

        _switchStates.value = _switchStates.value?.toMutableMap()?.apply {
            this[switchId] = isOn
        }
    }
}
