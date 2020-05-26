package com.utility

/*
*Project Name : mvvm                   
*Created by   : Azmin Purushotham              
*Date         : 2/9/19 8:23 PM      
*/
class ConnectionModel constructor(type: Int, isConnected: Boolean) {
    var type: Int = type
    var isConnected: Boolean = isConnected

    fun getCType(): Int {
        return type
    }

    fun getIsConnected(): Boolean {
        return isConnected;
    }
}