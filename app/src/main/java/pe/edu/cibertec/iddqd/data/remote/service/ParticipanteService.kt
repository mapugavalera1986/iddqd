package pe.edu.cibertec.iddqd.data.remote.service

import pe.edu.cibertec.iddqd.data.model.Participante
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ParticipanteService {
    @GET("participantes")
    fun revisar(@Query("dni") dni : String) : Call<List<Participante>>
    @GET("participantes")
    fun iniciarSSn(@Query("dni") dni : String) : Call<List<Participante>>
    @GET("participantes")
    fun obtenerParticipantePorDni(@Query("dni") dni : String) : Call<List<Participante>>
}