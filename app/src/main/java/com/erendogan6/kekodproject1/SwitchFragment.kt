package com.erendogan6.kekodproject1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.erendogan6.kekodproject1.databinding.FragmentSwitchBinding

class SwitchFragment : Fragment(R.layout.fragment_switch) {
    private var _binding: FragmentSwitchBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_SWITCH_ID = "switch_id"

        // Method to create a new instance of the fragment with the switch ID
        fun newInstance(switchId: Int): SwitchFragment {
            val fragment = SwitchFragment()
            val args = Bundle()
            args.putInt(ARG_SWITCH_ID, switchId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSwitchBinding.bind(view) // Initialize ViewBinding

        val switchId = arguments?.getInt(ARG_SWITCH_ID) ?: -1

        val text =
            "Welcome To " +
                when (switchId) {
                    1 -> "Happy"
                    2 -> "Money"
                    3 -> "Peace"
                    4 -> "Friend"
                    5 -> "Evolution"
                    else -> "Unknown"
                } + " Fragment"

        // Display content based on the switch ID
        binding.textSwitchDetail.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks by clearing the binding reference
    }
}
