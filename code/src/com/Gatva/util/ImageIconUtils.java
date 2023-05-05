package com.Gatva.util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 获取图片素材的相关的ImageIcon对象
 *
 * @since 14:14
 * @author wuguidong@cskaoyan.onaliyun.com
 */
public class ImageIconUtils {

    private ImageIconUtils() {
    }

    // 首页图片的URL
    private static URL home = ImageIconUtils.class.getResource("/statics/image.jpg");


    /**
     * 通过自适应屏幕设置图片的分辨率
     * @since 11:21
     * @param adaptiveWidth 自适应的宽度
     * @param adaptiveHeight 自适应的高度
     * @return javax.swing.ImageIcon
     * @author wuguidong@cskaoyan.onaliyun.com
     */
    public static ImageIcon getHome(int adaptiveWidth, int adaptiveHeight) {
        ImageIcon image = new ImageIcon(home);
        double widthProportion;
        double heightProportion;
        if (adaptiveWidth > 1900) {
            // 2K以上屏幕
            widthProportion = 0.87;
            heightProportion = 0.87;
        } else if (adaptiveWidth > 1400) {
            // 1080P
            widthProportion = 0.85;
            heightProportion = 0.85;
        } else {
            // 低分辨率
            widthProportion = 0.84;
            heightProportion = 0.83;
        }
        image.setImage(image.getImage().getScaledInstance((int) (adaptiveWidth * widthProportion), (int) (adaptiveHeight * heightProportion), Image.SCALE_DEFAULT));
        return image;
    }

}
