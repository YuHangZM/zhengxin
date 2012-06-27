
package org.xpup.hafmis.common.util.bizlog;
/**
 * ҵ����־������
 * @author ����
 *2007-6-2
 */
public class BusiLogConst {

  public BusiLogConst() {

  }

  /*����ҵ��ϵͳ����*/
  public static final String OP_SYSTEM_TYPE = "org.xpup.hafmis.common.util.bizlog.OpSystemType";
  
  /**
   * ����ҵ��ϵͳ���͡�ס��������ϵͳ����ϵͳ
   */
  public static final int OP_SYSTEM_TYPE_MANAGER=0;
  /**
   * ����ҵ��ϵͳ���͡�ס��������鼯ϵͳ
   */
  public static final int OP_SYSTEM_TYPE_COLLECTION =1;
  /**
   * ����ҵ��ϵͳ���͡�ס�����������ϵͳ
   */
  public static final int OP_SYSTEM_TYPE_LOAN =2;
  /**
   * ����ҵ��ϵͳ���͡�ס�����������ϵͳ
   */
  public static final int OP_SYSTEM_TYPE_FINANCE =3;
  
  /*����ģ��-��������*/
  public static final String OP_MODE_OPEN = "org.xpup.hafmis.common.util.bizlog.OpModeOpen";
  /**
   * ����ģ�顪��λ����-������
   */
  public static final int OP_MODE_OPEN_ORGOPEN_DO=11;
  /**
   * ����ģ�顪��λ����-����ά��
   */
  public static final int OP_MODE_OPEN_ORGOPEN_MAINTAIN=12;
  /**
   * ����ģ�顪ְ������
   */
  public static final int OP_MODE_OPEN_EMPOPEN=13;
  /**
   * ����ģ�顪��λ���-������
   */
  public static final int OP_MODE_OPEN_ORGCHG_DO=14;
  /**
   * ����ģ�顪��λ���-���ά��
   */
  public static final int OP_MODE_OPEN_ORGCHG_MAINTAIN=15;
  
  /*����ģ��-���ҵ��*/
  public static final String OP_MODE_CHANGE = "org.xpup.hafmis.common.util.bizlog.OpModeChange";
  /**
   * ����ģ�顪���ҵ��-��ɱ�������-������
   */
  public static final int OP_MODE_CHANGE_CHGRATE_DO=21;
  /**
   * ����ģ�顪���ҵ��-��ɱ�������-���ά��
   */
  public static final int OP_MODE_CHANGE_CHGRATE_MAINTAIN=22;
  /**
   * ����ģ�顪���ҵ��-���ʻ�������-������
   */
  public static final int OP_MODE_CHANGE_CHGSALARYBASE_DO=23;
  /**
   * ����ģ�顪���ҵ��-���ʻ�������-���ά��
   */
  public static final int OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN=24;
  /**
   * ����ģ�顪���ҵ��-�ɶ����-������
   */
  public static final int OP_MODE_CHANGE_CHGPAYMENT_DO=25;
  /**
   * ����ģ�顪���ҵ��-�ɶ����-���ά��
   */
  public static final int OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN=26;
  /**
   * ����ģ�顪���ҵ��-��Ա���-������
   */
  public static final int OP_MODE_CHANGE_CHGPERSON_DO=27;
  /**
   * ����ģ�顪���ҵ��-��Ա���-���ά��
   */
  public static final int OP_MODE_CHANGE_CHGPERSON_MAINTAIN=28;
  
  /*����ģ��-�ɴ����*/
  public static final String OP_MODE_PAYMENTMANAGE = "org.xpup.hafmis.common.util.bizlog.OpModePaymentManage";
  /**
   * ����ģ�顪�ɴ����-�������-����ɴ�
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENT_DO=31;
  /**
   * ����ģ�顪�ɴ����-�������-�ɴ�ά��
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN=32;
  /**
   * ����ģ�顪�ɴ����-��λ����-����ɴ�
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPAY_DO=33;
  /**
   * ����ģ�顪�ɴ����-��λ����-�ɴ�ά��
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN=34;
  /**
   * ����ģ�顪�ɴ����-���˲���-����ɴ�
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO=35;
  /**
   * ����ģ�顪�ɴ����-���˲���-�ɴ�ά��
   */
  public static final int OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN=36;
  /**
   * ����ģ�顪�ɴ����-��λ����-�������
   */
  public static final int OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO=37;
  /**
   * ����ģ�顪�ɴ����-��λ����-����ά��
   */
  public static final int OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN=38;
  /**
   * ����ģ�顪�ɴ����-�ɴ浽��ȷ��
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM=39;
  /**
   * ����ģ�顪�ɴ����-��������
   */
  public static final int OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT=310;
  
