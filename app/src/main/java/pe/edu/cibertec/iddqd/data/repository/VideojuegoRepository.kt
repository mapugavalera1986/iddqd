package pe.edu.cibertec.iddqd.data.repository

import pe.edu.cibertec.iddqd.data.model.Videojuego
import pe.edu.cibertec.iddqd.data.remote.ApiClient
import pe.edu.cibertec.iddqd.data.remote.service.VideojuegoService
import pe.edu.cibertec.iddqd.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideojuegoRepository(
    private val serviceVideojuego: VideojuegoService = ApiClient.getServicioVideojuego()
) {
    fun listarVideojuegos(callback: (Result<List<Videojuego>>) -> Unit) {
        serviceVideojuego.listarVideojuegos().enqueue(object : Callback<List<Videojuego>> {
            override fun onResponse(
                call: Call<List<Videojuego>>, response: Response<List<Videojuego>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback(Result.Success(response.body()!!))
                } else {
                    callback(Result.Error("No se encontró información"))
                }
            }

            override fun onFailure(call: Call<List<Videojuego>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }

    fun obtenerVideojuegoPorId(id: Int, callback: (Result<List<Videojuego>>) -> Unit) {
        serviceVideojuego.obtenerVideojuegoPorId(id).enqueue(object : Callback<List<Videojuego>> {
            override fun onResponse(
                call: Call<List<Videojuego>>,
                response: Response<List<Videojuego>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback(Result.Success(response.body()!!))
                } else {
                    callback(Result.Error("No se encontró información"))
                }
            }

            override fun onFailure(call: Call<List<Videojuego>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }
}