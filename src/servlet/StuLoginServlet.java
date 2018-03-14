package servlet;

import dao.DBOperation;
import javabean.ClassInfo;
import javabean.Student;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class StuLoginServlet extends javax.servlet.http.HttpServlet {
    private Logger logger=Logger.getLogger(this.getClass());
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session=request.getSession();
        if(session.getAttribute("teacher")==null){
            Student s=null;
            String number=request.getParameter("number");
            String password=request.getParameter("password");
            if(number!=null && password!=null){
                DBOperation ope=new DBOperation();
                s=ope.stuLogin(number,password);
                if(s!=null){
                    session.setAttribute("student",s);
                    ArrayList<ClassInfo> list=ope.getClassInfo(s.getGrade(),s.getClassn());
                    session.setAttribute("list",list);
                    response.sendRedirect("jsp/stu_info.jsp");
                }
                else{
                    response.sendRedirect("login.jsp?action=error");
                    logger.error("student "+number+" login fail");
                }
            }
        }
        else{
            response.sendRedirect("jsp/teach_info.jsp");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
