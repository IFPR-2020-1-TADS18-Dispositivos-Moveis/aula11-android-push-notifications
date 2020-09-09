package com.stiehl.testfcm.util

import com.stiehl.testfcm.api.FirebaseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseServiceGenerator {
    companion object {
        private var retrofit: Retrofit? = null
        private var service: FirebaseService? = null

        fun getService(): FirebaseService {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
//                    .baseUrl("http://10.0.2.2:3000/")     // Emulator
//                    .baseUrl("http://192.168.1.13:3000/") // Android device on the same network
                    .baseUrl("https://fcm-push-server.herokuapp.com/") // Real server
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit!!.create(FirebaseService::class.java)
            }
            return service!!
        }
    }
}