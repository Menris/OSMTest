package com.example.menris.osmtest

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions


@RuntimePermissions
class MainActivity : AppCompatActivity() {

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showLocation() {
    }

    @SuppressLint("MissingPermission")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, PreferenceManager.getDefaultSharedPreferences(applicationContext))
        setContentView(R.layout.activity_main)

        myMap.setTileSource(TileSourceFactory.MAPNIK)
        myMap.controller.setZoom(15.0)

        val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        mFusedLocationClient.lastLocation
                .addOnSuccessListener(this) {
                    it.latitude
                    myMap.controller.animateTo(GeoPoint(it.latitude, it.longitude))
                }
                .addOnFailureListener(this) { }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu) //your file name
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("MissingPermission")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_main_get_data -> {
                /*mFusedLocationClient.lastLocation
                        .addOnSuccessListener(this) {
                            it.latitude
                            myMap.controller.animateTo(GeoPoint(it.latitude, it.longitude))
                        }
                        .addOnFailureListener(this) {
                            toast("Местоположение не найдено")
                        }*/
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    public override fun onResume() {
        super.onResume()
        myMap.onResume()
    }

    public override fun onPause() {
        super.onPause()
        myMap.onPause()
    }
}
