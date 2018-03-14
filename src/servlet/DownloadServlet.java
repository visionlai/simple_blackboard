package servlet;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import javabean.ClassInfo;
import javabean.Student;
import javabean.TeachInfo;
import javabean.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename=request.getParameter("filename");
        if(filename!=null && !filename.contains("..")){
            HttpSession session=request.getSession();
            String number;
            String subject;
            int grade;
            int classn;
            Teacher t=(Teacher) session.getAttribute("teacher");
            TeachInfo ti=(TeachInfo) session.getAttribute("teachinfo");
            if(t!=null && ti!=null){
                number=t.getNumber();
                subject=ti.getSubject();
                grade=ti.getGrade();
                classn=ti.getClassn();
            }
            else{
                Student student=(Student) session.getAttribute("student");
                ClassInfo ci=(ClassInfo) session.getAttribute("classinfo");
                number=ci.getNumber();
                subject=ci.getSubject();
                grade=student.getGrade();
                classn=student.getClassn();
            }
            SmartUpload su=new SmartUpload();
            su.initialize(this.getServletConfig(),request,response);
            su.setContentDisposition(null);
            try{
                String uploaddir = getServletContext().getInitParameter("uploaddir");
                String path=uploaddir+"/"+number+"/"+grade+"/"+classn+"/"+subject+"/homework/";
                su.downloadFile(path+filename);
            }catch (SmartUploadException e){
                e.printStackTrace();
            }
        }
    }
}
