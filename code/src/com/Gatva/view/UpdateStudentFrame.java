package com.Gatva.view;

import com.Gatva.controller.StudentController;
import com.Gatva.controller.impl.StudentControllerImpl;
import com.Gatva.model.Student;
import com.Gatva.util.CheckAndHandleUtils;
import com.Gatva.util.ShowWindowUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

/**
 * 修改学生的页面
 */
public class UpdateStudentFrame extends JFrame implements ActionListener {
    // 需要处理父窗口
    private MainMenuFrame mainMenu;
    // 自适应屏幕像素位置
    private int mainWidth;
    private int mainHeight;
    private int font360x;
    private int font460x;

    // 选中的行数
    private int selectRow;
    private JTable table;

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
        底部三个按钮: 修改,重置,取消
     */
    private JButton updateBut;
    private JButton resetBut;
    private JButton cancelBut;

    // 性别按钮组,和相关单选按钮
    private ButtonGroup genderGroup;
    private JRadioButton maleBut, femaleBut;

    // 字体样式
    private Font butFont;
    private Font labelFont;
    private Font oldFont;
    private Font newFont;


    public UpdateStudentFrame(MainMenuFrame mainMenu, JTable table) {
        this.table = table;
        this.mainMenu = mainMenu;
        selectRow = table.getSelectedRow();
        mainWidth = mainMenu.adaptiveWidth;
        mainHeight = mainMenu.adaptiveHeight;
        font360x = (int) (mainWidth * 0.167);
        font460x = (int) (mainWidth * 0.212);
        labelFont = new Font("微软雅黑", Font.BOLD, (int) (mainHeight * 0.028));
        butFont = new Font("黑体", Font.BOLD, (int) (mainHeight * 0.031));
        oldFont = new Font("宋体", Font.PLAIN, (int) (mainHeight * 0.02));
        newFont = new Font("宋体", Font.PLAIN, (int) (mainHeight * 0.02));
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
        updateBut.addActionListener(this);
        resetBut.addActionListener(this);
        cancelBut.addActionListener(this);

        // 叉按钮监听
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UpdateStudentFrame.this.dispose();
                mainMenu.setEnabled(true);
                mainMenu.setVisible(true);
            }
        });

        // 按键ESC监听退出窗口
        this.getRootPane().registerKeyboardAction(e -> {
                    UpdateStudentFrame.this.dispose();
                    mainMenu.setEnabled(true);
                    mainMenu.setVisible(true);
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

        // 2.name相关
        nameLabel = new JLabel("姓名:");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.111), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));

        nameTextField = new JTextField(5);
        nameTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.111), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));

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

        // 5.专业相关
        majorLabel = new JLabel("专业:");
        majorLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.311), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        majorLabel.setFont(labelFont);

        majorTextField = new JTextField(10);
        majorTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.311), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));

        // 6.年龄
        ageLabel = new JLabel("年龄:");
        ageLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.377), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        ageLabel.setFont(labelFont);

        ageTextField = new JTextField(10);
        ageTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.377), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));

        // 7.城市相关
        cityLabel = new JLabel("城市:");
        cityLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.444), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        cityLabel.setFont(labelFont);

        cityTextField = new JTextField(10);
        cityTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.444), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));

        // 8.手机号
        phoneNumLabel = new JLabel("手机:");
        phoneNumLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.511), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        phoneNumLabel.setFont(labelFont);

        phoneNumTextField = new JTextField(20);
        phoneNumTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.511), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));

        // 9.邮箱
        emailLabel = new JLabel("邮箱:");
        emailLabel.setBounds(new Rectangle(font360x, (int) (mainHeight * 0.577), (int) (mainWidth * 0.0463), (int) (mainHeight * 0.03)));
        emailLabel.setFont(labelFont);

        emailTextField = new JTextField(20);
        emailTextField.setBounds(new Rectangle(font460x, (int) (mainHeight * 0.577), (int) (mainWidth * 0.115), (int) (mainHeight * 0.0333)));

        // 设置修改时的初始值
        setOldData();

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
        updateBut = new JButton("修改");
        updateBut.setFont(butFont);
        updateBut.setBounds(new Rectangle((int) (mainWidth * 0.125), (int) (mainHeight * 0.66), (int) (mainWidth * 0.069), (int) (mainHeight * 0.048)));

        resetBut = new JButton("重置");
        resetBut.setFont(butFont);
        resetBut.setBounds(new Rectangle((int) (mainWidth * 0.217), (int) (mainHeight * 0.66), (int) (mainWidth * 0.069), (int) (mainHeight * 0.048)));

        cancelBut = new JButton("取消");
        cancelBut.setFont(butFont);
        cancelBut.setBounds(new Rectangle((int) (mainWidth * 0.310), (int) (mainHeight * 0.66), (int) (mainWidth * 0.069), (int) (mainHeight * 0.048)));

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
                this.add(updateBut);
                this.add(resetBut);
                this.add(cancelBut);
            }

            private AddPanel() {
                draw();
            }
        }
        updateNewData();
        // 回车即点击修改按钮
        this.getRootPane().setDefaultButton(updateBut);
        this.getContentPane().add(new AddPanel());
    }

    /**
     * 监听输入框修改新数据,并且更改样式
     */
    private void updateNewData() {
        // id禁止修改

        // name输入框
        nameTextField.getDocument().addDocumentListener(new MyTextChangeListener(nameTextField));

        // 学校相关
        schoolTextField.getDocument().addDocumentListener(new MyTextChangeListener(schoolTextField));

        // 专业相关
        majorTextField.getDocument().addDocumentListener(new MyTextChangeListener(majorTextField));

        // 年龄相关
        ageTextField.getDocument().addDocumentListener(new MyTextChangeListener(ageTextField));

        // 城市相关
        cityTextField.getDocument().addDocumentListener(new MyTextChangeListener(cityTextField));

        // 手机号相关
        phoneNumTextField.getDocument().addDocumentListener(new MyTextChangeListener(phoneNumTextField));

        // 邮箱相关
        emailTextField.getDocument().addDocumentListener(new MyTextChangeListener(emailTextField));

    }

    /**
     * 鼠标点击事件,完成三个按钮的功能
     * @param e 点击事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取按钮的名字
        String butName = e.getActionCommand();
        // 1.完成修改按钮的功能
        /*
            修改的业务逻辑是:
                获取输入框各种用户输入,经常数据校验后,封装成学生对象
                然后根据id找到数据源中的对应对象,替换它
         */
        if ("修改".equals(butName)) {
            // 开始校验输入框数据,规则如下:
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
            // 获取修改的学生的id
            String targetStuId = (String) table.getValueAt(selectRow, 0);
            // 校验完毕,开始创建对象,并插入
            Student stuInput = new Student(targetStuId, nameInput, genderInput, schoolInput, majorInput, ageInput, cityInput, phoneNumInput, emailInput);
            // 校验修改结果
            switch (studentController.updateStudentByStuId(targetStuId, stuInput)) {
                case -1:
                    ShowWindowUtils.showWarning("系统错误！");
                    return;
                case 0:
                    ShowWindowUtils.showInfo("修改成功！");
                    // 关闭修改窗口,开启主窗口
                    this.dispose();
                    mainMenu.dispose();
                    MainMenuFrame newMain = new MainMenuFrame(-1);
                    StudentListPanel newPanel = new StudentListPanel(newMain);
                    newPanel.setVisible(true);
                    newMain.add(newPanel);
                    return;
                case 1:
                    ShowWindowUtils.showWarning("数据未修改！");
                    return;
                case 2:
                    ShowWindowUtils.showWarning("修改失败！");
                    return;
            }
        }

        // 取消按钮功能
        if ("取消".equals(butName)) {
            UpdateStudentFrame.this.dispose();
            mainMenu.setEnabled(true);
            mainMenu.setVisible(true);
        }

        // 重置按钮功能
        if ("重置".equals(butName)) {
            resetTextField();
        }
    }

    /**
     * 将原数据填入输入框中
     */
    private void setOldData() {
        // id输入框
        idTextField.setFont(oldFont);
        idTextField.setForeground(Color.GRAY);
        idTextField.setText(((String) table.getValueAt(selectRow, 0)));
        idTextField.setEnabled(false);

        // name输入框
        nameTextField.setFont(oldFont);
        nameTextField.setForeground(Color.GRAY);
        nameTextField.setText(((String) table.getValueAt(selectRow, 1)));

        // 性别相关
        femaleBut.setSelected(true);
        if ("男".equals(table.getValueAt(selectRow, 2))) {
            maleBut.setSelected(true);
        }

        // 学校相关
        schoolTextField.setFont(oldFont);
        schoolTextField.setForeground(Color.GRAY);
        schoolTextField.setText(((String) table.getValueAt(selectRow, 3)));

        // 专业相关
        majorTextField.setFont(oldFont);
        majorTextField.setForeground(Color.GRAY);
        majorTextField.setText(((String) table.getValueAt(selectRow, 4)));

        // 年龄相关
        ageTextField.setFont(oldFont);
        ageTextField.setForeground(Color.GRAY);
        ageTextField.setText(((String) table.getValueAt(selectRow, 5)));

        // 城市相关
        cityTextField.setFont(oldFont);
        cityTextField.setForeground(Color.GRAY);
        cityTextField.setText(((String) table.getValueAt(selectRow, 6)));

        // 手机号相关
        phoneNumTextField.setFont(oldFont);
        phoneNumTextField.setForeground(Color.GRAY);
        phoneNumTextField.setText(((String) table.getValueAt(selectRow, 7)));

        // 邮箱相关
        emailTextField.setFont(oldFont);
        emailTextField.setForeground(Color.GRAY);
        emailTextField.setText(((String) table.getValueAt(selectRow, 8)));

    }

    // 重置输入框
    private void resetTextField() {
        this.dispose();
        mainMenu.setVisible(true);
        new UpdateStudentFrame(mainMenu, table).setVisible(true);
    }

    // 自定义文本数据变更监听器
    private class MyTextChangeListener implements DocumentListener {
        private JTextField textField;

        private MyTextChangeListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            textField.setFont(newFont);
            textField.setForeground(Color.RED);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            textField.setFont(newFont);
            textField.setForeground(Color.RED);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            textField.setFont(newFont);
            textField.setForeground(Color.RED);
        }
    }

}
