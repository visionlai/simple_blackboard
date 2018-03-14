package servlet;

import javabean.TeachInfo;
import javabean.Teacher;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UploadHomeworkServlet extends HttpServlet {
    private Logger logger=Logger.getLogger(this.getClass());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String uploaddir = getServletContext().getInitParameter("uploaddir");
        Teacher t=(Teacher) session.getAttribute("teacher");
        TeachInfo ti=(TeachInfo)session.getAttribute("teachinfo");
        String path=uploaddir+"/"+t.getNumber()+"/"+ti.getGrade()+"/"+ti.getClassn()+"/"+ti.getSubject()+"/homework/";
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        try {
            List<FileItem> list=upload.parseRequest(request);
            FileItem item = getUploadFileItem(list);
            if(item!=null){
                // 获取文件名
                String filename = item.getName();
                File file=new File(path+filename);
                if(file.exists()){
                    out.print("alert(\"文件已存在\");");
                }
                else{
                    // 真正写到磁盘上
                    item.write(new File(dir, filename)); // 第三方提供的
                    out.print("alert(\"发布成功\");");
                }
            }
            else{
                out.print("alert(\"发布失败\");");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            out.print("alert(\"发布失败\");");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("alert(\"发布失败\");");
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private FileItem getUploadFileItem(List<FileItem> list) {
        for (FileItem fileItem : list) {
            if(!fileItem.isFormField()) {
                return fileItem;
            }
        }
        return null;
    }

}
