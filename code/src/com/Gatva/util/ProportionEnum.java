package com.Gatva.util;

/**
 * 设置页面放缩比例
 * @since 11:54
 * @author wuguidong@cskaoyan.onaliyun.com
 */
public enum ProportionEnum {

    // 主界面窗口缩放为全屏幕的75%
    MAIN_RATIO(0.75),LOGIN_RATIO(0.45);;
    double value;

    ProportionEnum(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
