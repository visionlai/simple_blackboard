package servlet;

import dao.DBOperation;
import javabean.TeachInfo;
import javabean.Teacher;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class TeachLoginServlet extends HttpServlet {
    private Logger logger=Logger.getLogger(this.getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        if(session.getAttribute("student")==null){
            DBOperation op=new DBOperation();
            Teacher t=null;
            ArrayList<TeachInfo> list=null;
            String number=request.getParameter("number");
            String password=request.getParameter("password");
            if(number!=null && password!=null){
                t=op.teachLogin(number,password);
                if(t!=null){
                    list=op.getTeachInfo(t);
                    session.setAttribute("teacher",t);
                    session.setAttribute("list",list);
                    response.sendRedirect("jsp/teach_info.jsp");
                }
                else{
                    response.sendRedirect("login.jsp?action=error");
                    logger.error("teacher "+number+" login fail");
                }
            }
        }
        else{
            response.sendRedirect("jsp/stu_info.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
