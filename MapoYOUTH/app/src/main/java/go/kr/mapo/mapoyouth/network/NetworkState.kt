package go.kr.mapo.mapoyouth.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-10-25
 * @desc
 */
class NetworkState(val app: Context) {

    @Suppress("DEPRECATION")
    fun isOnline() : Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}