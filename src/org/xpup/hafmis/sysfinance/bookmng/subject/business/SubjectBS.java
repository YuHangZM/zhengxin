package org.xpup.hafmis.sysfinance.bookmng.subject.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.CredenceModleDAO;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.SettleIncAndDecDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectRelationDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.Subject;

public class SubjectBS implements ISubjectBS {

  private BookParameterDAO bookParameterDAO = null;

  private SubjectDAO subjectDAO = null;

  private FnOperateLogDAO fnOperateLogDAO = null;

  private SubjectRelationDAO subjectRelationDAO = null;

  private CredenceModleDAO credenceModleDAO = null;

  private SettleIncAndDecDAO settleIncAndDecDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private TreasurerCredenceDAO treasurerCredenceDAO = null;

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setSubjectRelationDAO(SubjectRelationDAO subjectRelationDAO) {
    this.subjectRelationDAO = subjectRelationDAO;
  }

  public void setCredenceModleDAO(CredenceModleDAO credenceModleDAO) {
    this.credenceModleDAO = credenceModleDAO;
  }

  public void setSettleIncAndDecDAO(SettleIncAndDecDAO settleIncAndDecDAO) {
    this.settleIncAndDecDAO = settleIncAndDecDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }

  /**
   * ������ƿ�Ŀ
   */
  public void saveSubject(SubjectShowAF af, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      String code = af.getCode();// ��Ŀ����
      String name = af.getName();// ��Ŀ����
      String direction = af.getDirection();// ����
      String sortcode = af.getSortcode();// ��Ŀ���
      String property = af.getProperty();// ��Ŀ����
      
      // �жϿ�Ŀ�����Ƿ���ϸ����״���ṹ
      String level = this.is_Book_CodeStructure(code, securityInfo);
      // �ж�����Ŀ�Ŀ�������Ƿ�Ϊһ����Ŀ����
      boolean flag = this.is_CodeLevel_One(code, securityInfo);
      String codeLevelUp = "";
      if (flag == false) {// ��һ����Ŀ
        // ȡ�ö�Ӧ����һ����Ŀ�����λ��
        int temp_length = this.getCodeLevel_up(code, securityInfo);
        codeLevelUp = code.substring(0, temp_length);
      }

      //�ж��Ƿ��Ѿ����ڸÿ�Ŀ����
      String codeid=subjectDAO.is_CodeIn_WL(code,null,securityInfo);
      if(codeid!=null&&!codeid.equals("")){
        throw new BusinessException("��Ŀ������Ϣ�Ѿ�¼��!!");
      }
      
      
      Subject subject = new Subject();
      subject.setBookId(securityInfo.getBookId());
      subject.setSubjectCode(code);
      subject.setSubjectName(name);
      if (codeLevelUp != null && !codeLevelUp.equals("")) {
        subject.setParentSubjectCode(codeLevelUp);
      }
      if (!level.equals("0")) {
        subject.setSubjectLevel(level);
      }
      subject.setSubjectSortCode(sortcode);
      subject.setBalanceDirection(direction);
      subject.setSubjectProperty(property);
      subject.setSubjectSt("0");
      subject.setBizDate(securityInfo.getUserInfo().getFbizDate().substring(0,6));
      subjectDAO.insert(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
  }

  /**
   * �жϿ�Ŀ�����Ƿ���ϸ����״���ṹ,������-���������ĿӦ���ڵļ���
   */
  public String is_Book_CodeStructure(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    boolean flag = false;
    String level = "0";
    List codeLength = new ArrayList();
    codeLength = bookParameterDAO.getParamExplain_WL("1", securityInfo);
    if (codeLength.size() != 0) {
      String[] codestr = new String[codeLength.size()];
      for (int i = 0; i < codeLength.size(); i++) {
        codestr[i] = (String) codeLength.get(i);
      }
      String[] temp_codestr = new String[codeLength.size()];
      temp_codestr[0] = "0";
      for (int i = 0; i < codeLength.size(); i++) {
        if (i == 0) {
          temp_codestr[i] = "" + (Integer.parseInt(codestr[i]));
        } else {
          temp_codestr[i] = ""
              + (Integer.parseInt(temp_codestr[i - 1]) + Integer
                  .parseInt(codestr[i]));
        }
      }
      for (int i = 0; i < codeLength.size(); i++) {
        if (temp_codestr[i].equals((new Integer(code.length()).toString()))) {
          flag = true;
          level = new Integer(i + 1).toString();
          break;
        }
      }
    }
    if (flag == false) {
      throw new BusinessException("��������ȷ�Ŀ�Ŀ����!!");
    }
    return level;
  }

  /**
   * �ж�����Ŀ�Ŀ�����Ƿ����
   */
  public String is_Code_In(String code, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    String subjectId = "";
    try {
      subjectId = subjectDAO.is_CodeIn_WL(code, null, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return subjectId;
  }

  /**
   * �ж�����Ŀ�Ŀ�����Ƿ�Ϊһ����Ŀ����
   */
  public boolean is_CodeLevel_One(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    boolean flag = false;
    List codeLength = new ArrayList();
    try {
      codeLength = bookParameterDAO.getParamExplain_WL("1", securityInfo);
      if (codeLength.size() != 0) {
        String codestr = (String) codeLength.get(0);
        if (codestr.equals((new Integer(code.length()).toString()))) {
          flag = true;
        }
      }
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return flag;
  }

  /**
   * ȡ����һ����Ŀ��λ��
   */
  public int getCodeLevel_up(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    int length = 0;
    List codeLength = new ArrayList();
   try{
    codeLength = bookParameterDAO.getParamExplain_WL("1", securityInfo);
    if (codeLength.size() != 0) {
      String[] codestr = new String[codeLength.size()];
      for (int i = 0; i < codeLength.size(); i++) {
        codestr[i] = (String) codeLength.get(i);
      }
      String[] temp_codestr = new String[codeLength.size()];
      temp_codestr[0] = "0";
      for (int i = 0; i < codeLength.size(); i++) {
        if (i == 0) {
          temp_codestr[i] = "" + (Integer.parseInt(codestr[i]));
        } else {
          temp_codestr[i] = ""
              + (Integer.parseInt(temp_codestr[i - 1]) + Integer
                  .parseInt(codestr[i]));
        }
      }
      for (int i = 0; i < codeLength.size(); i++) {
        if (temp_codestr[i].equals((new Integer(code.length()).toString()))) {
          length = Integer.parseInt(temp_codestr[i - 1]);
          break;
        }
      }
    }
   } catch (Exception be) {
     be.printStackTrace();
     throw new BusinessException("��ѯ����");
   }
    return length;
  }

  /**
   * �жϿ�Ŀ�����Ƿ��Ѿ����ڣ�and SUBJECT_ST=0
   */
  public String is_ParentCode_Normal(String code, String states,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String parentCode = "";
    try {
      parentCode = subjectDAO.is_CodeIn_WL(code, states, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return parentCode;
  }

  /**
   * �ж�����Ŀ�Ŀ�������һ����Ŀ��FN111.SUBJECT_CODE���Ƿ����
   */
  public List is_ParentCodeRelation_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = subjectRelationDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return list;
  }

  /**
   * ���ݿ�Ŀ����ѯ��Ŀ��Ϣ
   */
  public List findSubjectTree(SubjectShowAF af, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = subjectDAO.findSubjectTree_WL(af.getSortcodeflag(), securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return list;
  }

  /**
   * �õ���Ŀ�Ŀ�Ŀ���
   */
  public String getSortcodeByCode(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String sortcode = "";
    try {
      sortcode = subjectDAO.getSortcodeByCode_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return sortcode;
  }

  /**
   * �õ���Ŀ�Ŀ�Ŀ����
   */
  public String getDirectionByCode(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String direction = "";
    try {
      direction = subjectDAO.getDirectionByCode_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return direction;
  }

  /**
   * �õ���Ŀ�Ŀ�Ŀ����
   */
  public String getProperyByCode_WL(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String propery = "";
    try {
      propery = subjectDAO.getProperyByCode_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return propery;
  }

  /**
   * ɾ����ƿ�Ŀ
   */
  public void deleteSubject(String codeid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // ȡ�ö�Ӧ�Ŀ�Ŀ����
      String subjectCode = subjectDAO.is_CodeInBySubjectId_WL(codeid, null,
          securityInfo);
      // �жϸ�����¼��SUBJECT_ID��FN110.SUBJECT_ID���Ƿ����
      String subjectId = this.is_Code_In(subjectCode, securityInfo);
      if (subjectId == null || subjectId.equals("")) {
        throw new BusinessException("�ÿ�Ŀ������ɾ��!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN110.PARENT_SUBJECT_CODE���Ƿ����
      List parentList = this.is_ParentCodeIn_WL(subjectCode, securityInfo);
      if (parentList.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ����һ����Ŀ���ܱ�ɾ��!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN111.SUBJECT_CODE���Ƿ����
      List codeRelationlist = this.is_ParentCodeRelation_In(subjectCode,
          securityInfo);
      if (codeRelationlist.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ�ѽ�����ϵ���ܱ�ɾ��!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN120.SUBJECT_CODE���Ƿ����
      List credModList = this.is_CredenceModle_In(subjectCode, securityInfo);
      if (credModList.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ������ƾ֤ģʽ���ܱ�ɾ��!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN202.BY_SUBJECT_CODE or FN202.SUBJECT_CODE���Ƿ����
      String flag = this.is_SettleIncAndDec_In(subjectCode, securityInfo);
      if (flag.equals("have")) {
        throw new BusinessException("�ÿ�Ŀ�����������ת���ܱ�ɾ��!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN201.SUBJECT_CODE or FN210.SUBJECT_CODE���Ƿ����
      List accCredList = this.is_AccountantCredence_In(subjectCode,
          securityInfo);
      if (accCredList.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ��Ӧ�ò��ܱ�ɾ��!!");
      }
      List treaCredList = this.is_TreasurerCredence_In(subjectCode,
          securityInfo);
      if (treaCredList.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ��Ӧ�ò��ܱ�ɾ��!!");
      }
      subjectDAO.delete_WL(subjectCode,securityInfo);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }

  }

  /**
   * �ж�����Ŀ�Ŀ�����Ƿ����
   */
  public List is_ParentCodeIn_WL(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List subjectIdByParentList = new ArrayList();
    ;
    try {
      subjectIdByParentList = subjectDAO.is_ParentCodeIn_WL(code, null,
          securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return subjectIdByParentList;
  }

  /**
   * �жϸ�����¼�Ŀ�Ŀ������FN120.SUBJECT_CODE���Ƿ����
   */
  public List is_CredenceModle_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = credenceModleDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return list;
  }

  /**
   * �жϸ�����¼�Ŀ�Ŀ������FN202.BY_SUBJECT_CODE or FN202.SUBJECT_CODE���Ƿ����
   */
  public String is_SettleIncAndDec_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String flag = "";
    try {
      flag = settleIncAndDecDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return flag;
  }

  /**
   * �жϸ�����¼�Ŀ�Ŀ������FN201.SUBJECT_CODE ���Ƿ����
   */
  public List is_AccountantCredence_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = accountantCredenceDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return list;
  }

  /**
   * �жϸ�����¼�Ŀ�Ŀ������FN210.SUBJECT_CODE���Ƿ����
   */
  public List is_TreasurerCredence_In(String code, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();
    try {
      list = treasurerCredenceDAO.is_CodeIn_WL(code, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return list;
  }

  /**
   * ���Ͽ�Ŀ
   */
  public void canceledSubject(String codeid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // ȡ�ö�Ӧ�Ŀ�Ŀ����
      String subjectCode = subjectDAO.is_CodeInBySubjectId_WL(codeid, null,
          securityInfo);
      // �жϸ�����¼�Ƿ�FN110.SUBJECT_ST=0
      String codestate = this.is_ParentCode_Normal(subjectCode, "0",
          securityInfo);
      if (codestate == null || codestate.equals("")) {
        throw new BusinessException("�ÿ�Ŀ����������!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN110.PARENT_SUBJECT_CODE���Ƿ����
      List parentList = this.is_ParentCodeIn_WL(subjectCode, securityInfo);
      if (parentList.size() != 0) {
        // �жϸ�����¼�Ŀ�Ŀ�������һ����Ŀ�����FN110.SUBJECT_ST=0
        List subjectIdByParentList = subjectDAO.is_ParentCodeIn_WL(subjectCode,
            "0", securityInfo);
        if (subjectIdByParentList.size() != 0) {
          throw new BusinessException("�ÿ�Ŀ����һ����Ŀ���ܱ�����!!");
        }
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN111.SUBJECT_CODE���Ƿ����
      List codeRelationlist = this.is_ParentCodeRelation_In(subjectCode,
          securityInfo);
      if (codeRelationlist.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ�ѽ�����ϵ���ܱ�����!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN120.SUBJECT_CODE���Ƿ����
      List credModList = this.is_CredenceModle_In(subjectCode, securityInfo);
      if (credModList.size() != 0) {
        throw new BusinessException("�ÿ�Ŀ������ƾ֤ģʽ���ܱ�����!!");
      }
      // �жϸ�����¼�Ŀ�Ŀ������FN202.BY_SUBJECT_CODE or FN202.SUBJECT_CODE���Ƿ����
      String flag = this.is_SettleIncAndDec_In(subjectCode, securityInfo);
      if (flag.equals("have")) {
        throw new BusinessException("�ÿ�Ŀ�����������ת���ܱ�����!!");
      }
      String balance = subjectDAO
          .checkIsExitBalance(securityInfo.getBookId(), subjectCode,
              securityInfo.getUserInfo().getFbizDate().substring(0, 6));
      if (!balance.equals("0")) {
        throw new BusinessException("�ÿ�Ŀ�Դ������,��������!!");
      }
      // ����FN110.SUBJECT_ST=1
      Subject subject = subjectDAO.queryById(new Integer(codeid));
      subject.setSubjectSt("" + BusiConst.PLCOMMONSTATUS_1_CANCELED);
      subject.setExpireDate(securityInfo.getUserInfo().getFbizDate().substring(0,4));
      //subjectDAO.update(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }

  }

  /**
   * �������Ͽ�Ŀ
   */
  public void canceledquashSubject(String codeid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // ȡ�ö�Ӧ�Ŀ�Ŀ����
      String subjectCode = subjectDAO.is_CodeInBySubjectId_WL(codeid, null,
          securityInfo);
      // �жϸ�����¼�Ƿ�FN110.SUBJECT_ST=1
      String codestate = this.is_ParentCode_Normal(subjectCode, "1",
          securityInfo);
      if (codestate == null || codestate.equals("")) {
        throw new BusinessException("�ÿ�Ŀ����������!!");
      }
      // �жϸ�����¼��FN110.PARENT_SUBJECT_CODE�Ƿ�Ϊ��
      String subjectParentCode = this.is_ParentCodeInByCode_WL(subjectCode,
          null, securityInfo);
      if (subjectParentCode != null && !subjectParentCode.equals("")) {
        // �жϸ�����¼�ĸ���Ŀ���� ��״̬�Ƿ�����
        String parentCode = this.is_ParentCode_Normal(subjectParentCode, "0",
            securityInfo);
        if (parentCode == null || parentCode.equals("")) {
          throw new BusinessException("���ȳ���������һ����Ŀ����!!");
        }
      }

      // ����FN110.SUBJECT_ST=0
      Subject subject = subjectDAO.queryById(new Integer(codeid));
      subject.setSubjectSt("" + BusiConst.PLCOMMONSTATUS_1_NORMAL);
      subjectDAO.update(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }

  }

  /**
   * �жϸ�����¼��FN110.PARENT_SUBJECT_CODE�Ƿ�Ϊ��
   */
  public String is_ParentCodeInByCode_WL(String code, String states,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String parentCode = "";
    try {
      parentCode = subjectDAO
          .is_ParentCodeInByCode_WL(code, null, securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return parentCode;
  }

  /**
   * ���ݿ�Ŀ��ˮ�Ų�ѯ��Ŀ��Ϣ
   */
  public SubjectShowAF findSubject(String subjectId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    SubjectShowAF af = new SubjectShowAF();
    try {
      Subject subject = subjectDAO.queryById(new Integer(subjectId));
      af.setId(subject.getSubjectId().toString());
      af.setCode(subject.getSubjectCode());
      af.setName(subject.getSubjectName());
      af.setDirection(subject.getBalanceDirection());
      af.setSortcode(subject.getSubjectSortCode());
      af.setProperty(subject.getSubjectProperty());
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }
    return af;
  }

  /**
   * �޸Ŀ�Ŀ
   */
  public void updateSubject(SubjectShowAF af, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // ����FN110
      Subject subject = subjectDAO.queryById(new Integer(af.getId()));
      subject.setSubjectName(af.getName());
      subjectDAO.update(subject);

      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel("" + BusiLogConst.FN_OP_BOOKMNG_SUBJECT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);
    } catch (Exception be) {
      be.printStackTrace();
      throw new BusinessException("��ѯ����");
    }

  }

  /**
   * ���ʱ������¼��Ŀ�Ŀ��������֤�������һ����Ŀ��Ϣ
   */
  public SubjectShowAF getSubjectMessage(SubjectShowAF af,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      String code = af.getCode();
      // �жϿ�Ŀ�����Ƿ���ϸ����״���ṹ
      this.is_Book_CodeStructure(code, securityInfo);
      // �ж�����Ŀ�Ŀ�����Ƿ����
      String subjectId = this.is_Code_In(code, securityInfo);
      if (subjectId != null && !subjectId.equals("")) {
        throw new BusinessException("�ÿ�Ŀ�����Ѿ�����!!");
      }
      // �ж�����Ŀ�Ŀ�������Ƿ�Ϊһ����Ŀ����
      boolean flag = this.is_CodeLevel_One(code, securityInfo);
      String codeLevelUp = "";
      if (flag == false) {// ��һ����Ŀ
        // ȡ�ö�Ӧ����һ����Ŀ�����λ��
        int temp_length = this.getCodeLevel_up(code, securityInfo);
        codeLevelUp = code.substring(0, temp_length);
        // �ж�����Ŀ�Ŀ�������һ����Ŀ������FN110�Ƿ��Ѿ����ڣ�and SUBJECT_ST=0
        String parentCode = this.is_ParentCode_Normal(codeLevelUp, "0",
            securityInfo);
        if (parentCode == null || parentCode.equals("")) {
          throw new BusinessException("�ϼ���Ŀ�����ڻ������ϣ������ٽ����ӿ�Ŀ!!");
        }
        // �ж�����Ŀ�Ŀ�������һ����Ŀ��FN111.SUBJECT_CODE���Ƿ����
        List list = this.is_ParentCodeRelation_In(parentCode, securityInfo);
        if (list.size() != 0) {
          throw new BusinessException("�ϼ���Ŀ�ѽ��������ϵ�������ٽ����ӿ�Ŀ!!");
        }
        // �õ�����Ŀ�Ŀ�Ŀ����
        String parentsortname = subjectDAO.querySubjectNameBySubjectCode(
            parentCode, securityInfo.getBookId());
        // �õ�����Ŀ�Ŀ�Ŀ���
        String parentsortcode = this
            .getSortcodeByCode(parentCode, securityInfo);
        // �õ�����Ŀ�Ŀ�Ŀ����
        String parentdirection = this.getDirectionByCode(parentCode,
            securityInfo);
        // �õ�����Ŀ�Ŀ�Ŀ����
        String parentpropery = this.getProperyByCode_WL(parentCode,
            securityInfo);
        af.setCode(code);
        af.setName(parentsortname);
        af.setSortcode(parentsortcode);
        af.setDirection(parentdirection);
        af.setProperty(parentpropery);
        af.setActionflag("0");
      }

    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
    return af;
  }

}
