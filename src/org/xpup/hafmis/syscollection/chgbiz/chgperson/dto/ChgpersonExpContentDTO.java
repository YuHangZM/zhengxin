package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class ChgpersonExpContentDTO implements ExpDto{

  
  /**
   ְ����š�ְ��������֤�����͡�֤�����롢�������ڡ��Ա����ڲ��š����ʻ�������λ�ɶְ���ɶ�Ƿ������,�������
   */
  
   private String empcode="";
   private String  empname="";
   private String  cardkind="";
   private String  cardnum="";
   private String birthday="";
   private String sex="";
   private String dept="";
   private String salarybase="";
   private String orgpay="";
   private String emppay="";
   private String chgtype="";
   private String partin="";
   private String chgreason="";

   private String payStatus = "";

  /**
   * @return ���� empcode��
   */
  public String getEmpcode() {
    return empcode;
  }
  /**
   * @param empcode Ҫ���õ� empcode��
   */
  public void setEmpcode(String empcode) {
    this.empcode = empcode;
  }
  /**
   * @return ���� empname��
   */
  public String getEmpname() {
    return empname;
  }
  /**
   * @param empname Ҫ���õ� empname��
   */
  public void setEmpname(String empname) {
    this.empname = empname;
  }

   /**
    * @return ���� birthday��
    */
   public String getBirthday() {
       return birthday;
   }
   /**
    * @param birthday Ҫ���õ� birthday��
    */
   public void setBirthday(String birthday) {
       this.birthday = birthday;
   }
   
    public String getInfo(String info) {
      // TODO �Զ����ɷ������
      
      if(info.equals("empcode")){
        return this.empcode;
        
      }
      if(info.equals("empname")){
        return this.empname;
        
      }
      
      if(info.equals("salarybase")){
        return this.salarybase;
        
      }
      if(info.equals("birthday")){
        return this.birthday;
        
      }
      if(info.equals("cardkind")){
        return this.cardkind;
        
      }
      if(info.equals("cardnum")){
        return this.cardnum;
        
      }
      
      if(info.equals("sex")){
        return this.sex;
        
      }
      if(info.equals("dept")){
        return this.dept;
        
      }
      if(info.equals("orgpay")){
        return this.orgpay;
        
      }
      
      if(info.equals("emppay")){
        return this.emppay;
        
      }
      if(info.equals("chgtype")){
        return this.chgtype;
        
      }
      if(info.equals("partin")){
        return this.partin;
        
      }
      if(info.equals("chgreason")){
        return this.chgreason;
        
      }
      if(info.equals("payStatus")){
        return this.payStatus;
        
      }
  
      return null;
    }
    public String getCardkind() {
      return cardkind;
    }
    public void setCardkind(String cardkind) {
      this.cardkind = cardkind;
    }
    public String getCardnum() {
      return cardnum;
    }
    public void setCardnum(String cardnum) {
      this.cardnum = cardnum;
    }
    public String getDept() {
      return dept;
    }
    public void setDept(String dept) {
      this.dept = dept;
    }
    public String getEmppay() {
      return emppay;
    }
    public void setEmppay(String emppay) {
      this.emppay = emppay;
    }
    public String getOrgpay() {
      return orgpay;
    }
    public void setOrgpay(String orgpay) {
      this.orgpay = orgpay;
    }
    public String getPartin() {
      return partin;
    }
    public void setPartin(String partin) {
      this.partin = partin;
    }
    public String getSalarybase() {
      return salarybase;
    }
    public void setSalarybase(String salarybase) {
      this.salarybase = salarybase;
    }
    public String getSex() {
      return sex;
    }
    public void setSex(String sex) {
      this.sex = sex;
    }
    public String getChgtype() {
      return chgtype;
    }
    public void setChgtype(String chgtype) {
      this.chgtype = chgtype;
    }
    public String getChgreason() {
      return chgreason;
    }
    public void setChgreason(String chgreason) {
      this.chgreason = chgreason;
    }
    public String getPayStatus() {
      return payStatus;
    }
    public void setPayStatus(String payStatus) {
      this.payStatus = payStatus;
    }

}
