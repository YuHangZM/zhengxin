package org.xpup.hafmis.syscommon.domain.entity;

public class Transactor {

  /**
   * ����
   */
  private String name = null;

  /**
   * �绰
   */
  private String telephone = null;

  /**
   * �ƶ��绰
   */
  private String mobileTelephone = null;

  /**
   * ��������
   */
  private String email = null;

  /**
   * @roseuid 45EFFE2C01C7
   */
  public Transactor() {

  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobileTelephone() {
    return mobileTelephone;
  }

  public void setMobileTelephone(String mobileTelephone) {
    this.mobileTelephone = mobileTelephone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
}
