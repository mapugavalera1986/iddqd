package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Videojuego
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VideojuegoService {
    @GET("videojuegos")
    fun listarVideojuegos(): Call<List<Videojuego>>

    @GET("videojuegos")
    fun obtenerVideojuegoPorId(@Query("id") id: Int): Call<List<Videojuego>>
}