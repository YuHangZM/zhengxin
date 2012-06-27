package org.xpup.hafmis.common.util;

/**
 * ҵ������
 * 
 * @author ���� 2007-6-2
 */
public class BusiConst {

  private BusiConst() {

  }

  /**
   * ��������
   */
  /*
   * �ϴ�ͼƬ���ڷ�����λ��
   */
  public static final String UPLOAD_SERVER_PATH = "d:/serverimage";

  public static final String PUB_DATE_Y_PATTERN = "yyyy";

  public static final String PUB_DATE_D_PATTERN = "dd";

  public static final String PUB_DATE_YM_PATTERN = "yyyy-MM";

  /**
   * �����ڸ�ʽ
   */
  public static final String PUB_LONG_DATE_PATTERN = "yyyy-MM-dd";

  public static final String PUB_LONG_YMD_PATTERN = "yyyyMMdd";

  public static final String PUB_LONG_DATE_PATTERNS = "yyyyMM";

  /**
   * ��ʱ���ʽ
   */
  public static final String PUB_LONG_TIME_PATTERN = "HH:mm:ss";

  /**
   * ������ʱ���ʽ
   */
  public static final String PUB_LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

  /**
   * ������ʱ���ʽ��������
   */

  public static final String PUB_LONG_DATE_TIME_SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * ������ʱ���ʽ��������
   */

  public static final String PUB_LONG_DATE_TIME_SECOND_PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";

  /* ����״�� */
  public static final String MARRY_STATUS = "org.xpup.hafmis.common.util.enumerate.MarryStatus";

  /**
   * ����״������δ��
   */
  public static final int MARRY_STATUS_LIVE = 10;

  /**
   * ����״�������ѻ�
   */
  public static final int MARRY_STATUS_DEAD = 20;

  /* �Ա� */
  public static final String SEX = "org.xpup.hafmis.common.util.enumerate.Sex";

  /**
   * �Ա𡪡�δ֪���Ա�
   */
  public static final int SEX_UNKNOW = 0;

  /**
   * �Ա𡪡���
   */
  public static final int SEX_MALE = 1;

  /**
   * �Ա𡪡�Ů
   */
  public static final int SEX_FEMALE = 2;

  /**
   * �Ա𡪡�δ֪˵���Ա�
   */
  public static final int SEX_ILLUSTRATED = 9;

  /* ѡ��״̬-�Ƿ� */
  public static final String YesNo = "org.xpup.hafmis.common.util.enumerate.YesNo";

  /**
   * �Ƿ񡪡���
   */
  public static final int YES = 0;

  /**
   * �Ƿ񡪡���
   */
  public static final int NO = 1;

  /* ѡ��״̬-�Ƿ��Ϣ */
  public static final String YesNoInterest = "org.xpup.hafmis.common.util.enumerate.YesNoInterest";

  /**
   * �Ƿ��Ϣ������
   */
  public static final int YESINTEREST = 1;

  /**
   * �Ƿ��Ϣ������
   */
  public static final int NOINTEREST = 2;

  /* ��λ�ɴ淽ʽ */
  public static final String ORGPAYWAY = "org.xpup.hafmis.common.util.enumerate.OrgPayWay";

  /**
   * ��λ�ɴ淽ʽ-��һ����
   */
  public static final int ORGPAYWAY_RATE = 1;

  /**
   * ��λ�ɴ淽ʽ-ְ���ɶ�
   */
  public static final int ORGPAYWAY_PAYMENT = 2;

  /* ��λ״̬ */
  public static final String ORGSTATE = "org.xpup.hafmis.common.util.enumerate.OrgState";

  /**
   * ��λ״̬-������
   */
  public static final int ORGSTATE_OPENING = 1;

  /**
   * ��λ״̬-����
   */
  public static final int ORGSTATE_NORMAL = 2;

  /**
   * ��λ״̬-���
   */
  public static final int ORGSTATE_SEAL = 3;

  /**
   * ��λ״̬-ע��
   */
  public static final int ORGSTATE_CANCEL = 4;

  /* �ɴ澫�� */
  public static final String PAYMENTACCURACY = "org.xpup.hafmis.common.util.enumerate.PaymentAccuracy";

  /**
   * �ɴ澫��-�������뵽Ԫ
   */
  public static final int PAYMENTACCURACY_ROUNDTOYUAN = 1;

  /**
   * �ɴ澫��-��Ԫ����
   */
  public static final int PAYMENTACCURACY_DISCARDTOYUAN = 2;

  /**
   * �ɴ澫��-���ǽ�Ԫ
   */
  public static final int PAYMENTACCURACY_SEEKOKONYUAN = 3;

  /**
   * �ɴ澫��-���ֽ���
   */
  public static final int PAYMENTACCURACY_SEECENTSONKOK = 4;

  /**
   * �ɴ澫��-�������뵽��
   */
  public static final int PAYMENTACCURACY_ROUNDTOKOK = 5;

  /**
   * �ɴ澫��-�������
   */
  public static final int PAYMENTACCURACY_DISCARDTOKOK = 6;

  /**
   * �ɴ澫��-���Ƿֽ�Ԫ
   */
  public static final int PAYMENTACCURACY_SEEKOKCENTSONYUAN = 7;

  /**
   * �ɴ澫��-�������뵽��
   */
  public static final int PAYMENTACCURACY_ROUNDTOCENT = 8;

  /* ֤������ */
  public static final String DOCUMENTSSTATE = "org.xpup.hafmis.common.util.enumerate.DocumentsState";

  /**
   * ֤������-���֤
   */
  public static final int DOCUMENTSSTATE_IDCARD = 0;

  /**
   * ֤������-���ڲ�
   */
  public static final int DOCUMENTSSTATE_HOUSEHOLDREGISTER = 1;

  /**
   * ֤������-����
   */
  public static final int DOCUMENTSSTATE_PASSPORTCARD = 2;

  /**
   * ֤������-����֤
   */
  public static final int DOCUMENTSSTATE_OFFICERCARD = 3;

  /**
   * ֤������-ʿ��֤
   */
  public static final int DOCUMENTSSTATE_SOLDIERCARD = 4;

  /**
   * ֤������-�۰ľ��������ڵ�ͨ��֤
   */
  public static final int DOCUMENTSSTATE_HKANDMACAO = 5;

  /**
   * ֤������-̨��ͬ�������ڵ�ͨ��֤
   */
  public static final int DOCUMENTSSTATE_TAIWAN = 6;

  /**
   * ֤������-��ʱ���֤
   */
  public static final int DOCUMENTSSTATE_TEMPCARD = 7;

  /**
   * ֤������-����˾���֤
   */
  public static final int DOCUMENTSSTATE_FOREIGNERRESIDENTIALCARD = 8;

  /**
   * ֤������-����֤
   */
  public static final int DOCUMENTSSTATE_POLICECARD = 9;

  /**
   * ֤������-����֤��
   */
  // public static final int DOCUMENTSSTATE_OTHERSCARD = X;
  /* ������ʽ */
  public static final String GUARANTEETYPE = "org.xpup.hafmis.common.util.enumerate.GuaranteeType";

  /**
   * ������ʽ-��Ѻ������֤��
   */
  public static final int GUARANTEETYPE_IMPAWN = 1;

  /**
   * ������ʽ-��Ѻ
   */
  public static final int GUARANTEETYPE_MORTAGAGE = 2;

  /**
   * ������ʽ-��Ȼ�˵���
   */
  public static final int GUARANTEETYPE_NATURALPERSONHYPOTHECATE = 3;

  /**
   * ������ʽ-����/�ⵣ��
   */
  public static final int GUARANTEETYPE_CREDIT = 4;

  /**
   * ������ʽ-��ϣ�����Ȼ�˱�֤��
   */
  public static final int GUARANTEETYPE_COMBINATIONINNATURAL = 5;

  /**
   * ������ʽ-��ϣ�������Ȼ�˱�֤��
   */
  public static final int GUARANTEETYPE_COMBINATIONOUTNATURAL = 6;

  /**
   * ������ʽ-ũ������
   */
  public static final int GUARANTEETYPE_JOINTGUARANTY = 7;

  /**
   * ������ʽ-����
   */
  public static final int GUARANTEETYPE_OTHERS = 9;

  /* �弶����״̬ */
  public static final String FIVECATEGORYASSETSCLASSIFICATION = "org.xpup.hafmis.common.util.enumerate.FiveCategoryAssetsClassification";

  /**
   * �弶����״̬-����
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_NORMAL = 1;

  /**
   * �弶����״̬-��ע
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_ATTENTION = 2;

  /**
   * �弶����״̬-�μ�
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_SECONDARY = 3;

  /**
   * �弶����״̬-����
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_SHADINESS = 4;

  /**
   * �弶����״̬-��ʧ
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_DAMNIFY = 5;

  /**
   * �弶����״̬-δ֪
   */
  public static final int FIVECATEGORYASSETSCLASSIFICATION_UNKOWN = 9;

  /* �˻�״̬-ҵ������Ϊ���� */
  public static final String ACCOUNTSTATEBYLOAN = "org.xpup.hafmis.common.util.enumerate.AccountStateByLoan";

  /**
   * �˻�״̬-ҵ������Ϊ����-����
   */
  public static final int ACCOUNTSTATEBYLOAN_NORMAL = 1;

  /**
   * �˻�״̬-ҵ������Ϊ����-����
   */
  public static final int ACCOUNTSTATEBYLOAN_OVERDUE = 2;

  /**
   * �˻�״̬-ҵ������Ϊ����-����
   */
  public static final int ACCOUNTSTATEBYLOAN_SETTLE = 3;

  /**
   * �˻�״̬-ҵ������Ϊ����-����
   */
  public static final int ACCOUNTSTATEBYLOAN_BADDEBT = 4;

  /* �˻�״̬-ҵ������Ϊ���ÿ� */
  public static final String ACCOUNTSTATEBYCREDITCARD = "org.xpup.hafmis.common.util.enumerate.AccountStateByCreditCard";

  /**
   * �˻�״̬-ҵ������Ϊ���ÿ�-����
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_NORMAL = 1;

  /**
   * �˻�״̬-ҵ������Ϊ���ÿ�-����
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_CONGEAL = 2;

  /**
   * �˻�״̬-ҵ������Ϊ���ÿ�-ֹ��
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_STOPPAYMENT = 3;

  /**
   * �˻�״̬-ҵ������Ϊ���ÿ�-����
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_DELACCOUNT = 4;

  /**
   * �˻�״̬-ҵ������Ϊ���ÿ�-����
   */
  public static final int ACCOUNTSTATEBYCREDITCARD_BADDEBT = 5;

  /* ������ȡ */
  public static final String SOMEPICK = "org.xpup.hafmis.common.util.enumerate.SomePick";

  /**
   * ������ȡ-����
   */
  public static final int BUYHOUSE = 1;

  /**
   * ������ȡ-�������»���
   */
  public static final int GIVEMONEYBYMON = 2;

