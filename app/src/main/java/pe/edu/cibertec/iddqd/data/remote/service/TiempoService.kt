package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Tiempo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TiempoService {
    @GET("tiempo")
    fun listarTiempo(): Call<List<Tiempo>>

    @GET("tiempo")
    fun obtenerTiempoPorId(@Query("id") id: Int): Call<List<Tiempo>>
}