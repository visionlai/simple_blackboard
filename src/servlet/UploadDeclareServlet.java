package servlet;

import dao.DBOperation;
import javabean.TeachInfo;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UploadDeclareServlet extends HttpServlet {
    private Logger logger=Logger.getLogger(this.getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String declare=request.getParameter("declare");
        declare=declare.trim();
        declare=declare.replaceAll("<","&lt;");
        declare=declare.replaceAll(">","&gt;");
        HttpSession session=request.getSession();
        TeachInfo ti=(TeachInfo)session.getAttribute("teachinfo");
        if(declare!=null && declare.length()!=0){
            boolean success=new DBOperation().uploadDeclare(ti.getGrade(),ti.getClassn(),ti.getSubject(),declare);
            if(success){
                out.print("alert(\"发布成功\");");
            }
            else{
                out.print("alert(\"发布失败\");");
            }
        }
        else{
            out.print("alert(\"发布失败\");");
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
