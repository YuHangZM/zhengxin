<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
%>
<html:html>
<head>  
	<title>��ȡ���������ȡְ��</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script language="javascript" src="js/common.js"></script>
</head>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	if(document.forms[0].name.value==""){
		document.forms[1].reason.disabled = "true";
		document.forms[1].type.disabled = "true";
		document.forms[1].pickBalance.disabled = "true";
	}
	var balance=document.forms[0].elements["balance"].value.trim();
	if(parseFloat(balance)<=0){
		alert("��ְ���˻����Ϊ0�����ܰ�����ȡҵ��");
		document.forms[0].elements["empId"].value="";
		document.forms[0].elements["name"].value="";
		document.forms[0].elements["cardKind"].value="";
		document.forms[0].elements["cardNum"].value="";
		document.forms[0].elements["curBalance"].value="";
		document.forms[0].elements["preBalance"].value="";
		document.forms[0].elements["balance"].value="";
		document.forms[0].elements["yearPickNumber"].value="";
		document.forms[0].elements["yearPickBalance"].value="";
	}
}
function toPop() {
	open_findEmp('<%=path%>','<bean:write name='orgId'/>',null,null,null,'0');;
}
function executeAjax(){
	var queryString = "pickupTransactionAddAAC.do?";
    var id = document.forms[0].elements["empId"].value.trim();
    queryString = queryString + "empId="+id; 	     
	findInfo(queryString);
}
var s1="";//����ť ������ť��������
var s2="";
var s3="";
var s4="";
function loads1(){//��ʼ��ҳ�水ť�ķ���
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
		//�����Ӧ��ť��˳��
			if(document.all.item(i).value=="��ȡ���"){
				s1=i;
			}
			/*
			if(document.all.item(i).value=="�������"){
				s2=i;
			}//���λ�������İ�ť
			*/
			if(document.all.item(i).value=="ȷ��"){
				s3=i;
			}
			if(document.all.item(i).value=="����"){
				s4=i;
			}
		}
	}
	var id = document.pickGetEmployeeInfoAF.elements["empId"].value
	if(id != ""){//��ְ�����ת����6λ��������
	    document.pickGetEmployeeInfoAF.elements["empId"].value=format(id)+id;
	}
	var initiaButton = document.getElementById("buttonState").value;
	if(initiaButton=='ȫ����ʾ'){
		document.all.item(s1).disabled="";
		//document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
	}else{
		document.all.item(s1).disabled="true";
		//document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		}
}

