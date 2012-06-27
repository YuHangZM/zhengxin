<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.othersloan.action.OthersLoanTbShowAC"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			OthersLoanTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>异地贷款维护列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">

var s1="";
var s2="";
var tr="tr0";

function loads(){

   	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="终审通过"){
				s1=i;
			}
			if(document.all.item(i).value=="撤消终审"){
				s2=i;
			}
		}
	}
	if(count==0){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
	}
}
function check(buttonName) {
    var temp_empid = [];
    var arr= document.getElementsByName("rowArray");
	var temp=0;
	var j=0;
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
			temp_empid[j] = arr[i].value;
 			j++;
		}
	}
	var buttonMethod=buttonName.value; 
	if(temp==0){
		alert("请选择要"+buttonMethod+"的业务！");
		return false;
	}else{
		var x=confirm("确定进行"+buttonMethod+"？");
		if(x){
			return true;
		}else{
			return false;
		}
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="loanvipcheckShowAC.do" />
	</jsp:include>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td width="3%" align="right" valign="middle">
				<table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
				</table>
			</td>
			<td width="97%" align="left" valign="top">
				<logic:equal value="1" name="forward">

					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="7">
											<img src="<%=path%>/img/table_left.gif" width="7" height="37">
										</td>
										<td background="<%=path%>/img/table_bg_line.gif" width="10">
											&nbsp;
										</td>
										<td background="<%=path%>/img/table_bg_line.gif">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="112" height="37"
														background="<%=path%>/img/buttong.gif" align="center"
														valign="top" style="PADDING-top: 7px">
														<a href="<%=path%>/sysloan/othersLoanForwardURLAC.do"
															class=a2>异地贷款信息</a>
													</td>
													<td width="112" height="37"
														background="<%=path%>/img/buttonbl.gif" align="center"
														style="PADDING-top: 7px" valign="top">
														<a href="<%=path%>/sysloan/othersLoanTbForwardURLAC.do"
															class=a2>异地贷款信息维护</a>
													</td>

												</tr>
											</table>
										</td>
										<td background="<%=path%>/img/table_bg_line.gif" align="right">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="37">
														<img src="<%=path%>/img/title_banner.gif" width="37"
															height="24">
													</td>
													<td width="228" class=font14 bgcolor="#FFFFFF"
														align="center" valign="bottom">
														<font color="00B5DB">申请贷款&gt;申请异地贷款维护</font>
													</td>
													<td width="35" class=font14>
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
										<td width="9">
											<img src="<%=path%>/img/table_right.gif" width="9"
												height="37">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</logic:equal>
				<logic:equal value="2" name="forward">

					<table width="98%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="7">
											<img src="<%=path%>/img/table_left.gif" width="7" height="37">
										</td>
										<td background="<%=path%>/img/table_bg_line.gif" width="10">
											&nbsp;
										</td>
										<td background="<%=path%>/img/table_bg_line.gif">
											<table border="0" cellspacing="0" cellpadding="0">

											</table>
										</td>
										<td background="<%=path%>/img/table_bg_line.gif" align="right">
											<table width="200" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="37">
														<img src="<%=path%>/img/title_banner.gif" width="37"
															height="24">
													</td>
													<td width="228" class=font14 bgcolor="#FFFFFF"
														align="center" valign="bottom">
														<font color="00B5DB">统计查询&gt;本中心异地贷款</font>
													</td>
													<td width="35" class=font14>
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
										<td width="9">
											<img src="<%=path%>/img/table_right.gif" width="9"
												height="37">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</logic:equal>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" class=td3>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="207">
													<b class="font14">查 询 条 件</b>
												</td>
												<td width="716" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/othersLoanTbFindAC.do" method="post"
								style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="11%" class="td1">
											办事处
										</td>
										<td width="21%" colspan="3">
											<html:select name="othersLoanTbShowAF" property="office"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value="">全部</html:option>
												<html:options collection="officeList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="11%" class="td1">
											职工编号
										</td>
										<td width="21%">
											<html:text name="othersLoanTbShowAF" property="empId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											职工姓名
										</td>
										<td width="21%" colspan="3">
											<html:text name="othersLoanTbShowAF" property="name"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											职工证件号码
										</td>
										<td width="21%">
											<html:text name="othersLoanTbShowAF" property="cardNum"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											单位编号
										</td>
										<td width="21%" colspan="3">
											<html:text name="othersLoanTbShowAF" property="orgId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											单位名称
										</td>
										<td width="21%">
											<html:text name="othersLoanTbShowAF" property="orgName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											操作时间
										</td>
										<td width="10%">
											<html:text name="othersLoanTbShowAF" property="binDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="1%">
											至
										</td>
										<td width="10%">
											<html:text name="othersLoanTbShowAF" property="endDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
										
										</td>
										<td width="21%">
											
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</html:form>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											align="center">
											<tr>
												<td class=h4>
													合计：房屋面积
													<u>：<bean:write name="othersLoanTbShowAF"
															property="totalHouseArea" /> 平方米</u>贷款金额
													<u>：<bean:write name="othersLoanTbShowAF"
															property="totalLoanMoney" /> 万元</u>贷款人数
													<u>：<bean:write name="othersLoanTbShowAF"
															property="totalPerson" /> </u>房屋总价
													<u>：<bean:write name="othersLoanTbShowAF"
															property="totalHousePrice" /> 万元</u>
												</td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="213">
													<b class="font14">本中心异地贷款列表</b>
												</td>
												<td width="710" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<html:form action="/othersLoanTbMaintainAC.do" method="post"
								style="margin: 0">
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr bgcolor="1BA5FF">
										<td align="center" height="6" colspan="1"></td>
									</tr>
								</table>
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr align="center" bgcolor="C4F0FF">
										<td height="23" bgcolor="C4F0FF">
											&nbsp;
										</td>

										<td align="center" class=td2>
											<a href="javascript:sort('p110.contract_id')">姓名</a>
											<logic:equal name="pagination" property="orderBy"
												value="p110.contract_id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p110.borrower_name')">个人账号</a>
											<logic:equal name="pagination" property="orderBy"
												value="p110.borrower_name">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											单位名称
										</td>
										<td align="center" class=td2>
											单位账号
										</td>
										<td align="center" class=td2>
											身份证号
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p115.loan_money')">房屋坐落</a>
											<logic:equal name="pagination" property="orderBy"
												value="p115.loan_money">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p115.loan_time_limit')">房屋总价（元）</a>
											<logic:equal name="pagination" property="orderBy"
												value="p115.loan_time_limit">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											建筑面积（M
											<sup>
												2
											</sup>
											）
										</td>
										<td align="center" class=td2>
											贷款额（元）
										</td>
										<td align="center" class=td2>
											年限(月)
										</td>
										<td align="center" class=td2>
											贷款城市
										</td>
										<td align="center" class=td2>
											备注
										</td>

									</tr>
									<logic:notEmpty name="othersLoanTbShowAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="othersLoanTbShowAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>

											<tr align="left" id="tr<%=i%>" class="<%=strClass%>"
												onclick='gotoClickpp("<%=i%>", idAF);'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColorpp("<%=i%>", idAF);'>
												<td>
													<input id="s<%=i%>" type="radio" name="id"
														value="<bean:write name="element" property="id"/>"
														<%if(new Integer(0).equals(i)) out.print("checked"); %>>
												</td>
												<td>
												<a href="#"
												onclick="window.open('<%=path%>/sysloan/othersLoanWinShowAC.do?id=<bean:write name="element" property="id"/>','window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=no');">
												<bean:write name="element" property="borrowerName" /> </a>
													
												</td>
												<td>
													<bean:write name="element" property="borrowerEmpId" />
												</td>
												<td>
													<bean:write name="element" property="BORROWERORGNAME" />
												</td>
												<td>
													<bean:write name="element" property="BORROWERORGID" />
												</td>
												<td align="right">
													<bean:write name="element" property="BORROWERCARDNUM"
														format="#,##0.00" />
												</td>
												<td align="right">
													<bean:write name="element" property="HOUSEADDR"
														format="#,##0.00" />
												</td>
												<td>
													<bean:write name="element" property="HOUSETOTALPRICE" />
												</td>
												<td>
													<bean:write name="element" property="HOUSEAREA" />
												</td>
												<td>
													<bean:write name="element" property="LOANMONEY" />
												</td>
												<td>
													<bean:write name="element" property="LOANTIME" />
												</td>
												<td>
													<bean:write name="element" property="LOANCITY" />
												</td>
												<td>
													<bean:write name="element" property="BORROWERADDR" />
												</td>
											</tr>

										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="othersLoanTbShowAF" property="list">
										<tr>
											<td colspan="11" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>

									</logic:empty>
								</table>

								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														共
														<bean:write name="pagination" property="nrOfElements" />
														项
													</td>
													<td align="right">
														<jsp:include page="/inc/pagination.jsp">
															<jsp:param name="url" value="othersLoanTbShowAC.do" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" cellspacing="0" cellpadding="0">
												<logic:equal value="1" name="forward">
													<logic:notEmpty name="othersLoanTbShowAF" property="list">
														<tr>

															<td width="70">
																<html:submit property="method" styleClass="buttona">
																	<bean:message key="button.update" />
																</html:submit>
															</td>
															<td width="70">
																<html:submit property="method" styleClass="buttona">
																	<bean:message key="button.delete" />
																</html:submit>
															</td>
															<td width="70">
																<html:submit property="method" styleClass="buttona">
																	<bean:message key="button.print" />
																</html:submit>
															</td>
														</tr>
													</logic:notEmpty>
													<logic:empty name="othersLoanTbShowAF" property="list">
														<tr>

															<td width="70">
																<html:submit property="method" styleClass="buttona"
																	disabled="true">
																	<bean:message key="button.update" />
																</html:submit>
															</td>
															<td width="70">
																<html:submit property="method" styleClass="buttona"
																	disabled="true">
																	<bean:message key="button.delete" />
																</html:submit>
															</td>
															<td width="70">
																<html:submit property="method" styleClass="buttona"
																	disabled="true">
																	<bean:message key="button.print" />
																</html:submit>
															</td>
														</tr>
													</logic:empty>
												</logic:equal>
												<logic:equal value="2" name="forward">
													<logic:notEmpty name="othersLoanTbShowAF" property="list">
														<tr>

															<td width="70">
																<html:submit property="method" styleClass="buttona">
																	<bean:message key="button.print" />
																</html:submit>
															</td>
														</tr>
													</logic:notEmpty>
													<logic:empty name="othersLoanTbShowAF" property="list">
														<tr>


															<td width="70">
																<html:submit property="method" styleClass="buttona"
																	disabled="true">
																	<bean:message key="button.print" />
																</html:submit>
															</td>
														</tr>
													</logic:empty>
												</logic:equal>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html:html>

