<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Semantic Web</title>
<style type="text/css">
body{
font-face: 'Arial'}
</style>
</head>
<body style="background-color:megenda;">


<H1 ALIGN="CENTER">Semantic Web in Moodle</H1>




<IMG STYLE="position:absolute; TOP:10px; LEFT:1px; WIDTH:150px; HEIGHT:100px" SRC="download.jpg">
<IMG STYLE="position:absolute; TOP:10px; RIGHT:1px;WIDTH:150px;  HEIGHT:100px" SRC="3553_IIIT-B_Campus_bangalore.gif">
<!--
<div style=" top: 30;  left: 230; width:100; height:200; position: absolute; z-index: 1; visibility: show;"> <H1>USER</H1> </div>
<div style=" top: 340;  left: 230; position: absolute; z-index: 1; visibility: show;"> <H1>ROLE</H1> </div>
<div style=" top: 40;  left: 610; position: absolute; z-index: 1; visibility: show;"> <H1>COURSE</H1> </div>
<div style=" top: 340;  left: 610; position: absolute; z-index: 1; visibility: show;"> <H1>CATEGORY</H1> </div>

 -->



<div id="div1" style="width:200px;height:240px; position:absolute;
        top: 120px;
        left: 200px; border:2px solid #000 ">
        <div style="top:-50px;left:2px;position:relative;">
        <a href="<s:url  action="get_user" includeParams="post">
        <s:param name="" value="" /></s:url>">
       <H3>USER</H3>
</a></div>

    <div style="top:0px;left:2px;position:absolute;">
     <s:iterator value="user" status="userStatus">
    <br>
     <a href="<s:url  action="user_action" includeParams="post">
        <s:param name="queryname" value="%{username}" /></s:url>">
    <s:property value="username"/>
        </a>

    </s:iterator> </div>
</div>

<div id="div2" style="width:200px;height:240px; position:absolute;
        top: 120px;
        left: 600px; border:2px solid #000 ">
        <div style="top:-50px;left:2px;position:relative;">
        <a href="<s:url  action="get_course" includeParams="post">
        <s:param name="" value="" /></s:url>">
        <H3>COURSE</H3>
        </a></div>
        <div style="top:0px;left:2px;position:absolute;">
<s:iterator value="course" status="userStatus">
<br>
<a href="<s:url  action="course_action" includeParams="post">
        <s:param name="queryname" value="%{coursename}" /></s:url>">

        <s:property value="coursename"/>
 </a>

    </s:iterator></div>
</div>

<div style="width:200px;height:240px; position:absolute;
        top: 450px;
        left: 200px; border:2px solid #000 ">
        <div style="top:-50px;left:2px;position:relative;">
        <a href="<s:url  action="get_role" includeParams="post">
        <s:param name="" value="" /></s:url>">
     <H3>ROLE</H3>
        </a>
    </div>
    <div style="top:0px;left:2px;position:absolute;">
 <s:iterator value="role" status="userStatus">
<br>
 <a href="<s:url  action="role_action" includeParams="post" >
        <s:param name="queryname" value="%{rolename}" /></s:url>">
    <s:property value="rolename"/>
     </a>

    </s:iterator></div>
</div>

<div id="div5" style="width:200px;height:280px; position:absolute;
        top: 280px;
        left: 920px; border:2px solid #000 ">
        <div style="top:-50px;left:2px;position:relative;">
        <a href="<s:url  action="list_properties" includeParams="post">
        <s:param name="" value="" /></s:url>">
       <H3>PROPERTIES</H3>
</a></div>
<div style="top:0px;left:2px;position:absolute;">
 <s:iterator value="properties" status="userStatus" >
<br>
 <a href="<s:url  action="list_properties" includeParams="post">
        <s:param name="queryname" value="%{propertyName}" /></s:url>">
    <s:property  value="propertyName"/>
    </a>

    </s:iterator></div>

</div>

<div style="width:200px;height:240px; position:absolute;
        top: 450px;
        left: 600px; border:2px solid #000 ">
        <div style="top:-50px;left:2px;position:relative;">
<a href="<s:url  action="get_category" includeParams="post">
        <s:param name="" value="" /></s:url>">
      <H3>CATEGORY</H3></a>
        <div style="top:0px;left:2px;position:absolute;">
        <br>
        <br>
 <s:iterator value="category" status="userStatus" >
<br>
 <a href="<s:url  action="category_action" includeParams="post">
        <s:param name="queryname" value="%{category}" /></s:url>">
    <s:property value="category"/>
    </a>

    </s:iterator></div>

</div>
</div>

</body>
</html>