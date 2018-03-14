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
        alert("Content can't be null!");
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
