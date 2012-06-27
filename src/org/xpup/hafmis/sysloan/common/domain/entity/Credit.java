package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ���
 */
public class Credit implements Serializable {

  private static final long serialVersionUID = 1L;

  /** ����ID */
  private Integer id;

  /** ҵ��ţ���ͬ�ţ� */
  private String yewuhao;

  /** ���´� */
  private String office;

  /** �ſ����� */
  private BigDecimal loan_bank_id;

  /** �������� */
  private String kaihuriqi;

  /** �������� */
  private String daoqiriqi;

  /** ������ */
  private BigDecimal daikuanjine;

  /** �������� */
  private String daikuanqixian;

  /** �������� */
  private String huankuanyueshu;

  /** ʣ������ */
  private String shengyuqixian;

  /** Ӧ�������� */
  private String yinghuankuanriqi;

  /** ʵ�ʻ������� */
  private String shijihuankuanriqi;

  /** Ӧ������ */
  private BigDecimal yinghuankuanjine;

  /** ʵ�ʻ����� */
  private BigDecimal shijihuankuanjine;

  /** ������� */
  private BigDecimal daikuanyue;

  /** ��ǰ�������� */
  private BigDecimal dangqianyuqiqishu;

  /** ��ǰ�����ܶ� */
  private BigDecimal dangqianyuqizonge;

  /** ����31-60��δ�黹����� */
  private BigDecimal yuqiyigeyue;

  /** ����61-90��δ�黹����� */
  private BigDecimal yuqilianggeyue;

  /** ����91-180��δ�黹����� */
  private BigDecimal yuqisangeyue;

  /** ����180������δ�黹����� */
  private BigDecimal yuqiliugeyue;

  /** ΥԼ���� */
  private BigDecimal weiyuecishu;

  /** ����������� */
  private BigDecimal zuigaoyuqiqishu;

  /** �˻�״̬ */
  private BigDecimal zhanghuzhuangtai;

  /** 24���£��˻�������״̬ */
  private String ershisigeyue;

  /** �˻�ӵ������Ϣ��ʾ */
  private BigDecimal zhanghuxinxitishi;

  /** ���� */
  private String xingming;

  /** ֤������ */
  private String zhengjianleixing;

  /** ֤������ */
  private String zhengjianhaoma;

  /** ������ȡ���� */
  private String shujutiquriqi;

  /** ������������ */
  private String baowenshengchengriqi;

  /** ��¼״̬ */
  private BigDecimal jiluzhuangtai;

  /** �Ƿ񵼳�����0�ǣ�1����ҪΪ���ж��Ƿ������������ */
  private BigDecimal isexport;

  public String getBaowenshengchengriqi() {
    return baowenshengchengriqi;
  }

  public void setBaowenshengchengriqi(String baowenshengchengriqi) {
    this.baowenshengchengriqi = baowenshengchengriqi;
  }

  public BigDecimal getDaikuanjine() {
    return daikuanjine;
  }

  public void setDaikuanjine(BigDecimal daikuanjine) {
    this.daikuanjine = daikuanjine;
  }

  public BigDecimal getDaikuanyue() {
    return daikuanyue;
  }

  public void setDaikuanyue(BigDecimal daikuanyue) {
    this.daikuanyue = daikuanyue;
  }

  public BigDecimal getDangqianyuqiqishu() {
    return dangqianyuqiqishu;
  }

  public void setDangqianyuqiqishu(BigDecimal dangqianyuqiqishu) {
    this.dangqianyuqiqishu = dangqianyuqiqishu;
  }

  public BigDecimal getDangqianyuqizonge() {
    return dangqianyuqizonge;
  }

  public void setDangqianyuqizonge(BigDecimal dangqianyuqizonge) {
    this.dangqianyuqizonge = dangqianyuqizonge;
  }

  public String getDaoqiriqi() {
    return daoqiriqi;
  }

  public void setDaoqiriqi(String daoqiriqi) {
    this.daoqiriqi = daoqiriqi;
  }

  public String getErshisigeyue() {
    return ershisigeyue;
  }

  public void setErshisigeyue(String ershisigeyue) {
    this.ershisigeyue = ershisigeyue;
  }

  public String getHuankuanyueshu() {
    return huankuanyueshu;
  }

