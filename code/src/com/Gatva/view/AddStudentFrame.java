package com.Gatva.view;




import com.Gatva.controller.StudentController;
import com.Gatva.controller.impl.StudentControllerImpl;
import com.Gatva.model.Student;
import com.Gatva.util.CheckAndHandleUtils;
import com.Gatva.util.ShowWindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 添加学生的界面
 */
public class AddStudentFrame extends JFrame implements ActionListener {

    // 需要处理父窗口
    private MainMenuFrame mainMenu;
    // 自适应屏幕像素位置
    private int mainWidth;
    private int mainHeight;
    private int font360x;
    private int font460x;

    // 依赖学生业务处理
    private StudentController studentController = new StudentControllerImpl();

    /*
        学生信息输入框
     */
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField schoolTextField;
    private JTextField majorTextField;
    private JTextField ageTextField;
    private JTextField cityTextField;
    private JTextField phoneNumTextField;
    private JTextField emailTextField;

    /*
        输入框的提示标签
     */
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel schoolLabel;
    private JLabel majorLabel;
    private JLabel ageLabel;
    private JLabel cityLabel;
    private JLabel phoneNumLabel;
    private JLabel emailLabel;

    /*
        底部三个按钮: 保存,重置,退出
     */
    private JButton saveBut;
    private JButton resetBut;
    private JButton exitBut;

    // 性别按钮组,和相关单选按钮
    private ButtonGroup genderGroup;
    private JRadioButton maleBut, femaleBut;

    // 字体样式
    private Font butFont;
    private Font labelFont;
    private Font inputFont;

    public AddStudentFrame(MainMenuFrame mainMenu) {
        this.mainMenu = mainMenu;
        mainWidth = mainMenu.adaptiveWidth;
        mainHeight = mainMenu.adaptiveHeight;
        font360x = (int) (mainWidth * 0.167);
        font460x = (int) (mainWidth * 0.212);
        labelFont = new Font("微软雅黑", Font.BOLD, (int) (mainHeight * 0.028));
        inputFont = new Font("宋体", Font.BOLD, (int) (mainHeight * 0.02));
        butFont = new Font("黑体", Font.BOLD, (int) (mainHeight * 0.031));
        drawInterface();
        addListener();
        // 设置窗口可见
        this.setVisible(true);
    }

