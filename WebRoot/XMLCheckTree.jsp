<%@ page contentType="text/html; charset=GBK" %>
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="css/XMLSelTree.css">
</HEAD>
<body topmargin="2" leftmargin="2" marginheight="0" marginwidth="0" bgcolor="#F1F1F1">
<DIV id="SrcDiv" onselectstart="selectstart()"></DIV>
<BODY>
</HTML>

<SCRIPT LANGUAGE=javascript src="js/XMLSelTree.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript>
	<!--
	var m_sXMLFile	= "CheckMenuNode.jsp";						// ���˵����ļ�
	var m_sXSLPath	= "xls/";									// xsl�ļ����·��
	var m_oSrcDiv	= SrcDiv;								// HTML���(�˵��������˵��ڴ�������ʾ)

	function window.onload()
	{
		InitTree(m_sXMLFile, m_sXSLPath, m_oSrcDiv);
	}

	//-->
</SCRIPT>
