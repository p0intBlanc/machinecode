package com.example.phonepe.dommain.usecases

interface GameplayUseCase {

    fun getNameinRandomOrder(name: String): String

    fun isCharValid(char: Char, position: Int, name: String): Boolean

    fun isGameComplete(name: Array<Char>?, answer: String): Boolean
}

class GameplayUseCaseImpl() : GameplayUseCase {
    override fun getNameinRandomOrder(name: String): String {
        TODO("Not yet implemented")
    }

    override fun isCharValid(char: Char, position: Int, name: String): Boolean {
        val letters = name.toCharArray()
        return letters[position] == char
    }

    override fun isGameComplete(userinPut: Array<Char>?, answer: String): Boolean {
        var isCommplete = false
        userinPut?.let {
            for (i in (0..it.size)) {
                isCommplete = userinPut[i] == answer[i]
            }
        }
        return isCommplete
    }

//    override fun getNameHintForUser(): String {
//        TODO("Not yet implemented")
//    }
}