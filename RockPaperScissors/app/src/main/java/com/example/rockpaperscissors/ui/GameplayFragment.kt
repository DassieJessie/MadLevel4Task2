package com.example.rockpaperscissors.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import com.example.rockpaperscissors.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_gameplay.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

    const val ROCK = 1
    const val PAPER = 2
    const val SCISSORS = 3
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameplayFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private var playersHand : Int = ROCK
    private lateinit var result : String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gameplay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        initView()
    }

    private fun initView(){
        restartGame()

        btnRock.setOnClickListener{
            showResults()
            ivYou.setImageResource(R.drawable.rock)
            playersHand = ROCK
            playGame(playersHand)
        }

        btnPaper.setOnClickListener{
            showResults()
            ivYou.setImageResource(R.drawable.paper)
            playersHand = PAPER
            playGame(playersHand)
        }

        btnScissors.setOnClickListener{
            showResults()
            ivYou.setImageResource(R.drawable.scissors)
            playersHand = SCISSORS
            playGame(playersHand)
        }

        btnPlayAgain.setOnClickListener{
            restartGame()
        }
    }

    private fun playGame(player: Int){
        var computer : Int = (1..3).random()
        showComputersHand(computer)
        calculateResult(player, computer)

        addGameToDatabase(player, computer)

    }

    private fun calculateResult(player : Int, computer : Int) {
        var win : Boolean = false

        when(player){
            ROCK ->{
                if(computer == PAPER){
                    win = false
                }
                if(computer == SCISSORS){
                    win = true
                }
            }
            PAPER -> {
                if(computer == SCISSORS){
                    win = false
                }
                if(computer == ROCK){
                    win = true
                }
            }
            SCISSORS ->{
                if(computer == PAPER){
                    win = true
                }

                if(computer == ROCK){
                    win = false
                }
            }
        }

        //lose
        if(!win && computer != player){
            tvResultGameplay.text = getString(R.string.result_lose)
            this.result = getString(R.string.result_lose)
        }

        //draw
        if(computer == player){
            tvResultGameplay.text = getString(R.string.result_draw)
            this.result = getString(R.string.result_draw)
        }

        //win
        if(win){
            tvResultGameplay.text = getString(R.string.result_win)
            this.result = getString(R.string.result_win)
        }
    }

    private fun showComputersHand(hand: Int){
        when(hand){
            1 -> ivComputer.setImageResource(R.drawable.rock)
            2 -> ivComputer.setImageResource(R.drawable.paper)
            3 -> ivComputer.setImageResource(R.drawable.scissors)
        }
    }

    private fun showResults(){
        clGameResult.isVisible = true
        tvChooseText.isVisible = false

        btnRock.isVisible = false
        btnPaper.isVisible = false
        btnScissors.isVisible = false

        btnPlayAgain.isVisible = true
    }

    private fun restartGame(){
        clGameResult.isVisible = false
        tvChooseText.isVisible = true

        btnRock.isVisible = true
        btnPaper.isVisible = true
        btnScissors.isVisible = true

        btnPlayAgain.isVisible = false
    }

    private fun addGameToDatabase(player: Int, computer: Int){
        mainScope.launch {
            val date = Date().toString()

            val game = Game(
                player,
                computer,
                date,
                result
            )

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }

    }
}