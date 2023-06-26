package pe.edu.cibertec.iddqd.data.repository

import pe.edu.cibertec.iddqd.data.model.Tiempo
import pe.edu.cibertec.iddqd.data.remote.ApiClient
import pe.edu.cibertec.iddqd.data.remote.service.TiempoService
import pe.edu.cibertec.iddqd.util.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class TiempoRepository(
    private val serviceTiempo: TiempoService = ApiClient.getServicioTiempo()
) {
    fun listarTiempo(callback: (Result<List<Tiempo>>) -> Unit ){
        serviceTiempo.listarTiempo().enqueue(object : Callback<List<Tiempo>>{
            override fun onResponse(
                call: Call<List<Tiempo>>, response: Response<List<Tiempo>>
            ){
                if (response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!))
                } else{
                    callback(Result.Error("No se encontró información"))
                }
            }
            override fun onFailure(call: Call<List<Tiempo>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }
}