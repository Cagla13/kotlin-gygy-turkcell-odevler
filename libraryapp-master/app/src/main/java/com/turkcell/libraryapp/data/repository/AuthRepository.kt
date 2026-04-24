package com.turkcell.libraryapp.data.repository

import kotlinx.coroutines.delay
import kotlin.random.Random

class AuthRepository {
    // Giriş ve Kayıt simülasyonu yapan fonksiyon
    suspend fun signIn(email: String, password: String): Result<Unit> = runCatching {
        delay(2000) // 2 saniye bekleme (Ağ isteği simülasyonu)

        val isSuccess = Random.nextBoolean() // %50 ihtimalle başarılı/başarısız
        if (isSuccess) {
            Unit
        } else {
            throw Exception("Giriş denemesi başarısız oldu (Simülasyon Hatası)")
        }
    }
}