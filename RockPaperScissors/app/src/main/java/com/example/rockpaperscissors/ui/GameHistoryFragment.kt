package com.example.rockpaperscissors.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameHistoryFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_game_history, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnMenuBack -> {
                findNavController().navigate(
                    R.id.action_gameHistoryFragment_to_gameplayFragment
                )
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}