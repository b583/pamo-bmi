package s15666.pjwstk.pamobmi.ui.covid19;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import s15666.pjwstk.pamobmi.R;

public class Covid19Fragment extends Fragment {

    private WebView web;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid19, container, false);
        web = view.findViewById(R.id.covid19_webview);

        init();
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("PAMO BMI", consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + " - " + consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        web.loadUrl("file:///android_asset/covid19_graph.html");
    }

}