  /*����ģ��-��ȡ����*/
  public static final String OP_MODE_DRAWING = "org.xpup.hafmis.common.util.bizlog.OpModeDrawing";
  /**
   * ����ģ��-��ȡ����-������ȡ-������ȡ
   */
  public static final int OP_MODE_DRAWING_DRAWING_DO=41;
  /**
   * ����ģ�顪��ȡ����-������ȡ-��ȡά��
   */
  public static final int OP_MODE_DRAWING_DRAWING_MAINTAIN=42;
  /**
   * ����ģ��-��ȡ����-����������ȡ-������ȡ
   */
  public static final int OP_MODE_DRAWING_SPECIALDRAWING_DO=43;
  /**
   * ����ģ�顪��ȡ����-����������ȡ-��ȡά��
   */
  public static final int OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN=44;
  /**
   * ����ģ�顪�����𻹴�
   */
  public static final int OP_MODE_DRAWING_COLLLOANBACK=45;
  
  /*����ģ��-ת��ת��*/
  public static final String OP_MODE_TRANINOUT = "org.xpup.hafmis.common.util.bizlog.OpModeTranInOut";
  /**
   * ����ģ��-ת��ת��-����ת��-����ת��
   */
  public static final int OP_MODE_TRANINOUT_TRANOUT_DO=51;
  /**
   * ����ģ�顪ת��ת��-����ת��-ת��ά��
   */
  public static final int OP_MODE_TRANINOUT_TRANOUT_MAINTAIN=52;
  /**
   * ����ģ��-ת��ת��-����ת��-��ת��Ǽ�
   */
  public static final int OP_MODE_TRANINOUT_TRANIN_PREPARE=53;
  /**
   * ����ģ��-ת��ת��-����ת��-ת��Ǽ�
   */
  public static final int OP_MODE_TRANINOUT_TRANIN_CHECKIN=54;
  /**
   * ����ģ�顪ת��ת��-����ת��-ת��ά��
   */
  public static final int OP_MODE_TRANINOUT_TRANIN_MAINTAIN=55;
  
  /*����ģ��-������*/
  public static final String OP_MODE_ACCOUNTMANAGE = "org.xpup.hafmis.common.util.bizlog.OpModeAccountManage";
  /**
   * ����ģ�顪������-ҵ�񸴺�
   */
  public static final int OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK =61;
  /**
   * ����ģ�顪������-���˵���-������˵���
   */
  public static final int OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO=62;
  /**
   * ����ģ�顪������-���˵���-���˵���ά��
   */
  public static final int OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN=63;
  /**
   * ����ģ�顪������-����-����
   */
  public static final int OP_MODE_ACCOUNTMANAGE_ACCOUNTING_DO=64;
  /**
   * ����ģ�顪������-����-���㵥��ѯ
   */
  public static final int OP_MODE_ACCOUNTMANAGE_ACCOUNTING_INQUIRIES=65;
  /**
   * ����ģ�顪������-�ս�-����
   */
  public static final int OP_MODE_ACCOUNTMANAGE_DAYCLEANING_DO=66;
  /**
   * ����ģ�顪������-�ս�-���㵥��ѯ
   */
  public static final int OP_MODE_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES=67;
  /**
   * ����ģ�顪������-�½�-����
   */
  public static final int OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_DO=68;
  /**
   * ����ģ�顪������-�½�-���㵥��ѯ
   */
  public static final int OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_INQUIRIES=69;
  /**
   * ����ģ�顪������-���ս�Ϣ
   */
  public static final int OP_MODE_ACCOUNTMANAGE_INTEREST=610;
  /**
   * ����ģ�顪������-ά������
   */
  public static final int OP_MODE_ACCOUNTMANAGE_MAINTAINRATE=611;
  
