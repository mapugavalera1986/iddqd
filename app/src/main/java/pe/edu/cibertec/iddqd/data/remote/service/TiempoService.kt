package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Tiempo
import retrofit2.Call
import retrofit2.http.GET

interface TiempoService {
    @GET("tiempo")
    fun listarTiempo(): Call<List<Tiempo>>
}