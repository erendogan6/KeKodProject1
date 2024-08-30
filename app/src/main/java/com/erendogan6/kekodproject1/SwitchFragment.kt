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

        fun newInstance(switchId: Int) =
            SwitchFragment().apply {
                arguments = Bundle().apply { putInt(ARG_SWITCH_ID, switchId) }
            }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSwitchBinding.bind(view)

        val switchName =
            when (arguments?.getInt(ARG_SWITCH_ID) ?: -1) {
                1 -> "Happy"
                2 -> "Money"
                3 -> "Peace"
                4 -> "Friend"
                5 -> "Evolution"
                else -> "Unknown"
            }

        binding.textSwitchDetail.text = "Welcome To $switchName Fragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks by clearing the binding reference
    }
}