function findEmployee(){
//����ְ����Ŷ������ְ����Ϣ
       var queryString = "pickupTransactionAddAAC.do?";
       var id = document.forms[0].elements["empId"].value.trim();
       if(id==""){
       alert("��������ְ�����");
       }
       else if(isNaN(id) || id.indexOf(".")>0){
       alert("��������Ϸ�����������");
       }
       else{
       	queryString = queryString + "empId="+id; 	     
	    findInfo(queryString);
	   }
}
function check(){
	var pickupreason=document.all.reason.value.trim();
	if(pickupreason=="1"){
		var housesnum=document.all.houseNum.value.trim();
		if(housesnum==""){
			alert("��ȡԭ��Ϊ����,����д���պ�!");
			return false;
		}
	}

	var form = document.forms[1];
	if(form.pickBalance.value==""){
		alert("����������ȡ���");
		return false;
	}
	if(form.reason.value==""){
		alert("����ѡ����ȡԭ��");
		return false;
	}
	if(isNaN(form.pickBalance.value)){
		alert("����������ȷ������");		
		return false;
	}
	return true;
	
}
function skip(){
	var empId = document.forms[0].elements["empId"].value.trim();
	window.open('pageSkipAC.do?empId='+empId,'','width=600,height=400,scrollbars=yes');
	return false ;
}
function displays(empId,orgId,flag){
	if(flag=='false'){
		if(!confirm("��λ�������º�ְ���������²��ȣ�����Ƿ��������Ƿ������")){
			document.forms[0].elements["empId"].focus();
			document.forms[0].elements["empId"].value="";
			return false;
		}
	}
	document.Form1.elements["empId"].value=empId;
	document.Form1.elements["orgId"].value=orgId;
    showlist(); 
}
function showlist() {
  document.Form1.submit();
}
function huiche(){
if(event.keyCode==13){
		event.keyCode=9;
		findEmployee();
	}
}
function checkAll(){
	var form = document.forms[1];
	var summoney = document.forms[1].elements["sum"].value;
	var type = document.forms[1].elements["type"].value;

	if(form.pickBalance.value==""){
		alert("����������ȡ���");
		return ;
	}
	if(isNaN(form.pickBalance.value)){
	form.pickBalance.value="";
		alert("����������ȷ������");		
		return;
	}else{
		var mpb =  document.forms[1].elements["maxPickBalance"].value
		var pb = document.forms[1].elements["pickBalance"].value;
		var interest = document.forms[1].elements["distoryInterest"].value;
		var mpbInt = parseFloat(mpb);
		var pbInt = parseFloat(pb);
		var money=parseFloat(pb)+parseFloat(interest);
		if(pbInt>mpbInt){
		document.forms[1].elements["sum"].value=""
		document.forms[1].elements["pickBalance"].value = "";
			alert("��ȡ���ܹ���");
		}else{
			document.forms[1].elements["sum"].value = money;//document.forms[1].elements["pickBalance"].value+interest;
		}
	}
	if(type=="2"){
		document.forms[1].elements["sum"].value=summoney;
	}
}
function choose(){//�����б�ת����function
		var x=document.forms[1].elements["type"].value;
		if(reason=""){
			alert("return");
			return;
		}
		document.URL='pickupTypeSkipAC.do?type='+x;
}
function getPickInfo(){
	var empId = document.forms[0].empId.value;
	alert("empId:"+empId);
	window.open('pageSkipAC.do?empId='+empId);
}
function getMaxBalance(){
	var form = document.forms[1];
	if(form.reason.value==""){
		return;
	}else{
	var reason=document.forms[1].elements["reason"].value
	var empId = document.forms[0].elements["empId"].value;
	var yearPickNumber = document.forms[0].elements["yearPickNumber"].value;
		document.URL = "pickupAddAjaxAAC.do?method=pickReason&reason="+form.reason.value+"&empId="+empId+"&yearPickNumber="+yearPickNumber+"&reason="+reason;
	}
}
</script>
<html:form action="/pickupDisplayAddInfoAC.do"  focus="empId">
	<body bgcolor="#FFFFFF" text="#000000"
		onload="loads1();return reportErrors()" >
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="../../../img/table_left.gif" width="7" height="37">
							</td>
							<td background="../../../img/table_bg_line.gif" width="55">
								&nbsp;
							</td>
							<td width="235" background="../../../img/table_bg_line.gif">
								&nbsp;
							</td>
							<td background="../../../img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="../../../img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="139" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">��ȡ����&gt;������ȡ</font>
										</td>
										<td width="124" class=font14>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="9">
								<img src="../../../img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class=td3>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
														<b class="font14">ְ �� �� Ϣ</b>
													</td>
													<td height="22" background="../../../img/bg2.gif"
														align="center">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1">
											ְ�����
										</td>
										<td width="22%">
											<html:text property="empId" name="display"
												styleClass="input3" ondblclick="findEmployee()"
												onkeydown="huiche()"></html:text>
										</td>
										<td width="11%">
											<input type="button" name="Submit5" value="..."
												class="buttona" onClick="toPop();">
										</td>
										<td width="17%" class="td1">
											ְ������
										</td>
										<td width="33%">
											<html:hidden property="buttonState" name="display"/>
											<html:text property="name" name="display" readonly="true"
												styleClass="input3"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											֤������
										</td>
										<td colspan="2">
											<html:text property="cardKind" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>

										<td width="17%" class="td1">
											֤������
										</td>
										<td width="33%">
											<html:text property="cardNum" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											�������
										</td>
										<td colspan="2">
											<html:text property="curBalance" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>

										<td width="17%" class="td1">
											�������
										</td>
										<td width="33%">
											<html:text property="preBalance" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											�˻����
										</td>
										<td colspan="2">
											<html:text property="balance" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>
										<td width="17%" class="td1">
											�����½ɶ�
										</td>
										<td colspan="2">
											<html:text property="old_yearpay" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>
									</tr>
								</table>

								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
														<b class="font14">�� ȡ �� Ϣ</b>
													</td>
													<td height="22" background="../../../img/bg2.gif"
														align="center">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1">
											����ȡ����
										</td>
										<td width="33%">
											<html:text property="yearPickNumber" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>
										<td width="17%" class="td1">
											����ȡ���
										</td>
										<td width="33%">
											<html:text property="yearPickBalance" name="display"
												styleClass="input3" readonly="true"></html:text>
										</td>
									</tr>
									</html:form>
									<html:form action="/pickupTransactionAddAC.do">
										<tr>
											<td width="17%" class="td1">
												��ȡ����
												<font color="#FF0000">*</font>
											</td>
											<td width="33%">
												<logic:notEmpty name="somePick">
													<html:select style="input4" property="type"
														onchange="choose();" styleClass="input4">
														<html:optionsCollection property="pickupType"
															name="pickup" label="value" value="key" />
													</html:select>
											</td>
											<td width="17%" class="td1">
												��ȡԭ��
												<font color="#FF0000">*</font>
											</td>
											<td width="33%">
												<html:select property="reason" styleClass="input4"
													onchange="getMaxBalance();" name="display">
													<option value=""></option>
													<html:optionsCollection property="someList" name="pickup"
														label="value" value="key" />
												</html:select>
											</td>
											</logic:notEmpty>
											<logic:notEmpty name="distoryPick">
												<!-- 
    //��δ��벻��ʾ����ȡԭ��ֻ���ǲ�����ȡ
          	<html:select style="input4" property="type" onchange="choose();">
          		<html:optionsCollection property="pickupType" name="pickup" label="value" value="key"/>
          	</html:select>
     -->
												<select name="type" onchange="choose()" class="input4">
													<option value="1">
														������ȡ
													</option>
													<option value="2" selected>
														������ȡ
													</option>
												</select>
												</td>
												<td width="17%" class="td1">
													��ȡԭ��
													<font color="#FF0000">*</font>
												</td>
												<td width="33%">
													<html:select style="input4" property="reason"
														styleClass="input4" onchange="getMaxBalance();"
														name="display">
														<option value=""></option>
														<html:optionsCollection property="distoryList"
															name="pickup" label="value" value="key" />
													</html:select>
												</td>
											</logic:notEmpty>
										</tr>
										
										<tr>
											<td width="17%" class="td1">
												�����ȡ���
											</td>
											<td width="33%">
												<html:text property="maxPickBalance" name="result"
													styleClass="input3" readonly="true"></html:text>
											</td>
											<td width="17%" class="td1">
												��ȡ���
												<font color="#FF0000">*</font>
											</td>
											<td width="33%">
												<logic:present name="read">
													<logic:equal value="onlyRead" name="read">
														<html:text property="pickBalance" styleClass="input3"
															name="result" readonly="true"></html:text>
													</logic:equal>
												</logic:present>
												<logic:notPresent name="read">
													<html:text property="pickBalance" styleClass="input3" maxlength="18"
														onblur="checkAll()"></html:text>
												</logic:notPresent>
											</td>
										</tr>
										<security:orgcannot>
										<tr>
											<td width="17%" class="td1">
												������Ϣ
											</td>
											<td width="33%">
												<html:text property="distoryInterest" name="result"
													styleClass="input3" readonly="true"></html:text>
											</td>
											<td width="17%" class="td1">
												��Ϣ�ϼ�
											</td>
											<td width="33%">
												<html:text property="sum" styleClass="input3"
													readonly="true" name="result" onblur="checkAll()"></html:text>
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												���պ�
											</td>
											<td width="33%">
												<html:text property="houseNum" name="result"
													styleClass="input3" ></html:text>
											</td>
											
										</tr>
										</security:orgcannot>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td width="70">
														<html:submit property="method" styleClass="buttona"
															disabled="true" onclick="return skip()">
															<bean:message key="button.pickup.infor" />
														</html:submit>
													<td width="70">
													<!-- 
														<html:submit property="method" styleClass="buttona"
															disabled="true">
															<bean:message key="button.repayment" />
														</html:submit>
													<td width="70">
													-->
														<html:submit property="method" styleClass="buttona"
															disabled="true" onclick="return check()">
															<bean:message key="button.sure" />
														</html:submit>
													</td>
													<td width="70">
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.back" />
														</html:submit>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
</html:form>
<form action="pickupDisplayAddInfoAC.do" method="POST" name="Form1"
	id="Form1">
	<input type="hidden" name="orgId" />
	<input type="hidden" name="empId" />
</form>
</body>
</html:html>



