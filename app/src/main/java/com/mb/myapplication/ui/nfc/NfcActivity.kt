package com.mb.myapplication.ui.nfc

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.util.Consumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.mb.myapplication.app.AppTheme
import com.mb.myapplication.utils.nfc.FelicaReader
import com.mb.myapplication.utils.nfc.StringHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NfcActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nfc()
        }
    }
}

@Composable
fun Nfc(
    viewModel: NfcViewModel = hiltViewModel()
) {
//    val uiState by viewModel.uiState.collectAsState()
    val nfcResult  = remember { mutableStateOf("") }
    val results = mutableListOf<String>()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context: Context = LocalContext.current
    val activity = (context as ComponentActivity)
    val nfcAdapter by lazy {
        NfcAdapter.getDefaultAdapter(context)
    }
    val felica = FelicaReader(context, activity)

    // nfc取得のリスナー
    val felicaListener = object : FelicaReaderInterface{
        override fun onReadTag(tag : Tag) {
            val idm : ByteArray = tag.id
            nfcResult.value = StringHelper.byteToHex(idm)
            results.add(StringHelper.byteToHex(idm))
        }
        override fun onConnect() {

        }
    }

    // lifecycle observer
    val observer = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    felica.setListener(felicaListener)
                    when(activity.intent.action) {
                        NfcAdapter.ACTION_NDEF_DISCOVERED -> {
                            viewModel.processIntent(activity.intent)
                            nfcResult.value = viewModel.nfcResult
                            results.add(nfcResult.value)
                        }
                        NfcAdapter.ACTION_TAG_DISCOVERED -> {
                            viewModel.processIntent(activity.intent)
                            nfcResult.value = viewModel.nfcResult
                            results.add(nfcResult.value)
                        }
                        NfcAdapter.ACTION_TECH_DISCOVERED -> {
                            viewModel.processIntent(activity.intent)
                            nfcResult.value = viewModel.nfcResult
                            results.add(nfcResult.value)
                        }
                    }
                }

                Lifecycle.Event.ON_PAUSE -> {
                    felica.stop()
                    nfcAdapter?.disableForegroundDispatch(context)
                }

                Lifecycle.Event.ON_RESUME -> {
                    felica.start()
                    val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                    } else {
                        PendingIntent.FLAG_UPDATE_CURRENT
                    }
                    val pendingIntent = PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, context.javaClass).addFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        ),
                        flags
                    )
                    nfcAdapter!!.enableForegroundDispatch(context, pendingIntent, null, null)
                }

                else -> {}
            }

        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                NfcScreen(nfcResult.value, results)
            }
        }
    }
}