  /**
   * ������ȡ-������һ���Ի�����
   */
  public static final int GIVEMONEYClEAR = 3;

  /**
   * ������ȡ-�ش󼲲�
   */
  public static final int DISEASE = 4;

  /**
   * ������ȡ-����
   */
  public static final int DISTRESS = 5;

  /**
   * ������ȡ-������ȡ����
   */
  public static final int PARTREST = 6;

  /* ������ȡ */
  public static final String DISTROYPICK = "org.xpup.hafmis.common.util.enumerate.DistoryPick";

  /**
   * ����
   */
  public static final int BOWOUT = 7;

  /**
   * ����
   */
  public static final int DEATH = 8;

  /**
   * ��������
   */
  public static final int DECRUITMENT = 9;

  /**
   * ʧҵ�¸�����
   */
  public static final int JOBLESS = 10;

  /**
   * �Ǳ��л��ڽ����ͬ
   */
  public static final int DISTORY = 11;

  /**
   * ��������
   */
  public static final int SETTLE = 12;

  /**
   * ��ȡԭ��
   */
  public static final String PICKUPREASON = "org.xpup.hafmis.common.util.enumerate.PickupReason";

  /**
   * ������ȡ-����
   */
  public static final int PICKUPREASON_BUYHOUSE = 1;

  /**
   * ������ȡ-�������»���
   */
  public static final int PICKUPREASON_GIVEMONEYBYMON = 2;

  /**
   * ������ȡ-������һ���Ի�����
   */
  public static final int PICKUPREASON_GIVEMONEYClEAR = 3;

  /**
   * ������ȡ-�ش󼲲�
   */
  public static final int PICKUPREASON_DISEASE = 4;

  /**
   * ������ȡ-����
   */
  public static final int PICKUPREASON_DISTRESS = 5;

  /**
   * ������ȡ-������ȡ����
   */
  public static final int PICKUPREASON_PARTREST = 6;

  /**
   * ����
   */
  public static final int PICKUPREASON_BOWOUT = 7;

  /**
   * ����
   */
  public static final int PICKUPREASON_DEATH = 8;

  /**
   * ��������
   */
  public static final int PICKUPREASON_DECRUITMENT = 9;

  /**
   * ʧҵ�¸�����
   */
  public static final int PICKUPREASON_JOBLESS = 10;

  /**
   * �Ǳ��л��ڽ����ͬ
   */
  public static final int PICKUPREASON_DISTORY = 11;

  /**
   * ��������
   */
  public static final int PICKUPREASON_SETTLE = 12;

  /* ���ѧ�� */
  public static final String HIGHESTEDUCATIONLEVEL = "org.xpup.hafmis.common.util.enumerate.HighestEducationLevel";

  /**
   * ���ѧ��-�о���
   */
  public static final int HIGHESTEDUCATIONLEVEL_GRADUATE = 10;

  /**
   * ���ѧ��-��ѧ���ƣ����"��ѧ"��
   */
  public static final int HIGHESTEDUCATIONLEVEL_UNDERGRADUATE = 20;

  /**
   * ���ѧ��-��ѧר�ƺ�ר��ѧУ�����"��ר"��
   */
  public static final int HIGHESTEDUCATIONLEVEL_SPECIALIST = 30;

  /**
   * ���ѧ��-�е�רҵѧУ���еȼ���ѧУ
   */
  public static final int HIGHESTEDUCATIONLEVEL_SECONDARY = 40;

  /**
   * ���ѧ��-����ѧУ
   */
  public static final int HIGHESTEDUCATIONLEVEL_TECHNICAL = 50;

  /**
   * ���ѧ��-����
   */
  public static final int HIGHESTEDUCATIONLEVEL_HIGHSCHOOL = 60;

  /**
   * ���ѧ��-����
   */
  public static final int HIGHESTEDUCATIONLEVEL_JUNIOR = 70;

  /**
   * ���ѧ��-Сѧ
   */
  public static final int HIGHESTEDUCATIONLEVEL_PRIMARY = 80;

  /**
   * ���ѧ��-��ä�����ä
   */
  public static final int HIGHESTEDUCATIONLEVEL_ILLITERACY = 90;

  /**
   * ���ѧ��-δ֪
   */
  public static final int HIGHESTEDUCATIONLEVEL_UNKNOW = 99;

  /* ���ѧλ */
  public static final String HIGHESTEDUCATIONALDEGREEOBTAINED = "org.xpup.hafmis.common.util.enumerate.HighestEducationDegreeObtained";

  /**
   * ���ѧλ-����
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_OTHERS = 0;

  /**
   * ���ѧλ-������ʿ
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_HONORARYDOCTOR = 1;

  /**
   * ���ѧλ-��ʿ
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_DOCTOR = 2;

  /**
   * ���ѧλ-˶ʿ
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_MASTER = 3;

  /**
   * ���ѧλ-ѧʿ
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_BACHELOR = 4;

  /**
   * ���ѧλ-δ֪
   */
  public static final int HIGHESTEDUCATIONALDEGREEOBTAINED_UNKNOW = 9;

  /* ְ�� */
  public static final String DUTY = "org.xpup.hafmis.common.util.enumerate.Duty";

  /**
   * ְ��-�߼��쵼����������ּ����ּ������쵼���˾�߼�������Ա��
   */
  public static final int DUTY_SENIORLEADER = 1;

  /**
   * ְ��-�м��쵼����������ּ������쵼���˾�м�������Ա��
   */
  public static final int DUTY_INTERMEDIATELEADER = 2;

  /**
   * ְ��-һ��Ա��
   */
  public static final int DUTY_GENERALSTAFF = 3;

  /**
   * ְ��-����
   */
  public static final int DUTY_OTHERS = 4;

  /**
   * ְ��-δ֪
   */
  public static final int DUTY_UNKNOW = 9;

  /* ְ�� */
  public static final String DUTYLEVEL = "org.xpup.hafmis.common.util.enumerate.DutyLevel";

  /**
   * ְ��-��
   */
  public static final int DUTYLEVEL_NOT = 0;

  /**
   * ְ��-�߼�
   */
  public static final int DUTYLEVEL_SENIOR = 1;

  /**
   * ְ��-�м�
   */
  public static final int DUTYLEVEL_INTERMEDIATE = 2;

  /**
   * ְ��-����
   */
  public static final int DUTYLEVEL_GENERAL = 3;

  /**
   * ְ��-δ֪
   */
  public static final int DUTYLEVEL_UNKNOW = 9;

  /* ��ס��� */
  public static final String LIVINGCONTEXT = "org.xpup.hafmis.common.util.enumerate.LivingContext ";

  /**
   * ��ס���-����
   */
  public static final int LIVINGCONTEXT_REHOUSINGYOURSELF = 1;

  /**
   * ��ס���-����
   */
  public static final int LIVINGCONTEXT_MORTGAGE = 2;

  /**
   * ��ס���-����¥��
   */
  public static final int LIVINGCONTEXT_RELATIVEHOME = 3;

  /**
   * ��ס���-��������
   */
  public static final int LIVINGCONTEXT_COLLECTIVEQUARTERS = 4;

  /**
   * ��ס���-�ⷿ
   */
  public static final int LIVINGCONTEXT_RENTHOME = 5;

  /**
   * ��ס���-����סլ
   */
  public static final int LIVINGCONTEXT_PUBLICHOME = 6;

  /**
   * ��ס���-����
   */
  public static final int LIVINGCONTEXT_OTHERS = 7;

  /**
   * ��ס���-δ֪
   */
  public static final int LIVINGCONTEXT_UNKNOW = 9;

  /* ��λ������ҵ */
  public static final String INDUSTRY = "org.xpup.hafmis.common.util.enumerate.Industry";

  /**
   * ��λ������ҵ-ũ���֡�������ҵ
   */
  public static final String INDUSTRY_A = "A";

  /**
   * ��λ������ҵ-�ɾ�ҵ
   */
  public static final String INDUSTRY_B = "B";

  /**
   * ��λ������ҵ-����ҵ
   */
  public static final String INDUSTRY_C = "C";

  /**
   * ��λ������ҵ-������ȼ����ˮ�������͹�Ӧҵ
   */
  public static final String INDUSTRY_D = "D";

  /**
   * ��λ������ҵ-����ҵ
   */
  public static final String INDUSTRY_E = "E";

  /**
   * ��λ������ҵ-��ͨ���䡢�ִ�������ҵ
   */
  public static final String INDUSTRY_F = "F";

  /**
   * ��λ������ҵ-��Ϣ���䡢�������������ҵ
   */
  public static final String INDUSTRY_G = "G";

  /**
   * ��λ������ҵ-����������ҵ
   */
  public static final String INDUSTRY_H = "H";

  /**
   * ��λ������ҵ-ס�޺Ͳ���ҵ
   */
  public static final String INDUSTRY_I = "I";

  /**
   * ��λ������ҵ-����ҵ
   */
  public static final String INDUSTRY_J = "J";

  /**
   * ��λ������ҵ-���ز�ҵ
   */
  public static final String INDUSTRY_K = "K";

  /**
   * ��λ������ҵ-���޺��������ҵ
   */
  public static final String INDUSTRY_L = "L";

  /**
   * ��λ������ҵ-��ѧ�о�����������ҵ�͵��ʿ���ҵ
   */
  public static final String INDUSTRY_M = "M";

  /**
   * ��λ������ҵ-ˮ���������͹�����ʩ����ҵ
   */
  public static final String INDUSTRY_N = "N";

  /**
   * ��λ������ҵ-����������������ҵ
   */
  public static final String INDUSTRY_O = "O";

  /**
   * ��λ������ҵ-����
   */
  public static final String INDUSTRY_P = "P";

  /**
   * ��λ������ҵ-��������ᱣ�Ϻ���ḣ��ҵ
   */
  public static final String INDUSTRY_Q = "Q";

  /**
   * ��λ������ҵ-�Ļ�������������ҵ
   */
  public static final String INDUSTRY_R = "R";

  /**
   * ��λ������ҵ-��������������֯
   */
  public static final String INDUSTRY_S = "S";

  /**
   * ��λ������ҵ-������֯
   */
  public static final String INDUSTRY_T = "T";

  /**
   * ��λ������ҵ-δ֪
   */
  public static final String INDUSTRY_Z = "Z";

  /* ��Ա����еı��״̬ */
  public static final String CHGSTATUS = "org.xpup.hafmis.common.util.enumerate.ChgStatus";

  /**
   * ��Ա����еı��״̬-����
   */
  public static final int CHGSTATUS_OPEN = 1;

  /**
   * ��Ա����еı��״̬-����
   */
  public static final int CHGSTATUS_QF = 2;

  /**
   * ��Ա����еı��״̬-���
   */
  public static final int CHGSTATUS_FC = 3;

  /* ���ڵ��� */
  public static final String INAREA = "org.xpup.hafmis.common.util.enumerate.InArea";

  /**
   * ���ڵ���-��ֱ
   */
  public static final int INAREA_SZ = 1;

  /**
   * ���ڵ���-��ʯ��
   */
  public static final int INAREA_DSQ = 2;

