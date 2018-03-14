package dao;

import javabean.ClassInfo;
import javabean.Student;
import javabean.TeachInfo;
import javabean.Teacher;

import java.util.ArrayList;

public interface DBOperationInterface {
    //学生登录
    public Student stuLogin(String number,String password);

    //教师登录
    public Teacher teachLogin(String number,String password);

    //教师获取所教班级的信息
    public ArrayList<TeachInfo> getTeachInfo(Teacher teacher);

    //教师获取这个班级的学会信息
    public ArrayList<Student> getStuOfClass(int grade,int classn);

    //教师发布公告
    public boolean uploadDeclare(int grade,int classn,String subject,String declare);

    //获取公告内容
    public String getDeclare(int grade,int classn,String subject);

    //获取这个班的科目和科目对应的老师编号
    public ArrayList<ClassInfo> getClassInfo(int grade,int classn);
}
