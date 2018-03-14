<%@ page import="javabean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javabean.ClassInfo" %><%--
  Created by IntelliJ IDEA.
  User: vision
  Date: 2017/12/16
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
</head>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="../js/stu_info.js"></script>

<style type="text/css">
    .body {
        border:0;padding:40px 0;
        margin: 0 auto;
        -webkit-box-shadow: 2px 2px 2px 2px rgba(1, 1, 1, .3);
        box-shadow: 2px 2px 2px 2px rgba(1, 1, 1, .3);
        background-color: #f0f0f0;
        filter:alpha(Opacity=60);-moz-opacity:0.8;opacity: 0.8;
        margin-bottom: 80px;
        margin-top:80px;
    }
    .subject_info,.speci_info{
        border:0;padding:5px 0;
        margin: 0 auto;
        -webkit-box-shadow: 2px 2px 2px 2px rgba(1, 1, 1, .3);
        box-shadow: 2px 2px 2px 2px rgba(1, 1, 1, .3);
        background-color: #f0f0f0;
    }
</style>

<body style="background: #fff url(../imag/4.jpg) 50% 0 no-repeat;">
<div class="body container" style="font-size: 16px;width:800px;">
    <div class="head" style="height:50px;">
        <!--学生信息-->
        <div class="stu_info" style="float:right;">
            <a href="../LogoutServlet?action=logout" title="注销">
                <span class="glyphicon glyphicon-log-out" style="margin-top:10px; margin-right: 40px;float:right;"></span>
            </a>
            <h4 style="margin-right: 10px;float:right;">
            <%
                Student student=(Student) session.getAttribute("student");
                ArrayList<ClassInfo> list=(ArrayList<ClassInfo>) session.getAttribute("list");
                out.print(student.getGrade()+"年级"+student.getClassn()+"班&nbsp;&nbsp;"+student.getName()+"("+student.getNumber()+")");
            %></h4>
        </div>
    </div>
    <!--教学信息-->
    <div class="teach_info">
        <!--学科信息-->
        <div  class="subject_info" style="width:200px;float:left;margin-left:30px;">
            <ul style="list-style-type: none;">
                <%
                    for(int i=0;i<list.size();i++){
                        ClassInfo temp=list.get(i);
                        if(temp.getSubject().equals("语文")){
                %>
                <li style="margin-top:15px;">
                    <a href="#" value="number=<%out.print(temp.getNumber());%>&subject=语文" onclick="getSpeci_Info(this)">语文</a>
                </li>
                <%
                            break;
                        }
                    }
                %>
                <%
                    for(int i=0;i<list.size();i++){
                        ClassInfo temp=list.get(i);
                        if(temp.getSubject().equals("数学")){
                %>
                <li style="margin-top:15px;">
                    <a href="#" value="number=<%out.print(temp.getNumber());%>&subject=数学" onclick="getSpeci_Info(this)">数学</a>
                </li>
                <%
                            break;
                        }
                    }
                %>
                <%
                    for(int i=0;i<list.size();i++){
                        ClassInfo temp=list.get(i);
                        if(temp.getSubject().equals("英语")){
                %>
                <li style="margin-top:15px;">
                    <a href="#" value="number=<%out.print(temp.getNumber());%>&subject=英语" onclick="getSpeci_Info(this)">英语</a>
                </li>
                <%
                            break;
                        }
                    }
                %>
            </ul>
        </div>
        <!--具体信息：公告和作业-->
        <div class="speci_info" id="speci_info" style="width:500px;float:left;margin-left:30px;font-size: 16px;">

        </div>
    </div>
</div>
</body>
</html>

