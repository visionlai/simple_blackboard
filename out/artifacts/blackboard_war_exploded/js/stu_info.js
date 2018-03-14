function getSpeci_Info(t) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("speci_info").innerHTML = xmlhttp.responseText;
        }
    }
    var d=t.getAttribute('value');
    xmlhttp.open("GET", "../GetSpecifiServlet?"+d, true);
    xmlhttp.send();
}