  /**
   * ����ģ�顪�����������ݵ���-�������Ĳ�������
   */
  public static final int OP_MODE_BANKDATAEXP_RECORDPARAMSETTING=71;
  
  /**
   * ����ģ�顪�����������ݵ���-��������ͷ����
   */
  public static final int OP_MODE_BANKDATAEXP_RECORDHEADSETTING=72;
  
  /**
   * ����ģ�顪�����������ݵ���-��������
   */
  public static final int OP_MODE_BANKDATAEXP_RECORD=73;
  
  /*�鼯ȫҵ��ģ��*/
  public static final String OPMODECOLL = "org.xpup.hafmis.common.util.bizlog.OpModeColl";
  
  /*����ȫҵ��ģ��*/
  public static final String OPMODELOAN = "org.xpup.hafmis.common.util.bizlog.OpModeLoan";
  
  /*����ȫҵ��ģ��*/
  public static final String OPMODEFINANCE = "org.xpup.hafmis.common.util.bizlog.OpModeFinance";
  
  /*ҵ����־��Ĳ�����Ϊ*/
  public static final String BIZLOG_ACTION = "org.xpup.hafmis.common.util.bizlog.BizlogAction";
  /**
   * ҵ����־��Ĳ�����Ϊ�����
   */
  public static final int BIZLOG_ACTION_ADD =1;
  /**
   * ҵ����־��Ĳ�����Ϊ���޸�
   */
  public static final int BIZLOG_ACTION_MODIFY =2;
  /**
   * ҵ����־��Ĳ�����Ϊ��ɾ��
   */
  public static final int BIZLOG_ACTION_DELETE =3;
  /**
   * ҵ����־��Ĳ�����Ϊ����ѯ
   */
  public static final int BIZLOG_ACTION_QUERY =4;
  /**
   * ҵ����־��Ĳ�����Ϊ������
   */
  public static final int BIZLOG_ACTION_EXP =5;
  /**
   * ҵ����־��Ĳ�����Ϊ������
   */
  public static final int BIZBLOG_ACTION_IMP =6;
  /**
   * ҵ����־��Ĳ�����Ϊ������
   */
  public static final int BIZLOG_ACTION_OPENING =7;
  /**
   * ҵ����־��Ĳ�����Ϊ������
   */
  public static final int BIZLOG_ACTION_REVOCATION =8;
  /**
   * ҵ����־��Ĳ�����Ϊ��ȫ��ɾ��
   */
  public static final int BIZLOG_ACTION_DELETEALL =9;
  /**
   * ҵ����־��Ĳ�����Ϊ������
   */
  public static final int BIZLOG_ACTION_CHECKS =10;
  /**
   * ҵ����־��Ĳ�����Ϊ��ȷ��
   */
  public static final int BIZLOG_ACTION_CONFIRM =11;
  /**
   * ҵ����־��Ĳ�����Ϊ����Ϣ
   */
  public static final int BIZLOG_ACTION_INTEREST =12;
  /**
   * ҵ����־��Ĳ�����Ϊ������
   */
  public static final int BIZLOG_ACTION_ACCOUNTIN =13;
  /**
   * ҵ����־��Ĳ�����Ϊ����ɵ���
   */
  public static final int BIZLOG_ACTION_ENDCHG =14;
  /**
   * ҵ����־��Ĳ�����Ϊ�����ͨ��
   */
  public static final int BIZLOG_ACTION_AUDITINGPASS =15;
  /**
   * ҵ����־��Ĳ�����Ϊ����˲�ͨ��
   */
  public static final int BIZLOG_ACTION_AUDITING =16;
  /**
   * ҵ����־��Ĳ�����Ϊ���������
   */
  public static final int BIZLOG_ACTION_AUDITINGQUASH =17;
  /**
   * ҵ����־��Ĳ�����Ϊ������ͨ��
   */
  public static final int BIZLOG_ACTION_PPROVALPASS =18;
  /**
   * ҵ����־��Ĳ�����Ϊ��������ͨ��
   */
  public static final int BIZLOG_ACTION_PPROVAL =19;
  /**
   * ҵ����־��Ĳ�����Ϊ����������
   */
  public static final int BIZLOG_ACTION_PPROVALQUASH =20;
  /**
   * ҵ����־��Ĳ�����Ϊ�����ս�ת
   */
  public static final int BIZLOG_ACTION_CARRYFORWARD =21;
  /**
   * ҵ����־��Ĳ�����Ϊ���ύ����
   */
  public static final int BIZLOG_ACTION_REFERRINGDATE =22;
  /**
   * ҵ����־��Ĳ�����Ϊ�������ύ����
   */
  public static final int BIZLOG_ACTION_PPROVALDATA =23;
  /**
   * ҵ����־��Ĳ�����Ϊ����ȡ����
   */
  public static final int BIZLOG_ACTION_PICKUPDATA =24;
  /**
   * ҵ����־��Ĳ�����Ϊ����ת���
   */
  public static final int BIZLOG_ACTION_CARRYFORWORDBALANCE =25;

