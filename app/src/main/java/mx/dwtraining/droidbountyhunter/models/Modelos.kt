package mx.dwtraining.droidbountyhunter.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fugitivo(
    val id: Int = 0,
    var name: String = "",
    var status: Int = 0,
    var photo: String? = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var capture_date: String? = "",
) : Parcelable

data class FugitivoRequest(
    @SerializedName("UDIDString")
    var udidString: String
)
data class FugitivoResponse(
    @SerializedName("mensaje")
    val mensaje: String
)