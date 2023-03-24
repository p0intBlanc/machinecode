package com.example.phonepe.dommain.usecases

import com.example.phonepe.data.model.GameData
import com.example.phonepe.data.repo.PgRepo

interface GamedataUseCase {

    fun getGameData(): List<GameData>
}


class GamedataUseCaseImpl(
    private val repo: PgRepo
) : GamedataUseCase {
    override fun getGameData(): List<GameData> {
       return repo.getGameData()
    }
}