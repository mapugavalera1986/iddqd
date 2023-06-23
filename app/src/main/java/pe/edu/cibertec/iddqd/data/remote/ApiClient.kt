package pe.edu.cibertec.iddqd.data.remote

import pe.edu.cibertec.iddqd.data.remote.service.ParticipanteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_URL_PRINCIPAL = "https://pebble-seasoned-stitch.glitch.me/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(API_URL_PRINCIPAL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getServicioParticipante() : ParticipanteService {
        return getRetrofit().create(ParticipanteService::class.java)
    }
}