  /**
   * ���ڵ���-����
   */
  public static final int INAREA_GX = 3;

  /**
   * ���ڵ���-����Ȧ
   */
  public static final int INAREA_BYQ = 4;

  /* ��λ���� */
  public static final String NATUREOFUNITS = "org.xpup.hafmis.common.util.enumerate.NatureOfUnits";

  /**
   * ��λ����-���������ҵ
   */
  public static final int NATUREOFUNITS_1 = 1;

  /**
   * ��λ����-ʡ������ҵ
   */
  public static final int NATUREOFUNITS_2 = 2;

  /**
   * ��λ����-���ػ�����ҵ
   */
  public static final int NATUREOFUNITS_3 = 3;

  /**
   * ��λ���ʣ�������ҵ
   */
  public static final int NATUREOFUNITS_4 = 4;

  /**
   * ��λ���ʣ�ʡ��ȫ����ҵ
   */
  public static final int NATUREOFUNITS_5 = 5;

  /**
   * ��λ���ʣ�ʡ��������ҵ
   */
  public static final int NATUREOFUNITS_6 = 6;

  /**
   * ��λ���ʣ��м�ȫ����ҵ
   */
  public static final int NATUREOFUNITS_7 = 7;

  /**
   * ��λ���ʣ����ؼ�������ҵ
   */
  public static final int NATUREOFUNITS_8 = 8;

  /**
   * ��λ���ʣ���ʡ�е�λ
   */
  public static final int NATUREOFUNITS_9 = 9;

  /**
   * ��λ����-����
   */
  public static final int NATUREOFUNITS_10 = 10;

  /**
   * ��λ����-������ҵ(ȫ��)
   */
  public static final int NATUREOFUNITS_11 = 11;

  /**
   * ��λ����-������ҵ(���)
   */
  public static final int NATUREOFUNITS_12 = 12;

  /**
   * ��λ���ʣ�������ҵ(������֧)
   */
  public static final int NATUREOFUNITS_13 = 13;

  /**
   * ��λ���ʣ�������ҵ
   */
  public static final int NATUREOFUNITS_14 = 14;

  /**
   * ��λ���ʣ�������ҵ
   */
  public static final int NATUREOFUNITS_15 = 15;

  /**
   * ��λ���ʣ��ɷ����޹�˾
   */
  public static final int NATUREOFUNITS_16 = 16;

  /**
   * ��λ���ʣ�˽����Ӫ��ҵ
   */
  public static final int NATUREOFUNITS_17 = 17;

  /**
   * ��λ���ʣ�������ҵ
   */
  public static final int NATUREOFUNITS_18 = 18;

  /**
   * ��λ���ʣ�����
   */
  public static final int NATUREOFUNITS_19 = 19;

  /* ���ܲ��� */
  public static final String AUTHORITIES = "org.xpup.hafmis.common.util.enumerate.Authorities";

  public static final int AUTHORITIES_1 = 1;

  public static final int AUTHORITIES_2 = 2;

  public static final int AUTHORITIES_3 = 3;

  public static final int AUTHORITIES_4 = 4;

  public static final int AUTHORITIES_5 = 5;

  public static final int AUTHORITIES_6 = 6;

  public static final int AUTHORITIES_7 = 7;

  public static final int AUTHORITIES_8 = 8;

  public static final int AUTHORITIES_9 = 9;

  public static final int AUTHORITIES_10 = 10;

  public static final int AUTHORITIES_11 = 11;

  public static final int AUTHORITIES_12 = 12;

  public static final int AUTHORITIES_13 = 13;

  public static final int AUTHORITIES_14 = 14;

  public static final int AUTHORITIES_15 = 15;

  public static final int AUTHORITIES_16 = 16;

  public static final int AUTHORITIES_17 = 17;

  public static final int AUTHORITIES_18 = 18;

  public static final int AUTHORITIES_19 = 19;

  public static final int AUTHORITIES_20 = 20;

  public static final int AUTHORITIES_22 = 22;

  public static final int AUTHORITIES_23 = 23;

  public static final int AUTHORITIES_24 = 24;

  public static final int AUTHORITIES_25 = 25;

  public static final int AUTHORITIES_26 = 26;

  public static final int AUTHORITIES_27 = 27;

  public static final int AUTHORITIES_28 = 28;

  public static final int AUTHORITIES_29 = 29;

  public static final int AUTHORITIES_30 = 30;

  public static final int AUTHORITIES_31 = 31;

  public static final int AUTHORITIES_32 = 32;

  public static final int AUTHORITIES_33 = 33;

  public static final int AUTHORITIES_34 = 34;

  public static final int AUTHORITIES_35 = 35;

  public static final int AUTHORITIES_36 = 36;

  public static final int AUTHORITIES_37 = 37;

  public static final int AUTHORITIES_38 = 38;

  public static final int AUTHORITIES_39 = 39;

  public static final int AUTHORITIES_40 = 40;

  public static final int AUTHORITIES_42 = 42;

  public static final int AUTHORITIES_43 = 43;

  public static final int AUTHORITIES_44 = 44;

  public static final int AUTHORITIES_45 = 45;

  public static final int AUTHORITIES_46 = 46;

  public static final int AUTHORITIES_47 = 47;

  public static final int AUTHORITIES_48 = 48;

  public static final int AUTHORITIES_49 = 49;

  public static final int AUTHORITIES_50 = 50;

  public static final int AUTHORITIES_51 = 51;

  public static final int AUTHORITIES_52 = 52;

  public static final int AUTHORITIES_53 = 53;

  public static final int AUTHORITIES_54 = 54;

  public static final int AUTHORITIES_55 = 55;

  public static final int AUTHORITIES_56 = 56;

  public static final int AUTHORITIES_57 = 57;

  public static final int AUTHORITIES_58 = 58;

  public static final int AUTHORITIES_59 = 59;

  public static final int AUTHORITIES_60 = 60;

  public static final int AUTHORITIES_61 = 61;

  public static final int AUTHORITIES_62 = 62;

  /**
   * ���ܲ���-��ֱ
   */
  public static final int AUTHORITIES_ZHONGZHI = 63;

  /**
   * ���ܲ���-ʡֱ
   */
  public static final int AUTHORITIES_SHENGZHI = 64;

  /**
   * ���ܲ���-��ֱ
   */
  public static final int AUTHORITIES_SHIZHI = 65;

  /**
   * ���ܲ���-�м�����
   */
  public static final int AUTHORITIES_SHIJIYIXIA = 66;

  /**
   * ���ܲ���-����
   */
  public static final int AUTHORITIES_OTHER = 67;

  /* ����ҵ������ */
  public static final String CHGBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.ChgBusinessType";

  /**
   * ����ҵ������-���ɴ�
   */
  public static final String CHGBUSINESSTYPE_K = "K";

  /**
   * ����ҵ������-����ȡ
   */
  public static final String CHGBUSINESSTYPE_L = "L";

  /**
   * ����ҵ������-������
   */
  public static final String CHGBUSINESSTYPE_G = "G";

  /* ҵ������ */
  public static final String BUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.BusinessType";

  /**
   * ҵ������-�������
   */
  public static final int BUSINESSTYPE_1 = 1;

  /**
   * ҵ������-��λ����
   */
  public static final int BUSINESSTYPE_2 = 2;

  /**
   * ҵ������-���˲���
   */
  public static final int BUSINESSTYPE_3 = 3;

  /**
   * ҵ������-����
   */
  public static final int BUSINESSTYPE_4 = 4;

  /* ҵ��״̬ */
  public static final String BUSINESSSTATE = "org.xpup.hafmis.common.util.enumerate.BusinessState";

  /**
   * ҵ��״̬-¼�����
   */
  public static final int BUSINESSSTATE_1 = 1;

  /**
   * ҵ��״̬-�Ǽ�
   */
  public static final int BUSINESSSTATE_2 = 2;

  /**
   * ҵ��״̬-ȷ��
   */
  public static final int BUSINESSSTATE_3 = 3;

  /**
   * ҵ��״̬-����
   */
  public static final int BUSINESSSTATE_4 = 4;

  /**
   * ҵ��״̬-����
   */
  public static final int BUSINESSSTATE_5 = 5;

  /* ҵ��״̬ */
  public static final String ORGOVERPAYTYPE = "org.xpup.hafmis.common.util.enumerate.OrgOverPay";

  /**
   * �������ͣ��������
   */
  public static final int ORGOVERPAYTYPE_1 = 1;

  /**
   * �������ͣ�������
   */
  public static final int ORGOVERPAYTYPE_2 = 2;

  /* ҵ��״̬ */
  public static final String BUSINESSLOGSTATE = "org.xpup.hafmis.common.util.enumerate.BusinessLogState";

  /**
   * ҵ��״̬-¼�����
   */
  public static final String BUSINESSLOGSTATE_1 = "1";

  /**
   * ҵ��״̬-�Ǽ�
   */
  public static final String BUSINESSLOGSTATE_2 = "2";

  /**
   * ҵ��״̬-ȷ��
   */
  public static final String BUSINESSLOGSTATE_3 = "3";

  /**
   * ҵ��״̬-����
   */
  public static final String BUSINESSLOGSTATE_4 = "4";

  /**
   * ҵ��״̬-����
   */
  public static final String BUSINESSLOGSTATE_5 = "5";

  /* ����- ҵ��״̬ */
  public static final String CLEARACCOUNTSTATUS = "org.xpup.hafmis.common.util.enumerate.ClearAccountStatus";

  /**
   * ҵ��״̬-ȷ��
   */
  public static final int CLEARACCOUNTSTATUS_3 = 3;

  /**
   * ҵ��״̬-����
   */
  public static final int CLEARACCOUNTSTATUS_4 = 4;

  /**
   * ҵ��״̬-����
   */
  public static final int CLEARACCOUNTSTATUS_5 = 5;

  /* ҵ�񸴺� ҵ��״̬ */
  public static final String BIZCHECKBIZSTATUS = "org.xpup.hafmis.common.util.enumerate.BizcheckBizStatus";

  /**
   * ҵ�񸴺� ҵ��״̬-δ����
   */
  public static final int BIZCHECKBIZSTATUS_1 = 3;

  /**
   * ҵ�񸴺� ҵ��״̬-�Ѹ���
   */
  public static final int BIZCHECKBIZSTATUS_2 = 4;

  /* ���ǰ�ɴ�״̬ */
  public static final String OLDPAYMENTSTATE = "org.xpup.hafmis.common.util.enumerate.OldPaymentState";

  /**
   * ���ǰ�ɴ�״̬-����
   */
  public static final int OLDPAYMENTSTATE_1 = 1;

  /**
   * ���ǰ�ɴ�״̬-���
   */
  public static final int OLDPAYMENTSTATE_2 = 2;

  /**
   * ���ǰ�ɴ�״̬-����
   */
  public static final int OLDPAYMENTSTATE_3 = 3;

  /**
   * ���ǰ�ɴ�״̬-����
   */
  public static final int OLDPAYMENTSTATE_4 = 4;

