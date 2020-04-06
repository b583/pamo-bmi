package s15666.pjwstk.pamobmi.ui.covid19

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import s15666.pjwstk.pamobmi.R

class Covid19Fragment : Fragment() {

    private var web: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_covid19, container, false)
        web = view.findViewById(R.id.covid19_webview)

        init()
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        web!!.settings.javaScriptEnabled = true
        web!!.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d("PAMO BMI", consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + " - " + consoleMessage.message())
                return super.onConsoleMessage(consoleMessage)
            }
        }
        web!!.loadUrl("file:///android_asset/covid19_graph.html")
    }
}