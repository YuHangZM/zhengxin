<%@ page contentType="text/html; charset=GBK" %>
<%
	String nParentID = (String)request.getParameter("nParentID");
	
%>
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="./css/XMLSelTree.css">
</HEAD>
<body topmargin="2" leftmargin="2" marginheight="0" marginwidth="0" bgcolor="#CEE7F4">
<DIV id="SrcDiv" onselectstart="selectstart()"></DIV>
<BODY>
</HTML>

<SCRIPT LANGUAGE=javascript src="./js/XMLSelTree.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript>
<!--
var m_sXMLFile	= "TreeNode.jsp?nParentID=<%=nParentID%>";			// ���˵����ļ�
var m_sXSLPath	= "./xls/";									// xsl�ļ����·��
var m_oSrcDiv	= SrcDiv;											// HTML���(�˵��������˵��ڴ�������ʾ)


function window.onload()
{
	InitTree(m_sXMLFile, m_sXSLPath, m_oSrcDiv, false, true);
	
	m_oConfig.bImgAsync			= true ;						//���ͼ���ʱ��ѡ�иýڵ�
	m_oConfig.bShowElseBranch	= true ;						//ֻ��ʾ��ǰ1����֦������������֦
}

/************************************************
** GoLink(p_sHref, p_sTarget)
************************************************/
function GoLink(p_sHref, p_sTarget)
{
	var sHref	= p_sHref;
	var sTarget	= p_sTarget;
	window.open(sHref, sTarget);
}
//-->
</SCRIPT>