  /*����ģ��-����-����׼��*/
  public static final String PL_OP_DATAPREPARATION = "org.xpup.hafmis.common.util.bizlog.PlOpDataPreparation";
  /**
   * ����ģ��-����-����׼��-����ά��
   */
  public static final int PL_OP_DATAPREPARATION_RATE=11;
  /**
   * ����ģ��-����-����׼��-����ά��
   */
  public static final int PL_OP_DATAPREPARATION_BANK=12;
  /**
   * ����ģ��-����-����׼��-����ά��
   */
  public static final int PL_OP_DATAPREPARATION_PARAMETERS=13;
  /**
   * ����ģ��-����-����׼��-��ǰ�������ά��
   */
  public static final int PL_OP_DATAPREPARATION_PREPAYMENTPARAM=14;
  /**
   * ����ģ��-����-����׼��-������ά��
   */
  public static final int PL_OP_DATAPREPARATION_DEVELOPERS=15;
  /**
   * ����ģ��-����-����׼��-������˾ά��
   */
  public static final int PL_OP_DATAPREPARATION_GUARANTEECORP=16;
  /**
   * ����ģ��-����-����׼��-�������ά��
   */
  public static final int PL_OP_DATAPREPARATION_AGENCIES=17;
  /**
   * ����ģ��-����-����׼��-���չ�˾ά��
   */
  public static final int PL_OP_DATAPREPARATION_INSURANCECOMP=18;
  /**
   * ����ģ��-����-����׼��-��������ά��
   */
  public static final int PL_OP_DATAPREPARATION_ASSESSMENTAGEN=19;
  /**
   * ����ģ��-����-����׼��-��֤��ά��
   */
  public static final int PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE=110;
  /**
   * ����ģ��-����-����׼��-������ά��-¥��
   */
  public static final int PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR=111;
  /**
   * ����ģ��-����-����׼��-������ά��-¥��
   */
  public static final int PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_BUILD=115;
  /**
   * ����ģ��-����-����׼��-�����𻹴���������
   */
  public static final int PL_OP_DATAPREPARATION_COLLLOANBACKPARA=112;
  /**
   * ����ģ��-����-����׼��-���л��ĸ�ʽ����
   */
  public static final int PL_OP_DATAPREPARATION_BANKPALINDROME=113;
  /**
   * ����ģ��-����-����׼��-���л��Ķ�Ӧ��ʽ����
   */
  public static final int PL_OP_DATAPREPARATION_PALINDROMFORMAT=114;

