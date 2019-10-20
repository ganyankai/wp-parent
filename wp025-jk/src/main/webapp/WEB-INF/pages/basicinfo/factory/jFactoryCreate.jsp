<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('insert.action','_self');">确定</a></li>
<li id="back"><a href="list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		新增生产厂家信息
    </div> 
    </div>
    </div>
<div>
 
<hr>
	<font color="blue"><b>内容区域，表格布局</b></font>
<hr>
 
    <div>
        <tr>
            <td class="columnTitle_mustbe">厂家全称：</td>
            <td class="tableContent"><input type="text" name="fullName" value="" dataType="非空字符串" dispName="厂家全称"/></td>
            <td class="columnTitle_mustbe">简称：</td>
            <td class="tableContent"><input type="text" name="factoryName" value="" dataType="非空字符串" dispName="厂家简称"/></td>
        </tr>
        <tr>
            <td class="columnTitle_mustbe">联系人：</td>
            <td class="tableContent"><input type="text" name="contractor" value="" dataType="非空字符串" dispName="联系人"/></td>
            <td class="columnTitle_mustbe">电话：</td>
            <td class="tableContent"><input type="text" name="phone" value="" dataType="非空电话" dispName="电话"/></td>
        </tr>
        <tr>
            <td class="columnTitle_mustbe">手机：</td>
            <td class="tableContent"><input type="text" name="mobile" value="" dataType="非空手机" dispName="手机"/></td>
            <td class="columnTitle">传真：</td>
            <td class="tableContent"><input type="text" name="fax" value=""/></td>
        </tr>
        <tr>
            <td class="columnTitle">验货员：</td>
            <td class="tableContent"><input type="text" name="inspector" value=""/></td>
            <td class="columnTitle_mustbe">排序号：</td>
            <td class="tableContent"><input type="text" name="orderNo" value="" dataType="非空整数" dispName="排序号"/></td>
        </tr>
        <tr>
            <td class="columnTitle">说明：</td>
            <td class="tableContent"><textarea name="cnote" style="height:100px;"></textarea></td>
        </tr>
	</div>
</div>
 
 
</form>
</body>
</html>

