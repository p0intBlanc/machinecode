package com.example.phonepe.data.repo

import com.example.phonepe.data.model.GameData
import com.google.gson.Gson

interface PgRepo {

    fun getGameData(): List<GameData>
}

class PgRepoImpl() : PgRepo {
    override fun getGameData(): List<GameData> {
        return Gson().fromJson<List<GameData>>(MockLocalData.DUMMY_JSON, GameData::class.java)
    }

}