package com.example.menris.osmtest

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.osmdroid.config.Configuration


@RuntimePermissions
class MainActivity : AppCompatActivity() {

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showLocation() {
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, PreferenceManager.getDefaultSharedPreferences(applicationContext))
        setContentView(R.layout.activity_main)

        myMap.setTileSource(TileSourceFactory.MAPNIK)
        myMap.controller.setZoom(15.0)
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
