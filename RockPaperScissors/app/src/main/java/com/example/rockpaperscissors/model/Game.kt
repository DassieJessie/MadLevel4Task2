package com.example.rockpaperscissors.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameTable")
data class Game(
    @ColumnInfo(name = "player_hand")
    var gamePlayerHand : Int,

    @ColumnInfo(name = "computer_hand")
    var gameComputerHand : Int,

    @ColumnInfo(name = "game_date")
    var gameDate : String,

    @ColumnInfo(name = "result")
    var gameResult : String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long? = null
)