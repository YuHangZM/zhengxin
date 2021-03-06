<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			LoanVIPCheckShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，审批贷款信息列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0";

function loads(){

   	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s4=i;
			}
		}
	}
  	//初始状态按钮全部禁用
	if(count==0){
		document.all.item(s4).disabled="true";
	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
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
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="10">
							&nbsp;
						</td>
						<td width="695" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
								</tr>
							</table>

						</td>
						<td width="255">
							<table width="255" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td width="169" class=font14 bgcolor="#FFFFFF" align="center"
										valign="middle">
										<font color="00B5DB">申请贷款&gt;贷款查询</font>
									</td>
									<td width="30" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="18">
							<img src="<%=path%>/img/table_bg_line.gif" width="18" height="37">
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
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
							<html:form action="/loanvipcheckFindAC.do" method="post"
								style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="11%" class="td1">
											办事处
										</td>
										<td width="21%" colspan="3">
											<html:select name="loanvipcheckShowAF" property="officeCode"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value="">全部</html:option>
												<html:options collection="officeList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="11%" class="td1">
											放款银行
										</td>
										<td width="21%">
											<logic:notEmpty name="loanBankNameList">
												<html:select name="loanvipcheckShowAF"
													property="loanBankName" styleClass="input3"
													onkeydown="enterNextFocus1();">
													<html:option value="">全部</html:option>
													<html:options collection="loanBankNameList"
														property="value" labelProperty="label" />
												</html:select>
											</logic:notEmpty>
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											合同编号
										</td>
										<td width="21%" colspan="3">
											<html:text name="loanvipcheckShowAF" property="contractId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											借款人姓名
										</td>
										<td width="21%">
											<html:text name="loanvipcheckShowAF" property="borrowerName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											证件号码
										</td>
										<td width="21%" colspan="3">
											<html:text name="loanvipcheckShowAF" property="cardNum"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											购房类型
										</td>
										<td width="21%">
											<html:select name="loanvipcheckShowAF" property="houseType"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="houseTypeMap"
													name="loanvipcheckShowAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											单位名称
										</td>
										<td width="21%" colspan="3">
											<html:text name="loanvipcheckShowAF" property="orgName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="11%" class="td1">
											合同状态
										</td>
										<td width="21%">
											<html:select name="loanvipcheckShowAF"
												property="contractStFind" styleClass="input3"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="contractStMap"
													name="loanvipcheckShowAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="11%" class="td1">
											操作时间
										</td>
										<td width="10%">
											<html:text name="loanvipcheckShowAF" property="beginBizDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="1%">
											至
										</td>
										<td width="10%">
											<html:text name="loanvipcheckShowAF" property="endBizDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
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
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td class=h4>
											合计：贷款户数
											<u>：<bean:write name="loanvipcheckShowAF"
													property="count" /> </u> 借款金额
											<u>：<bean:write name="loanvipcheckShowAF"
													property="loanTotleMoney" />万元 </u> 房屋总价
											<u>：<bean:write name="loanvipcheckShowAF"
													property="totalHousePrice" /> </u> 总面积
											<u>：<bean:write name="loanvipcheckShowAF"
													property="totalArea" />平方米 </u>
										</td>
									</tr>
								</table>
							</html:form>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="213">
													<b class="font14">贷款查询列表</b>
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

							<html:form action="/loanvipcheckAgainMaintainAC.do" method="post"
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
											<a href="javascript:sort('p110.contract_id')">合同编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="p110.contract_id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p110.borrower_name')">借款人姓名</a>
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
											配偶姓名
										</td>
										<td align="center" class=td2>
											房屋总价(元)
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p115.loan_money')">借款金额(万元)</a>
											<logic:equal name="pagination" property="orderBy"
												value="p115.loan_money">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('p115.loan_time_limit')">借款期限（年）</a>
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
											房屋座落
										</td>
										<td align="center" class=td2>
											操作员
										</td>
										<td align="center" class=td2>
											合同状态
										</td>
									</tr>
									<logic:notEmpty name="loanvipcheckShowAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="loanvipcheckShowAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr align="left" id="tr<%=i%>" class="<%=strClass%>">
												<td>
													<html:multibox property="rowArray">
														<bean:write name="element" property="contractId" />
													</html:multibox>
												</td>
												<td>
													<bean:write name="element" property="contractId" />
												</td>
												<td>
													<a href="#"
														onclick="window.open('<%=path%>/sysloan/showLoanVIPCheckTaAC.do?contractIdWY=<bean:write 
													name="element" property="contractId" />','window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');">

														<bean:write name="element" property="borrowerName" /> </a>
												</td>
												<td>
													<bean:write name="element" property="orgName" />
												</td>
												<td>
													<bean:write name="element" property="assistantborrowerName" />
												</td>
												<td>
													<bean:write name="element" property="totalPrice" />
												</td>
												<td>
													<bean:write name="element" property="loanMoney" />
												</td>
												<td>
													<bean:write name="element" property="loanTimeLimit" />
												</td>
												<td>
													<bean:write name="element" property="houseArea" />
												</td>
												<td>
													<bean:write name="element" property="houseAddr" />
												</td>
												<td>
													<bean:write name="element" property="operator" />
												</td>
												<td>
													<bean:write name="element" property="contractSt" />
												</td>
												<td style="display:none">
													<bean:write name="element" property="type" />
												</td>
											</tr>

										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="loanvipcheckShowAF" property="list">
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
															<jsp:param name="url" value="loanvipcheckShowAC.do" />
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
												<tr>
													<td width="70">
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.print" />
														</html:submit>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
							<form action="loanvipcheckShowAC.do" name="Form1"></form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html:html>

