package com.erendogan6.kekodproject1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erendogan6.kekodproject1.R
import com.erendogan6.kekodproject1.model.SwitchModel

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _switchModels: MutableLiveData<List<SwitchModel>> =
        MutableLiveData(
            listOf(
                SwitchModel(
                    1,
                    application.getString(R.string.switch_happy),
                    R.drawable.ic_happy,
                ),
                SwitchModel(
                    2,
                    application.getString(R.string.switch_money),
                    R.drawable.ic_money,
                ),
                SwitchModel(
                    3,
                    application.getString(R.string.switch_peace),
                    R.drawable.ic_peace,
                ),
                SwitchModel(
                    4,
                    application.getString(R.string.switch_friend),
                    R.drawable.ic_friend,
                ),
                SwitchModel(
                    5,
                    application.getString(R.string.switch_evolution),
                    R.drawable.ic_evolution,
                ),
            ),
        )
    val switchModels: LiveData<List<SwitchModel>> = _switchModels

    private val _isEgoSwitchOn = MutableLiveData(true)
    val isEgoSwitchOn: LiveData<Boolean> = _isEgoSwitchOn

    private val _activeMenuItems = MutableLiveData(mutableListOf(R.id.nav_main_screen))
    val activeMenuItems: LiveData<MutableList<Int>> = _activeMenuItems

    // List to keep track of the switches in the order they were turned ON
    private val _switchHistory = mutableListOf<Int>()

    fun updateSwitchModels(models: List<SwitchModel>) {
        _switchModels.value = models
    }

    fun onEgoSwitchChanged(isOn: Boolean) {
        _isEgoSwitchOn.value = isOn
        if (isOn) {
            resetSwitches()
        }
    }

    // Method to handle changes in other switches
    fun onSwitchChanged(
        switchId: Int,
        isOn: Boolean,
    ) {
        if (_isEgoSwitchOn.value == true) return // Do nothing if Ego switch is on

        _switchModels.value =
            _switchModels.value?.map { switch ->
                if (switch.id == switchId) switch.copy(isChecked = isOn) else switch
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

    private fun resetSwitches() {
        _switchModels.value = _switchModels.value?.map { it.copy(isChecked = false) }
        _switchHistory.clear()
        _activeMenuItems.value = mutableListOf(R.id.nav_main_screen)
    }

    private fun updateActiveMenuItems() {
        val menuItems = mutableListOf<Int>()
        menuItems.add(R.id.nav_main_screen)

        val switchesToAdd = _switchHistory.take(4)
        menuItems.addAll(switchesToAdd)

        _activeMenuItems.value = menuItems
    }
}
