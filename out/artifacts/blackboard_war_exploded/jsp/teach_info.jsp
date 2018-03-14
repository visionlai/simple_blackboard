<%@ page import="javabean.Teacher" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javabean.TeachInfo" %><%--
  Created by IntelliJ IDEA.
  User: vision
  Date: 2017/12/17
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息</title>
</head>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%--<script src="../js/teach_info.js"></script>--%>

<script type="text/javascript">
    var link;

    function getDeclareJsp(t) {
        var xmlhttp;
        link=t;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("info").innerHTML = xmlhttp.responseText;
            }
        }
        var d=t.getAttribute('value');
        xmlhttp.open("GET", "../UploadDeclareJsp?"+d, true);
        xmlhttp.send();
    }

    function uploadDeclare() {
        var xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var text=xmlhttp.responseText;
                eval(text);
                link.click(this);
            }
        }
        var d=document.getElementById("upload_declare").value;
        d=d.trim();
        if(d.length!=0){
            xmlhttp.open("POST", "../UploadDeclareServlet", true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("declare="+d);
        }
        else{
            alert("内容不能为空");
        }
        return false;
    }

    function getHomeWorkJsp(t) {
        var xmlhttp;
        link=t;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("info").innerHTML = xmlhttp.responseText;
            }
        }
        var d=t.getAttribute('value');
        xmlhttp.open("GET", "../UploadHomeworkJsp?"+d, true);
        xmlhttp.send();
    }

    function uploadHomework() {
        var formData = new FormData();
        if(document.getElementById("homework").files[0]==null){
            alert("未选择文件");
            return;
        }
        formData.append("homework",document.getElementById("homework").files[0]);
        var xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var text=xmlhttp.responseText;
                eval(text);
                link.click(this);
            }
        }
        xmlhttp.open("POST","../UploadHomeworkServlet",true);
        xmlhttp.send(formData);
    }

    function reset() {
        document.getElementById("upload_declare").value="    ";
    }

    function deleteFile() {
        var deletefile=document.getElementsByName("delete");
        var filename="";
        for(var i=0;i<deletefile.length;i++){
            if(deletefile[i].checked){
                filename=filename+deletefile[i].value+"/";
            }
        }
        if(filename.length==0){
            alert("未选择文件");
            return false;
        }

        var xmlhttp;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var text=xmlhttp.responseText;
                eval(text);
                link.click(this);
            }
        }
        xmlhttp.open("POST", "../DeleteFileServlet", true);
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send("filename="+filename);
    }
</script>

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
    .class,.info{
        border:0;padding:5px 0;
        margin: 0 auto;
        -webkit-box-shadow: 2px 2px 2px 2px rgba(1, 1, 1, .3);
        box-shadow: 2px 2px 2px 2px rgba(1, 1, 1, .3);
        background-color: #f0f0f0;
    }
</style>

<body style="background: #fff url(../imag/4.jpg) 50% 0 no-repeat;">
<div class="body container" id="body" style="width:800px;background-color: #f0f0f0;">
    <div class="teach_info_box" id="teach_info_box" style="height: 50px;">
        <!--老师信息-->
        <div class="teach_info" id="teach_info" style="float:right;">
            <a href="../LogoutServlet?action=logout" title="注销">
                <span class="glyphicon glyphicon-log-out" style="margin-top:12px; margin-right: 40px;float:right;"></span>
            </a>
            <h4 style="margin-right: 10px;float:right;">
            <%
                Teacher teacher=(Teacher) session.getAttribute("teacher");
                if(teacher!=null) {
                    out.print(teacher.getName() + "(" + teacher.getNumber() + ")");
                }
            %></h4>
        </div>
    </div>

    <!--老师管理班级的信息-->
    <div class="class_info" id="class_info" >
        <!--班级信息-->
        <div class="class" id="class" style="width:200px;float:left;margin-left:30px;">
            <%
                ArrayList<TeachInfo> list=(ArrayList<TeachInfo>) session.getAttribute("list");
                if(list!=null){
                    TeachInfo ti=null;
                    int grade;
                    int classn;
                    String subject;
            %>

            <%
                for(int i=0;i<list.size();i++){
                    ti=list.get(i);
                    grade=ti.getGrade();
                    classn=ti.getClassn();
                    subject=ti.getSubject();
            %>

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion"
                                       href="#collapse<% out.print(i);%>">
                                        <% out.print(grade+"年级");out.print(classn+"班");out.print("（"+subject+"）");%>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse<% out.print(i);%>" class="panel-collapse collapse in">
                                <div class="container-fluid" style="margin-bottom: 8px">
                                    <div class="row clearfix">
                                        <div class="col-md-12 column">
                                            <a class="navbar-brand" href="#" value="grade=<%out.print(grade);%>&class=<%out.print(classn);%>&subject=<%out.print(subject);%>" onclick="getDeclareJsp(this)">发布公告</a>
                                        </div>
                                    </div>

                                    <div class="row clearfix">
                                        <div class="col-md-12 column">
                                            <a class="navbar-brand" href="#" value="grade=<%out.print(grade);%>&class=<%out.print(classn);%>&subject=<%out.print(subject);%>" onclick="getHomeWorkJsp(this)">发布作业</a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <%
                    }
                %>

            <%
                }
            %>
        </div>

        <!--管理信息-->
        <div class="info" id="info" style="width:500px;float:left;margin-left:30px;font-size: 16px;">

        </div>
    </div>
</div>

</body>
</html>
