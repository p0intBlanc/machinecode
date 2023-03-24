package com.example.phonepe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonepe.data.model.GameData
import com.example.phonepe.dommain.GuessingGame
import com.example.phonepe.dommain.usecases.GamedataUseCase
import com.example.phonepe.dommain.usecases.GamedataUseCaseImpl
import com.example.phonepe.dommain.usecases.GameplayUseCase

class GameViewModel(
    private val gamedataUseCase: GamedataUseCase,
    private val gameplayUseCase: GameplayUseCase
) : ViewModel() {

    private val gameDataList = mutableListOf<GameData>()
    private val _gameState = MutableLiveData<GameStateUIModel>()
    val gameState: LiveData<GameStateUIModel> = _gameState

    private var game: GuessingGame? = null

    fun getGameData() {
        gameDataList.addAll(gamedataUseCase.getGameData())
    }

    fun startGame() {
        if (canStart()) {
            startGameInternal()
        } else {
            _gameState.value = GameStateUIModel.DataError("gamme data not available.")
        }


    }

    fun getCurrentLevel() {
        if (game == null) {
            startGame()
        }
        game?.let {
            _gameState.value = GameStateUIModel.SetupCurrentLevel(
                nameHint = gameplayUseCase.getNameinRandomOrder(it.getCurrentGameData().image),
                currentLevel = it.getCurrentLevele(),
                logo = it.getCurrentGameData().image

            )
        }

    }


    private fun startGameInternal() {
        game = GuessingGame(gameplayUseCase, object : GuessingGame.GameCallbacks {
            override fun onGameComplete(currentLevel: Int) {
                _gameState.value = GameStateUIModel.LevelComplete(currentLevel)
            }

            override fun onCorrectDataEntered(position: Int, userInput: Char) {
                _gameState.value = GameStateUIModel.CorrectData(position, userInput)
            }

            override fun onWrongDataEntered(currentLevel: Int) {
                _gameState.value = GameStateUIModel.WrongData(currentLevel)
            }
        })
    }

    // any further validatations can be added
    private fun canStart(): Boolean {
        return gameDataList.size > 0
    }


}

sealed class GameStateUIModel {
    data class WrongData(val currentLevel: Int) : GameStateUIModel()
    data class CorrectData(val position: Int, val userInput: Char) : GameStateUIModel()
    data class LevelComplete(val currentLevel: Int) : GameStateUIModel()
    data class DataError(val error: String) : GameStateUIModel()
    data class SetupCurrentLevel(
        val nameHint: String,
        val currentLevel: Int,
        val logo: String
    ) : GameStateUIModel()

}