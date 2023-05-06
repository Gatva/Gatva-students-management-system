package com.Gatva.dao.impl;

import com.Gatva.dao.StudentDao;
import com.Gatva.model.Student;
import com.Gatva.model.StudentData;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 与学生Student相关的，所有数据处理都在该类下进行
 */
public class StudentDaoImpl implements StudentDao {

    // 从数据源获取数据
    private List<Student> students = StudentData.STUDENTS;
    private String[] columns = StudentData.COLUMNS;

    @Override
    public String[] getTableColumns() {
        return columns;
    }

    @Override
    public List<Student> getAllTableData() {

        return students;

    }

    /**
     * 判断id是否和已有数据发生重复
     * @param id 不为空
     * @return 是否发生重复
     */
    @Override
    public boolean checkStuIdRepeat(String id) {

        for (Student stu :
                students) {
            if(id.equals(stu.getStuId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delStudent(String id) {

        Iterator<Student> iterator=students.listIterator();
        while (iterator.hasNext()){
            Student next=iterator.next();
            if(next.getStuId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addStudent(Student stu) {
        students.add(stu);
        return true;

    }

    @Override
    public List<Student> getStudentByStuId(String stuId) {
        List<Student> stuByStuId=new ArrayList<>();
        for(Student student:students){
            if(stuId.equals(student.getStuId())){
                stuByStuId.add(student);
            }
        }
        return stuByStuId;
    }

    @Override
    public List<Student> getStudentByName(String name) {

        List<Student> stusByName=new ArrayList<>();

        for(Student student:students){
            if(student.getName().equals(name)){
                stusByName.add(student);
            }
        }

        return stusByName;
    }

    @Override
    public boolean saveDataToFile() {
        if(students.size()==0){
            return false;
        }
        ObjectOutputStream oos=null;
        try {
            String outputPath = "students.txt";
            oos=new ObjectOutputStream(new FileOutputStream(outputPath));

           oos.writeObject(students);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return true;
    }

}
