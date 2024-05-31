package com.mb.myapplication.utils.nfc

object StringHelper {
    fun byteToHex(b : ByteArray) : String{
        var s = ""
        for (element in b){
            s += element
        }
        return s
    }
}