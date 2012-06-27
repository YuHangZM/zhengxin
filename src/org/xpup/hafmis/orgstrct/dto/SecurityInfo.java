package org.xpup.hafmis.orgstrct.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.hafmis.orgstrct.domain.HafEmployee;
/**
 * Ȩ����Ϣ
 * @author ����
 *
 */
public class SecurityInfo implements Serializable{

  private static final long serialVersionUID = 8241107375146934274L;

  //��¼����
  private String userName="";
  
  //��¼�˵���ʵ����
  private String realName="";
  
  //��¼IP
  private String userIp="";
  
  //�û�������´�Ȩ��HqlSQL
  private String officeSecurityHqlSQL="";
  
//�û�������´�Ȩ��SQL
  private String officeSecuritySQL="";
  
  //������Ȩ��SQL
  private String gjjSecuritySQL="";
  
  //������Ȩ��HqlSQL
  private String gjjSecurityHqlSQL="";
  
  //����Ȩ��SQL
  private String dkSecuritySQL="";
  
  //����Ȩ��HqlSQL
  private String dkSecurityHqlSQL="";
  
  //ҵ������
  private String bizDate="";
  
  //��ɫID
  private String roleId="";
  
  //��¼���������´�
  private OfficeDto officeDto=new OfficeDto();
  
  //�û�Ȩ��Ȩ�ް��´�
  private List officeList=new ArrayList();
  
  //���а��´�
  private List allOfficeList=new ArrayList();
  
  //��������
  private List allCenterList=new ArrayList();
  
  //�û�Ȩ�����û�
  private List userList=new ArrayList();
  
  //�����û�
  private List allUserList=new ArrayList();
  
  //�û��¹鼯��
  private List collBankList = new ArrayList();

  private HafEmployee UserInfo=new HafEmployee();
  //���´��ڲ�����MAP
  private Map officeInnerCodeMap=new HashMap();
  
  //���������
  private int plLoanReturnType=999;
  
  //�汾���� �Ƿ�Ϊ��λ��
  private int isOrgEdition=999;
  
  //ȡ�ô���Ȩ��HqlSql���Ƶ�����Ա
  private String dkUserSecurityHqlSQL="";
  
  //ȡ�ô���Ȩ��Sql���Ƶ�����Ա
  private String dkUserSecuritySQL="";
  
  //����Ȩ������List
  private List dkUserBankList=new ArrayList();
  
  //�û�Ȩ������
  private List userBookList=new ArrayList();
  
  private int fnSettleType=999;
  
  private String bookId="";
  
  //��������ƾ֤ģʽ 
  private int AgentPayModel=999;
  
  //�����𻹴�ƾ֤ģʽ
  private int LoanBackByCollModel=999;
  
  //�õ��Ƿ���ڵ�λ��
  private int isHaveOrgVersion=999;
  
  //�õ��Ƿ������ʼ�����
  private int mailFunction=999;
  
  //ϵͳ����
  private String opSystemType="";
  
  //ȡ��CA200�е��ʼ���Ϣ
  private MailMessageDTO maildto=new MailMessageDTO();
  
  public MailMessageDTO getMaildto() {
    return maildto;
  }

  public void setMaildto(MailMessageDTO maildto) {
    this.maildto = maildto;
  }

  public int getLoanBackByCollModel() {
    return LoanBackByCollModel;
  }

  public void setLoanBackByCollModel(int loanBackByCollModel) {
    LoanBackByCollModel = loanBackByCollModel;
  }

  public int getAgentPayModel() {
    return AgentPayModel;
  }

