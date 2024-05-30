package com.mb.myapplication.utils.nfc

object StringHelper {
    fun byteToHex(b : ByteArray) : String{
        var s = ""
        for (element in b){
            s += "[%02X]".format(element)
        }
        return s
    }
}