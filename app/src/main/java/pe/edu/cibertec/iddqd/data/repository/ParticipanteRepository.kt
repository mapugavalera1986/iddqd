package pe.edu.cibertec.iddqd.data.repository

import pe.edu.cibertec.iddqd.data.model.Participante
import pe.edu.cibertec.iddqd.data.remote.ApiClient
import pe.edu.cibertec.iddqd.data.remote.service.ParticipanteService
import pe.edu.cibertec.iddqd.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParticipanteRepository(
    private val servicioParticipante : ParticipanteService = ApiClient.getServicioParticipante()
) {
    private fun validarParticipante(dni : String, callback: (Result<Boolean>) -> Unit) {
        servicioParticipante.validarParticipante(dni).enqueue(object : Callback<List<Participante>> {
            override fun onResponse(call: Call<List<Participante>>, response: Response<List<Participante>>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(Result.Success(true))
                } else {
                    callback(Result.Error("El DNI no está registrado"))
                }
            }
            override fun onFailure(call: Call<List<Participante>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }
    fun iniciarSsn(dni: String, callback: (Result<Boolean>) -> Unit) {
        servicioParticipante.iniciarSsn(dni).enqueue(object : Callback<List<Participante>> {
            override fun onResponse(call: Call<List<Participante>>, response: Response<List<Participante>>) {

                if (response.isSuccessful && response.body() != null) {
                    callback(Result.Success(true))
                } else {
                    callback(Result.Error("No se encontró ese DNI"))
                }
            }
            override fun onFailure(call: Call<List<Participante>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }
}