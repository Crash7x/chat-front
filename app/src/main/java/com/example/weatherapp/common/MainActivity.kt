package com.example.weatherapp.common

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Browser
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.core.navigation.NavCommand
import com.example.weatherapp.BluetoothState
import com.example.weatherapp.ForegroundService
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("Binding is not initialized")

    @Inject
    lateinit var navigationCommand: MutableSharedFlow<NavCommand>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}