  public void setAgentPayModel(int agentPayModel) {
    AgentPayModel = agentPayModel;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public int getFnSettleType() {
    return fnSettleType;
  }

  public void setFnSettleType(int fnSettleType) {
    this.fnSettleType = fnSettleType;
  }

  public List getUserBookList() {
    return userBookList;
  }

  public void setUserBookList(List userBookList) {
    this.userBookList = userBookList;
  }

  public List getDkUserBankList() {
    return dkUserBankList;
  }

  public void setDkUserBankList(List dkUserBankList) {
    this.dkUserBankList = dkUserBankList;
  }

  public int getPlLoanReturnType() {
    return plLoanReturnType;
  }

  public void setPlLoanReturnType(int plLoanReturnType) {
    this.plLoanReturnType = plLoanReturnType;
  }

  public List getAllOfficeList() {
    return allOfficeList;
  }

  public void setAllOfficeList(List allOfficeList) {
    this.allOfficeList = allOfficeList;
  }

  public List getAllUserList() {
    return allUserList;
  }

  public void setAllUserList(List allUserList) {
    this.allUserList = allUserList;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getGjjSecuritySQL() {
    return gjjSecuritySQL;
  }

  public void setGjjSecuritySQL(String gjjSecuritySQL) {
    this.gjjSecuritySQL = gjjSecuritySQL;
  }

  public OfficeDto getOfficeDto() {
    return officeDto;
  }

  public void setOfficeDto(OfficeDto officeDto) {
    this.officeDto = officeDto;
  }

  public List getOfficeList() {
    return officeList;
  }

  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getUserIp() {
    return userIp;
  }

  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }

  public List getUserList() {
    return userList;
  }

  public void setUserList(List userList) {
    this.userList = userList;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public HafEmployee getUserInfo() {
    return UserInfo;
  }

  public void setUserInfo(HafEmployee userInfo) {
    UserInfo = userInfo;
  }

  
  public String getOfficeSecurityHqlSQL() {
    return officeSecurityHqlSQL;
  }

  public void setOfficeSecurityHqlSQL(String officeSecurityHqlSQL) {
    this.officeSecurityHqlSQL = officeSecurityHqlSQL;
  }

  public String getOfficeSecuritySQL() {
    return officeSecuritySQL;
  }

  public void setOfficeSecuritySQL(String officeSecuritySQL) {
    this.officeSecuritySQL = officeSecuritySQL;
  }

  public String getGjjSecurityHqlSQL() {
    return gjjSecurityHqlSQL;
  }

  public void setGjjSecurityHqlSQL(String gjjSecurityHqlSQL) {
    this.gjjSecurityHqlSQL = gjjSecurityHqlSQL;
  }

  public List getCollBankList() {
    return collBankList;
  }

  public void setCollBankList(List collBankList) {
    this.collBankList = collBankList;
  }

  public List getAllCenterList() {
    return allCenterList;
  }

  public void setAllCenterList(List allCenterList) {
    this.allCenterList = allCenterList;
  }

  public String getDkSecurityHqlSQL() {
    return dkSecurityHqlSQL;
  }

  public void setDkSecurityHqlSQL(String dkSecurityHqlSQL) {
    this.dkSecurityHqlSQL = dkSecurityHqlSQL;
  }

  public String getDkSecuritySQL() {
    return dkSecuritySQL;
  }

  public void setDkSecuritySQL(String dkSecuritySQL) {
    this.dkSecuritySQL = dkSecuritySQL;
  }

  public Map getOfficeInnerCodeMap() {
    return officeInnerCodeMap;
  }

  public void setOfficeInnerCodeMap(Map officeInnerCodeMap) {
    this.officeInnerCodeMap = officeInnerCodeMap;
  }

  public String getDkUserSecurityHqlSQL() {
    return dkUserSecurityHqlSQL;
  }

  public void setDkUserSecurityHqlSQL(String dkUserSecurityHqlSQL) {
    this.dkUserSecurityHqlSQL = dkUserSecurityHqlSQL;
  }

  public String getDkUserSecuritySQL() {
    return dkUserSecuritySQL;
  }

  public void setDkUserSecuritySQL(String dkUserSecuritySQL) {
    this.dkUserSecuritySQL = dkUserSecuritySQL;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getOpSystemType() {
    return opSystemType;
  }

  public void setOpSystemType(String opSystemType) {
    this.opSystemType = opSystemType;
  }

  public int getIsOrgEdition() {
    return isOrgEdition;
  }

  public void setIsOrgEdition(int isOrgEdition) {
    this.isOrgEdition = isOrgEdition;
  }

  public int getIsHaveOrgVersion() {
    return isHaveOrgVersion;
  }

  public void setIsHaveOrgVersion(int isHaveOrgVersion) {
    this.isHaveOrgVersion = isHaveOrgVersion;
  }

  public int getMailFunction() {
    return mailFunction;
  }

  public void setMailFunction(int mailFunction) {
    this.mailFunction = mailFunction;
  }

}