  public void setHuankuanyueshu(String huankuanyueshu) {
    this.huankuanyueshu = huankuanyueshu;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getJiluzhuangtai() {
    return jiluzhuangtai;
  }

  public void setJiluzhuangtai(BigDecimal jiluzhuangtai) {
    this.jiluzhuangtai = jiluzhuangtai;
  }

  public String getKaihuriqi() {
    return kaihuriqi;
  }

  public void setKaihuriqi(String kaihuriqi) {
    this.kaihuriqi = kaihuriqi;
  }

  public String getShengyuqixian() {
    return shengyuqixian;
  }

  public void setShengyuqixian(String shengyuqixian) {
    this.shengyuqixian = shengyuqixian;
  }

  public BigDecimal getShijihuankuanjine() {
    return shijihuankuanjine;
  }

  public void setShijihuankuanjine(BigDecimal shijihuankuanjine) {
    this.shijihuankuanjine = shijihuankuanjine;
  }

  public String getShijihuankuanriqi() {
    return shijihuankuanriqi;
  }

  public void setShijihuankuanriqi(String shijihuankuanriqi) {
    this.shijihuankuanriqi = shijihuankuanriqi;
  }

  public String getShujutiquriqi() {
    return shujutiquriqi;
  }

  public void setShujutiquriqi(String shujutiquriqi) {
    this.shujutiquriqi = shujutiquriqi;
  }

  public BigDecimal getWeiyuecishu() {
    return weiyuecishu;
  }

  public void setWeiyuecishu(BigDecimal weiyuecishu) {
    this.weiyuecishu = weiyuecishu;
  }

  public String getXingming() {
    return xingming;
  }

  public void setXingming(String xingming) {
    this.xingming = xingming;
  }

  public String getYewuhao() {
    return yewuhao;
  }

  public void setYewuhao(String yewuhao) {
    this.yewuhao = yewuhao;
  }

  public BigDecimal getYinghuankuanjine() {
    return yinghuankuanjine;
  }

  public void setYinghuankuanjine(BigDecimal yinghuankuanjine) {
    this.yinghuankuanjine = yinghuankuanjine;
  }

  public String getYinghuankuanriqi() {
    return yinghuankuanriqi;
  }

  public void setYinghuankuanriqi(String yinghuankuanriqi) {
    this.yinghuankuanriqi = yinghuankuanriqi;
  }

  public BigDecimal getYuqilianggeyue() {
    return yuqilianggeyue;
  }

  public void setYuqilianggeyue(BigDecimal yuqilianggeyue) {
    this.yuqilianggeyue = yuqilianggeyue;
  }

  public BigDecimal getYuqiliugeyue() {
    return yuqiliugeyue;
  }

  public void setYuqiliugeyue(BigDecimal yuqiliugeyue) {
    this.yuqiliugeyue = yuqiliugeyue;
  }

  public BigDecimal getYuqisangeyue() {
    return yuqisangeyue;
  }

  public void setYuqisangeyue(BigDecimal yuqisangeyue) {
    this.yuqisangeyue = yuqisangeyue;
  }

  public BigDecimal getYuqiyigeyue() {
    return yuqiyigeyue;
  }

  public void setYuqiyigeyue(BigDecimal yuqiyigeyue) {
    this.yuqiyigeyue = yuqiyigeyue;
  }

  public BigDecimal getZhanghuxinxitishi() {
    return zhanghuxinxitishi;
  }

  public void setZhanghuxinxitishi(BigDecimal zhanghuxinxitishi) {
    this.zhanghuxinxitishi = zhanghuxinxitishi;
  }

  public BigDecimal getZhanghuzhuangtai() {
    return zhanghuzhuangtai;
  }

  public void setZhanghuzhuangtai(BigDecimal zhanghuzhuangtai) {
    this.zhanghuzhuangtai = zhanghuzhuangtai;
  }

  public String getZhengjianhaoma() {
    return zhengjianhaoma;
  }

  public void setZhengjianhaoma(String zhengjianhaoma) {
    this.zhengjianhaoma = zhengjianhaoma;
  }

  public String getZhengjianleixing() {
    return zhengjianleixing;
  }

  public void setZhengjianleixing(String zhengjianleixing) {
    this.zhengjianleixing = zhengjianleixing;
  }

  public BigDecimal getZuigaoyuqiqishu() {
    return zuigaoyuqiqishu;
  }

  public void setZuigaoyuqiqishu(BigDecimal zuigaoyuqiqishu) {
    this.zuigaoyuqiqishu = zuigaoyuqiqishu;
  }

  public BigDecimal getIsexport() {
    return isexport;
  }

  public void setIsexport(BigDecimal isexport) {
    this.isexport = isexport;
  }

  public String getDaikuanqixian() {
    return daikuanqixian;
  }

  public void setDaikuanqixian(String daikuanqixian) {
    this.daikuanqixian = daikuanqixian;
  }

  public BigDecimal getLoan_bank_id() {
    return loan_bank_id;
  }

  public void setLoan_bank_id(BigDecimal loan_bank_id) {
    this.loan_bank_id = loan_bank_id;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

}
