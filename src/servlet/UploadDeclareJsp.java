package servlet;

import dao.DBOperation;
import javabean.TeachInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UploadDeclareJsp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        ArrayList<TeachInfo> list=(ArrayList<TeachInfo>) session.getAttribute("list");
        TeachInfo temp=null;
        String grade=request.getParameter("grade");
        String classn=request.getParameter("class");
        String subject=request.getParameter("subject");
        if(grade!=null && classn!=null && subject!=null){
            try{
                int g=Integer.parseInt(grade);
                int c=Integer.parseInt(classn);
                for(int i=0;i<list.size();i++){
                    temp=list.get(i);
                    if(temp.getGrade()==g &&temp.getClassn()==c && temp.getSubject().equals(subject)){
                        session.setAttribute("teachinfo",temp);
                        String info="<div style=\"width:450px;height:23px;margin:20px;\"><label style=\"float:right;\">"+g+"年级"+c+"班（"+subject+"）</label></div>";
                        String declare=new DBOperation().getDeclare(temp.getGrade(),temp.getClassn(),temp.getSubject());
                        String old="<div style=\"width:450px;margin:20px;\"><center><label>公告</label></center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+declare+"</div>";
                        String text="<div style=\"width:450px;margin:20px;\"><center><label>发布公告</label></center><center><textarea id=\"upload_declare\" cols=\"40\" rows=\"8\">    \n" +
                                "</textarea></center>\n" +
                                "<center><input type=\"reset\" class=\"btn btn-primary\" value=\"清空\" onclick=\"reset();\">\n" +
                                "<input type=\"submit\" value=\"发布\" class=\"btn btn-primary\" onclick=\"return uploadDeclare();\"></div>" +
                                "</center>";
                        out.print(info+old+text);
                        break;
                    }
                }
            }catch(NumberFormatException e){

            }
        }
        out.close();
    }
}
