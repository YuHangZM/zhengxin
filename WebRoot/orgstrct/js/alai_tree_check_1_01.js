/********************************************************************************************
	����Ŀ¼���ؼ�֧��checkbox�ڵ㹦����չ���� �������������2003��7��17�գ���������Ȩ����
**********************************************************************************************/
/*******************************************************************************
�޶��汾��ver1.01
�޶����ߣ���Ӣ��
�޶�ʱ�䣺2005-10-08
1�����һ���ܹ����ѡ�н��ļ��ϵķ�����
������ͣ����������
������������getSelectedNodes()������
������ݣ�
--���getSelectedNodes()���������ӽ�㱻ѡ��ʱ���丸���ҲΪѡ��״̬��
********************************************************************************/
function alai_tree_check()
{
	if(typeof(alai_tree)!="function")
	{
		alert("run alai_tree_check() fail, please load alai_tree firt!")
		return
	}
	//add(toNode,relation,text,key,ico,exeCategory,exeArg)
	var colChkNode=[]
	alai_tree.prototype.colChkNode=colChkNode
	alai_tree.prototype.addChkNode=function(toNode,itemId,text,ico,exeCategory,exeArg,checked)
	{
		var newNode=this.add(toNode,"last",itemId,text,"",ico,exeCategory,exeArg)
		var chkBox=document.createElement('<input type="Checkbox" name="item">')
		var tree=this
		newNode.label.insertAdjacentElement("beforeBegin",chkBox)
		newNode.isCheck=true
		if(typeof(checked)=="boolean")chkBox.checked=checked;
		newNode.oncheck=new Function("return true;")
		chkBox.onpropertychange=function(){if(newNode.oncheck())tree.oncheck(newNode)}
		colChkNode[colChkNode.length]=newNode
		newNode.checkBox=chkBox
		return newNode
	}
	alai_tree.prototype.oncheck=new Function("return true;")
	alai_tree.prototype.getSelectedNodes=function()
	{
		var selectedNodes = new Array();
		var parentNodes = new Array();
		var j=0;
		for(i=0;i<colChkNode.length;i++) {
			if(colChkNode[i].checkBox.checked) {
				selectedNodes[j++]=colChkNode[i];
				
				var parentNode = colChkNode[i].parent;
				while(parentNode!=this.root) {
					var found = false
					for(k=0;k<parentNodes.length;k++) {
						if(parentNode.itemId==parentNodes[k].itemId) {found = true; break;}
					}
					if(!found) parentNodes[parentNodes.length]=parentNode;
					parentNode = parentNode.parent;
				}
			}
		}
		for(i=0;i<parentNodes.length;i++) {
			selectedNodes[selectedNodes.length]=parentNodes[i];
		}
		return selectedNodes;
	}
}
alai_tree_check()