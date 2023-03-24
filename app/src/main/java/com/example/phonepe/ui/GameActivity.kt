package com.example.phonepe.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phonepe.R
import javax.inject.Inject

class GameActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_container_layout)
        viewModel.gameState.observe(this) {
            handeUIStates(it)
        }

    }

    private fun handeUIStates(it: GameStateUIModel?) {
        when (it) {
            is GameStateUIModel.SetupCurrentLevel -> {}
            is GameStateUIModel.CorrectData -> TODO()
            is GameStateUIModel.DataError -> TODO()
            is GameStateUIModel.LevelComplete -> TODO()
            is GameStateUIModel.WrongData -> TODO()
            null -> TODO()
        }
    }

}