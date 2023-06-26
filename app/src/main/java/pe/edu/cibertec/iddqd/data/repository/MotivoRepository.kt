package pe.edu.cibertec.iddqd.data.repository

import pe.edu.cibertec.iddqd.data.model.Motivo
import pe.edu.cibertec.iddqd.data.remote.ApiClient
import pe.edu.cibertec.iddqd.data.remote.service.MotivoService
import pe.edu.cibertec.iddqd.util.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MotivoRepository (
    private val serviceMotivo: MotivoService = ApiClient.getServicioMotivo()
) {
    fun listarMotivos(callback: (Result<List<Motivo>>) -> Unit ){
        serviceMotivo.listarMotivos().enqueue(object : Callback<List<Motivo>>{
            override fun onResponse(
                call: Call<List<Motivo>>, response: Response<List<Motivo>>
            ){
                if (response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!))
                } else{
                    callback(Result.Error("No se encontró información"))
                }
            }
            override fun onFailure(call: Call<List<Motivo>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }
}