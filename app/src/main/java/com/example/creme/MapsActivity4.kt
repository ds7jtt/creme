package com.example.creme


import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray
import org.json.JSONObject
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.net.URL
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.CameraUpdateFactory
import kotlinx.android.synthetic.main.map3.*

class MapsActivity4 : AppCompatActivity() {

    val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
                                            )
    val REQUEST_PERMISSION_CODE = 815 //
    val DEFAULT_ZOOM_LEVEL = 17f
    val CITY_HALL =LatLng(37.5662952, 126.9779450)

    var googleMap: GoogleMap? = null
    // private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map3)
        mapView.onCreate(savedInstanceState)

        if(hasPermissions()){
            initMap()
        }else{
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)
        }

        ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        initMap()
    }

    fun hasPermissions(): Boolean {
        for(permission in PERMISSIONS){
            if(ActivityCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    @SuppressLint("MissingPermission")
    fun initMap(){
        mapView.getMapAsync{
            googleMap = it

            when {
                hasPermissions() -> {
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL))
                }
                else -> {
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(CITY_HALL, DEFAULT_ZOOM_LEVEL))
                }
            }
        }
    }

    fun getMyLocation(): LatLng{
        val locationProvider: String = LocationManager.GPS_PROVIDER
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastKnownLocation: Location = locationManager.getLastKnownLocation(locationProvider)
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    val API_KEY = "534e74534e64733734355374416158"
    var task: ToiletReadTask? = null
    var toilets = JSONArray()

    fun JSONArray.merge(anotherArray: JSONArray) {
        for(i in 0 until anotherArray.length()) {
            this.put(anotherArray.get(i))
        }
    }

    fun readData(startIndex: Int, lastIndex: Int) : JSONObject {
        val url = URL("http://openAPI.seoul.go.kr:8088" + "/${API_KEY}/json/SearchPublicToiletPOIService/${startIndex}/${lastIndex}")
        val connection = url.openConnection()

        val data = connection.getInputStream().readBytes().toString(charset("UTF-8"))
                return JSONObject(data)
    }

    inner class ToiletReadTask : AsyncTask<Void, JSONArray, String>() {

        override fun onPreExecute() {
            googleMap?.clear()
            toilets = JSONArray()
    }

        override fun doInBackground(vararg p0: Void?): String {

            val step = 1000;
            var startIndex = 1
            var lastIndex = step
            var totalCount = 0

            do {
                if (isCancelled) break

                if (totalCount != 0) {
                    startIndex += step
                    lastIndex += step
                }

                val jsonObject = readData(startIndex, lastIndex)

                totalCount = jsonObject.getJSONObject("SearchPublicToiletPOIService").getInt("list_total_count")

                val rows = jsonObject.getJSONObject("SearchPublicToiletPOIService").getJSONArray("row")
                toilets.merge(rows)
                publishProgress(rows)

            } while (lastIndex < totalCount)

            return "complete"
        }

        override fun onProgressUpdate(vararg values: JSONArray?) {
            val array = values[0]
            array?.let{
                for (i in 0 until array.length()) {
                    addMarkers(array.getJSONObject(i))
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        task?.cancel(true)
        task = ToiletReadTask()
        task?.execute()
    }

    override fun onStop() {
        super.onStop()
        task?.cancel(true)
        task = null
    }

    fun addMarkers(toilet: JSONObject){
        googleMap?.addMarker(
                MarkerOptions()
                        .position(LatLng(toilet.getDouble("Y_WGS84"), toilet.getDouble("X_WGS84")))
                        .title(toilet.getString("FNAME"))
                        .snippet(toilet.getString("ANAME"))

        )
    }

}