  /*����ģ��-����-�������*/
  public static final String PL_OP_LOANAPPL = "org.xpup.hafmis.common.util.bizlog.PlOpLoanAppl";
  /**
   * ����ģ��-����-�������-�������-�������Ϣ
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO=21;
  /**
   * ����ģ��-����-�������-�������-�����������Ϣ
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO=22;
  /**
   * ����ģ��-����-�������-�������-������Ϣ
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_HOUSEINFO=23;
  /**
   * ����ģ��-����-�������-�������-����˶����Ϣ
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_BORROWERINFOLIMITED=24;
  /**
   * ����ģ��-����-�������-�������-�������ά��
   */
  public static final int PL_OP_LOANAPPL_LOANAPPL_LOANAPPLMAINTAIN=25;
  /**
   * ����ģ��-����-�������-��������-������������
   */
  public static final int PL_OP_LOANAPPL_SPECIALAPPL_DO=26;
  /**
   * ����ģ��-����-�������-��������-��������ά��
   */
  public static final int PL_OP_LOANAPPL_SPECIALAPPL_MAINTAIN=27;
  /**
   * ����ģ��-����-�������-��˴���
   */
  public static final int PL_OP_LOANAPPL_LOANAUDIT=28;
  /**
   * ����ģ��-����-�������-��������
   */
  public static final int PL_OP_LOANAPPL_LOANAPPROVAL=29;
  /**
   * ����ģ��-����-�������-ǩ����ͬ-����ͬ��Ϣ
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_LOANCONTRACT=210;
  /**
   * ����ģ��-����-�������-ǩ����ͬ-��Ѻ��ͬ��Ϣ
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT=211;
  /**
   * ����ģ��-����-�������-ǩ����ͬ-��Ѻ��ͬ��Ϣ
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_IMPAWNCONTRACT=212;
  /**
   * ����ģ��-����-�������-ǩ����ͬ-��֤����Ϣ
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER=213;
  /**
   * ����ģ��-����-�������-ǩ����ͬ-ǩ����ͬά��
   */
  public static final int PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN=214;
  /**
   * ����ģ��-����-�������-�տ��˺��޸�-�տ��˺��޸�
   */
  public static final int PL_OP_LOANAPPL_GATHERINGACC_DO=215;
  /**
   * ����ģ��-����-�������-�տ��˺��޸�-�տ��˺�ά��
   */
  public static final int PL_OP_LOANAPPL_GATHERINGACC_MAINTAIN=216;
  /**
   * ����ģ��-����-�������-�����˺��޸�-�����˺��޸�
   */
  public static final int PL_OP_LOANAPPL_GIVEACC_DO=217;
  /**
   * ����ģ��-����-�������-�����˺��޸�-�����˺�ά��
   */
  public static final int PL_OP_LOANAPPL_GIVEACC_MAINTAIN=218;
  /**
   * ����ģ��-����-�������-�´﷢��֪ͨ��-�´﷢��֪ͨ��
   */
  public static final int PL_OP_LOANAPPL_ISSUEDNOTICE_DO=219;
  /**
   * ����ģ��-����-�������-�´﷢��֪ͨ��-����֪ͨ��ά��
   */
  public static final int PL_OP_LOANAPPL_ISSUEDNOTICE_MAINTAIN=220;
  /**
   * ����ģ��-����-�������-ɾ�������ͬ
   */
  public static final int PL_OP_LOANAPPL_DELCONTRACT=221;
  /**
   * ����ģ��-����-�������-�����𻹴�ǩ����ͬ
   */
  public static final int PL_OP_LOANRETURN_MAINTAIN=222;

  /*����ģ��-����-���Ŵ���*/
  public static final String PL_OP_LOANISSUED = "org.xpup.hafmis.common.util.bizlog.PlOpLoanIssued";
  /**
   * ����ģ��-����-���Ŵ���-���Ŵ���-������
   */
  public static final int PL_OP_LOANISSUED_DO=31;
  /**
   * ����ģ��-����-���Ŵ���-���Ŵ���-����ά��
   */
  public static final int PL_OP_LOANISSUED_MAINTAIN=32;
  /**
   * ����ģ��-����-���Ŵ���-��ӡ����ƻ���
   */
  public static final int PL_OP_LOANISSUED_PRINTRESTORELOAN=33;

