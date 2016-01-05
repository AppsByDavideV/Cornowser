package io.xdevs23.cornowser.browser.browser.modules;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.RelativeLayout;

import org.xdevs23.ui.utils.BarColors;

import io.xdevs23.cornowser.browser.CornBrowser;
import io.xdevs23.cornowser.browser.browser.xwalk.CrunchyWalkView;

public class WebThemeHelper {

    private static int currentColor = 0;

    public static void setWebThemeColor(String color, RelativeLayout omnibox, Window window) {
        if(!color.contains("#")) return;
        setWebThemeColor(Color.parseColor(color), omnibox, window);
    }

    public static void setWebThemeColor(int color, RelativeLayout omnibox, Window window) {
        if(currentColor == 0) currentColor = ((ColorDrawable)omnibox.getBackground()).getColor();
        omnibox.setBackgroundColor(color);
        BarColors.updateBarsColor(color, window);
    }

    public static void resetWebThemeColor(RelativeLayout omnibox) {
        if(currentColor != 0) omnibox.setBackgroundColor(currentColor);
        CornBrowser.resetBarColor();
    }

    public static void tintNow(CrunchyWalkView view) {
        if(!view.getUrl().toLowerCase().contains("cornhandler://"))
            CornHandler.sendJSRequestWithCallback(view, CornHandler.CornRequests.setWebThemeColor.name(),
                "setWebThemeColor:\" + document.querySelector(\"meta[name='theme-color']\").getAttribute(\"content\").toString() + \"");
    }

}