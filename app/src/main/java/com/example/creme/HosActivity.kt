package com.example.creme
// HOS

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.map3.*
import kotlinx.android.synthetic.main.mapforhos.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL


class HosActivity : AppCompatActivity() {

    val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    )
    val REQUEST_PERMISSION_CODE = 817 //
    val DEFAULT_ZOOM_LEVEL = 16f
    val CITY_HALL =LatLng(37.5662954, 126.9779452)

    var googleMap3: GoogleMap? = null
    // private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapforhos)

        mapView3.onCreate(savedInstanceState)


////////////////////

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigation.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.antt -> {
                            Toast.makeText(applicationContext, "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, MapsActivity2::class.java)
                            startActivityForResult(intent, MapsActivity.REQUEST_CODE_MENU1)
                            finish()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.aedd -> {
                            Toast.makeText(applicationContext, "두 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                            val intent2 = Intent(applicationContext, AedActivity::class.java)
                            startActivityForResult(intent2, MapsActivity.REQUEST_CODE_MENU1)
                            finish()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.toii -> {
                            Toast.makeText(applicationContext, "세 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                            val intent3 = Intent(applicationContext, MapsActivity4::class.java)
                            startActivityForResult(intent3, MapsActivity.REQUEST_CODE_MENU1)
                            finish()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.hoss -> {
                            Toast.makeText(applicationContext, "세 번째 탭 선택됨", Toast.LENGTH_LONG).show()
                            val intent4 = Intent(applicationContext, HosActivity::class.java)
                            startActivityForResult(intent4, MapsActivity.REQUEST_CODE_MENU1)
                            finish()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.mouu -> {
                            Toast.makeText(applicationContext, "마지막 탭 선택됨", Toast.LENGTH_LONG).show()
                            val intent5 = Intent(applicationContext, webActivity::class.java)
                            startActivityForResult(intent5, MapsActivity.REQUEST_CODE_MENU1)
                            finish()
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                }
        )





////////////////////






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
        mapView3.getMapAsync{
            googleMap3 = it
            it.uiSettings.isMyLocationButtonEnabled = true


            when {
                hasPermissions() -> {

                    it.isMyLocationEnabled = true
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(getMyLocation(), DEFAULT_ZOOM_LEVEL))
                }
                else -> {
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(CITY_HALL, DEFAULT_ZOOM_LEVEL))
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getMyLocation(): LatLng {
        val locationProvider: String = LocationManager.GPS_PROVIDER
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(locationProvider)
        if (lastKnownLocation != null){
            return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
        }else{
            return CITY_HALL
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    val API_KEY = "5a4d7a4769647337353644644d5769"
    var task: AedReadTask? = null
    //ToiletReadTask
    var aeds = JSONArray()
//    var toilets = JSONArray()

    fun JSONArray.merge(anotherArray: JSONArray) {
        for(i in 0 until anotherArray.length()) {
            this.put(anotherArray.get(i))
        }
    }

    fun readData(startIndex: Int, lastIndex: Int) : JSONObject {
        val url = URL("http://openAPI.seoul.go.kr:8088" + "/${API_KEY}/json/TvEmgcHospitalInfo/${startIndex}/${lastIndex}")
        // val url = URL("http://openAPI.seoul.go.kr:8088" + "/${API_KEY}/json/SearchPublicToiletPOIService/${startIndex}/${lastIndex}")
        val connection = url.openConnection()

        val data = connection.getInputStream().readBytes().toString(charset("UTF-8"))
        return JSONObject(data)
    }

    inner class AedReadTask : AsyncTask<Void, JSONArray, String>() {

        override fun onPreExecute() {
            googleMap3?.clear()
            aeds = JSONArray()
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

                totalCount = jsonObject.getJSONObject("TvEmgcHospitalInfo").getInt("list_total_count")

                val rows = jsonObject.getJSONObject("TvEmgcHospitalInfo").getJSONArray("row")
                aeds.merge(rows)
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

    override fun onResume(){
        super.onResume()
        mapView3.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView3.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView3.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        task?.cancel(true)
        task = AedReadTask()
        task?.execute()
    }

    override fun onStop() {
        super.onStop()
        task?.cancel(true)
        task = null
    }

    fun addMarkers(aeds: JSONObject){
        googleMap3?.addMarker(
                MarkerOptions()
                        .position(LatLng(aeds.getDouble("WGS84LAT"), aeds.getDouble("WGS84LON")))
                        .title(aeds.getString("DUTYDIVNAM"))
                        .snippet(aeds.getString("DUTYTEL3"))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

        )
    }

}