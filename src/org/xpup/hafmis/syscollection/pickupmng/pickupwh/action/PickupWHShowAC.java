package org.xpup.hafmis.syscollection.pickupmng.pickupwh.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.PickHead;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.VindicateListAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;
public class PickupWHShowAC extends Action {
  public static final String VINDICATE = "org.xpup.hafmis.syscollection.pickupmng.pickup.action.PickupWHShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      VindicateListAF submitForm = (VindicateListAF) form;// TODO Auto-generated method stub
      VindicateListAF result = new VindicateListAF();// TODO Auto-generated method stub
      IPickupBS pbs = (IPickupBS)BSUtils.getBusinessService("pickupBS",this,mapping.getModuleConfig());
      Pagination pa = null;
      if(submitForm.getSearch()!=null){//�����ѯ��ť��ʱ��...������������
        request.getSession().removeAttribute(VINDICATE);//������˲�ѯ��ť..��ô�������²�ѯ..�������Session
        pa = getPagination(VINDICATE, request);//���»��һ��Pagination����...
        pa.getQueryCriterions().put("orgId", submitForm.getDto().getOrgId());
        pa.getQueryCriterions().put("orgName", submitForm.getDto().getOrgName());
        pa.getQueryCriterions().put("noteNumber", submitForm.getDto().getNoteNumber());
        pa.getQueryCriterions().put("docNumber", submitForm.getDto().getDocNumber());
        pa.getQueryCriterions().put("start", submitForm.getDto().getStart());
        pa.getQueryCriterions().put("end", submitForm.getDto().getEnd());
        pa.getQueryCriterions().put("pickDate", submitForm.getDto().getPickDate());
        pa.getQueryCriterions().put("pickDate_end", submitForm.getDto().getPickDate_end());
        pa.getQueryCriterions().put("state", submitForm.getDto().getState());
        pa.getQueryCriterions().put("reason", submitForm.getDto().getReason());
        pa.getQueryCriterions().put("collectionBank", submitForm.getDto().getCollectionBank());
        pa.getQueryCriterions().put("select", "����Ա���˲�ѯ��ť");
      }else{//����ǵ�һ�ν���
        pa = this.getPagination(VINDICATE, request);
      }
      Map busiState = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
      Map reason = BusiTools.listBusiProperty(BusiConst.PICKUPREASON);
      pa = (Pagination)request.getSession().getAttribute(VINDICATE);//������ʲôʱ���������ط�������ֵ��...
      PaginationUtils.updatePagination(pa, request);
      //�������õ�����͡�����
      result.setBusiState(busiState);
      result.setReason(reason);
//      result.getDto().setState("1");
      
      SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      int type=sInfo.getIsOrgEdition();
      request.setAttribute("type", type+"");
      List list =pbs.getVindicatePageData(pa,sInfo);//��ñ�ҳ������ --- ��Ȩ�� ---
      List count =pbs.getVindicateAllDate(pa,sInfo);//��û��ܵļ��� --- ��Ȩ�� ---
      List taillist = new ArrayList();
      List templist = new ArrayList();
      if(count!=null && !count.isEmpty()){
        Iterator it = count.iterator();
        while(it.hasNext()){//��ȡΪ���ֵ
          PickHead h = (PickHead)it.next();
          taillist=pbs.querytailbyheadid(h.getId().toString(), sInfo);
           for (int i = 0; i < taillist.size(); i++) {
            PickTail tail = (PickTail)taillist.get(i);
            templist.add(tail);
          }
        }
       }
      
      count(count,result,templist);
    
      result.setList(list);
      
