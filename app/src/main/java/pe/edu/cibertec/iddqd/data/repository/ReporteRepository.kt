package pe.edu.cibertec.iddqd.data.repository

import pe.edu.cibertec.iddqd.data.model.Reporte
import pe.edu.cibertec.iddqd.data.remote.ApiClient
import pe.edu.cibertec.iddqd.data.remote.service.ReporteService
import pe.edu.cibertec.iddqd.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReporteRepository(
    private val serviceReporte: ReporteService = ApiClient.getServicioReporte()
) {
    fun crearReporte(
        id: Int,
        id_participante: Int,
        id_videojuego: Int,
        id_motivo: Int,
        id_tiempo: Int,
        fecha: String,
        callback: (Result<Boolean>) -> Unit
    ) {
        serviceReporte.crearReporte(
            Reporte(
                id,
                id_participante,
                id_videojuego,
                id_motivo,
                id_tiempo,
                fecha
            )
        ).enqueue(object : Callback<Reporte> {
            override fun onResponse(call: Call<Reporte>, response: Response<Reporte>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(Result.Success(true))
                }
            }

            override fun onFailure(call: Call<Reporte>, t: Throwable) {
                callback(Result.Error(t.message.toString()))
            }
        })
    }

    fun listarReportesPorParticipante(
        id_participante: Int,
        callback: (Result<List<Reporte>>) -> Unit
    ) {
        serviceReporte.listarReportesPorParticipante(id_participante)
            .enqueue(object : Callback<List<Reporte>> {
                override fun onResponse(
                    call: Call<List<Reporte>>,
                    response: Response<List<Reporte>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback(Result.Success(response.body()!!))
                    } else {
                        callback(Result.Error("No se encontró información"))
                    }
                }

                override fun onFailure(call: Call<List<Reporte>>, t: Throwable) {
                    callback(Result.Error(t.message.toString()))
                }
            })
    }
}
