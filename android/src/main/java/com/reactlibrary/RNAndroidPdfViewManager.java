package com.reactlibrary;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.android.pdfview.listener.LoadCompleteListener;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;
import java.util.Map;

import javax.annotation.Nullable;

public class RNAndroidPdfViewManager extends SimpleViewManager<JRNPdfView> implements LoadCompleteListener, OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {
    public static final String REACT_CLASS = "JRNPdfView";
    JRNPdfView rnAndroidPdfView;
    Context mContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected JRNPdfView createViewInstance(ThemedReactContext reactContext) {
        mContext=reactContext;
        rnAndroidPdfView = new JRNPdfView(reactContext);
        return rnAndroidPdfView;
    }

    @ReactProp(name = "url")
    public void setUrl(JRNPdfView view, String url) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mContext,"没有读写权限,请打开权限",Toast.LENGTH_SHORT).show();
            //            if(ActivityCompat.shouldShowRequestPermissionRationale(reactContext,Manifest.permission.CALL_PHONE)){
            //                //如果之前有申请，但是被用户拒绝了，则返回true
            //                Toast.makeText(reactContext,"之前有申请权限，但是被拒绝",Toast.LENGTH_SHORT).show();
            //            }else{
            //                Toast.makeText(reactContext,"没有申请过权限，---运行这里",Toast.LENGTH_SHORT).show();
            //            }
        }else {
        view.setUrl(url,this);
        }
    }


    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        "loadComplete",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "loadComplete")))
                .build();
    }

    @Override
    public void loadComplete(File file) {
        rnAndroidPdfView.fromFile(file)
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .spacing(10) // in dp
                .onPageError(this)
                .pageFitPolicy(FitPolicy.BOTH)
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {
        rnAndroidPdfView.onReceiveNativeEvent();
    }

    @Override
    public void onPageError(int page, Throwable t) {
        rnAndroidPdfView.onReceiveErrorNativeEvent(t.getMessage());
    }
}
