package com.example.rockpaperscissors.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import com.example.rockpaperscissors.repository.GameRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameHistoryFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

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
        gameRepository = GameRepository(requireContext())
        initRV()
        getGameList()
    }

    private fun initRV(){
        rvGames.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter

        rvGames.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
    }

    private fun getGameList(){
        mainScope.launch {
            //Get all games from database
            val games = withContext(Dispatchers.IO){
                gameRepository.getAllGames()
            }

            //Clears game list die er al was
            this@GameHistoryFragment.games.clear()

            //Voegt alle games weer toe
            this@GameHistoryFragment.games.addAll(games)

            gameAdapter.notifyDataSetChanged()
        }
    }

    private fun clearGameList(){
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.deleteAllGames()
            }

            //update list
            getGameList()
        }
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
            R.id.btnMenuDelete -> {
                clearGameList()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}