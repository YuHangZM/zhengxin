package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;


/**
 * �˻�״̬-ҵ������Ϊ���ÿ�
 * @author ����
 * 2007-6-22
 */
public class AccountStateByCreditCard extends AbsBusiProMap{

  private static final long serialVersionUID = 2003445450075369723L;

    static final Integer[] keys = {
				new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_NORMAL),
				new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_CONGEAL),
        new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_STOPPAYMENT),
        new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_DELACCOUNT),
        new Integer(BusiConst.ACCOUNTSTATEBYCREDITCARD_BADDEBT)
				};

		static final String[] values = { "����", "����","ֹ��","����","����" };
    public AccountStateByCreditCard()
		{
			this.putValues(keys,values);
		}
	}


