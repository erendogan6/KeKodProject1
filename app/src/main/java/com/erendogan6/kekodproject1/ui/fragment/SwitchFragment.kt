package com.erendogan6.kekodproject1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.erendogan6.kekodproject1.R
import com.erendogan6.kekodproject1.ui.components.switchFragmentContent

class SwitchFragment : Fragment() {
    companion object {
        private const val ARG_SWITCH_ID = "switch_id"

        fun newInstance(switchId: Int) =
            SwitchFragment().apply {
                arguments = Bundle().apply { putInt(ARG_SWITCH_ID, switchId) }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val switchId = arguments?.getInt(ARG_SWITCH_ID) ?: -1

        val (switchNameRes, iconRes) =
            when (switchId) {
                1 -> R.string.switch_happy to R.drawable.ic_happy
                2 -> R.string.switch_money to R.drawable.ic_money
                3 -> R.string.switch_peace to R.drawable.ic_peace
                4 -> R.string.switch_friend to R.drawable.ic_friend
                5 -> R.string.switch_evolution to R.drawable.ic_evolution
                else -> R.string.switch_unknown to R.drawable.ic_home
            }

        // Return a ComposeView instead of an XML-based layout
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    switchFragmentContent(getString(switchNameRes), iconRes)
                }
            }
        }
    }
}
