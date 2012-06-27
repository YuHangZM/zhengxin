package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class BusinessLogSearch extends AbsBusiProMap {
  /**
   * ¬�֡�������ҵ����־��ѯ
   */
  private static final long serialVersionUID = 1L;

  static final String[] keys = { 
      BusiConst.BUSINESSLOGSEARCH_PAYMENT,
      BusiConst.BUSINESSLOGSEARCH_ORGADDPAY,
      BusiConst.BUSINESSLOGSEARCH_ORGOVERPAY,    
      BusiConst.BUSINESSLOGSEARCH_PICKUP,
      BusiConst.BUSINESSLOGSEARCH_TRANOUT,
      BusiConst.BUSINESSLOGSEARCH_TRANIN,    
      BusiConst.BUSINESSLOGSEARCH_CHGACCOUNT,     
      BusiConst.BUSINESSLOGSEARCH_OVERDUEINTEREST,    
      BusiConst.BUSINESSLOGSEARCH_SPECIALPICKUP, 
      BusiConst.BUSINESSLOGSEARCH_ADJUSTINTEREST,    
      BusiConst.BUSINESSLOGSEARCH_EMPADDPAY,     
      BusiConst.BUSINESSLOGSEARCH_ADJUSTPAYMENTRATE,
      BusiConst.BUSINESSLOGSEARCH_ADJUSTSALARYBASE,
      BusiConst.BUSINESSLOGSEARCH_ADJUSTPAYMENT,
      BusiConst.BUSINESSLOGSEARCH_CHGPERSON,
      BusiConst.BUSINESSLOGSEARCH_ORGOPENACCOUNT,
      BusiConst.BUSINESSLOGSEARCH_CHGORG
  
  };

  static final String[] values = { "���", "��λ����", "����","��ȡ","ת��","ת��","����","��Ϣ","������ȡ","���ʵ���","���˲���","��ɱ�������","���ʻ�������","�ɶ����","��Ա���","��λ����","��λ���"};

  public BusinessLogSearch() {
    this.putValues_Str(keys, values);
  }
}
