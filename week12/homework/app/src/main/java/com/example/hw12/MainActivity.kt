package com.example.hw12

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var checkMagnetometer: Button
    lateinit var checkWifi: Button
    lateinit var call: Button
    lateinit var sms: Button
    lateinit var camera: Button
    lateinit var video: Button
    lateinit var discovery: Button
    private lateinit var bluetoothAdapter: BluetoothAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkMagnetometer = findViewById(R.id.magnetometer)
        checkWifi = findViewById(R.id.wifi)
        call = findViewById(R.id.call)
        sms = findViewById(R.id.sms)
        camera = findViewById(R.id.camera)
        video = findViewById(R.id.video)
        discovery = findViewById(R.id.discovery)

        checkMagnetometer.setOnClickListener {
            if (hasMagnetometer(this@MainActivity)) {
                Toast.makeText(this@MainActivity, "Magnetometer active", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Magnetometer inactive",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        checkWifi.setOnClickListener {
            if (isWifiConnected(this@MainActivity)) {
                Toast.makeText(this@MainActivity, "Wi-Fi is connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Wi-Fi is disconnected", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        call.setOnClickListener {
            val phoneNumber = "+8499999999"
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(callIntent)
        }

        sms.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:55512345"))
            smsIntent.putExtra("sms_body", "Press send to send me")
            startActivity(smsIntent)
        }

        discovery.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            if (bluetoothAdapter.isDiscovering) {
                bluetoothAdapter.cancelDiscovery()
            }

            val foundDevices = bluetoothAdapter.bondedDevices
            val deviceNames = ArrayList<String>()

            for (device in foundDevices) {
                deviceNames.add(device.name)
            }
            val message = if (deviceNames.isNotEmpty()) {
                "Devices: ${deviceNames.joinToString(", ")}"
            } else {
                "No devices."
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        camera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }

        video.setOnClickListener {
            val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            startActivityForResult(videoIntent, VIDEO_REQUEST_CODE)
        }
    }

    private fun hasMagnetometer(context: Context): Boolean {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        return magnetometer != null
    }

    private fun isWifiConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // TODO: handle image
        } else if (requestCode == VIDEO_REQUEST_CODE && resultCode == RESULT_OK) {
            val videoUri: Uri? = data?.data
            // TODO: handle video
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
        private const val VIDEO_REQUEST_CODE = 101
    }
}