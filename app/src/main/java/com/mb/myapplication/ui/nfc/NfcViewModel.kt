package com.mb.myapplication.ui.nfc

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareUltralight
import android.nfc.tech.Ndef
import android.os.Build
import android.os.Message
import android.util.Log
import androidx.lifecycle.ViewModel
import com.mb.myapplication.utils.nfc.StringHelper
import javax.inject.Inject

class NfcViewModel @Inject constructor(): ViewModel() {
    var nfcResult: String = ""
    fun processIntent(intent: Intent) {
        val tag = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: ByteArray(0)
        try {
            nfcResult = StringHelper.byteToHex(tag)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}