  /*����ģ��-����-���մ���*/
  public static final String PL_OP_LOANRECOVER = "org.xpup.hafmis.common.util.bizlog.PlOpLoanRecover";
  /**
   * ����ģ��-����-���մ���-������ѯ
   */
  public static final int PL_OP_LOANRECOVER_RECOVERQUIRY=41;
  /**
   * ����ģ��-����-���մ���-���մ���-�������
   */
  public static final int PL_OP_LOANRECOVER_DO=42;
  /**
   * ����ģ��-����-���մ���-���մ���-����ά��
   */
  public static final int PL_OP_LOANRECOVER_MAINTAIN=43;
  /**
   * ����ģ��-����-���մ���-���д��۵���
   */
  public static final int PL_OP_LOANRECOVER_LOANKOUEXP=44;
  /**
   * ����ģ��-����-���մ���-���д��۵���
   */
  public static final int PL_OP_LOANRECOVER_LOANKOUIMP=45;
  /**
   * ����ģ��-����-���մ���-���˺���-������˺���
   */
  public static final int PL_OP_LOANRECOVER_BADDEBTSOFF_DO=46;
  /**
   * ����ģ��-����-���մ���-���˺���-���˺���ά��
   */
  public static final int PL_OP_LOANRECOVER_BADDEBTSOFF_MAINTAIN=47;
  /**
   * ����ģ��-����-���մ���-�����ջ�-��������ջ�
   */
  public static final int PL_OP_LOANRECOVER_CANRECOVER_DO=48;
  /**
   * ����ģ��-����-���մ���-�����ջ�-�����ջ�ά��
   */
  public static final int PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN=49;
  /**
   * ����ģ��-����-���մ���-��Ѻ��Ѻ���-�����Ѻ��Ѻ���
   */
  public static final int PL_OP_LOANRECOVER_LIVING_DO=410;
  /**
   * ����ģ��-����-���մ���-��Ѻ��Ѻ���-��Ѻ��Ѻ���ά��
   */
  public static final int PL_OP_LOANRECOVER_LIVING_MAINTAIN=411;
  /**
   * ����ģ��-����-���մ���-���ע��-���ע������
   */
  public static final int PL_OP_LOANRECOVER_LOANERLOGOUT_DO=412;
  /**
   * ����ģ��-����-���մ���-���ע��-���ע��ά��
   */
  public static final int PL_OP_LOANRECOVER_LOANERLOGOUT_MAINTAIN=413;

  /*����ģ��-����-������*/
  public static final String PL_OP_ACCOUNTMANAGE = "org.xpup.hafmis.common.util.bizlog.PlOpAccountMange";
  /**
   * ����ģ��-����-������-ҵ�񸴺�
   */
  public static final int PL_OP_ACCOUNTMANAGE_OPERATIONCHECK=51;
  /**
   * ����ģ�顪����-������-����-����
   */
  public static final int PL_OP_ACCOUNTMANAGE_ACCOUNTING_DO=52;
  /**
   * ����ģ�顪����-������-����-���㵥��ѯ
   */
  public static final int PL_OP_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES=53;
  /**
   * ����ģ�顪������������-���˳���-������˵���
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO=54;
  /**
   * ����ģ�顪������������-���˳���-���˵���ά��
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN=55;
  /**
   * ����ģ�顪������������-����-�������
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_DO=56;
  /**
   *����ģ�顪������������-����-����ά��
   */
  public static final int PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_MAINTAIN=57;
  /**
   * ����ģ��-����-������-���ڽ�ת
   */
  public static final int PL_OP_ACCOUNTMANAGE_LATECARRYOVER=58;
  /**
   * ����ģ��-����-������-�ս�
   */
  public static final int PL_OP_ACCOUNTMANAGE_DAYCLEANING=59;
  /**
   * ����ģ��-����-������-��ĩ��ת
   */
  public static final int PL_OP_ACCOUNTMANAGE_MONTHLATECARRYOVER=510;
  /**
   * ����ģ��-����-������-���ս�ת
   */
  public static final int PL_OP_ACCOUNTMANAGE_CARRYFORWARD=511;

  /*����ģ��-����-��ͬ���*/
  public static final String PL_OP_CONTRACTCHG = "org.xpup.hafmis.common.util.bizlog.PlOpContractChg";
  /**
   * ����ģ��-����-��ͬ���-������Ϣ���-�������Ϣ
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO=61;
  /**
   * ����ģ��-����-��ͬ���-������Ϣ���-�����������Ϣ
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO=62;
  /**
   * ����ģ��-����-��ͬ���-������Ϣ���-������Ϣ
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO=63;
  /**
   * ����ģ��-����-��ͬ���-������Ϣ���-������Ϣ���ά��
   */
  public static final int PL_OP_CONTRACTCHG_BASEMESSINFOCHG_MAINTAIN=64;
  /**
   * ����ģ��-����-��ͬ���-������Ѻ���-��Ѻ��ͬ��Ϣ
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT=65;
  /**
   * ����ģ��-����-��ͬ���-������Ѻ���-��Ѻ��ͬ��Ϣ
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT=66;
  /**
   * ����ģ��-����-��ͬ���-������Ѻ���-��֤����Ϣ
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER=67;
  /**
   * ����ģ��-����-��ͬ���-������Ѻ���-������Ѻ���ά��
   */
  public static final int PL_OP_CONTRACTCHG_GUARANTEECHG_CONTRACTMAINTAIN=68;
  /**
   * ����ģ��-����-��ͬ���-������Ϣ���
   */
  public static final int PL_OP_CONTRACTCHG_SPECIALINFOCHG=69;

