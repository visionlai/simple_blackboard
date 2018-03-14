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
import java.util.ArrayList;

public class UploadHomeworkJsp extends HttpServlet {
    private Logger logger=Logger.getLogger(this.getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        ArrayList<TeachInfo> list=(ArrayList<TeachInfo>) session.getAttribute("list");
        Teacher t=(Teacher) session.getAttribute("teacher");
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
                    if(temp.getGrade()==g && temp.getClassn()==c && temp.getSubject().equals(subject)){
                        session.setAttribute("teachinfo",temp);
                        String uploaddir = getServletContext().getInitParameter("uploaddir");
                        String path=uploaddir+"/"+t.getNumber()+"/"+temp.getGrade()+"/"+temp.getClassn()+"/"+temp.getSubject()+"/homework/";
                        String info="<div style=\"width:450px;height:23px;margin:20px;\"><label style=\"float:right;\">"+g+"年级"+c+"班（"+subject+"）</label></div>";
                        out.print(info);
                        String form="<div style=\"width:450px;margin:20px;height:50px;\">\n" +
                                "    <center><label>发布作业</label></center>\n" +
                                "    <form name=\"homework\">\n" +
                                "        <input id=\"homework\" type=\"file\" style=\"float:left;\"/>\n" +
                                "    <input type=\"button\" id=\"upload\" class=\"btn btn-primary\" value=\"发布\" style=\"float:left;\" onclick=\"uploadHomework()\">\n" +
                                "    </form>\n" +
                                "</div>";
                        out.print(form);
                        File dir=new File(path);
                        String[] files=dir.list();
                        if(files!=null && files.length!=0){
                            out.print("<div style=\"width:450px;margin:20px;\"><center><label>作业</label></center><ul style=\"list-style-type: none;\">");
                            for(int j=0;j<files.length;j++){
                                out.print("<li>");
                                out.print("<input type=\"checkbox\" name=\"delete\" value=\""+files[j]+"\">&nbsp;&nbsp;&nbsp;<a href=\"../DownloadServlet?filename="+files[j]+"\">"+files[j]+"</a>");
                                out.print("</li>");
                            }
                            out.print("<input style=\"margin-top:10px;\" type=\"button\" class=\"btn btn-primary\" value=\"删除\" onclick=\"deleteFile()\">");
                            out.print("</ul></div>");
                        }
                        break;
                    }
                }
            }catch(NumberFormatException e){
                logger.error(t.getNumber()+"NumberFormatException");
            }
        }
        out.close();
    }
}
