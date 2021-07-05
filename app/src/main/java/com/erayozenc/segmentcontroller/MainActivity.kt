package com.erayozenc.segmentcontroller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erayozenc.lib.SegmentController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val segmentController = findViewById<SegmentController>(R.id.segmentController2)

        segmentController.setSelectedTabColor(Color.WHITE)
        segmentController.setUnselectedTabColor(Color.GRAY)

        segmentController.setCornerRadius(22f)
        segmentController.setStrokeWidth(4f)

    }
}