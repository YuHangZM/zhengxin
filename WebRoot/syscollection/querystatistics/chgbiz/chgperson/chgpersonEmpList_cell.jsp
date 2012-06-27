<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/chgbiz/chgperson/report/chgpersonEmpList_wl.cll","");
	
	var orgid=[];
	var orgname=[];
	var empid=[];
	var empname=[];
	var oldchgstatus=[];
	var newchgstatus=[];
	var orgpayment=[];
	var emppayment=[];
	var sumpayment=[];
	var chgYearMonth=[];
	var chgDate=[];
	var chgReason=[];
	var i=0;
	
	<%
			session = request.getSession();
    		List list=(List) session.getAttribute("cellList");
  			ChgPersonTail chgPersonTail=null;
  			for(int j=0;j<list.size();j++)
  			{
  				chgPersonTail=(ChgPersonTail)list.get(j);
  				String tempid = chgPersonTail.getChgPersonHead().getOrg().getId().toString();
                String orgid = 	BusiTools.convertTenNumber(tempid);
 	%>	    
				//orgid[i]=format("<%=chgPersonTail.getChgPersonHead().getOrg().getId().toString() %>")+"<%=chgPersonTail.getChgPersonHead().getOrg().getId().toString() %>";
				orgid[i]="<%=orgid %>"
				orgname[i]="<%=chgPersonTail.getChgPersonHead().getOrg().getOrgInfo().getName() %>";
				empid[i]=format("<%=chgPersonTail.getEmpId().toString() %>")+"<%=chgPersonTail.getEmpId().toString() %>";
				empname[i]="<%=chgPersonTail.getName() %>";
				oldchgstatus[i]="<%=chgPersonTail.getTemp_oldPayStatus() %>";
				newchgstatus[i]="<%=chgPersonTail.getTemp_newPayStatus() %>";
				orgpayment[i]=<%=chgPersonTail.getOrgPay() %>;
				emppayment[i]=<%=chgPersonTail.getEmpPay() %>;
				sumpayment[i]=<%=chgPersonTail.getSumPay() %>;
				chgYearMonth[i]="<%=chgPersonTail.getChgPersonHead().getChngMonth() %>";
				chgDate[i]="<%=chgPersonTail.getChgPersonHead().getBizDate() %>";
				var temp_reason = "<%=chgPersonTail.getTemp_chgreason() %>";
				if(temp_reason==null){
					chgReason[i]="";
				}else{
					chgReason[i]=temp_reason;
				}
				i++;
 	<%
 			}
 	%>
 		var totalLine=orgname.length;			//总的行数
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var orgpaymenttotal=0;				    //总的合计-单位缴额
		var emppaymenttotal=0;				    //总的合计-职工缴额
		var sumpaymenttotal=0;				    //总的合计-缴额合计
		
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,totalPageLine+3,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (7, totalPageLine+3, pageCurrent, "Sum(G2:G"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+3, pageCurrent, "Sum(H2:H"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.SetFormula (9, totalPageLine+3, pageCurrent, "Sum(I2:I"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.DeleteRow(50,2,pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/chgbiz/chgperson/report/chgpersonEmpList_wl.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+4,0,orgid[k]);
				document.forms(0).Cell1.S(2,k+4,0,orgname[k]);
				document.forms(0).Cell1.S(3,k+4,0,empid[k]);
				document.forms(0).Cell1.S(4,k+4,0,empname[k]);
				document.forms(0).Cell1.S(5,k+4,0,oldchgstatus[k]);
				document.forms(0).Cell1.S(6,k+4,0,newchgstatus[k]);
				document.forms(0).Cell1.d(7,k+4,0,orgpayment[k]);
				document.forms(0).Cell1.d(8,k+4,0,emppayment[k]);
				document.forms(0).Cell1.d(9,k+4,0,sumpayment[k]);
				document.forms(0).Cell1.S(10,k+4,0,chgYearMonth[k]);
				document.forms(0).Cell1.S(11,k+4,0,chgDate[k]);
				document.forms(0).Cell1.S(12,k+4,0,chgReason[k]);
				orgpaymenttotal=orgpaymenttotal+parseFloat(orgpayment[k]);
				emppaymenttotal=emppaymenttotal+parseFloat(emppayment[k]);
				sumpaymenttotal=sumpaymenttotal+parseFloat(sumpayment[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,empid[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,empname[k]);
				document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,oldchgstatus[k]);
				document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,newchgstatus[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,orgpayment[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,emppayment[k]);
				document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,sumpayment[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,chgYearMonth[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,chgDate[k]);
				document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,chgReason[k]);
				orgpaymenttotal=orgpaymenttotal+parseFloat(orgpayment[k]);
				emppaymenttotal=emppaymenttotal+parseFloat(emppayment[k]);
				sumpaymenttotal=sumpaymenttotal+parseFloat(sumpayment[k]);
			}	
		}
		
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G2:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H2:H"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I2:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,orgpaymenttotal);
					document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,emppaymenttotal);
					document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,sumpaymenttotal);
					document.forms(0).Cell1.DeleteRow(totalLine+6,51-(totalLine+5),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
								
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
					//F1 第F列的第1行: N9 到第N列的第9行  求和
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G2:G"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H2:H"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I2:I"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,orgpaymenttotal);
					document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,emppaymenttotal);
					document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,sumpaymenttotal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageline-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	

}
function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);
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
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}		
			
</script>
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>

<body  onContextmenu="return false" onload = "load();"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
		<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
		<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">
		<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
		<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
	</tr>
</table>
</form>
</body>
</html>