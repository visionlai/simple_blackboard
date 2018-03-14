<%@ page import="javabean.Student" %>
<%@ page import="javabean.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: vision
  Date: 2017/12/28
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link href="css/login.css" rel="stylesheet">

<body>
<%
    Student student=(Student) session.getAttribute("student");
    if(student!=null)
        response.sendRedirect("jsp/stu_info.jsp");

    Teacher teacher=(Teacher) session.getAttribute("teacher");
    if(teacher!=null)
        response.sendRedirect("jsp/teach_info.jsp");

    String action=request.getParameter("action");
    if(action!=null && action.equals("error")){
        out.print("<script>alert(\"账号或密码错误\");</script>");
    }
%>
<div class="login" style="margin-top: 100px;">
    <!--转换-->
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">学生登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">老师登录</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>

    <!--学生登录-->
    <div class="web_login" id="stu_login">
        <div class="login_form">
            <form class="form-horizontal"role="form" method="post" action="StuLoginServlet">
                <div class="form-group">
                    <label for="stu_number" class="col-sm-4 control-label"></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="stu_number" name="number" placeholder="学号" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="stu_password" class="col-sm-4 control-label"></label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="stu_password" name="password" placeholder="密码" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-8 col-sm-10">
                        <button type="submit" class="btn btn-default">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!--教师登录-->
    <div class="web_login" id="teach_login">
        <div class="login_form">
            <form class="form-horizontal" role="form" method="post" action="TeachLoginServlet">
                <div class="form-group">
                    <label for="teach_number" class="col-sm-4 control-label"></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="teach_number" name="number" placeholder="编号" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="teach_password" class="col-sm-4 control-label"></label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password" id="teach_password" placeholder="密码" required/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-8 col-sm-10">
                        <button type="submit" class="btn btn-default">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
