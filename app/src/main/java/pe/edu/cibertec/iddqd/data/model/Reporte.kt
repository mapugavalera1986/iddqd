package pe.edu.cibertec.iddqd.data.model

data class Reporte(
    val id : Int,
    val id_participante : Int,
    val fecha : String,
    val hora_inicio : String,
    val hora_fin : String,
    val id_videojuego : Int,
    val id_motivo : Int
)
