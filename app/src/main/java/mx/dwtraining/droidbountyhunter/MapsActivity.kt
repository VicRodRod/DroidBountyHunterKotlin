package mx.dwtraining.droidbountyhunter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mx.dwtraining.droidbountyhunter.databinding.ActivityMapsBinding
import mx.dwtraining.droidbountyhunter.models.Fugitivo

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapsBinding
    private var mMap: GoogleMap? = null
    private var fugitivo: Fugitivo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fugitivo = IntentCompat.getParcelableExtra(intent, "fugitivo"
            ,Fugitivo::class.java)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        title = fugitivo!!.name
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val position: LatLng = if (fugitivo!!.latitude == 0.0 && fugitivo!!.longitude ==
            0.0) {
            LatLng(-34.0, 151.0)
        } else {
            LatLng(fugitivo!!.latitude, fugitivo!!.longitude)
        }
        mMap!!.addMarker(MarkerOptions().position(position).title(fugitivo!!.name))
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }
}