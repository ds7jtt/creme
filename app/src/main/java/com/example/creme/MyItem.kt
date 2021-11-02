package com.example.creme

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MyItem(val _position: LatLng, val _title: String, val _snippet: String)
    : ClusterItem {

        override fun getSnippet(): String {
            return _snippet
        }
    override fun getTitle(): String {
        return _title
    }

    override fun getPosition(): LatLng {
        return _position
    }
    }