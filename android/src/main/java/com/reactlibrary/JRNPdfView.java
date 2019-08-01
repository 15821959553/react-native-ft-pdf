package com.reactlibrary;

import android.content.Context;
import android.util.AttributeSet;

import com.android.pdfview.AndroidPdfView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;


public class JRNPdfView extends AndroidPdfView {
    /**
     * Construct the initial view
     *
     * @param context
     */
    public JRNPdfView(Context context) {
        this(context, null);
    }

    public JRNPdfView(Context context, AttributeSet set) {
        super(context, set);
    }

    public void onReceiveNativeEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("message", "MyMessage");
        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "loadComplete",
                event);
    }
}
