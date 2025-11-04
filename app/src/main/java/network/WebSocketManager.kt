package com.example.chessapp.network

import kotlinx.coroutines.*

class WebSocketManager {
    suspend fun sendMove(from: String, to: String) {
        // тут позже будет реальное соединение
        println("📤 Отправлен ход: $from -> $to")
    }
}
