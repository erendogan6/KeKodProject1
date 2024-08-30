package com.erendogan6.kekodproject1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.argumentCaptor

class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule() // To execute tasks synchronously

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var menuObserver: Observer<List<Int>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel()
    }

    @Test
    fun `test initial state with Ego switch on`() {
        assertTrue(viewModel.isEgoSwitchOn.value == true) // Ego switch is on initially
        assertTrue(viewModel.activeMenuItems.value?.size == 1) // Only main screen should be present
        assertTrue(viewModel.activeMenuItems.value?.get(0) == R.id.nav_main_screen) // Main screen in first slot
    }

    @Test
    fun `test switches cannot be enabled when Ego is on`() {
        viewModel.onEgoSwitchChanged(true) // Ego switch on
        viewModel.onSwitchChanged(1, true)
        assertTrue(viewModel.switchStates.value?.get(1) == false) // Switch should remain off
    }

    @Test
    fun `test BottomNavigationView visibility when Ego switch is toggled`() {
        val observer: Observer<Boolean> = mock()
        viewModel.isEgoSwitchOn.observeForever(observer)

        // Act
        viewModel.onEgoSwitchChanged(false)
        viewModel.onEgoSwitchChanged(true)

        // Use ArgumentCaptor to capture all the invocations
        val captor = argumentCaptor<Boolean>()
        verify(observer, times(3)).onChanged(captor.capture()) // Adjusted to expect 3 calls

        // Assert the captured values
        assertEquals(listOf(true, false, true), captor.allValues) // Adjust the list to match the expected values

        viewModel.isEgoSwitchOn.removeObserver(observer)
    }

    @Test
    fun `test adding icons to BottomNavigationView when switches are enabled`() {
        viewModel.activeMenuItems.observeForever(menuObserver)

        viewModel.onEgoSwitchChanged(false) // Turn off Ego switch
        viewModel.onSwitchChanged(1, true) // Turn on switch 1
        viewModel.onSwitchChanged(2, true) // Turn on switch 2
        viewModel.onSwitchChanged(3, true) // Turn on switch 3
        viewModel.onSwitchChanged(4, true) // Turn on switch 4
        viewModel.onSwitchChanged(5, true) // Turn on switch 5 (should not be added)

        assertEquals(5, viewModel.activeMenuItems.value?.size) // Main screen + 4 switches
        assertEquals(R.id.nav_main_screen, viewModel.activeMenuItems.value?.get(0)) // Main screen in the first slot
        assertFalse(viewModel.activeMenuItems.value?.contains(5) == true) // Last switch (5) should not be added
    }

    @Test
    fun `test removing icons from BottomNavigationView when switches are disabled`() {
        viewModel.activeMenuItems.observeForever(menuObserver)

        viewModel.onEgoSwitchChanged(false) // Ego switch is off
        viewModel.onSwitchChanged(1, true) // Turn on switch 1
        viewModel.onSwitchChanged(2, true) // Turn on switch 2
        viewModel.onSwitchChanged(3, true) // Turn on switch 3

        viewModel.onSwitchChanged(1, false) // Turn off switch 1

        assertEquals(3, viewModel.activeMenuItems.value?.size) // Main screen + 2 switches
        assertFalse(viewModel.activeMenuItems.value?.contains(1) == true) // Switch 1 should be removed
    }
}
