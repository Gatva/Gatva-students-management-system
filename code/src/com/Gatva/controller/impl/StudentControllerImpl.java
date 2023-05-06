package com.Gatva.controller.impl;

import com.Gatva.controller.StudentController;
import com.Gatva.dao.StudentDao;
import com.Gatva.dao.impl.StudentDaoImpl;
import com.Gatva.model.Student;

import java.util.List;

/**
 * 与学生信息相关的,业务操作的具体实现
 */
public class StudentControllerImpl implements StudentController {

    // 业务处理需要获取数据,所以需要依赖数据处理层
    private StudentDao studentDao = new StudentDaoImpl();

    /**
     * Swing GUI表格需要的表格数据data是一个String类型的二维数组,
     *      所以需要封装出二维数组MainMenuFrame
     */
    @Override
    public String[][] getAllTableData() {

        return convert2TwoD(studentDao.getAllTableData());
    }

    /**
     *  Swing GUI表格需要的表格列是一个String数组
     */
    @Override
    public String[] getTableColumns() {
        return studentDao.getTableColumns();
    }

    @Override
    public boolean delStudent(String id) {
        return studentDao.delStudent(id);
    }

    /**
     * 检查id是否重复,true表示重复,否则为不重复
     * @param id 学生id
     * @return boolean
     */
    @Override
    public boolean checkStuIdRepeat(String id) {
        return studentDao.checkStuIdRepeat(id);
    }

    /**
     * 导入一条学生信息，true表示成功，否则为失败
     * @since 14:21
     * @param stu GUI界面输入的学生对象
     * @return boolean
     */
    @Override
    public boolean addStudent(Student stu) {
        return studentDao.addStudent(stu);
    }

    @Override
    public String[][] getResultByStuId(String stuId) {
        return convert2TwoD(studentDao.getStudentByStuId(stuId));
    }

    @Override
    public String[][] getResultByName(String name) {
        return convert2TwoD(studentDao.getStudentByName(name));
    }

    @Override
    public boolean saveDataToFile() {
        return studentDao.saveDataToFile();
    }

    @Override
    public boolean updateCellByStuId(String targetStuId, int targetCol, String newValue) {
        return false;
    }

    @Override
    public int updateStudentByStuId(String targetStuId, Student stu) {
        return 0;
    }

    private String[][] convert2TwoD(List<Student> students){
        String[][] strings=new String[students.size()][];
        int index=0;
        for (Student stu :
                students) {
            strings[index++]=new String[]{stu.getStuId(),stu.getName(),stu.getGender(),stu.getSchool(),
                    stu.getMajor(),stu.getAge(),stu.getCity(),stu.getCity(),stu.getPhone(),stu.getEmail()};
        }
        return strings;
    }
}
