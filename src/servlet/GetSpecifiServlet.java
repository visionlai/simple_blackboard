package servlet;

import dao.DBOperation;
import javabean.ClassInfo;
import javabean.Student;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GetSpecifiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();
        ArrayList<ClassInfo> list=(ArrayList<ClassInfo>) session.getAttribute("list");
        Student student=(Student) session.getAttribute("student");
        String number=request.getParameter("number");
        String subject=request.getParameter("subject");
        if(number!=null && subject!=null){
            for(int i=0;i<list.size();i++){
                ClassInfo temp=list.get(i);
                if(temp.getNumber().equals(number) && temp.getSubject().equals(subject)){
                    session.setAttribute("classinfo",temp);
                    String info="<div style=\"width:450px;height:23px;margin:20px;margin-bottom:0;\"><label style=\"float:right;\">"+subject+"</label></div>";
                    out.print(info);
                    int g=student.getGrade();
                    int c=student.getClassn();
                    String declare=new DBOperation().getDeclare(g,c,subject);
                    out.print("<div class=\"declare\" style=\"width:500px;padding:15px;\">\n<center><label>公告</label></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + declare +
                            "    \n" +
                            "</div>");
                    String path="WEB-INF/"+number+"/"+g+"/"+c+"/"+subject+"/homework/";
                    File dir=new File(path);
                    String[] files=dir.list();
                    if(files!=null && files.length!=0){
                        out.print("<div style=\"width:500px;margin-top:30px;\"><center><label>作业</label></center><ul style=\"list-style-type: none\">");
                        for(int j=0;j<files.length;j++){
                            out.print("<li>");
                            out.print("<a href=\"../DownloadServlet?filename="+files[j]+"\">"+files[j]+"</a>");
                            out.print("</li>");
                        }
                        out.print("</ul></div>");
                    }
                    out.close();
                    break;
                }
            }
        }
    }
}