  /**
   * ���ǰ�ɴ�״̬-ɾ��
   */
  public static final int OLDPAYMENTSTATE_5 = 5;

  /**
   * ���ǰ�ɴ�״̬-���˻�
   */
  public static final int OLDPAYMENTSTATE_6 = 6;

  /* ���״̬ */
  public static final String CHGTYPESTATUS = "org.xpup.hafmis.common.util.enumerate.ChgTypeStatus";

  /**
   * ���״̬-δ����
   */
  public static final int CHGTYPESTATUS_1 = 1;

  /**
   * ���״̬-����
   */
  public static final int CHGTYPESTATUS_2 = 2;

  /* ��ȡ���� */
  public static final String PICKUPTYPE = "org.xpup.hafmis.common.util.enumerate.PickUpType";

  /**
   * ��ȡ����-������ȡ
   */
  public static final int PICKUPTYPE_1 = 1;

  /**
   * ��ȡ����-������ȡ
   */
  public static final int PICKUPTYPE_2 = 2;

  /* ��λ���������� */
  public static final String ORGCHGSTRUTS = "org.xpup.hafmis.common.util.enumerate.OrgChgStruts";

  /**
   * ��λ����������-����
   */
  public static final String ORGCHGSTRUTS_1 = "A";

  /**
   * ��λ����������-����
   */
  public static final String ORGCHGSTRUTS_2 = "B";

  /**
   * ��λ����������-���
   */
  public static final String ORGCHGSTRUTS_3 = "C";

  /**
   * ��λ����������-ע��
   */
  public static final String ORGCHGSTRUTS_4 = "D";

  /* ��λ������״̬ */
  public static final String ORGCHGTYPE = "org.xpup.hafmis.common.util.enumerate.OrgChgType";

  /**
   * ��λ����������-����
   */
  public static final String ORGCHGTYPE_1 = "A";

  /**
   * ��λ����������-���
   */
  public static final String ORGCHGTYPE_2 = "B";

  /**
   * ��λ����������-ע��
   */
  public static final String ORGCHGTYPE_3 = "C";

  /* ����ҵ������ */
  public static final String CLEARACCOUNTBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.ClearAccountBusinessType";

  /**
   * ����ҵ������-���ɴ�
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_K = "K";

  /**
   * ����ҵ������-����ȡ
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_L = "L";

  /**
   * ����ҵ������-������
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_G = "G";

  /**
   * ����ҵ������-��ȡ
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_D = "D";

  /**
   * ����ҵ������-ת��
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_E = "E";

  /**
   * ����ҵ������-���
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_A = "A";

  /**
   * ����ҵ������-���˲���
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_B = "B";

  /**
   * ����ҵ������-��λ����
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_M = "M";

  /**
   * ����ҵ������-����
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_C = "C";

  /**
   * ����ҵ������-ת��
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_F = "F";

  /**
   * ����ҵ������-��Ϣ
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_H = "H";

  /**
   * ����ҵ������-����������ת
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_I = "I";

  /**
   * ����ҵ������-��������ת
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_J = "J";

  /* ��λ��ҵ������ */
  public static final String ORGBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.OrgBusinessType";

  /**
   * ���
   */
  public static final String ORGBUSINESSTYPE_A = "A";

  /**
   * ��λ����
   */
  public static final String ORGBUSINESSTYPE_B = "B";

  /**
   * ����
   */
  public static final String ORGBUSINESSTYPE_C = "C";

  /**
   * ��ȡ
   */
  public static final String ORGBUSINESSTYPE_D = "D";

  /**
   * ת��
   */
  public static final String ORGBUSINESSTYPE_E = "E";

  /**
   * ת��
   */
  public static final String ORGBUSINESSTYPE_F = "F";

  /**
   * ����
   */
  public static final String ORGBUSINESSTYPE_G = "G";

  /**
   * ��Ϣ
   */
  public static final String ORGBUSINESSTYPE_H = "H";

  /**
   * ������ȡ
   */
  public static final String ORGBUSINESSTYPE_I = "I";

  /**
   * ���ʵ���
   */
  public static final String ORGBUSINESSTYPE_J = "J";

  /**
   * ���˲���
   */
  public static final String ORGBUSINESSTYPE_K = "K";

  /**
   * ��ɱ�������
   */
  public static final String ORGBUSINESSTYPE_L = "L";

  /**
   * ���ʻ�������
   */
  public static final String ORGBUSINESSTYPE_M = "M";

  /**
   * �ɶ����
   */
  public static final String ORGBUSINESSTYPE_N = "N";

  /**
   * ��Ա���
   */
  public static final String ORGBUSINESSTYPE_O = "O";

  /**
   * ��λ����
   */
  public static final String ORGBUSINESSTYPE_P = "P";

  /**
   * ��λ���
   */
  public static final String ORGBUSINESSTYPE_Q = "Q";

  /* ����ҵ������ */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL = "org.xpup.hafmis.common.util.enumerate.ClearAccountBusinessType_WL";

  /**
   * ����ҵ������-���ɴ�
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_K = "K";

  /**
   * ����ҵ������-����ȡ
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_L = "L";

  /**
   * ����ҵ������-������
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_G = "G";

  /**
   * ����ҵ������-��ȡ
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_D = "D";

  /**
   * ����ҵ������-ת��
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_E = "E";

  /**
   * ����ҵ������-���
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_A = "A";

  /**
   * ����ҵ������-���˲���
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_B = "B";

  /**
   * ����ҵ������-��λ����
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_M = "M";

  /**
   * ����ҵ������-����
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_C = "C";

  /**
   * ����ҵ������-ת��
   */
  public static final String CLEARACCOUNTBUSINESSTYPE_WL_F = "F";

  /* �鼯����״̬ */
  public static final String COLLBANKSTATUS = "org.xpup.hafmis.common.util.enumerate.CollBankStatus";

  /**
   * �鼯����״̬-����
   */
  public static final int COLLBANKSTATUS_NORMAL = 1;

  /**
   * �鼯����״̬-����
   */
  public static final int COLLBANKSTATUS_CANCELED = 2;

  /* ��ȡ״̬ */
  public static final String PICKSTATUS = "org.xpup.hafmis.common.util.enumerate.PickStatus";

  /**
   * ��ȡ״̬-δʹ��
   */
  public static final int PICKSTATUS_NO = 1;

  /**
   * ��ȡ״̬-��ʹ��
   */
  public static final int PICKSTATUS_YES = 2;

  /* ����������� */
  public static final String OCCURREDDIRECTION = "org.xpup.hafmis.common.util.enumerate.OccurredDirection";

  /**
   * �����������-��
   */
  public static final int OCCURREDDIRECTION_DEBIT = 1;

  /**
   * �����������-��
   */
  public static final int OCCURREDDIRECTION_CREDIT = 2;

  /**
   * �����������-ƽ
   */
  public static final int OCCURREDDIRECTION_PARALLEL = 3;

  /* ��Ա����ı��ԭ�� */
  public static final String CHGPERSONREASON = "org.xpup.hafmis.common.util.enumerate.ChgpersonReason";

  /**
   * ��Ա����ı��ԭ������
   */
  public static final int CHGPERSONREASON_OTHER = 1;

  /**
   * ��Ա����ı��ԭ��ת��
   */
  public static final int CHGPERSONREASON_OUT = 2;

  /**
   * ��Ա����ı��ԭ��ת��
   */
  public static final int CHGPERSONREASON_IN = 3;

  /**
   * ��Ա����ı��ԭ��ע��
   */
  public static final int CHGPERSONREASON_DEL = 4;

  /* ��Ϣ���� */
  public static final String CLEARINTERESTTYPE = "org.xpup.hafmis.common.util.enumerate.ClearInterestType";

  /**
   * ��Ϣ����-���ս�Ϣ
   */
  public static final String CLEARINTERESTTYPE_YEARCLEAR = "A";

  /**
   * ��Ϣ����-������ȡ
   */
  public static final String CLEARINTERESTTYPE_DELACCOUNT = "B";

  /**
   * ��Ϣ����-ת��
   */
  public static final String CLEARINTERESTTYPE_TRANS = "C";

  /* ����ҵ������ */
  public static final String ESPECIALOPERATIONTYPE = "org.xpup.hafmis.common.util.enumerate.EspecialOperationType";

  /**
   * ��������
   */
  public static final String ESPECIALOPERATIONTYPE_AGENT = "1";

  /**
   * �����𻹴�
   */
  public static final String ESPECIALOPERATIONTYPE_LOANBACK = "2";

  /* ����״̬ */
  public static final String AGENTSTATUS = "org.xpup.hafmis.common.util.enumerate.AgentStatus";

  /**
   * δʹ��
   */
  public static final String AGENTSTATUS_NO = "0";

  /**
   * ��ʹ��
   */
  public static final String AGENTSTATUS_YES = "1";

  /** -----------------------------------------����---------------------------------------------------------------* */
  /* ��������� */
  public static final String PLLOANRETURNTYPE = "org.xpup.hafmis.common.util.enumerate.PLLoanReturnType";

  /**
   * ������Ϊ��
   */
  public static final int PLLOANRETURNTYPE_CENTER = 1;

  /**
   * ������Ϊ��
   */
  public static final int PLLOANRETURNTYPE_BANK = 2;

  /* ����-�������� */
  public static final String PLLOANTYPE = "org.xpup.hafmis.common.util.enumerate.PLLoanType";

  /**
   * ����-��������-1-5��
   */
  public static final int PLLOANTYPE_FIVE = 0;

  /**
   * ����-��������-5������
   */
  public static final int PLLOANTYPE_FIVEUP = 1;

  /**
   * ����-��������-һ����
   */
  public static final int PLLOANTYPE_ONEYEAR = 2;

  /**
   * ����-��������-������
   */
  public static final int PLLOANTYPE_TWOYEAR = 3;

  /* ����-�ۿʽ */
  public static final String PLDEBITTYPE = "org.xpup.hafmis.common.util.enumerate.PLDebitType";

  /**
   * ����-�ۿʽ-���ۿ�
   */
  public static final int PLDEBITTYPE_SUFF = 1;

  /**
   * ����-�ۿʽ-ȫ��ۿ�
   */
  public static final int PLDEBITTYPE_ALL = 2;

  /* ����-������ */
  public static final String PLRECOVERDAY = "org.xpup.hafmis.common.util.enumerate.PLRecoverDay";

  /**
   * ����-������-��������
   */
  public static final int PLRECOVERDAY_ACCOUNT = 1;

  /**
   * ����-������-ͳһ����
   */
  public static final int PLRECOVERDAY_DAY = 2;

  /* ����-���������Ϣ */
  public static final String PLALLMESS = "org.xpup.hafmis.common.util.enumerate.PLAllMess";

  /**
   * ����-���������Ϣ-��������
   */
  public static final String PLALLMESS_CORPUS = "A";

  /**
   * ����-���������Ϣ-������Ϣ
   */
  public static final String PLALLMESS_INTEREST = "B";

