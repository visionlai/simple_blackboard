package dao;

import javabean.ClassInfo;
import javabean.Student;
import javabean.TeachInfo;
import javabean.Teacher;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBOperation implements DBOperationInterface {
    private Connection con=null;
    private PreparedStatement pre=null;
    private ResultSet rs=null;
    private Logger logger=Logger.getLogger(this.getClass());

    @Override
    public Student stuLogin(String number, String password){
        Student s=null;
        String sql="SELECT * FROM stu_info WHERE number=? AND password=PASSWORD(?)";
        con=DBConnnection.getConnection();
        try {
            pre=con.prepareStatement(sql);
            pre.setString(1,number);
            pre.setString(2,password);
            rs=pre.executeQuery();
            if(rs.next()){
                s=new Student();
                s.setNumber(rs.getString("number"));
                s.setName(rs.getString("name"));
                s.setPassword(rs.getString("password"));
                s.setGrade(rs.getInt("grade"));
                s.setClassn(rs.getInt("class"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
        return s;
    }

    @Override
    public Teacher teachLogin(String number, String password) {
        Teacher t=null;
        String sql="SELECT * FROM teach_info WHERE number=? AND password=PASSWORD(?)";
        con=DBConnnection.getConnection();
        try {
            pre=con.prepareStatement(sql);
            pre.setString(1,number);
            pre.setString(2,password);
            rs=pre.executeQuery();
            if(rs.next()){
                t=new Teacher();
                t.setNumber(rs.getString("number"));
                t.setName(rs.getString("name"));
                t.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
        return t;
    }

    @Override
    public ArrayList<TeachInfo> getTeachInfo(Teacher teacher) {
        ArrayList<TeachInfo> list=new ArrayList<TeachInfo>();
        String number=teacher.getNumber();
        con=DBConnnection.getConnection();
        String sql_Chinese="SELECT grade,class FROM class_info WHERE Chinese=?";
        String sql_Maths="SELECT grade,class FROM class_info WHERE Maths=?";
        String sql_English="SELECT grade,class FROM class_info WHERE English=?";
        try {
            //查询该老师教的语文班级
            pre=con.prepareStatement(sql_Chinese);
            pre.setString(1,number);
            rs=pre.executeQuery();
            while(rs.next()){
                TeachInfo ti=new TeachInfo();
                ti.setGrade(rs.getInt("grade"));
                ti.setClassn(rs.getInt("class"));
                ti.setSubject("语文");
                list.add(ti);
            }

            //查询该老师教的数学班级
            pre=con.prepareStatement(sql_Maths);
            pre.setString(1,number);
            rs=pre.executeQuery();
            while(rs.next()){
                TeachInfo ti=new TeachInfo();
                ti.setGrade(rs.getInt("grade"));
                ti.setClassn(rs.getInt("class"));
                ti.setSubject("数学");
                list.add(ti);
            }

            //查询该老师教的英语班级
            pre=con.prepareStatement(sql_English);
            pre.setString(1,number);
            rs=pre.executeQuery();
            while(rs.next()){
                TeachInfo ti=new TeachInfo();
                ti.setGrade(rs.getInt("grade"));
                ti.setClassn(rs.getInt("class"));
                ti.setSubject("英语");
                list.add(ti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
        return list;
    }

    @Override
    public ArrayList<Student> getStuOfClass(int grade, int classn) {
        ArrayList<Student> list=new ArrayList<Student>();
        con=DBConnnection.getConnection();
        String sql="SELECT number,name FROM stu_info WHERE grade=? AND class=?";
        try {
            pre=con.prepareStatement(sql);
            pre.setInt(1,grade);
            pre.setInt(2,classn);
            rs=pre.executeQuery();
            while(rs.next()){
                Student s=new Student();
                s.setNumber(rs.getString("number"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
        return list;
    }

    @Override
    public boolean uploadDeclare(int grade, int classn, String subject, String declare) {
        boolean success=false;
        con=DBConnnection.getConnection();
        if(subject.equals("语文")){
            subject="Chinese";
        }
        else if(subject.equals("数学")){
            subject="Maths";
        }
        else
            subject="English";
        String sql="UPDATE class_info SET "+subject+"_public=? WHERE grade=? and class=?";
        try {
            pre=con.prepareStatement(sql);
            pre.setString(1,declare);
            pre.setInt(2,grade);
            pre.setInt(3,classn);
            int s=pre.executeUpdate();
            if(s==1)
                success=true;
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public String getDeclare(int grade, int classn, String subject) {
        con=DBConnnection.getConnection();
        String declare=null;
        if(subject.equals("语文")){
            subject="Chinese";
        }
        else if(subject.equals("数学")){
            subject="Maths"+"";
        }
        else
            subject="English";
        String sql="SELECT "+subject+"_public FROM class_info WHERE grade=? and class=?";
        try {
            pre=con.prepareStatement(sql);
            pre.setInt(1,grade);
            pre.setInt(2,classn);
            rs=pre.executeQuery();
            if(rs.next()){
                declare=rs.getString(subject+"_public");
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return declare;
    }

    @Override
    public ArrayList<ClassInfo> getClassInfo(int grade, int classn) {
        ArrayList<ClassInfo> list=new ArrayList<ClassInfo>();
        con=DBConnnection.getConnection();
        String sql="SELECT Chinese,Maths,English FROM class_info WHERE grade=? and class=?";
        try {
            pre=con.prepareStatement(sql);
            pre.setInt(1,grade);
            pre.setInt(2,classn);
            rs=pre.executeQuery();
            if(rs.next()){
                ClassInfo ci=new ClassInfo();
                ci.setNumber(rs.getString("Chinese"));
                ci.setSubject("语文");
                list.add(ci);
                ci=new ClassInfo();
                ci.setNumber(rs.getString("Maths"));
                ci.setSubject("数学");
                list.add(ci);
                ci=new ClassInfo();
                ci.setNumber(rs.getString("English"));
                ci.setSubject("英语");
                list.add(ci);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
        return list;
    }

    private void close(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pre!=null){
            try {
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
