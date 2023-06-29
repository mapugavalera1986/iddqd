package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Reporte
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReporteService {
    @POST("reportes")
    fun crearReporte(@Body nuevoreporte: Reporte): Call<Reporte>
    @GET("reportes")
    fun listarReportesPorParticipante(@Query("id_participante") id_participante: Int): Call<List<Reporte>>
}