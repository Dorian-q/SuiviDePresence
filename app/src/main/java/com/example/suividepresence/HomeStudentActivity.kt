package com.example.suividepresence

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.suividepresence.databinding.ActivityHomeStudentBinding


class HomeStudentActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeStudentBinding
    private var btnTakePicture: Button? = null
    private var btnScanBarcode: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeStudentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_student)
        initViews()
    }

    private fun initViews() {
        btnTakePicture = findViewById(R.id.btnTakePicture)
        btnScanBarcode = findViewById(R.id.btnScanBarcode)
        binding.btnTakePicture.setOnClickListener(this)
        binding.btnScanBarcode.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnTakePicture -> startActivity(
                Intent(
                    this@HomeStudentActivity,
                    PictureBarcodeActivity::class.java
                )
            )
            R.id.btnScanBarcode -> startActivity(
                Intent(
                    this@HomeStudentActivity,
                    ScannedBarcodeActivity::class.java
                )
            )
        }
    }
}