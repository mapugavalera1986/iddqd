package pe.edu.cibertec.iddqd.data.remote

import pe.edu.cibertec.iddqd.data.remote.service.MotivoService
import pe.edu.cibertec.iddqd.data.remote.service.ParticipanteService
import pe.edu.cibertec.iddqd.data.remote.service.TiempoService
import pe.edu.cibertec.iddqd.data.remote.service.VideojuegoService
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
    fun getServicioVideojuego(): VideojuegoService {
        return getRetrofit().create(VideojuegoService::class.java)
    }
    fun getServicioMotivo(): MotivoService {
        return getRetrofit().create(MotivoService::class.java)
    }

    fun getServicioTiempo(): TiempoService {
        return getRetrofit().create(TiempoService::class.java)
    }
}