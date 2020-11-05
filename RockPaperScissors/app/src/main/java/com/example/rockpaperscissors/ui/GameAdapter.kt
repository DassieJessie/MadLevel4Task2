package com.example.rockpaperscissors.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            if(game.gamePlayerHand == ROCK){
                itemView.ivYou.setImageResource(R.drawable.rock)
            } else if (game.gamePlayerHand == PAPER){
                itemView.ivYou.setImageResource(R.drawable.paper)
            } else{
                itemView.ivYou.setImageResource(R.drawable.scissors)
            }

            if(game.gameComputerHand == ROCK){
                itemView.ivComputer.setImageResource(R.drawable.rock)
            } else if (game.gameComputerHand == PAPER){
                itemView.ivComputer.setImageResource(R.drawable.paper)
            } else{
                itemView.ivComputer.setImageResource(R.drawable.scissors)
            }

            itemView.tvDate.text = game.gameDate
            itemView.tvResultHistory.text = game.gameResult
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }
}