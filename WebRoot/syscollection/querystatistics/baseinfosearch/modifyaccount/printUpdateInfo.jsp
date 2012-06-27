<%@ page language="java" pageEncoding="gbk" import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.BaseGhgInfo" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>
<%
	List list = (List)request.getAttribute("printUpdateDate");
	String path=request.getContextPath();
%>
<html:html lang="true">
  <head>
    <title>��ӡ�޸���Ϣ</title>
  </head>
  <script type="text/javascript">
  	function load(){//���ļ�...
  	loginReg();	
		document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/baseinfosearch/modifyaccount/print/orgOpenUpdateInfo.cll","");
			var orgId=[];
			var orgName=[];
			var content=[];
			var start=[];
			var end=[];
			var time=[];
			var i=0;
            //property="chgBefInfo"
            //property="chgEndInfo"
            //property="opTime"
			<%
				BaseGhgInfo info = new BaseGhgInfo();
				for(int j=0;j<list.size();j++){
				info = (BaseGhgInfo)list.get(j);
				String tempid = info.getOrg().getId().toString();
                String orgid = BusiTools.convertTenNumber(tempid);
			%>
			    //�����ݴ���JS����������..
				orgId[i] = "<%=orgid%>"
				orgName[i] = "<%=info.getOrg().getOrgInfo().getName()%>"; 
				content[i] = "<%=info.getChgColumn()%>";
				start[i] = "<%=info.getTemp_chgBefInfo()%>";
				end[i] = "<%=info.getTemp_chgEndInfo()%>";
				time[i] = "<%=info.getOpTime().toLocaleString()%>";
				i++;
			<%}%>
			var totalLine=orgId.length;			//�ܵ����� ����ĳ���
			var totalPageLine=10;					//ÿҳ��ʾ������--���˵�һ��
			var pageTotal=totalLine/totalPageLine;	//�ܹ��ּ�ҳ ������/ÿҳ������
			var pageCurrent=0;						//��ǰҳ
			var completeline=0;						//��¼�е�
			var moneytotal=0;						//�ܵĺϼ�
			for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.ReDraw();//�ػ�һ�����
				pageCurrent++;//��ǰҳ++	
				completeline=k-3;
				//���Ʊ�ǩ param 	��ҳ�����š�param Ҫ���õı�ҳҳǩ����					
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/baseinfosearch/modifyaccount/print/orgOpenUpdateInfo.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"��"+(pageCurrent+1)+"ҳ")	
			}
			if(pageCurrent==0)//�����ǰҳΪ��һҳ��ʱ�� ��������-->����ط�Ӧ��д��ǰҳ�ǵ�2ҳ��..��Ϊ��һҳ��ƾ֤
			{	
				//document.forms(0).Cell1.S(��,��,ҳ��,"��ҳС��");
				document.forms(0).Cell1.S(1,k+3,0,orgId[k]);//����ط�ҲҪд�ڶ�ҳ
				document.forms(0).Cell1.S(2,k+3,0,orgName[k]);
				document.forms(0).Cell1.S(3,k+3,0,content[k]);
				document.forms(0).Cell1.S(4,k+3,0,start[k]);
				document.forms(0).Cell1.S(5,k+3,0,end[k]);
				document.forms(0).Cell1.S(6,k+3,0,time[k]);
			}else{//���һҳ�������ҳ������
				document.forms(0).Cell1.S(1,k-completeline,pageCurrent,orgId[k]);
				document.forms(0).Cell1.S(2,k-completeline,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(3,k-completeline,pageCurrent,content[k]);
				document.forms(0).Cell1.S(4,k-completeline,pageCurrent,start[k]);
				document.forms(0).Cell1.S(5,k-completeline,pageCurrent,end[k]);
				document.forms(0).Cell1.S(6,k-completeline,pageCurrent,time[k]);
				//d�����ǲ�����ɫ����,���Ǵ˷�����������������
				//document.forms(0).Cell1.d(6,k-completeline,pageCurrent,sum[k]);
			}	
		}		
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
	}
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//��ʾ��ӡԤ���Ǹ�ҳ��
		document.forms(0).Cell1.printPreviewEx(1,k,false);//key����ǰҳ
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);//û����
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button4_onclick()
	{
		document.forms(0).Cell1.FindDialogEx( 0,"");
	}
		function Button5_onclick()
	{
		document.forms(0).Cell1.ImportExcelDlg();
	}
	function printsheet(){//�����Ĵ�ӡ
		var k=document.forms(0).Cell1.GetCurSheet();//��ʾ��ӡԤ���Ǹ�ҳ��--�̶�
		document.forms(0).Cell1.PrintSheet(1,k);//�̶�...
	}
  </script>
  <script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr><input type="button" name="print" value = "��ӡԤ��" onclick = "printPreview();"/>
<INPUT id="Button1" onclick="printsheet()" type="button" value="��ӡ" name="Button1">
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="���ΪExcel" name="Button1">
<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="���Ϊpdf" name="Button1">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="ҳ������">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="���ҶԻ���">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel����">
<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" ���� ">	
</table>
</form>
  </body>
</html:html>
