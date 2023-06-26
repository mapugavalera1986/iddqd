package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Videojuego
import retrofit2.Call
import retrofit2.http.GET

interface VideojuegoService {
    @GET("videojuegos")
    fun listarVideojuegos(): Call<List<Videojuego>>
}