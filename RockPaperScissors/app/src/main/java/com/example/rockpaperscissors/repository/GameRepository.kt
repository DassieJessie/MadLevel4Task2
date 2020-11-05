package com.example.rockpaperscissors.repository

import android.content.Context
import com.example.rockpaperscissors.dao.GameDao
import com.example.rockpaperscissors.database.GameRoomDatabase
import com.example.rockpaperscissors.model.Game

class GameRepository (context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun deleteAllGames() {
        return gameDao.deleteAllGames()
    }

    suspend fun insertGame(game: Game) {
        return gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        return gameDao.deleteGame(game)
    }



}