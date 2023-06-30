package pe.edu.cibertec.iddqd.data.repository

import pe.edu.cibertec.iddqd.data.model.Participante
import pe.edu.cibertec.iddqd.data.remote.ApiClient
import pe.edu.cibertec.iddqd.data.remote.service.ParticipanteService
import pe.edu.cibertec.iddqd.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ParticipanteRepository(
    private val serviceParticipante: ParticipanteService = ApiClient.getServicioParticipante()
) {
    fun revisar(dni: String, callback: (Result<Boolean>) -> Unit) {
        serviceParticipante.revisar(dni).enqueue(object : Callback<List<Participante>> {
            override fun onResponse(
                call: Call<List<Participante>>,
                response: Response<List<Participante>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.isEmpty()) {
                        callback(Result.Success(true))
                    } else {
                        callback(Result.Error("Ya existe", false))
                    }
                } else {
                    callback(Result.Error("No data found"))
                }
            }

            override fun onFailure(call: Call<List<Participante>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }

    fun iniciarSSn(dni: String, callback: (Result<Boolean>) -> Unit) {
        serviceParticipante.iniciarSSn(dni).enqueue(object : Callback<List<Participante>> {
            override fun onResponse(
                call: Call<List<Participante>>,
                response: Response<List<Participante>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.isNotEmpty()) {
                        callback(Result.Success(true))
                    } else {
                        callback(Result.Error("DNI incorrecto"))
                    }
                } else {
                    callback(Result.Error("No existe"))
                }
            }

            override fun onFailure(call: Call<List<Participante>>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }

    fun obtenerParticipantePorDni(dni: String, callback: (Result<List<Participante>>) -> Unit) {
        serviceParticipante.obtenerParticipantePorDni(dni)
            .enqueue(object : Callback<List<Participante>> {
                override fun onResponse(
                    call: Call<List<Participante>>,
                    response: Response<List<Participante>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback(Result.Success(response.body()!!))
                    } else {
                        callback(Result.Error("No se encontró información"))
                    }
                }

                override fun onFailure(call: Call<List<Participante>>, t: Throwable) {
                    callback(Result.Error(t.message.toString()))
                }
            })
    }
}
