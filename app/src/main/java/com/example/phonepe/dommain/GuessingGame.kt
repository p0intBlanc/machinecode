package com.example.phonepe.dommain

import com.example.phonepe.data.model.GameData
import com.example.phonepe.dommain.usecases.GameplayUseCase
import java.text.FieldPosition

class GuessingGame(
    private val gameplayUseCase: GameplayUseCase,
    private val gameCallbacks: GameCallbacks
) {
    private val gameData: List<GameData> = mutableListOf()
    private var currentLevel = 0
    private var userInput: Array<Char>? = null


    fun startGame() {
        userInput = Array<Char>(gameData[currentLevel].name.length) {
            '$'
        }
        currentLevel = 0

    }

    fun startNextLevel() {
        currentLevel++
        userInput = Array<Char>(gameData[currentLevel].name.length) {
            '$'
        }

    }

    fun takUserInput(char: Char, pos: Int) {
        if (checkifInputValid(char, pos)) {
            validatepostiveCase(pos, char)
        } else {
            gameCallbacks.onWrongDataEntered(currentLevel)
        }
    }

    private fun validatepostiveCase(pos: Int, char: Char) {
        userInput?.set(pos, char)
        if (checkIfGameComplete()) {
            gameCallbacks.onGameComplete(currentLevel)
        } else {
            gameCallbacks.onCorrectDataEntered(pos, char)
        }
    }

    private fun checkIfGameComplete() =
        gameplayUseCase.isGameComplete(userInput, gameData[currentLevel].name)


    private fun checkifInputValid(char: Char, pos: Int): Boolean {
        return gameplayUseCase.isCharValid(char, pos, gameData[currentLevel].name)
    }

    fun isGameActive(): Boolean {
        return true
    }

    fun getNameHintTodisplay(): String {
        return gameplayUseCase.getNameinRandomOrder(gameData[currentLevel].name)
    }

    fun getCurrentName(): String {
        return gameData[currentLevel].name
    }

    fun getCurrentLevele(): Int {
        return currentLevel
    }

    fun getCurrentGameData(): GameData {
        return gameData[currentLevel]
    }


    interface GameCallbacks {
        fun onGameComplete(currentLevel: Int)
        fun onWrongDataEntered(currentLevel: Int)
        fun onCorrectDataEntered(position: Int, userInput: Char)
    }
}