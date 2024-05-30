package com.mb.myapplication.ui.nfc

import android.nfc.Tag
import com.mb.myapplication.utils.nfc.FelicaReader
interface FelicaReaderInterface: FelicaReader.Listener {
    fun onReadTag(tag: Tag)
    fun onConnect()
}