  /**
   * ����-���������Ϣ-���ڱ���
   */
  public static final String PLALLMESS_OVERDUECORPUS = "C";

  /**
   * ����-���������Ϣ-������Ϣ
   */
  public static final String PLALLMESS_OVERDUEINTEREST = "D";

  /**
   * ����-���������Ϣ-��Ϣ
   */
  public static final String PLALLMESS_PUNISHINTEREST = "E";

  /* ����-��Ϣ�������� */
  public static final String PLPUNISHINTERESTTYPE = "org.xpup.hafmis.common.util.enumerate.PLPunishInterestType";

  /**
   * ����-��Ϣ����-����Ϣ������
   */
  public static final int PLPUNISHINTERESTTYPE_PUNISHDAYRATE = 1;

  /**
   * ����-��Ϣ����-����ͬ������
   */
  public static final int PLPUNISHINTERESTTYPE_CONTRACTDAYRATE = 2;

  /**
   * ����-��Ϣ����-����ÿ��
   */
  public static final int PLPUNISHINTERESTTYPE_PAYMENTDAYRATE = 3;

  /* ����-�������� */
  public static final String PLLOANPROCESS = "org.xpup.hafmis.common.util.enumerate.PLLoanProcess";

  /**
   * ����-���������Ϣ-��������
   */
  public static final String PLLOANPROCESS_LOANAPPROVAL = "A";

  /**
   * ����-���������Ϣ-ǩ������
   */
  public static final String PLLOANPROCESS_CONTRACTSIGN = "B";

  /* ����-�������� */
  public static final String PLRATETYPE = "org.xpup.hafmis.common.util.enumerate.PLRateType";

  /**
   * ����-��������-��������
   */
  public static final int PLRATETYPE_SUDTDEMAND = 1;

  /**
   * ����-��������-��������
   */
  public static final int PLRATETYPE_SUBTREGULAR = 2;

  /* ����-ǩ����ͬ״̬-δǩ��or��ǩ�� */
  public static final String PLISCONTRACTWRITE = "org.xpup.hafmis.common.util.enumerate.PLContractWrite";

  /**
   * ����-ǩ����ͬ״̬-δǩ��
   */
  public static final int PLCONTRACTWRITE_NO = 0;

  /**
   * ����-ǩ����ͬ״̬-��ǩ��
   */
  public static final int PLCONTRACTWRITE_YES = 1;

  /* ����-����״̬-����or���� */
  public static final String PLCOMMONSTATUS_1 = "org.xpup.hafmis.common.util.enumerate.PLCommonStatus_FIR";

  /**
   * ����-����״̬-����
   */
  public static final int PLCOMMONSTATUS_1_NORMAL = 0;

  /**
   * ����-����״̬-����
   */
  public static final int PLCOMMONSTATUS_1_CANCELED = 1;

  /**
   * ����-����״̬-���
   */
  // public static final int PLCOMMONSTATUS_1_RELIEVE = 2;
  /* ����-����״̬-����or������ */
  public static final String PLCOMMONSTATUS_2 = "org.xpup.hafmis.common.util.enumerate.PLCommonStatus_SEC";

  /**
   * ����-����״̬-������
   */
  public static final int PLCOMMONSTATUS_2_NOTALLOW = 1;

  /**
   * ����-����״̬-����
   */
  public static final int PLCOMMONSTATUS_2_ALLOW = 2;

  /* ���� */
  public static final String NATIONAL = "org.xpup.hafmis.common.util.enumerate.National";

  /**
   * ����-����
   */
  public static final int NATIONAL_HAN = 1;

  /**
   * ����-����
   */
  public static final int NATIONAL_MAN = 2;

  /* ����-���ʽ */
  public static final String PLRECOVERTYPE = "org.xpup.hafmis.common.util.enumerate.PLRecoverType";

  /**
   * ����-���ʽ-�ȶ��
   */
  public static final int PLRECOVERTYPE_CORPUS = 1;

  /**
   * ����-���ʽ-�ȶϢ
   */
  public static final int PLRECOVERTYPE_INTEREST = 2;

  /**
   * ����-���ʽ-����
   */
  public static final int PLRECOVERTYPE_OTHER = 3;

  /**
   * ����-���ʽ-һ����
   */
  public static final int PLRECOVERTYPE_ONEYEAR = 4;

  /**
   * ����-���ʽ-������
   */
  public static final int PLRECOVERTYPE_TWOYEAR = 5;

  /* ����-�������� */
  public static final String PLHOUSETYPE = "org.xpup.hafmis.common.util.enumerate.PLHouseType";

  /**
   * ����-��������-��Ʒ��
   */
  public static final String PLHOUSETYPE_HOUSING = "01";

  /**
   * ����-��������-���ַ�
   */
  public static final String PLHOUSETYPE_SECHOUSING = "02";
  
  /*����-�ص�����*/
  public static final String PL_LOANTYPE  = "org.xpup.hafmis.common.util.enumerate.PLLoanWhereType";
  /**
   * ����-�ص�����-����
   */
  public static final String PL_LOANTYPE_LOCAL ="0";
  /**
   * ����-�ص�����-���
   */
  public static final String PL_LOANTYPE_OTHERS ="1";

  /* ����-Э����λ���� */
  public static final String PLASSISTANTORG_TYPE = "org.xpup.hafmis.common.util.enumerate.PLAssistantOrgType";

  /**
   * ����_Э����λ����-������˾
   */
  public static final String PLASSURE_ORG = "A";

  /**
   * ����_Э����λ����-�������
   */
  public static final String PLSURROGATE_ORG = "B";

  /**
   * ����_Э����λ����-���չ�˾
   */
  public static final String PLSPONSION_ORG = "C";

  /**
   * ����_Э����λ����-��������
   */
  public static final String PLINSURANCE_ORG = "D";

  /**
   * ����_Э����λ����-��֤��
   */
  public static final String PLNOTARIZATION_ORG = "E";

  /* ����-��ͬ״̬ */
  public static final String PLCONTRACTSTATUS = "org.xpup.hafmis.common.util.enumerate.PLContractStatus";

  /**
   * ����-��ͬ״̬-����
   */
  public static final int PLCONTRACTSTATUS_APPL = 1;

  /**
   * ����-��ͬ״̬-�ύ���
   */
  //public static final int PLCONTRACTSTATUS_AUDIT = 2;

  /**
   * ����-��ͬ״̬-���ͨ��
   */
  public static final int PLCONTRACTSTATUS_THROUGHAUDIT = 3;

  /**
   * ����-��ͬ״̬-����ͨ��
   */
  public static final int PLCONTRACTSTATUS_THROUGHAPPROVAL = 4;

  /**
   * ����-��ͬ״̬-���δͨ��
   */
  public static final int PLCONTRACTSTATUS_NOTHROUGHAUDIT = 5;

  /**
   * ����-��ͬ״̬-����δͨ��
   */
  public static final int PLCONTRACTSTATUS_NOTHROUGHAPPROVAL = 6;

  /**
   * ����-��ͬ״̬-�ٴ����
   */
  public static final int PLCONTRACTSTATUS_SECAUDIT = 7;

  /**
   * ����-��ͬ״̬-�ٴ�����
   */
  public static final int PLCONTRACTSTATUS_SECAPPROVAL = 8;

  /**
   * ����-��ͬ״̬-��ӡ���
   */
  public static final int PLCONTRACTSTATUS_ISSUEDNOTICES = 9;

  /**
   * ����-��ͬ״̬-�ѷ���
   */
  public static final int PLCONTRACTSTATUS_ISSUING = 10;

  /**
   * ����-��ͬ״̬-������
   */
  public static final int PLCONTRACTSTATUS_RECOVING = 11;

  /**
   * ����-��ͬ״̬-�ѻ���
   */
  public static final int PLCONTRACTSTATUS_RECOVERCLEAR = 12;

  /**
   * ����-��ͬ״̬-ע��
   */
  public static final int PLCONTRACTSTATUS_CANCELLATION = 13;

  /**
   * ����-��ͬ״̬-����ͨ��
   */
  public static final int PLCONTRACTSTATUS_FINALJUDGMENT = 14;

  /**
   * ����-��ͬ״̬-����ͨ��
   */
  public static final int PLCONTRACTSTATUS_FIRSTCHECK = 15;

  /**
   * ����-��ͬ״̬-�ؼ�ȷ��
   */
  public static final int PLCONTRACTSTATUS_REDATESURE = 16;

  /**
   * ����-��ͬ״̬-�������ͨ��
   */
  public static final int PLCONTRACTSTATUS_CHKAGAIN = 17;

  /**
   * ����-��ͬ״̬-��������ͨ��
   */
  public static final int PLCONTRACTSTATUS_APPROVALAGAIN = 18;
  
  /**
   * ����-��ͬ״̬-�������ͨ��
   */
  public static final int PLCONTRACTSTATUS_CHKAGAIN_NOTPASS = 19;

  /**
   * ����-��ͬ״̬-��������ͨ��
   */
  public static final int PLCONTRACTSTATUS_APPROVALAGAIN_NOTPASS = 20;

  /* ����-��ǰ�������� */
  public static final String PLADVANCERECOVERTYPE = "org.xpup.hafmis.common.util.enumerate.PLAdvanceRecoverType";

  /**
   * ����-��ǰ��������-������ǰ����
   */
  public static final int PLADVANCERECOVERTYPE_PART = 1;

  /**
   * ����-��ǰ��������-һ�����廹
   */
  public static final int PLADVANCERECOVERTYPE_ALL = 2;

  /* ����-ҵ��״̬ */
  public static final String PLBUSINESSSTATE = "org.xpup.hafmis.common.util.enumerate.PLBusinessState";

  /**
   * ����-ҵ��״̬-����
   */
  public static final int PLBUSINESSSTATE_EXP = 1;

  /**
   * ����-ҵ��״̬-����
   */
  public static final int PLBUSINESSSTATE_IMP = 2;

  /**
   * ����-ҵ��״̬-�Ǽ�
   */
  public static final int PLBUSINESSSTATE_SIGN = 3;

  /**
   * ����-ҵ��״̬-ȷ��
   */
  public static final int BUSINESSSTATE_SURE = 4;

  /**
   * ����-ҵ��״̬-����
   */
  public static final int BUSINESSSTATE_CHECK = 5;

  /**
   * ����-ҵ��״̬-����
   */
  public static final int BUSINESSSTATE_CLEARACCOUNT = 6;

  /* ����-ҵ������ */
  public static final String PLBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.PLBusinessType";

  /**
   * ����-ҵ������-����
   */
  public static final int PLBUSINESSTYPE_ISSUED = 1;

  /**
   * ����-ҵ������-���ʻ���
   */
  public static final int PLBUSINESSTYPE_SINGLERECOVER = 2;

  /**
   * ����-ҵ������-������ǰ����
   */
  public static final int PLBUSINESSTYPE_PARTRECOVER = 3;

  /**
   * ����-ҵ������-һ���Ի���
   */
  public static final int PLBUSINESSTYPE_ALLCLEAR = 4;

