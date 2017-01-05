package com.zomzo.softgrouptestwork.ui;

import com.zomzo.softgrouptestwork.domain.RGBHolder;
import com.zomzo.softgrouptestwork.domain.RGBPresenter;

/**
 * Created by zomzo on 03.01.2017.
 */

public class RGBPresenterImpl implements RGBPresenter {
    private RGBDataViewer viewer;
    private RGBHolder holder;
    public RGBPresenterImpl(RGBDataViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void getRGB() {
        holder = new RGBHolder();
        viewer.displayRGB(holder);
    }

    @Override
    public void createHolder(int[] r, int[] g, int[] b) {
        holder = new RGBHolder(r,g,b);
        viewer.displayRGB(holder);
    }
}
