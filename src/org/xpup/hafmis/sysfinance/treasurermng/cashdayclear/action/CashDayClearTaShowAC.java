package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTaAF;


public class CashDayClearTaShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    saveToken(request);
    CashDayClearTaAF cashDayClearTaAF=null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    List officeList1 = null;
    List officeCodeList = new ArrayList();
    List credenceCharacterList1=null;
    List summrayList1=null;
    List settTypeList1=null;
    try {
      // ȡ���û�Ȩ�ް��´�,��ʾ�������˵���
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
        officeCodeList.add(officedto.getOfficeCode());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      Object[] obj=cashDayClearBS.findCredenceCharacterList(securityInfo,"");
      if(obj.length>0){
        //�����ݿ��в��ƾ֤�ֵ�list����ʾ�������˵���
        credenceCharacterList1=new ArrayList();
        List credenceCharacterList=(List)obj[0];
        if(credenceCharacterList.size()>0){
          BookParameterDTO bookParameterDTO = null;
          Iterator itr1 = credenceCharacterList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTO = (BookParameterDTO) itr1.next();
            credenceCharacterList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTO.getBookParameterName()
                , bookParameterDTO.getBookParameterId()));
          }
        }
        //�����ݿ��в��ժҪ��list����ʾ�������˵���
        summrayList1=new ArrayList();
        List summrayList=(List)obj[1];
        if(summrayList.size()>0){
          BookParameterDTO bookParameterDTO = null;
          Iterator itr1 = summrayList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTO = (BookParameterDTO) itr1.next();
            summrayList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTO.getBookParameterName()
                , bookParameterDTO.getBookParameterId()));
          }
        }
        //�����ݿ��в�����㷽ʽ��list����ʾ�������˵���
        settTypeList1=new ArrayList();
        List settTypeList=(List)obj[2];
        if(settTypeList.size()>0){
          BookParameterDTO bookParameterDTO = null;
          Iterator itr1 = settTypeList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTO = (BookParameterDTO) itr1.next();
            settTypeList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTO.getBookParameterName()
                , bookParameterDTO.getBookParameterId()));
          }
        }
      }
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      //type��Ϊ�˵�ҳ��������ʶ��0Ϊ�ֽ��ռ��ˣ�1Ϊ���д���ռ���
      if(request.getSession().getAttribute("cashDayClearTaAF")!=null){
        cashDayClearTaAF=(CashDayClearTaAF)request.getSession().getAttribute("cashDayClearTaAF");
      }else{
        cashDayClearTaAF=new CashDayClearTaAF();
        //��һ�ν���ҳ��ʱ�����´�Ĭ����ʾ��һ����¼
        cashDayClearTaAF.getCashDayClearTaDTO().setOffice(officeCodeList.get(0).toString());
        String credenceDate=cashDayClearBS.findCredenceDateByOffice(officeCodeList.get(0).toString(),credenceType,securityInfo);
        if(credenceDate!=null){
          cashDayClearTaAF.getCashDayClearTaDTO().setCredenceDate(credenceDate);
        }else{
          System.out.println(securityInfo.getUserInfo().getBizDate()+"================>");
          cashDayClearTaAF.getCashDayClearTaDTO().setCredenceDate(securityInfo.getUserInfo().getBizDate());
        }
      }
      if(cashDayClearTaAF.getType()=="1"){
        //�������Ϊ��ӻ��޸ĳɹ�ʱ���룡
        cashDayClearTaAF=new CashDayClearTaAF();
        if(request.getSession().getAttribute("office_gjp")!=null){
          //ֻ����ӳɹ��󣬰Ѹող������ݿ�İ��´�����ҳ��
          String office=(String)request.getSession().getAttribute("office_gjp");
          cashDayClearTaAF.getCashDayClearTaDTO().setOffice(office);
          if(request.getSession().getAttribute("credenceDate_gjp")!=null){
            String credenceDate=(String)request.getSession().getAttribute("credenceDate_gjp");
            cashDayClearTaAF.getCashDayClearTaDTO().setCredenceDate(credenceDate);
          }
        }
      }
      
      if(credenceType.equals("0")){
        request.setAttribute("type", "0");
      }
      if(credenceType.equals("1")){
        request.setAttribute("type", "1");
      }
      String bookSt=cashDayClearBS.findBookSt(securityInfo);
      if(!bookSt.equals("")){
        cashDayClearTaAF.setBookSt(bookSt);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    request.setAttribute("cashDayClearTaAF", cashDayClearTaAF);
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("credenceCharacterList1", credenceCharacterList1);
    request.setAttribute("summrayList1", summrayList1);
    request.setAttribute("settTypeList1", settTypeList1);
    return mapping.findForward("to_cashdayclearta_show");
  }
}
