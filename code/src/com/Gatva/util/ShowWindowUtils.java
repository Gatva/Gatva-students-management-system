package com.Gatva.util;

import javax.swing.*;

/**
 * 弹窗工具类
 */
public class ShowWindowUtils {

    private ShowWindowUtils() {
    }

    /*
      弹窗目前分为两类:
        1.错误警示弹窗
        2.一般信息弹窗
     */
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "错误提示", JOptionPane.WARNING_MESSAGE);
    }

    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "信息提示", JOptionPane.INFORMATION_MESSAGE);
    }

}