  /*����ģ��-����-����ҵ����*/
  public static final String PL_OP_SPECIALBUSS = "org.xpup.hafmis.common.util.bizlog.PlOpSpecialBuss";
  /**
   * ����ģ��-����-����ҵ����-�弶�����޸�-ҵ�����
   */
  public static final int PL_OP_SPECIALBUSS_FIVELEVAL_DO=71;
  /**
   * ����ģ��-����-����ҵ����-�弶�����޸�-ҵ��ά��
   */
  public static final int PL_OP_SPECIALBUSS_FIVELEVAL_MAINTAIN=72;
  /**
   * ����ģ��-����-����ҵ����-��֤��Ǽ�-ҵ�����
   */
  public static final int PL_OP_SPECIALBUSS_BONDREGIST_DO=73;
  /**
   * ����ģ��-����-����ҵ����-��֤��Ǽ�-ҵ��ά��
   */
  public static final int PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN=74;
  /**
   * ����ģ��-����-����ҵ����-��֤���Ϣ-ҵ�����
   */
  public static final int PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO=75;
  /**
   * ����ģ��-����-����ҵ����-��֤���Ϣ-ҵ��ά��
   */
  public static final int PL_OP_SPECIALBUSS_BAILCLEARINTEREST_MAINTAIN=76;

  /*����ģ��-����-���׹���*/
  public static final String FN_OP_BOOKMNG = "org.xpup.hafmis.common.util.bizlog.FnOpBookmng";
  /**
   * ����ģ��-����-���׹���-��ƿ�Ŀ
   */
  public static final int FN_OP_BOOKMNG_SUBJECT =11;
  /**
   * ����ģ��-����-���׹���-��ʼ����
   */
  public static final int FN_OP_BOOKMNG_DATAINITIALIZE =12;
  /**
   * ����ģ��-����-���׹���-��������
   */
  public static final int FN_OP_BOOKMNG_BOOKSTART =13;
  /**
   * ����ģ��-����-���׹���-ƾ֤��
   */
  public static final int FN_OP_BOOKMNG_CREDENCECHAR =14;
  /**
   * ����ģ��-����-���׹���-���㷽ʽ
   */
  public static final int FN_OP_BOOKMNG_SETTLEMODLE =15;
  /**
   * ����ģ��-����-���׹���-����ժҪ
   */
  public static final int FN_OP_BOOKMNG_SUMMARY =16;
  /**
   * ����ģ��-����-���׹���-��Ŀ��ϵ����
   */
  public static final int FN_OP_BOOKMNG_SUBJECTRELATION =17;
  /**
   * ����ģ��-����-���׹���-ƾ֤ģʽ����
   */
  public static final int FN_OP_BOOKMNG_CREDENCEMODLE =18;
  /**
   * ����ģ��-����-���׹���-�����ת����
   */
  public static final int FN_OP_BOOKMNG_SETTLEINCADDEC =19;
  /**
   * ����ģ��-����-���׹���-��������
   */
  public static final int FN_OP_BOOKMNG_CREATEBOOK =110;

  /*����ģ��-����-������*/
  public static final String FN_OP_ACCOUNTHANDLE = "org.xpup.hafmis.common.util.bizlog.FnOpAccounthandle";
  /**
   * ����ģ��-����-������-ƾ֤¼��-ƾ֤¼��
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN =211;
  /**
   * ����ģ��-����-������-ƾ֤¼��-�Զ�ת��
   */
  public static final int FN_OP_ACCOUNTHANDLE_AUTOTRANSFERS =212;
  /**
   * ����ģ��-����-������-ƾ֤¼��-�����ת 
   */
  public static final int FN_OP_ACCOUNTHANDLE_CARRYOVERPROFITLOSS =213;
  /**
   * ����ģ��-����-������-ƾ֤¼��-ƾ֤ά��
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN =214;
  /**
   * ����ģ��-����-������-ƾ֤���
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCECHECK =22;
  /**
   * ����ģ��-����-������-ƾ֤����
   */
  public static final int FN_OP_ACCOUNTHANDLE_CREDENCECLEAR =23;

