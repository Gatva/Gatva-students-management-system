package com.Gatva.dao;

import com.Gatva.model.Student;

import java.util.List;

/**
 * 与Student学生数据相关的，数据操作的接口
 */
public interface StudentDao {

    // 获取表格列数据
    String[] getTableColumns();

    List<Student> getAllTableData();
    // 检查学号重复性
    boolean checkStuIdRepeat(String id);

    // 通过id删除学生
    boolean delStudent(String id);

    // 插入一条学生信息
    boolean addStudent(Student stu);


    // 通过stuId查询学生
    List<Student> getStudentByStuId(String stuId);

    // 通过name查询学生
    List<Student> getStudentByName(String name);

    // 将内存中的学生数组保存到文件中
    boolean saveDataToFile();
}