  /**
   * ����-ҵ������-��������
   */
  public static final int PLBUSINESSTYPE_SOMERECOVER = 5;

  /**
   * ����-ҵ������-���˺��������ģ�
   */
  public static final int PLBUSINESSTYPE_BADDEBTSOFFCENTRE = 6;

  /**
   * ����-ҵ������-���˺�����������
   */
  public static final int PLBUSINESSTYPE_BADDEBTSOFFOTHER = 7;

  /**
   * ����-ҵ������-�����ջأ����ģ�
   */
  public static final int PLBUSINESSTYPE_BADDEBTSRECOVERCENTER = 8;

  /**
   * ����-ҵ������-�����ջأ�������
   */
  public static final int PLBUSINESSTYPE_BADDEBTSRECOVEROTHER = 9;

  /**
   * ����-ҵ������-��ת����
   */
  public static final int PLBUSINESSTYPE_CARRYOVERDUE = 10;

  /**
   * ����-ҵ������-��ת���
   */
  public static final int PLBUSINESSTYPE_CARRYPAY = 11;

  /**
   * ����-ҵ������-���˵���
   */
  public static final int PLBUSINESSTYPE_MISDIRECTCHG = 12;

  /**
   * ����-ҵ������-����
   */
  public static final int PLBUSINESSTYPE_OVERPAY = 13;

  /**
   * ����-ҵ������-��֤��
   */
  public static final int PLBUSINESSTYPE_MARGIN = 14;

  /**
   * ����-ҵ������-��Ϣ
   */
  public static final int PLBUSINESSTYPE_CLEARINTEREST = 15;

  /**
   * ����-ҵ������-����ת
   */
  public static final int PLBUSINESSTYPE_INITDATA = 16;

  /* ����-������λ */
  public static final String PLCANCELORG = "org.xpup.hafmis.common.util.enumerate.PLCancelORG";

  /**
   * ����-������λ-����
   */
  public static final int PLCANCELORG_CENTRE = 1;

  /**
   * ����-������λ-������˾
   */
  public static final int PLCANCELORG_GUARANTEECORP = 2;

  /**
   * ����-������λ-����
   */
  public static final int PLCANCELORG_OTHERS = 3;

  /* ����-��ǰ�����Ի��������ĸ������� */
  public static final String PLRECOVERPARASCHG = "org.xpup.hafmis.common.util.enumerate.PLRecoverParasChg";

  /**
   * ����-��ǰ�����Ի��������ĸ�������-����ԭ���»����
   */
  public static final int PLRECOVERPARASCHG_SAMEPAY = 1;

  /**
   * ����-��ǰ�����Ի��������ĸ�������-����ʣ������
   */
  public static final int PLRECOVERPARASCHG_SAMEMONTHS = 2;

  /**
   * ����-��ǰ�����Ի��������ĸ�������-����ı�ʣ������
   */
  public static final int PLRECOVERPARASCHG_CHGMONTHS = 3;

  /* ����-��ǰ������ͽ�� */
  public static final String PLMINSPREPAYMENT = "org.xpup.hafmis.common.util.enumerate.PLMinsPrepayment";

  /**
   * ����-��ǰ������ͽ��
   */
  public static final int PLMINSPREPAYMENT_1 = 1;

  /* ����-��������������ǰ���� */
  public static final String PLRPREPAYMENTYEARTIMES = "org.xpup.hafmis.common.util.enumerate.PLPrepaymentYearTimes";

  /**
   * ����-��������������ǰ����
   */
  public static final int PLRPREPAYMENTYEARTIMES_1 = 1;

  /* ����-�������������������ǰ���� */
  public static final String PLPREPAYMENTDEADLINESTIMES = "org.xpup.hafmis.common.util.enumerate.PLPrepaymentDeadlinesTimes";

  /**
   * ����-�������������������ǰ����
   */
  public static final int PLPREPAYMENTDEADLINESTIMES_1 = 1;

  /* ����-��ǰ�����Ƿ���ȡ������ */
  public static final String PLPREPAYMENTFEES = "org.xpup.hafmis.common.util.enumerate.PLPrepaymentFees";

  /**
   * ����-��ǰ�����Ƿ���ȡ������-�ǣ�����ǰ�����
   */
  public static final int PLPREPAYMENTFEES_PREPAYMENT = 1;

  /**
   * ����-��ǰ�����Ƿ���ȡ������-�ǣ�����
   */
  public static final int PLPREPAYMENTFEES_PAYMENT = 2;

  /**
   * ����-��ǰ�����Ƿ���ȡ������-��
   */
  public static final int PLPREPAYMENTFEES_NO = 3;

  /* ����״̬ */
  public static final String APPSTATUS = "org.xpup.hafmis.common.util.enumerate.AppStatus";

  /**
   * ���״̬-δ����
   */
  public static final int APPSTATUS_1 = 0;

  /**
   * ���״̬-����
   */
  public static final int APPSTATUS_2 = 1;

  /* ����-��ͬ�����Ϣ */
  public static final String CONTRACTCHANGEINFO = "org.xpup.hafmis.common.util.enumerate.PLContractChangeInfo";

  /**
   * ����-��ͬ�����Ϣ���������Ϣ
   */
  public static final int PLPREPAYMENTFEES_BORROWERINFO = 1;

  /**
   * ����-��ͬ�����Ϣ,�����������Ϣ
   */
  public static final int PLPREPAYMENTFEES_AUXILIARYINFO = 2;

  /**
   * ����-��ͬ�����Ϣ,������Ϣ
   */
  public static final int PLPREPAYMENTFEES_FLOORINFO = 3;

  /**
   * ����-��ͬ�����Ϣ,��Ѻ��ͬ��Ϣ
   */
  public static final int PLPREPAYMENTFEES_PLEDGEINFO = 4;

  /**
   * ����-��ͬ�����Ϣ,��Ѻ��ͬ��Ϣ
   */
  public static final int PLPREPAYMENTFEES_IMPAWNINFO = 5;

  /**
   * ����-��ͬ�����Ϣ,��֤��
   */
  public static final int PLPREPAYMENTFEES_BIALINFO = 6;

  /* ����-������������Ϣ */
  public static final String CONGEALINFOTYPE = "org.xpup.hafmis.common.util.enumerate.PLCongealInfoType";

  /**
   * ����-���������ͣ������
   */
  public static final int PLPREPAYMENTFEES_BORROWERTYPE = 1;

  /**
   * ����-����������,���������
   */
  public static final int PLPREPAYMENTFEES_AUXILIARYTYPE = 2;

  /**
   * ����-����������,��֤��
   */
  public static final int PLPREPAYMENTFEES_BIALTYPE = 3;

  /* ����-������������Ϣ */
  public static final String CONGEALINFOTHAWTYPE = "org.xpup.hafmis.common.util.enumerate.PLCongealInfoThaw";

  /**
   * ����-���������ͣ��ⶳ
   */
  public static final int PLPREPAYMENTFEES_CONGEALINFOTHAW = 1;

  /**
   * ����-����������,����
   */
  public static final int PLPREPAYMENTFEES_CONGEALINFOGELATION = 0;

  /* �˻�״̬-ҵ������Ϊ���� */
  public static final String GIVEBACK = "org.xpup.hafmis.common.util.enumerate.PLGiveBack";

  /**
   * ��������-ҵ������-����
   */
  public static final int PLPREPAYMENTFEES_NORMAL = 1;

  /**
   * ��������-ҵ������-����
   */
  public static final int PLPREPAYMENTFEES_OVERDUE = 2;

  /**
   * ��������-ҵ������-����
   */
  public static final int PLPREPAYMENTFEES_OTHERS = 3;

  /*-----------------����-------------------------*/
  /* ���㷽ʽ */
  public static final String FNSEETLETYPE = "org.xpup.hafmis.common.util.enumerate.FnSettleType";

  /**
   * ���㷽ʽ-ͳһ����
   */
  public static final String FNSEETLETYPE_UNIONIZE = "0";

  /**
   * ���㷽ʽ-��������
   */
  public static final String FNSEETLETYPE_INDEPENDENCE = "1";

  /* ����ƾ֤������ */
  public static final String CREDENCE_NUM_TYPE = "org.xpup.hafmis.common.util.enumerate.CredenceNumType";

  /**
   * ����ƾ֤������-���ƾ֤
   */
  public static final String CREDENCE_NUM_TYPE_ACCOUNTANT = "0";

  /**
   * ����ƾ֤������-����ƾ֤
   */
  public static final String CREDENCE_NUM_TYPE_TREASURER = "1";

  /* ����-ƾ֤���� */
  public static final String CREDENCETYPE = "org.xpup.hafmis.common.util.enumerate.FnCredenceType";

  /**
   * ����ƾ֤������-�ֽ�ƾ֤
   */
  public static final String CREDENCETYPE_CASH = "0";

  /**
   * ����ƾ֤������-���д��ƾ֤
   */
  public static final String CREDENCETYPE_BANK = "1";

  /* ����-ƾ֤״̬&ҵ����־���� */
  public static final String CREDSTATE = "org.xpup.hafmis.common.util.enumerate.FnCredenceState";

  /**
   * ƾ֤״̬-ȷ��
   */
  public static final String CREDSTATE_SIGN = "0";

  /**
   * ƾ֤״̬-����
   */
  public static final String CREDSTATE_CHECK = "1";

  /**
   * ƾ֤״̬-����
   */
  public static final String CREDSTATE_BOOKKEEPING = "2";

  /* ����-��Ŀ������ */
  public static final String SUBCATEGORYCODE = "org.xpup.hafmis.common.util.enumerate.FnSubjectCategoryCode";

  /**
   * ��Ŀ������-�ʲ�
   */
  public static final String SUBCATEGORYCODE_ASSETS = "0";

  /**
   * ��Ŀ������-��ծ
   */
  public static final String SUBCATEGORYCODE_LIABILITIES = "1";

  /**
   * ��Ŀ������-Ȩ��
   */
  public static final String SUBCATEGORYCODE_INTERESTS = "2";

  /**
   * ��Ŀ������-�ɱ�
   */
  public static final String SUBCATEGORYCODE_COST = "3";

  /**
   * ��Ŀ������-����
   */
  public static final String SUBCATEGORYCODE_PROFIT_LOSS = "4";

  /* ����-���� */
  public static final String BALANCEDIRECTION = "org.xpup.hafmis.common.util.enumerate.FnBalanceDirection";

  /**
   * ����-��
   */
  public static final String BALANCEDIRECTION_DEBIT = "0";

  /**
   * ����-��
   */
  public static final String BALANCEDIRECTION_CREDIT = "1";

  /**
   * ����-ƽ
   */
  public static final String BALANCEDIRECTION_AVE = "2";

  /* ����-��Ŀ���� */
  public static final String SUBATTRIBUTE = "org.xpup.hafmis.common.util.enumerate.FnSubjectsAttribute";

  /**
   * ��Ŀ����-�ֽ�
   */
  public static final String SUBATTRIBUTE_CASH = "0";

