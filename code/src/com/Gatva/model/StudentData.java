package com.Gatva.model;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 模拟学生数据源
 */
public class StudentData {
    public static List<Student> STUDENTS=new ArrayList<>();
    public static final String[] COLUMNS;

    static {
        // 初始化
        init();
        // 表格列数据
        COLUMNS = new String[]{"学号", "姓名", "性别", "学校", "专业", "年龄", "城市", "手机号", "电子邮箱"};
    }

    /**
     * 初始化数据源数据
     */
    private static void init() {
        // 创建对象流
        ObjectInputStream ois=null;
        Student[] students=null;
        try {
            File inputFile = new File("students.txt");

            // 文件中暂时没有数据
            if(inputFile.length()==0){
                return;
            }

            ois=new ObjectInputStream(new FileInputStream(inputFile));
            // 反序列化
            STUDENTS=(List<Student>) ois.readObject();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
