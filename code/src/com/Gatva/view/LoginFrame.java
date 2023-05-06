package com.Gatva.view;

import com.Gatva.controller.impl.UserControllerImpl;
import com.Gatva.model.User;
import com.Gatva.util.ProportionEnum;
import com.Gatva.util.SelfAdaptiveScreen;;

import com.Gatva.controller.impl.UserControllerImpl;
import com.Gatva.model.User;
import com.Gatva.util.ProportionEnum;
import com.Gatva.util.SelfAdaptiveScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 学生管理系统-登录页面,首页
 */
public class LoginFrame extends JFrame implements ActionListener {

    // 登陆所需按钮和输入框
    private JButton loginBut;
    private JButton exitBut;
    private JTextField usernameText;
    private JTextField passwordText;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel titleLabel;

    // 自适应屏幕分辨率后的宽高
    private int adaptiveWidth = (int) SelfAdaptiveScreen.getAdaptiveWidth(this, ProportionEnum.LOGIN_RATIO);
    private int adaptiveHeight = SelfAdaptiveScreen.getAdaptiveHeight(this, ProportionEnum.LOGIN_RATIO);
    private int font50y = (int) (adaptiveHeight * 0.08);
    private int font330x = (int) (adaptiveWidth * 0.33);
    private int font430x = (int) (adaptiveWidth * 0.43);
    private int font450y = (int) (adaptiveHeight * 0.714);

    // 依赖业务处理对象
    private final UserControllerImpl userController = new UserControllerImpl();

    public LoginFrame() {
        init();
    }

    /**
     * 用于初始化登陆界面
     */
    private void init() {
        // 1.界面基本设置
        // 设置背景为白色
        this.setForeground(Color.GRAY);
        // 设置窗口大小固定不可变
        this.setResizable(false);
        // 设置窗口大小尺寸,单位是像素

        // 自适应屏幕设置界面大小
        this.setSize(adaptiveWidth, adaptiveHeight);
        // 设置窗口标题
        this.setTitle("Gatva-学生信息管理系统");
        // 设置窗口居中
        this.setLocationRelativeTo(null);
        // 设置关闭窗口即关闭应用
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2.开始绘制元素
        // 设置页面标题,即登录标签
        titleLabel = new JLabel("Gatva-学生信息管理系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, font50y));
        titleLabel.setBounds(((int) (adaptiveWidth * 0.25)), (int) (adaptiveHeight * 0.1), (int) (adaptiveWidth * 0.65), font50y + 3);
        titleLabel.setForeground(Color.DARK_GRAY);

        Font labelFont = new Font("微软雅黑", Font.BOLD, font50y / 2);
        // 用户名输入框的标签
        usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(new Rectangle(font330x, (int) (adaptiveHeight * 0.37), (int) (adaptiveWidth * 0.1), font50y / 2 + 2));
        usernameLabel.setFont(labelFont);

        // 密码输入框的标签
        passwordLabel = new JLabel("密\u0020\u0020\u0020码：");
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(new Rectangle(font330x, (int) (adaptiveHeight * 0.47), (int) (adaptiveWidth * 0.1), font50y / 2 + 2));

        // 用户名输入框
        Font inputFont = new Font("宋体", Font.PLAIN, font50y / 2 + 5);
        usernameText = new JTextField(30);
        usernameText.setBounds(new Rectangle(font430x, (int) (adaptiveHeight * 0.357), (int) (adaptiveWidth * 0.235), (int) (adaptiveHeight * 0.067)));
        usernameText.setFont(inputFont);

        // 密码输入框
        passwordText = new JPasswordField(30);
        passwordText.setBounds(new Rectangle(font430x, (int) (adaptiveHeight * 0.468), (int) (adaptiveWidth * 0.235), (int) (adaptiveHeight * 0.067)));
        passwordText.setFont(inputFont);

        // 3.设置登录按钮
        loginBut = new JButton("登陆");
        loginBut.setBounds(new Rectangle((int) (adaptiveWidth * 0.385), font450y, (int) (adaptiveWidth * 0.1), font50y));
        loginBut.setFont(new Font("微软雅黑", Font.BOLD, (int) (adaptiveHeight * 0.03)));
        // 设置回车即相当于点击按钮,即回车登录
        this.getRootPane().setDefaultButton(loginBut);

        // 6.设置退出按钮
        exitBut = new JButton("退出");
        exitBut.setFont(new Font("微软雅黑", Font.BOLD, (int) (adaptiveHeight * 0.03)));
        exitBut.setBounds(new Rectangle((int) (adaptiveWidth * 0.54), font450y, (int) (adaptiveWidth * 0.1), font50y));

        /*
        7.为了简化代码,避免init方法过于臃肿,所以抽取出一个独立的内部类,即登录Panel画布类。
        Java的GUI图形界面很像画画，画布中“画上”元素，然后将画图放进界面的“框”中。
        该类就是一个画布，里面会有各种元素。
        */
        class LoginPanel extends JPanel {
            /**
             * 初始化画板类对象
             */
            private void draw() {
                this.setLayout(null);
                this.add(titleLabel);
                this.add(usernameLabel);
                this.add(passwordLabel);
                this.add(loginBut);
                this.add(exitBut, null);
                this.add(usernameText, null);
                this.add(passwordText, null);
            }

            private LoginPanel() {
                draw();
            }
        }
        // 8.画布绘制好了,添加进当前界面即可
        this.getContentPane().add(new LoginPanel());

        // 接下来开始进行和键盘输入监听相关的操作
        // 1.监听用户名输入框
        userNameKeyListener();
        passwordKeyListener();

        this.setVisible(true);
        // 添加监控生效
        usernameText.addActionListener(this);
        passwordText.addActionListener(this);
        loginBut.addActionListener(this);
        exitBut.addActionListener(this);
    }


    /**
     * 两个按钮的点击事件监控
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBut) {
            // 点击登陆按钮触发登陆判断
            loginJudge();
            return;
        }
        if (e.getSource() == exitBut) {
            System.exit(0);
        }
    }

    /**
     * 密码输入框键盘监听事件
     */
    private void passwordKeyListener() {
        // 密码框键盘录入监听回车，表示登录
        passwordText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    loginJudge();
                }
            }
        });
    }


    /**
     * 用户名输入框键盘录入监听,目的不是为了接收键盘录入,而是为了监听回车键
     * 在用户名输入框中,只要完成一次敲击回车,就将焦点转移到密码输入框,方便用户输入密码
     */
    private void userNameKeyListener() {
        usernameText.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    passwordText.requestFocus();
                }
            }
        });
    }

    /**
     * 判断用户名和密码是否合法，并完成对应操作
     *  1.账户密码正确，则允许登陆，跳转到主页面
     *  2.错误，则提示错误，要求重新输入
     */
    private void loginJudge() {
        String usernameInput = usernameText.getText();
        String passwordInput = passwordText.getText();
        // 登录用户对象,用来判断用户名,密码是否正确
        User user = new User(usernameInput, passwordInput);
        switch (userController.judgeLogin(user)) {
            case 0:
                // 登陆成功，跳转到主页面，菜单页面
                setVisible(false);
                new MainMenuFrame(0);
                break;
            case 1:
                // 登录失败，用户名不存在
                JOptionPane.showMessageDialog(null, "用户名不存在", "消息提示", JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                // 登录失败，密码错误
                JOptionPane.showMessageDialog(null, "密码错误", "消息提示", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