  /**
   * ��Ŀ����-����
   */
  public static final String SUBATTRIBUTE_BANK = "1";

  /**
   * ��Ŀ����-����
   */
  public static final String SUBATTRIBUTE_OTHERS = "2";

  /* ����-�����ϵ���� */
  public static final String SUBRELATION = "org.xpup.hafmis.common.util.enumerate.FnSubjectRelation";

  /**
   * �����ϵ����-���´�
   */
  public static final String SUBRELATION_OFFICE = "0";

  /**
   * �����ϵ����-����
   */
  public static final String SUBRELATION_BANK = "1";

  /**
   * �����ϵ����-��λ
   */
  public static final String SUBRELATION_ORG = "2";

  /**
   * �����ϵ����-����
   */
  public static final String SUBRELATION_OTHERS = "3";

  /* ����-�����ѯ��ʽ */
  public static final String REPORTQUERYMANNER = "org.xpup.hafmis.common.util.enumerate.FnReportQueryManner";

  /**
   * �����ѯ��ʽ-����
   */
  public static final String REPORTQUERYMANNER_YEAR = "0";

  /**
   * �����ѯ��ʽ-����
   */
  public static final String REPORTQUERYMANNER_MONTH = "1";

  /**
   * �����ѯ��ʽ-����
   */
  public static final String REPORTQUERYMANNER_DAY = "2";

  /* ����-��ת״̬ */
  public static final String CARRYOVERSTATE = "org.xpup.hafmis.common.util.enumerate.FnCarryoverState";

  /**
   * ��ת״̬-δ��ת
   */
  public static final String CARRYOVERSTATE_NO = "0";

  /**
   * ��ת״̬-�ѽ�תδ����
   */
  public static final String CARRYOVERSTATE_YESNOKEEPING = "1";

  /**
   * ��ת״̬-�Ѽ���
   */
  public static final String CARRYOVERSTATE_BOOKKEEPING = "2";

  /* ����-ҵ������ */
  public static final String FNBUSINESSTYPE = "org.xpup.hafmis.common.util.enumerate.FnBusinessType";

  /**
   * ҵ������-������_�ɴ�
   */
  public static final String FNBUSINESSTYPE_COLL_PAYMENT = "1";

  /**
   * ҵ������-������_������ȡ
   */
  public static final String FNBUSINESSTYPE_COLL_SOMEPICK = "2";

  /**
   * ҵ������-������_������ȡ
   */
  public static final String FNBUSINESSTYPE_COLL_ALLPICK = "3";

  /**
   * ҵ������-������_�ⲿת��
   */
  public static final String FNBUSINESSTYPE_COLL_OUTTRANSOUT = "4";

  /**
   * ҵ������-������_�ⲿת��
   */
  public static final String FNBUSINESSTYPE_COLL_OUTTRANSIN = "5";

  /**
   * ҵ������-������_�ڲ�ת��
   */
  public static final String FNBUSINESSTYPE_COLL_INTRANSOUT = "6";

  /**
   * ҵ������-������_�ڲ�ת��
   */
  public static final String FNBUSINESSTYPE_COLL_INTRANSIN = "7";

  /**
   * ҵ������-������_�ڲ�ת��ת��
   */
  public static final String FNBUSINESSTYPE_COLL_INTRANS = "8";

  /**
   * ҵ������-������_���ˣ��գ�
   */
  public static final String FNBUSINESSTYPE_COLL_OVERPAYMENT = "9";

  /**
   * ҵ������-������_���ˣ���ȡ��
   */
  public static final String FNBUSINESSTYPE_COLL_OVERPICKPAYMENT = "10";

  /**
   * ҵ������-������_��Ϣ
   */
  public static final String FNBUSINESSTYPE_COLL_CLEARINTEREST = "11";

  /**
   * ҵ������-������_���˵���
   */
  public static final String FNBUSINESSTYPE_COLL_ERRACCOUNTUP = "12";

  /**
   * ҵ������-������_���˵���
   */
  public static final String FNBUSINESSTYPE_COLL_ERRACCOUNTDOWN = "13";

  /**
   * ҵ������-�����𻹴�
   */
  public static final String FNBUSINESSTYPE_COLL_GJJBACK = "23";

  /**
   * ҵ������-����_����
   */
  public static final String FNBUSINESSTYPE_LOAN_ACCORD = "21";

  /**
   * ҵ������-����_����
   */
  public static final String FNBUSINESSTYPE_LOAN_CALLBACK = "22";

  /* ����-������� */
  public static final String FNMONEYTYPE = "org.xpup.hafmis.common.util.enumerate.FnMoneyType";

  /**
   * �������-������_�ɴ���
   */
  public static final String FNMONEYTYPE_COLL_PAYMENT = "1";

  /**
   * �������-������_��ȡ���
   */
  public static final String FNMONEYTYPE_COLL_PICK = "2";

  /**
   * �������-������_������Ϣ
   */
  public static final String FNMONEYTYPE_COLL_CLEARACCOUNTINTEREST = "3";

  /**
   * �������-������_ת�����
   */
  public static final String FNMONEYTYPE_COLL_TRANSOUT = "4";

  /**
   * �������-������_ת����
   */
  public static final String FNMONEYTYPE_COLL_TTRANSIN = "5";

  /**
   * �������-������_ת����Ϣ
   */
  public static final String FNMONEYTYPE_COLL_TRANSOUTINTEREST = "6";

  /**
   * �������-������_��Ϣ��Ϣ
   */
  public static final String FNMONEYTYPE_COLL_CLEARINTEREST = "7";

  /**
   * �������-������_���˽��
   */
  public static final String FNMONEYTYPE_COLL_OVERPAYMENT = "8";

  /**
   * �������-����_���Ž��
   */
  public static final String FNMONEYTYPE_LOAN_ACCORD = "11";

  /**
   * �������-����_������������
   */
  public static final String FNMONEYTYPE_LOAN_RECEVERNORMALPRINCIPAL = "12";

  /**
   * �������-����_�������ڱ���
   */
  public static final String FNMONEYTYPE_LOAN_RECEVEROVERDUEPRINCIPAL = "13";

  /**
   * �������-����_������Ϣ
   */
  public static final String FNMONEYTYPE_LOAN_RECEVERINTEREST = "14";

  /**
   * �������-����_�����ջؽ��
   */
  public static final String FNMONEYTYPE_LOAN_CLEARRECOVERMONEY = "15";

  /**
   * �������-����_���˽��
   */
  public static final String FNMONEYTYPE_LOAN_OVERPAYMENT = "16";

  /**
   * �������-����_��֤���
   */
  public static final String FNMONEYTYPE_LOAN_MARGIN = "17";

  /**
   * �������-����_��֤����Ϣ
   */
  public static final String FNMONEYTYPE_LOAN_MARGININTEREST = "18";

  /**
   * �������-����_ʵ�ս��
   */
  public static final String FNMONEYTYPE_LOAN_REALRECEVER = "19";

  /**
   * �������-������_������
   */
  public static final String FNMONEYTYPE_LOAN_PUNISHINTEREST = "20";
  
  /**
   * �������-����_��ȡ���
   */
  public static final String FNMONEYTYPE_LOAN_PICKMONEY = "21";

  /* ����-����ʽ��ʶ */
  public static final String REPORTLOGO = "org.xpup.hafmis.common.util.enumerate.FnReportLogo";

  /**
   * ����ʽ��ʶ-������
   */
  public static final String REPORTLOGO_BEGBALANCE_DEBIT = "AA";

  /**
   * ����ʽ��ʶ-�ڳ����
   */
  public static final String REPORTLOGO_BEGBALANCE_CREDIT = "AB";

  /**
   * ����ʽ��ʶ-��ĩ���(����)
   */
  public static final String REPORTLOGO_ENDBALANCE_DEBIT = "AC";

  /**
   * ����ʽ��ʶ-��ĩ���
   */
  public static final String REPORTLOGO_ENDBALANCE_CREDIT = "AD";

  /**
   * ����ʽ��ʶ-���ڷ�����跽��
   */
  public static final String REPORTLOGO_CURFIGURES_DEBIT = "AE";

  /**
   * ����ʽ��ʶ-���ڷ����������
   */
  public static final String REPORTLOGO_CURFIGURES_CREDIT = "AF";

  /**
   * ����ʽ��ʶ-��ԭ�����귢����跽����Ϊ�������ۼƷ�����跽��
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT = "AG";

  /**
   * ����ʽ��ʶ-��ԭ�����귢�����������Ϊ�������ۼƷ����������
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT = "AH";

  /**
   * ����ʽ��ʶ-��ԭ�����귢����跽����Ϊ�������ۼƷ�����跽��
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_DEBIT = "AI";

  /**
   * ����ʽ��ʶ-��ԭ�����귢�����������Ϊ�������ۼƷ����������
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_CREDIT = "AJ";

  /**
   * ����ʽ��ʶ-�����ۼƷ�����跽�������ã�
   */
  public static final String REPORTLOGO_CURFIGURES_SUMDEBIT = "AK";

  /**
   * ����ʽ��ʶ-�����ۼƷ���������������ã�
   */
  public static final String REPORTLOGO_CURFIGURES_SUMCREDIT = "AL";

  /**
   * ����ʽ��ʶ-��ԭ�������ۼƷ�����跽����Ϊ����ĩ�ۼƷ�����跽��
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT = "AM";

  /**
   * ����ʽ��ʶ-��ԭ�������ۼƷ������������Ϊ����ĩ�ۼƷ����������
   */
  public static final String REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT = "AN";

  /**
   * ����ʽ��ʶ-�����ۼƷ�����跽�������ã�
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT = "AO";

  /**
   * ����ʽ��ʶ-�����ۼƷ���������������ã�
   */
  public static final String REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT = "AP";

  /**
   * ����ʽ��ʶ-��
   */
  public static final String REPORTLOGO_COL = "AQ";

  /**
   * ����ʽ��ʶ-��ȡ�����򡢽��졢����������ס����ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_REPAIR_CURTERMAMOUNT = "BA";

  /**
   * ����ʽ��ʶ-��ȡ�����򡢽��졢����������ס����ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_REPAIR_CURYEARAMOUNT = "BB";

  /**
   * ����ʽ��ʶ-��ȡ�����򡢽��졢����������ס����ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_REPAIR_CURYEARSUMAMOUNT = "BC";

  /**
   * ����ʽ��ʶ-��ȡ����������ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_RETIREMENT_CURTERMAMOUNT = "BD";

  /**
   * ����ʽ��ʶ-��ȡ����������ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_RETIREMENT_CURYEARAMOUNT = "BE";

  /**
   * ����ʽ��ʶ-��ȡ����������ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT = "BF";

  /**
   * ����ʽ��ʶ-��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_LOSEABILITY_CURTERMAMOUNT = "BG";

  /**
   * ����ʽ��ʶ-��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_LOSEABILITY_CURYEARAMOUNT = "BH";

  /**
   * ����ʽ��ʶ-��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT = "BI";

  /**
   * ����ʽ��ʶ-��ȡ������������ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_ABROAD_CURTERMAMOUNT = "BJ";

  /**
   * ����ʽ��ʶ-��ȡ������������ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_ABROAD_CURYEARAMOUNT = "BK";

  /**
   * ����ʽ��ʶ-��ȡ������������ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_ABROAD_CURYEARSUMAMOUNT = "BL";

  /**
   * ����ʽ��ʶ-��ȡ�������������Ϣ��ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT = "BM";

  /**
   * ����ʽ��ʶ-��ȡ�������������Ϣ��ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT = "BN";

  /**
   * ����ʽ��ʶ-��ȡ�������������Ϣ��ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT = "BO";

  /**
   * ����ʽ��ʶ-��ȡ��֧��������ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT = "BP";

  /**
   * ����ʽ��ʶ-��ȡ��֧��������ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT = "BQ";

  /**
   * ����ʽ��ʶ-��ȡ��֧��������ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT = "BR";

  /**
   * ����ʽ��ʶ-��ȡ��������ȡ�����ڷ�����
   */
  public static final String REPORTLOGO_OTHERS_CURTERMAMOUNT = "BS";

