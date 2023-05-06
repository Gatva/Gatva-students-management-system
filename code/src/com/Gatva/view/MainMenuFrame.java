package com.Gatva.view;

import com.Gatva.controller.StudentController;
import com.Gatva.controller.impl.StudentControllerImpl;
import com.Gatva.util.ImageIconUtils;
import com.Gatva.util.ProportionEnum;
import com.Gatva.util.SelfAdaptiveScreen;
import com.Gatva.util.ShowWindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 学生管理系统-登陆成功后，主页面，菜单页面
 *
 * @since 12:01
 * @author wuguidong@cskaoyan.onaliyun.com
 */
public class MainMenuFrame extends JFrame implements ActionListener {

    private JPanel centerPanel;
    // 图片和label
    private ImageIcon logoIcon;
    private JLabel centerLabel;

    // 自适应屏幕分辨率后的宽高
    public int adaptiveWidth = SelfAdaptiveScreen.getAdaptiveWidth(this, ProportionEnum.MAIN_RATIO);
    public int adaptiveHeight = SelfAdaptiveScreen.getAdaptiveHeight(this, ProportionEnum.MAIN_RATIO);


    private StudentController studentController=new StudentControllerImpl();
    /**
     * 当mark值为0时，表示需要欢迎界面logo。其余数值传入则不需要。
     * @since 11:24
     * @param mark 指示是否创建欢迎界面logo
     */
    public MainMenuFrame(int mark) {
        init(mark);
    }

    private void init(int mark) {
        // 主界面自适应屏幕
        SelfAdaptiveScreen.setAdaptiveSize(this, ProportionEnum.MAIN_RATIO);
        // 1.界面
        // 设置背景为白色
        this.setForeground(Color.GRAY);
        // 设置窗口标题
        this.setTitle("Gatva-学生信息管理系统");
        // 设置关闭窗口即关闭应用
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2.添加欢迎语标签
        // 欢迎栏
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("欢迎光临学生管理系统");
        welcomeLabel.setFont(new Font("黑体", Font.BOLD, (int) (adaptiveHeight * 0.04)));
        welcomePanel.add(welcomeLabel);
        this.add(welcomePanel, BorderLayout.NORTH);

        // 3.添加功能按钮
        // 功能按钮
        JPanel menuPanel = new JPanel();
        GridLayout gl = new GridLayout(4, 1);
        gl.setVgap(0);
        menuPanel.setLayout(gl);
        String[] menus = {"查询学生", "新增学生", "保存数据至文件", "退出程序"};
        JButton[] menusButs = new JButton[menus.length];
        for (int i = 0; i < menus.length; i++) {
            menusButs[i] = new JButton(menus[i]);
            menusButs[i].setFont(new Font("微软雅黑", Font.BOLD, (int) (adaptiveHeight * 0.018)));
            // 按钮添加窗口监听
            menusButs[i].addActionListener(this);
            menuPanel.add(menusButs[i]);
        }
        this.add(menuPanel, BorderLayout.WEST);

        // 标志0代表第一次进入,需要欢迎界面
        if (mark == 0) {
            centerPanel = new JPanel();
            logoIcon = ImageIconUtils.getHome(adaptiveWidth, adaptiveHeight);
            centerLabel = new JLabel(logoIcon);
            centerPanel.setVisible(true);
            centerPanel.add(centerLabel);
            this.add(centerPanel, BorderLayout.CENTER);
        }

        /*
            开始实现左侧按钮功能
         */
        // 按钮1: 查询学生
        menusButs[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击查询,先关闭欢迎logo
                if (centerPanel != null) {
                    centerPanel.setVisible(false);
                }
                StudentListPanel studentListPanel = new StudentListPanel(MainMenuFrame.this);
                MainMenuFrame.this.add(studentListPanel, BorderLayout.CENTER);
            }
        });

        // 按钮2: 新增学生
        menusButs[1].addActionListener(e -> {
            MainMenuFrame.this.setVisible(false);
            MainMenuFrame newMain = new MainMenuFrame(0);
            AddStudentFrame add = new AddStudentFrame(newMain);
            newMain.setEnabled(false);
        });

        // 按钮3：保存至文件
        menusButs[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击保存执行的操作
                boolean flag = studentController.saveDataToFile();
                if (flag) {
                    ShowWindowUtils.showInfo("保存成功!");
                    return;
                }
                ShowWindowUtils.showWarning("保存失败!");
            }
        });

        // 按钮4: 退出系统
        menusButs[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 直接关闭虚拟机,退出程序
                System.exit(1);
            }
        });


        // 启动窗口
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    }

}