  /*����ģ��-����-���ɹ���*/
  public static final String FN_OP_TREASURERMNG = "org.xpup.hafmis.common.util.bizlog.FnOpTreasurermng";
  /**
   * ����ģ��-����-���ɹ���-����ʼ
   */
  public static final int FN_OP_TREASURERMNG_BALANCEINITIALIZE =31;
  /**
   * ����ģ��-����-���ɹ���-�ֽ��ռ���-�ֽ��ռ���
   */
  public static final int FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR =321;
  /**
   * ����ģ��-����-���ɹ���-�ֽ��ռ���-�Զ�ת��
   */
  public static final int FN_OP_TREASURERMNG_CASHDAYCLEAR_AUTOCASHDAYCLEAR =322;
  /**
   * ����ģ��-����-���ɹ���-�ֽ��ռ���-�ֽ��ռ���ά��
   */
  public static final int FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN =323;
  /**
   * ����ģ��-����-���ɹ���-���д���ռ���-���д���ռ���
   */
  public static final int FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEAR =331;
  /**
   * ����ģ��-����-���ɹ���-���д���ռ���-�Զ�ת��
   */
  public static final int FN_OP_TREASURERMNG_BANKDAYCLEAR_AUTOBANKDAYCLEAR =332;
  /**
   * ����ģ��-����-���ɹ���-���д���ռ���-���д���ռ���ά��
   */
  public static final int FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN =333;
  /**
   * ����ģ��-����-���ɹ���-���ж��˵�-���ж��˵�
   */
  public static final int FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACC =341;
  /**
   * ����ģ��-����-���ɹ���-���ж��˵�-���ж��˵�ά��
   */
  public static final int FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN =342;
  /**
   * ����ģ��-����-���ɹ���-���д����˵�
   */
  public static final int FN_OP_TREASURERMNG_DEPOSITCHECKACC =35;
  /**
   * ����ģ��-����-���ɹ���-��������
   */
  public static final int FN_OP_TREASURERMNG_CLEARACCOUNT =36;

  /*����ģ��-����-�˲�����*/
  public static final String FN_OP_ACCMNG = "org.xpup.hafmis.common.util.bizlog.FnOpAccmng";
  /**
   * ����ģ��-����-�˲�����-����
   */
  public static final int FN_OP_ACCMNG_TOTLEACC =41;
  /**
   * ����ģ��-����-�˲�����-��ϸ��
   */
  public static final int FN_OP_ACCMNG_LISTACC =42;
  /**
   * ����ģ��-����-�˲�����-��ʱ��
   */
  public static final int FN_OP_ACCMNG_SEQUENCEACC =43;
  /**
   * ����ģ��-����-�˲�����-ƾ֤���ܱ�
   */
  public static final int FN_OP_ACCMNG_SUBJECTMUSTER =44;
  /**
   * ����ģ��-����-�˲�����-��Ŀ�ձ���
   */
  public static final int FN_OP_ACCMNG_SUBJECTDAYREPORT =45;
  /**
   * ����ģ��-����-�˲�����-�ֽ��ռ���
   */
  public static final int FN_OP_ACCMNG_CASHDAYCLEARREPORT =46;
  /**
   * ����ģ��-����-�˲�����-���д���ռ���
   */
  public static final int FN_OP_ACCMNG_BANKDAYCLEARREPORT =47;
  /**
   * ����ģ��-����-�˲�����-��Ŀ����
   */
  public static final int FN_OP_ACCMNG_SUBJECTBALANCE =48;

  /*����ģ��-����-�������*/
  public static final String FN_OP_REPORTMNG = "org.xpup.hafmis.common.util.bizlog.FnOpReportmng";
  /**
   * ����ģ��-����-�������-���񱨱�-��������
   */
  public static final int FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT =51;
}