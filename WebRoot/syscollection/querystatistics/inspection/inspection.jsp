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
	<title>ͳ�Ʋ�ѯ-ҵ�񿼺�</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script language="javascript" src="js/common.js"></script>
</head>

<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function check(){
	var date = document.forms[0].elements["date"].value.trim();
	checkYear(date);
}
function reportMessage() {

	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>

<body bgcolor="#FFFFFF" text="#000000" onLoad="reportMessage();"
	onContextmenu="return false">

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="55">
							&nbsp;

						</td>
						<td width="235" background="<%=path%>/img/table_bg_line.gif">
							&nbsp;

						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">ͳ�Ʋ�ѯ</font><font color="00B5DB">&gt;ҵ�񿼺�</font>
									</td>
									<td width="47" class=font14>
										&nbsp;

									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>

			</td>
		</tr>



		<tr>
			<td class=td3>
				<html:form action="/inspectionFindAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">�� ѯ �� Ϣ</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;

										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td class="td1" width="17%">
								���´�
							</td>
							<td width="33%">
								<html:select property="officecode" styleClass="input4"
									name="inspectionFindAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1" width="17%">
								�� ��
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<p>
									<html:text name="inspectionFindAF" property="date"
										styleClass="input3" styleId="txtsearch" />
								</p>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="check()">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/inspectionPrintAC.do" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="61%" class="td1">
								<table border="0">
									<tr>
										<td width="487">
											<table width="497" border="0">
												<tr>
													<td width="479">
														<strong>���ס����������ɴ���</strong>��
													</td>
												</tr>
												<tr>
													<td>
														����ס��������ʵ�ʽɴ���Ԫ��/����ס��������Ӧ�ɴ���Ԫ��*100%
													</td>
												</tr>
											</table>
										</td>
										<td width="16">
										</td>
										<td width="11">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="gjjpayrate" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
							</td>
						</tr>
						<tr>
							<td width="61%" class="td1">
								<table border="0">
									<tr>
										<td width="497">
											<table border="0">
												<tr>
													<td width="491">
														<strong>ס��������ɴ�������</strong>��
													</td>
												</tr>
												<tr>
													<td>
														����ס��������ʵ�ʽɴ���Ԫ��/����ס��������ʵ�ʽɴ���Ԫ��*100%-1
													</td>
												</tr>
											</table>
										</td>
										<td width="15">
										</td>
										<td width="10">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="gjjpayaddrate" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
							</td>
						</tr>
						<tr>
							<td width="61%" class="td1">
								<table border="0">
									<tr>
										<td width="494">
											<table width="497" border="0">
												<tr>
													<td width="493">
														<strong>ס����������˴������</strong>��
													</td>
												</tr>
												<tr>
													<td>
														��ĩס����������˴�������Ԫ��/��ĩס��������ɴ�����Ԫ��*100%
													</td>
												</tr>
											</table>
										</td>
										<td width="17">
										</td>
										<td width="10">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="personloan" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
								<html:hidden property="officeName" name="inspectionShowAF" />
								<html:hidden property="operator" name="inspectionShowAF" />
								<html:hidden property="date" name="inspectionShowAF" />
							</td>
						</tr>
						<tr>
							<td width="61%" class="td1">
								<table width="546" border="0">
									<tr>
										<td width="515">
											<table border="0">
												<tr>
													<td width="511">
														<strong>ס����������˴���������</strong>��
													</td>
												</tr>
												<tr>
													<td>
														����ס����������˴���Ŷ��Ԫ��/����ס����������˴���Ŷ��Ԫ��*100%-1
													</td>
												</tr>
											</table>
										</td>
										<td width="10">
										</td>
										<td width="12">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="personloanaddrate" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
							</td>
						</tr>
						<tr>
							<td width="61%" class="td1">
								<table border="0">
									<tr>
										<td width="494">
											<table border="0">
												<tr>
													<td width="464">
														<strong>ס����������˴���������</strong>��
													</td>
												</tr>
												<tr>
													<td>
														��ĩס����������˴������ڶ��Ԫ��/��ĩס����������˴�������Ԫ��*100%
													</td>
												</tr>
											</table>
										</td>
										<td width="10">
										</td>
										<td width="13">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="personloanoverduerate" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
							</td>
						</tr>
						<tr>
							<td width="61%" class="td1">
								<table border="0">
									<tr>
										<td width="513">
											<table border="0">
												<tr>
													<td width="518">
														<strong>ס�����������׼���������</strong>��
													</td>
												</tr>
												<tr>
													<td>
														��ĩס��������������׼������Ԫ��/��ĩס����������˴�������Ԫ��*100%
													</td>
												</tr>
											</table>
										</td>
										<td width="10">
										</td>
										<td width="10">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="loanmoneyforriskready" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
							</td>
						</tr>
						<tr>
							<td width="61%" class="td1">
								<table border="0">
									<tr>
										<td width="495">
											<table border="0">
												<tr>
													<td width="480">
														<strong>ס����������ֵ������</strong>��
													</td>
												</tr>
												<tr>
													<td>
														����ס����������ֵ���棨��Ԫ��/����ס����������ƽ���ɴ�����Ԫ��*100%
													</td>
												</tr>
											</table>
										</td>
										<td width="10">
										</td>
										<td width="13">
										</td>
									</tr>
								</table>
							</td>
							<td width="39%">
								<html:text property="gjjaddincome" maxlength="20"
									styleClass="input3" styleId="txtsearch" name="inspectionShowAF"></html:text>
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
			</td>
		</tr>
	</table>
</body>
</html:html>
