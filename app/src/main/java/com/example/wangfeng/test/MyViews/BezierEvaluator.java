package com.example.wangfeng.test.MyViews;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by fengye on 2017/8/9.
 * email 1040441325@qq.com
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF p1, p2;

    public BezierEvaluator(PointF p1, PointF p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF point = new PointF();
        point.x =bezier(fraction,startValue.x,p1.x,p2.x,endValue.x);
        point.y =bezier(fraction,startValue.y,p1.y,p2.y,endValue.y);
        return point;
    }

    /**
     *
     * @param t
     * @param p0
     * @param p1
     * @param p2
     * @param p3
     * @return 贝塞尔方程(见README.md)
     */
    private float bezier(float t, float p0, float p1, float p2, float p3) {
        return p0 * (1 - t) * (1 - t) * (1 - t)
                + 3 * p1 * t * (1 - t) * (1 - t)
                + 3 * p2 * t * t * (1 - t)
                + p3 * t * t * t;
    }
}
