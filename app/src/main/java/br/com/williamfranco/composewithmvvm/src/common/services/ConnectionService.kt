package br.com.williamfranco.composewithmvvm.src.common.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface ConnectionService {
    val isConnected: Boolean
    suspend fun checkConnection()
}

class ConnectionServiceImpl(context: Context) : ConnectionService {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var _isConnected = false
    override val isConnected: Boolean get() = _isConnected

    override suspend fun checkConnection() {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        _isConnected = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}