      if(list.size()>0){
        PickHead head = (PickHead)list.get(0);
        if(head.getPickSatatus().toString().equals("1")){
          result.setButtonState("1");
        }
        else if(head.getPickSatatus().toString().equals("3")){
          result.setButtonState("3");
        }
        else{
            result.setButtonState("other");
        }
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      List operList1 = null;
      List bankList1 = null;
      if (securityInfo != null) {
        List operList = securityInfo.getUserList();
        operList1 = new ArrayList();
        User user = null;
        Iterator itr2 = operList.iterator();
        while (itr2.hasNext()) {
          user = (User) itr2.next();
          operList1.add(new org.apache.struts.util.LabelValueBean(user
              .getUsername(), user.getUsername()));
        }
        request.getSession(true).setAttribute("operList1", operList1);

        List bankList = securityInfo.getCollBankList();
          bankList1 = new ArrayList();
        Userslogincollbank bankdto = null;   
        Iterator itr3 = bankList.iterator();    
        while (itr3.hasNext()) {
          bankdto = (Userslogincollbank) itr3.next();   
          bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("bankList1", bankList1);
      }
      //saveDisplayValue(pa, result);������������ò�ѯ��������ʾ���������,����ҵ��Ҫ��Ҫ��ʾ...
      request.getSession().setAttribute("info",result);
    }catch(Exception s){
      s.printStackTrace();
    }
    
    return new ActionForward("/pickupwh.jsp");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {//��һ�ν�����ʱ��...
      pagination = new Pagination(0,10,1,"p.id","desc",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  private void count(List list,VindicateListAF result,List taillist){
    int sumPerson = 0;
    int countmon = 0;
    int countcls = 0;
    int countrest = 0;
    BigDecimal sumTotal = new BigDecimal(0.00);
    BigDecimal sumBalance = new BigDecimal(0.00); 
    BigDecimal sumInterest = new BigDecimal(0.00);
    BigDecimal moneymon = new BigDecimal(0.00);
    BigDecimal moneycls = new BigDecimal(0.00);
    BigDecimal moneyrest = new BigDecimal(0.00);
    int count_xh=0;
    BigDecimal sum_xh = new BigDecimal(0.00);
    if(list!=null && !list.isEmpty()){
      Iterator it = list.iterator();
      while(it.hasNext()){//ѭ���ۼӸ��ֱ���...�����ݻ���
        PickHead h = (PickHead)it.next();
        sumPerson = sumPerson + h.getPickPersonCount().intValue();
        sumTotal = sumTotal.add(h.getPickMoneyCount());
        sumBalance = sumBalance.add(h.getPickBalance());
        sumInterest = sumInterest.add(h.getDistroyInterest());
      }
    if(taillist!=null&& !taillist.isEmpty()){
      Iterator it1 = taillist.iterator();
      while(it1.hasNext()){
        PickTail t=(PickTail)it1.next();
        if(t.getPickReason()!=null){
        if(t.getPickReason().equals(new BigDecimal(2))){
          countmon++;
          moneymon = moneymon.add(t.getTotal());
        }else{
          if(t.getPickReason().equals(new BigDecimal(3))){
            countcls++; 
            moneycls = moneycls.add(t.getTotal());
          }else{
            if(t.getPickReason().equals(new BigDecimal(1))
                ||t.getPickReason().equals(new BigDecimal(4))
                ||t.getPickReason().equals(new BigDecimal(5))
                ||t.getPickReason().equals(new BigDecimal(6))){
              countrest++;
              moneyrest = moneyrest.add(t.getTotal());
            }
            else{
              //������ȡ�ϼ�:
              count_xh++;
              sum_xh=sum_xh.add(t.getPickCurBalance()).add(t.getPickPreBalance());
            }
          }
        }
       }
      }
    }
      //�ѻ��ܵ����ݷ���Form��������...������ʾ����
      result.setSumPerson(sumPerson);
      result.setSumBalance(sumBalance);
      result.setSumInterest(sumInterest);
      result.setSumTotal(sumTotal);
      result.setCountmon(countmon);
      result.setMoneymon(moneymon);
      result.setMoneycls(moneycls);
      result.setCountcls(countcls);
      result.setCountrest(countrest);
      result.setMoneyrest(moneyrest);
      result.setCount_xh(count_xh);
      result.setSum_xh(sum_xh);
    }
  }
}