  /**
   * ����ʽ��ʶ-��ȡ��������ȡ�������ۼƷ�����
   */
  public static final String REPORTLOGO_OTHERS_CURYEARAMOUNT = "BT";

  /**
   * ����ʽ��ʶ-��ȡ��������ȡ����ĩ�ۼƷ�����
   */
  public static final String REPORTLOGO_OTHERS_CURYEARSUMAMOUNT = "BU";

  /**
   * ����ʽ��ʶ-!��ʽ��
   */
  public static final String REPORTLOGO_FORMULA = "J";

  /* ����-�˲�����-���ֺϼ����� */
  public static final String FNSUMMARY = "org.xpup.hafmis.common.util.enumerate.FnSummary";

  /**
   * �� �� �� ת
   */
  public static final String FNSUMMARY_LASTYEARCLEAR = "0";

  /**
   * �� �� �� ��
   */
  public static final String FNSUMMARY_BGNBLAN = "1";

  /**
   * �� �� �� ��
   */
  public static final String FNSUMMARY_DAYSUM = "2";

  /**
   * �� �� �� ��
   */
  public static final String FNSUMMARY_TERMSUM = "3";

  /**
   * �� �� �� ��
   */
  public static final String FNSUMMARY_YEARSUM = "4";

  /* ����-�������� */
  public static final String SETTLETYPE = "org.xpup.hafmis.common.util.enumerate.SettleType";

  /**
   * ȫ��
   */
  public static final String SETTLETYPE_ALL = "0";

  /**
   * ��λ
   */
  public static final String SETTLETYPE_ORG = "1";

  /**
   * ����
   */
  public static final String SETTLETYPE_BANK = "2";

  /**
   * ���´�
   */
  public static final String SETTLETYPE_OFFICE = "3";

  /* �����𻹴�״̬ */
  public static final String COLLLOANBACKSTATUS = "org.xpup.hafmis.common.util.enumerate.CollLoanbackStatus";

  /**
   * ����
   */
  public static final String COLLLOANBACKSTATUS_IMPORT = "1";

  /**
   * �ۿ�
   */
  public static final String COLLLOANBACKSTATUS_KOUMONEY = "2";

  /**
   * ����
   */
  public static final String COLLLOANBACKSTATUS_EXPORT = "3";

  /* ����ҵ��ƾ֤ģʽ */
  public static final String SPECAILBIZMODEL = "org.xpup.hafmis.common.util.enumerate.SpecailBizModel";

  /**
   * ����ҵ��ƾ֤ģʽ-���´�
   */
  public static final String SPECAILBIZMODEL_OFFICE = "1";

  /**
   * ����ҵ��ƾ֤ģʽ-����
   */
  public static final String SPECAILBIZMODEL_BANK = "2";

  /**
   * ����ҵ��ƾ֤ģʽ-��λ
   */
  public static final String SPECAILBIZMODEL_ORG = "3";

  /* �Ƿ���ڵ�λ�� */
  public static final String ISHAVEORGVERSION = "org.xpup.hafmis.common.util.enumerate.IshaveOrgversion";

  /**
   * �Ƿ���ڵ�λ��-������
   */
  public static final String IS_NOHAVE = "0";

  /**
   * �Ƿ���ڵ�λ��-����
   */
  public static final String IS_HAVE = "1";

  /* ����-����������ת��Ӣ������ */
  public static final String TENNUMBER = "org.xpup.hafmis.common.util.enumerate.TenNumber";

  public static final String TENNUMBER_1 = "1";

  public static final String TENNUMBER_2 = "2";

  public static final String TENNUMBER_3 = "3";

  public static final String TENNUMBER_4 = "4";

  public static final String TENNUMBER_5 = "5";

  public static final String TENNUMBER_6 = "6";

  public static final String TENNUMBER_7 = "7";

  public static final String TENNUMBER_8 = "8";

  public static final String TENNUMBER_9 = "9";

  public static final String TENNUMBER_10 = "10";

  public static final String TENNUMBER_11 = "11";

  public static final String TENNUMBER_12 = "12";

  public static final String TENNUMBER_13 = "13";

  public static final String TENNUMBER_14 = "14";

  public static final String TENNUMBER_15 = "15";

  public static final String TENNUMBER_16 = "16";

  public static final String TENNUMBER_17 = "17";

  public static final String TENNUMBER_18 = "18";

  public static final String TENNUMBER_19 = "19";

  public static final String TENNUMBER_20 = "20";

  public static final String TENNUMBER_21 = "21";

  public static final String TENNUMBER_22 = "22";

  /* ������ҵ����־��ѯ */
  public static final String BUSINESSLOGSEARCH = "org.xpup.hafmis.common.util.enumerate.BusinessLogSearch";

  /**
   * ���
   */
  public static final String BUSINESSLOGSEARCH_PAYMENT = "A";

  /**
   * ��λ����
   */
  public static final String BUSINESSLOGSEARCH_ORGADDPAY = "B";

  /**
   * ����
   */
  public static final String BUSINESSLOGSEARCH_ORGOVERPAY = "C";

  /**
   * ��ȡ
   */
  public static final String BUSINESSLOGSEARCH_PICKUP = "D";

  /**
   * ת��
   */
  public static final String BUSINESSLOGSEARCH_TRANOUT = "E";

  /**
   * ת��
   */
  public static final String BUSINESSLOGSEARCH_TRANIN = "F";

  /**
   * ����
   */
  public static final String BUSINESSLOGSEARCH_CHGACCOUNT = "G";

  /**
   * ��Ϣ
   */
  public static final String BUSINESSLOGSEARCH_OVERDUEINTEREST = "H";

  /**
   * ������ȡ
   */
  public static final String BUSINESSLOGSEARCH_SPECIALPICKUP = "I";

  /**
   * ���ʵ���
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTINTEREST = "J";

  /**
   * ���˲���
   */
  public static final String BUSINESSLOGSEARCH_EMPADDPAY = "K";

  /**
   * ��ɱ�������
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTPAYMENTRATE = "L";

  /**
   * ���ʻ�������
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTSALARYBASE = "M";

  /**
   * �ɶ����
   */
  public static final String BUSINESSLOGSEARCH_ADJUSTPAYMENT = "N";

  /**
   * ��Ա���
   */
  public static final String BUSINESSLOGSEARCH_CHGPERSON = "O";

  /**
   * ��λ����
   */
  public static final String BUSINESSLOGSEARCH_ORGOPENACCOUNT = "P";

  /**
   * ��λ���
   */
  public static final String BUSINESSLOGSEARCH_CHGORG = "Q";

  /* ��ҵ�濪�� */
  public static final String ORG_OR_CENTER_INFO = "org.xpup.hafmis.common.util.enumerate.OcIsOrgOrCenter";

  /**
   * ����ģ��-����������-��λ��
   */
  public static final int ORG_OR_CENTER_INFO_ORG = 1;

  /**
   * ����ģ��-����������-���İ�
   */
  public static final int ORG_OR_CENTER_INFO_CENTER = 2;

  /* ��ȡ״̬ */
  public static final String OC_NOT_PICK_UP_INFO = "org.xpup.hafmis.common.util.enumerate.OcIsPickUp";

  /**
   * ����ģ��-����������-δ��ȡ
   */
  public static final String OC_NOT_PICK_UP = "0";

  /**
   * ����ģ��-����������-����ȡ
   */
  public static final String OC_PICK_UP = "1";

  /**
   * ����ģ��-����������-δ�ύ
   */
  public static final String OC_PICK_UP_OVER = "2";

  /*
   * ���-�ɴ淽ʽ-����
   */

  public static final int PAY_WAY_HUAKUAN = 1;

  /*
   * ���-�ɴ淽ʽ-֧Ʊ
   */
  public static final int PAY_WAY_CHECK = 2;

  /*
   * ���-�ɴ淽ʽ-�ֽ�
   */
  public static final int PAY_WAY_CASH = 3;

  /*
   * ���-�ɴ淽ʽ
   */
  public static final String PAY_WAY_INFO = "org.xpup.hafmis.common.util.enumerate.PayWay";

  /*
   * ���-�ɴ����-���
   */

  public static final int PAY_KIND_PAYMENT = 1;

  /*
   * ���-�ɴ����-����
   */

  public static final int PAY_KIND_ADDPAY = 2;

  /*
   * ���-�ɴ����-Ԥ��
   */
  public static final int PAY_KIND_ADVANCEPAY = 3;

  public static final String PAY_KIND_INFO = "org.xpup.hafmis.common.util.enumerate.PayKind";

  /**
   * ��������
   */
  public static final String ADDPAYTYPE = "org.xpup.hafmis.common.util.enumerate.AddPayType";

  public static final String ADDPAYTYPE_A = "1";

  public static final String ADDPAYTYPE_B = "2";

  public static final String ADDPAYTYPE_C = "3";

  /**
   * ��ǰ��������
   */
  public static final String AHEADTYPE = "org.xpup.hafmis.common.util.enumerate.AheadType";

  /**
   * �ӳ�
   */
  public static final String AHEADTYPE_1 = "1";

  /**
   * ����
   */
  public static final String AHEADTYPE_2 = "2";

  /**
   * ���ı�����
   */
  public static final String AHEADTYPE_3 = "3";

  /**
   * �б������´�����
   */
  public static final String OFFICECODE_SBJ = "4028810c120af23c01120b14ed840005";

  /* ��������˹�ϵ */
  public static final String RELATION = "org.xpup.hafmis.common.util.enumerate.Relation";

  /**
   * ��������˹�ϵ-��ż
   */
  public static final String RELATION_CONSORT = "1";

  /**
   * ��������˹�ϵ-��ĸ
   */
  public static final String RELATION_PARENT = "2";

  /**
   * ��������˹�ϵ-��Ů
   */
  public static final String RELATION_CHILD = "3";
  /**
   * ��������˹�ϵ-����
   */
  // public static final String RELATION_FRIEND = "4";
}