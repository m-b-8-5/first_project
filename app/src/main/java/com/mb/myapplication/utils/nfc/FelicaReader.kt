package com.mb.myapplication.utils.nfc

import android.app.Activity
import android.content.Context
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NfcF
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import com.mb.myapplication.ui.nfc.FelicaReaderInterface

class FelicaReader(
    private val context: Context,
    private val activity: Activity
): Handler(Looper.getMainLooper()) {
    private var nfcManager : NfcManager? = null
    private var nfcAdapter : NfcAdapter? = null
    private var callback : CustomReaderCallback? = null

    private var listener: FelicaReaderInterface? = null
    interface Listener

    fun start() {
        callback = CustomReaderCallback()
        callback?.setHandler(this@FelicaReader)
        nfcManager = context.getSystemService(Context.NFC_SERVICE) as NfcManager?
        nfcAdapter = nfcManager!!.defaultAdapter
        nfcAdapter!!.enableReaderMode(activity, callback, NfcAdapter.FLAG_READER_NFC_F or NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_NFC_B or NfcAdapter.FLAG_READER_NFC_V or NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,null)
    }
    fun stop() {
        nfcAdapter!!.disableReaderMode(activity)
        callback = null
    }
    // コールバックからのメッセージクラス
    override fun handleMessage(msg: Message) {
        if (msg.arg1 == 1) {
            listener?.onReadTag(msg.obj as Tag)
        }
        if (msg.arg1 == 2) {
            listener?.onConnect()
        }
    }

    fun setListener(listener: Listener?) {
        if (listener is FelicaReaderInterface) {
            this.listener = listener
        }
    }

    private class CustomReaderCallback : NfcAdapter.ReaderCallback {
        private var handler : Handler? = null
        override fun onTagDiscovered(tag: Tag) {
            val msg = Message.obtain()
            msg.arg1 = 1
            msg.obj = tag
            if (handler != null) handler?.sendMessage(msg)
            val nfc : Ndef = Ndef.get(tag) ?: return
            try {
                nfc.connect()
                nfc.close()
                msg.arg1 = 2
                msg.obj = tag
                if (handler != null) handler?.sendMessage(msg)
            } catch (e : Exception){
                nfc.close()
            }
        }
        fun setHandler(handler  : Handler){
            this.handler = handler
        }
    }
}