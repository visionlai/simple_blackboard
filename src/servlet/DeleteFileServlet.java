package servlet;

import javabean.TeachInfo;
import javabean.Teacher;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteFileServlet extends HttpServlet {
    private Logger logger=Logger.getLogger(this.getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String uploaddir = getServletContext().getInitParameter("uploaddir");
        Teacher teacher=(Teacher) session.getAttribute("teacher");
        TeachInfo ti=(TeachInfo) session.getAttribute("teachinfo");
        String path=uploaddir+"/"+teacher.getNumber()+"/"+ti.getGrade()+"/"+ti.getClassn()+"/"+ti.getSubject()+"/homework/";
        String filename=request.getParameter("filename");
        if(filename!=null){
            String[] files=filename.split("/");
            boolean success=true;
            for(int i=0;i<files.length;i++){
                File file=new File(path+files[i]);
                success=file.delete();
                if(!success){
                    out.print("alert(\"删除失败\");");
                    logger.error(path+files[i]+"删除失败");
                    break;
                }
            }
            if(success){
                out.print("alert(\"删除成功\");");
            }
        }
        else{
            out.print("alert(\"未选择文件\");");
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
