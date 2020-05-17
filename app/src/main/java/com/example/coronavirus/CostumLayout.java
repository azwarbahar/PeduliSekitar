package com.example.coronavirus;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CostumLayout extends RelativeLayout {
    private GoogleMap map;
    private int offPXL;
    private Marker marker;

    private View infoWindow;

    public CostumLayout(Context context) {
        super(context);
    }

    public CostumLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CostumLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    //Must be called before we can route the touch events

    public void init(GoogleMap map, int bottomOffsetPixels) {
        this.map = map;
        this.offPXL = bottomOffsetPixels;
    }


    public void setMarkerWithInfoWindow(Marker marker, View infoWindow) {
        this.marker = marker;
        this.infoWindow = infoWindow;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean ret = false;


        if (marker != null && marker.isInfoWindowShown() && map != null
                && infoWindow != null) {

            Point point = map.getProjection().toScreenLocation(
                    marker.getPosition());


            MotionEvent copyEv = MotionEvent.obtain(ev);
            copyEv.offsetLocation(-point.x + (infoWindow.getWidth() / 2),
                    -point.y + infoWindow.getHeight() + offPXL);

            ret = infoWindow.dispatchTouchEvent(copyEv);
        }

        return ret || super.dispatchTouchEvent(ev);
    }
}
