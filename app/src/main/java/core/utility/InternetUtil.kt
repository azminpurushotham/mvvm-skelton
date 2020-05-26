package com.utility

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import timber.log.Timber
import javax.inject.Singleton

/*
*Project Name : mvvm                   
*Created by   : Azmin Purushotham              
*Date         : 23/8/19 2:48 PM      
*/
@Singleton
object InternetUtil : LiveData<ConnectionModel>() {

    private var mBroadcastReceiver: BroadcastReceiver? = null
    private lateinit var application: Application

    fun init(application: Application) {
        Timber.v("Internet Utils init")
        this.application = application
    }

    fun isInternet(): ConnectionModel {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        if (activeNetwork != null) {
            activeNetwork?.isConnectedOrConnecting
            return ConnectionModel(activeNetwork!!.type, true)
        } else {
            false
            return ConnectionModel(0, false)
        }

    }


    override fun onActive() {
        super.onActive()
        Timber.v("Internet Utils onActive // registerBroadCastRecever")
        registerBroadCastRecever()
    }

    override fun onInactive() {
        super.onInactive()
        Timber.v("Internet Utils onInactive // unRegisterBroadCastReceiver")
        unRegisterBroadCastReceiver()
    }

    private fun registerBroadCastRecever() {

        if (mBroadcastReceiver == null) {
            val filter = IntentFilter()
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

            mBroadcastReceiver = object : BroadcastReceiver() {

                override fun onReceive(_context: Context, intent: Intent) {
                    if (intent.getExtras() != null) {
                        val extras = intent.extras
                        val info = extras?.getParcelable<NetworkInfo>("networkInfo")
                        var isInternet = info != null && info.isConnectedOrConnecting
                        when (info?.getType()) {
                            ConnectivityManager.TYPE_WIFI -> {
                                Timber.v("Internet Utils Connectyvity Type Wifi")
                                postValue(ConnectionModel(info.getType(), isInternet))
                            }
                            ConnectivityManager.TYPE_MOBILE -> {
                                Timber.v("Internet Utils Connectyvity Type mobile")
                                postValue(ConnectionModel(info.getType(), isInternet))
                            }
                            else -> {
                                Timber.v("Internet Utils Connectyvity Type nothing ")
                                postValue(ConnectionModel(0, isInternet))
                            }
                        }

                    }
                }
            }
            application.registerReceiver(mBroadcastReceiver, filter)
        }
    }

    fun unRegisterBroadCastReceiver() {
        if (mBroadcastReceiver != null) {
            application.unregisterReceiver(mBroadcastReceiver)
            mBroadcastReceiver = null
        }
    }
}