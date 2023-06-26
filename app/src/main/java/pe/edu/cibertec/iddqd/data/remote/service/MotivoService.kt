package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Motivo
import retrofit2.Call
import retrofit2.http.GET

interface MotivoService {
    @GET("motivos")
    fun listarMotivos(): Call<List<Motivo>>
}