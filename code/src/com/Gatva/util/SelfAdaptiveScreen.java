package com.Gatva.util;


import java.awt.*;

/**
 * 自适应屏幕获取高度和宽度,工具类
 * @since 13:59
 * @author wuguidong@cskaoyan.onaliyun.com
 */
public class SelfAdaptiveScreen {
    private SelfAdaptiveScreen() {
    }

    public static int getAdaptiveWidth(Frame frame, ProportionEnum ps) {
        // 获得窗体工具包
        Toolkit toolkit = frame.getToolkit();
        // 获取屏幕大小
        Dimension screenSize = toolkit.getScreenSize();
        return (int) (screenSize.width * ps.getValue());
    }

    public static int getAdaptiveHeight(Frame frame, ProportionEnum ps) {
        // 获得窗体工具包
        Toolkit toolkit = frame.getToolkit();
        // 获取屏幕大小
        Dimension screenSize = toolkit.getScreenSize();
        return (int) (screenSize.height * ps.getValue());
    }

    public static void setAdaptiveSize(Frame frame, ProportionEnum ps) {
        // 设置窗体大小
        frame.setSize(getAdaptiveWidth(frame, ps), getAdaptiveHeight(frame, ps));
        // 设置窗口居中
        frame.setLocationRelativeTo(null);
        // 设置窗口大小固定不可变
        frame.setResizable(false);
    }
}
