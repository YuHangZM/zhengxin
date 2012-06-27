package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ���
 */
public class CreditIdentity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** ����ID */
  private Integer id;

  /** ��ͬ�� */
  private String contract_id;

  /** �Ա� */
  private BigDecimal sex;

  /** ���� */
  private BigDecimal birthday;

  /** ����״�� */
  private BigDecimal marriage_st;

  /** ���ѧ�� */
  private BigDecimal degree;

  /** סլ�绰 */
  private String house_tel;

  /** �ֻ����� */
  private String home_mobile;

  /** ��λ�绰 */
  private String org_tel;

  /** ͨѶ��ַ����ס��ַ */
  private String home_addr;

  /** ͨѶ��ַ�������룬��ס��ַ�������� */
  private BigDecimal home_mail;

  /** ������ַ */
  private String native_place;

  /** ��ż���� */
  private String p_name;

  /** ��ż֤������ */
  private String p_card_kind;

  /** ��ż֤������ */
  private String p_card_num;

  /** ��ż������λ */
  private String p_org_name;

  /** ��ż��ϵ�绰 */
  private String p_home_mobile;

  /** ��λ���� */
  private String org_name;

  /** ��λ��ַ */
  private String org_addr;

  /** ��λ��ַ�������� */
  private BigDecimal org_mail;

  /** TITLE */
  private BigDecimal title;

  /** ������ */
  private BigDecimal year_salary;

  public CreditIdentity() {
    super();
  }

  public CreditIdentity(Integer id, String contract_id, BigDecimal sex,
      BigDecimal birthday, BigDecimal marriage_st, BigDecimal degree,
      String house_tel, String home_mobile, String org_tel, String home_addr,
      BigDecimal home_mail, String native_place, String p_name,
      String p_card_kind, String p_card_num, String p_org_name,
      String p_home_mobile, String org_name, String org_addr,
      BigDecimal org_mail, BigDecimal title, BigDecimal year_salary) {
    super();
    this.id = id;
    this.contract_id = contract_id;
    this.sex = sex;
    this.birthday = birthday;
    this.marriage_st = marriage_st;
    this.degree = degree;
    this.house_tel = house_tel;
    this.home_mobile = home_mobile;
    this.org_tel = org_tel;
    this.home_addr = home_addr;
    this.home_mail = home_mail;
    this.native_place = native_place;
    this.p_name = p_name;
    this.p_card_kind = p_card_kind;
    this.p_card_num = p_card_num;
    this.p_org_name = p_org_name;
    this.p_home_mobile = p_home_mobile;
    this.org_name = org_name;
    this.org_addr = org_addr;
    this.org_mail = org_mail;
    this.title = title;
    this.year_salary = year_salary;
  }

  public BigDecimal getBirthday() {
    return birthday;
  }

  public void setBirthday(BigDecimal birthday) {
    this.birthday = birthday;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_id) {
    this.contract_id = contract_id;
  }

  public BigDecimal getDegree() {
    return degree;
  }

  public void setDegree(BigDecimal degree) {
    this.degree = degree;
  }

  public String getHome_addr() {
    return home_addr;
  }

  public void setHome_addr(String home_addr) {
    this.home_addr = home_addr;
  }

  public BigDecimal getHome_mail() {
    return home_mail;
  }

  public void setHome_mail(BigDecimal home_mail) {
    this.home_mail = home_mail;
  }

  public String getHome_mobile() {
    return home_mobile;
  }

  public void setHome_mobile(String home_mobile) {
    this.home_mobile = home_mobile;
  }

  public String getHouse_tel() {
    return house_tel;
  }

  public void setHouse_tel(String house_tel) {
    this.house_tel = house_tel;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getMarriage_st() {
    return marriage_st;
  }

  public void setMarriage_st(BigDecimal marriage_st) {
    this.marriage_st = marriage_st;
  }

  public String getNative_place() {
    return native_place;
  }

  public void setNative_place(String native_place) {
    this.native_place = native_place;
  }

  public String getOrg_addr() {
    return org_addr;
  }

  public void setOrg_addr(String org_addr) {
    this.org_addr = org_addr;
  }

  public BigDecimal getOrg_mail() {
    return org_mail;
  }

  public void setOrg_mail(BigDecimal org_mail) {
    this.org_mail = org_mail;
  }

  public String getOrg_name() {
    return org_name;
  }

  public void setOrg_name(String org_name) {
    this.org_name = org_name;
  }

  public String getOrg_tel() {
    return org_tel;
  }

  public void setOrg_tel(String org_tel) {
    this.org_tel = org_tel;
  }

  public String getP_card_kind() {
    return p_card_kind;
  }

  public void setP_card_kind(String p_card_kind) {
    this.p_card_kind = p_card_kind;
  }

  public String getP_card_num() {
    return p_card_num;
  }

  public void setP_card_num(String p_card_num) {
    this.p_card_num = p_card_num;
  }

  public String getP_home_mobile() {
    return p_home_mobile;
  }

  public void setP_home_mobile(String p_home_mobile) {
    this.p_home_mobile = p_home_mobile;
  }

  public String getP_name() {
    return p_name;
  }

  public void setP_name(String p_name) {
    this.p_name = p_name;
  }

  public String getP_org_name() {
    return p_org_name;
  }

  public void setP_org_name(String p_org_name) {
    this.p_org_name = p_org_name;
  }

  public BigDecimal getSex() {
    return sex;
  }

  public void setSex(BigDecimal sex) {
    this.sex = sex;
  }

  public BigDecimal getTitle() {
    return title;
  }

  public void setTitle(BigDecimal title) {
    this.title = title;
  }

  public BigDecimal getYear_salary() {
    return year_salary;
  }

  public void setYear_salary(BigDecimal year_salary) {
    this.year_salary = year_salary;
  }

}