    /**
     * 用于添加各种监听器
     */
    private void addListener() {
        // 按钮监听
        saveBut.addActionListener(this);
        resetBut.addActionListener(this);
        exitBut.addActionListener(this);
        // 叉按钮监听
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AddStudentFrame.this.dispose();
                mainMenu.setVisible(false);
                new MainMenuFrame(0);
            }
        });

        // 按键ESC监听退出窗口
        this.getRootPane().registerKeyboardAction(e -> {
                    AddStudentFrame.this.dispose();
                    mainMenu.setVisible(false);
                    new MainMenuFrame(0);
                }, "command",
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    /**
     * 用于绘制出界面
     */
    private void drawInterface() {
        // 窗口基本设置
        this.setTitle("新增学生信息");
        this.setSize((int) (mainWidth * 0.5), (int) (mainHeight * 0.8));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // 点击右上角的关闭，因为已经监听,所以设置为不作任何事情
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // 开始绘制界面
        // 1.id相关
        idLabel = new JLabel("学号:");
        idLabel.requestFocus();
        idLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.044), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        idLabel.setFont(labelFont);

        idTextField = new JTextField(5);
        idTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.044), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        idTextField.setFont(inputFont);

        // 2.name相关
        nameLabel = new JLabel("姓名:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.111), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));

        nameTextField = new JTextField(5);
        nameTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.111), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        nameTextField.setFont(inputFont);

        // 3.性别相关
        genderLabel = new JLabel("性别:");
        genderLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.177), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        genderLabel.setFont(labelFont);

        genderGroup = new ButtonGroup();
        maleBut = new JRadioButton("男");
        maleBut.setBounds(new Rectangle((int) (mainWidth * 0.22), (int) (mainHeight * 0.177), (int) (mainWidth * 0.046), (int) (mainHeight * 0.033)));
        maleBut.setFont(butFont);

        femaleBut = new JRadioButton("女");
        femaleBut.setBounds(new Rectangle((int) (mainWidth * 0.29), (int) (mainHeight * 0.177), (int) (mainWidth * 0.046), (int) (mainHeight * 0.033)));
        femaleBut.setFont(butFont);
        genderGroup.add(maleBut);
        genderGroup.add(femaleBut);

        // 4.学校相关
        schoolLabel = new JLabel("学校:");
        schoolLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.244), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        schoolLabel.setFont(labelFont);

        schoolTextField = new JTextField(10);
        schoolTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.244), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        schoolTextField.setFont(inputFont);

        // 5.专业相关
        majorLabel = new JLabel("专业:");
        majorLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.311), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        majorLabel.setFont(labelFont);

        majorTextField = new JTextField(10);
        majorTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.311), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        majorTextField.setFont(inputFont);

        // 6.年龄
        ageLabel = new JLabel("年龄:");
        ageLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.377), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        ageLabel.setFont(labelFont);

        ageTextField = new JTextField(10);
        ageTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.377), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        ageTextField.setFont(inputFont);

        // 7.城市相关
        cityLabel = new JLabel("城市:");
        cityLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.444), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        cityLabel.setFont(labelFont);

        cityTextField = new JTextField(10);
        cityTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.444), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        cityTextField.setFont(inputFont);

        // 8.手机号
        phoneNumLabel = new JLabel("手机:");
        phoneNumLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.511), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        phoneNumLabel.setFont(labelFont);

        phoneNumTextField = new JTextField(20);
        phoneNumTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.511), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        phoneNumTextField.setFont(inputFont);

        // 9.邮箱
        emailLabel = new JLabel("邮箱:");
        emailLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.577), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        emailLabel.setFont(labelFont);

        emailTextField = new JTextField(20);
        emailTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.577), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));
        emailTextField.setFont(inputFont);

        // 必填项添加星标
        for (int i = 0; i < 9; i++) {
            // 阶段四时,仅学校和专业非必填
            if (i == 3 || i == 4) {
                continue;
            }
            JLabel starLabel = new JLabel("[必填]");
            starLabel.setFont(new Font("微软雅黑", Font.BOLD, (int) (mainHeight * 0.015)));
            starLabel.setForeground(Color.RED);
            starLabel.setBounds(new Rectangle((int) (mainWidth * 0.141), ((int) (mainHeight * 0.044) + i * (int) (mainHeight * 0.0671)), (int) (mainWidth * 0.04), (int) (mainHeight * 0.03)));
            this.add(starLabel);
        }

        // 10.底部功能按钮
        saveBut = new JButton("保存");
        saveBut.setFont(butFont);
        saveBut.setBounds(new Rectangle((int) (mainWidth * 0.125), (int) (mainHeight * 0.66), (int) (mainWidth * 0.069), (int) (mainHeight * 0.048)));

        resetBut = new JButton("重置");
        resetBut.setFont(butFont);
        resetBut.setBounds(new Rectangle((int) (mainWidth * 0.217), (int) (mainHeight * 0.66), (int) (mainWidth * 0.069), (int) (mainHeight * 0.048)));

        exitBut = new JButton("退出");
        exitBut.setFont(butFont);
        exitBut.setBounds(new Rectangle((int) (mainWidth * 0.310), (int) (mainHeight * 0.66), (int) (mainWidth * 0.069), (int) (mainHeight * 0.048)));

        class AddPanel extends JPanel {
            private void draw() {
                this.setLayout(null);
                this.add(idLabel, null);
                this.add(idTextField, null);
                this.add(nameLabel, null);
                this.add(nameTextField, null);
                this.add(genderLabel, null);
                this.add(maleBut);
                this.add(femaleBut);
                this.add(schoolLabel);
                this.add(schoolTextField);
                this.add(majorLabel);
                this.add(majorTextField);
                this.add(ageLabel);
                this.add(ageTextField);
                this.add(cityLabel);
                this.add(cityTextField);
                this.add(phoneNumLabel);
                this.add(phoneNumTextField);
                this.add(emailLabel);
                this.add(emailTextField);
                this.add(saveBut);
                this.add(resetBut);
                this.add(exitBut);
            }

            private AddPanel() {
                draw();
            }
        }
        // 回车即点击保存按钮
        this.getRootPane().setDefaultButton(saveBut);
        this.getContentPane().add(new AddPanel());
    }


    /**
     * 鼠标点击事件,完成三个按钮的功能
     * @param e 点击事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取按钮的名字
        String butName = e.getActionCommand();
        // 1.完成保存按钮的功能
        if ("保存".equals(butName)) {
            // 开始校验输入框数据,规则如下:
            // 首先检验id
            String idInput = idTextField.getText();
            if (CheckAndHandleUtils.CheckAndHandleStuId(idInput)) {
                return;
            }

            // 校验姓名
            String nameInput = nameTextField.getText();
            if (CheckAndHandleUtils.CheckAndHandleName(nameInput)) {
                return;
            }

            // 校验性别
            // isSelected()，如果按钮被选中返回true
            if (!maleBut.isSelected() && !femaleBut.isSelected()) {
                ShowWindowUtils.showWarning("请选择性别！");
                return;
            }
            // 获取性别
            String genderInput = "女";
            if (maleBut.isSelected()) {
                genderInput = "男";
            }

            // 校验年龄
            String ageInput = ageTextField.getText();
            if (CheckAndHandleUtils.CheckAndHandleAge(ageInput)) {
                return;
            }

            // 校验城市非空
            String cityInput = cityTextField.getText();
            if (CheckAndHandleUtils.CheckAndHandleCity(cityInput)) {
                return;
            }

            // 校验手机号
            String phoneNumInput = phoneNumTextField.getText();
            if (CheckAndHandleUtils.CheckAndHandlePhoneNum(phoneNumInput)) {
                return;
            }

            // 邮箱校验
            String emailInput = emailTextField.getText();
            if (CheckAndHandleUtils.CheckAndHandleEmail(emailInput)) {
                return;
            }

            String schoolInput = schoolTextField.getText();
            String majorInput = majorTextField.getText();
            // 校验完毕,开始创建对象,并插入
            Student stuInput = new Student(idInput, nameInput, genderInput, schoolInput, majorInput, ageInput, cityInput, phoneNumInput, emailInput);
            if (studentController.addStudent(stuInput)) {
                ShowWindowUtils.showInfo("保存成功！");
                resetTextField();
                return;
            }
            ShowWindowUtils.showWarning("空间不足，插入失败！");
            return;
        }

        // 退出按钮功能
        if ("退出".equals(butName)) {
            this.dispose();
            mainMenu.setVisible(false);
            new MainMenuFrame(0);
        }

        // 重置按钮功能
        if ("重置".equals(butName)) {
            resetTextField();
        }
    }

    // 重置输入框
    private void resetTextField() {
        // 清空输入框
        idTextField.setText("");
        nameTextField.setText("");
        genderGroup.clearSelection();
        schoolTextField.setText("");
        majorTextField.setText("");
        ageTextField.setText("");
        phoneNumTextField.setText("");
        cityTextField.setText("");
        emailTextField.setText("");
    }
}
