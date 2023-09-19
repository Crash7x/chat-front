package com.example.weatherapp

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

enum class BluetoothState {
    INITIAL,
    CONNECTED,
    DISCONNECTED
}

class BluetoothReceiver(): BroadcastReceiver() {

    private val filter = IntentFilter().apply {
        addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
    }


    override fun onReceive(context: Context, intent: Intent) {
        Log.d("fff","intent :${intent.action}")
        when (intent.action) {
            Intent.ACTION_VIEW -> { Log.d("fff","connect")}
            BluetoothDevice.ACTION_ACL_DISCONNECTED -> { Log.d("fff","disconnect")}
        }
    }
}