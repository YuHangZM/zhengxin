package org.xpup.hafmis.syscollection.peoplebank.documentstop.dto;

import java.io.Serializable;

public class DocumentstopDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /** dataReportingAgencies property */
  private String dataReportingAgencies = ""; //�����ϱ�����

  /** contactPhone property */
  private String contactPhone = "";          //��ϵ�绰

  /** locationSpace property */
  private String locationSpace = "";         //�����ص�

  /** contactPreson property */
  private String contactPreson = "";         //��ϵ��

  /** dataFormatVersion property */
  private String dataFormatVersion = "";     //���ݸ�ʽ�汾��

  /** dataMechanism property */
  private String dataMechanism = "";         //���ݷ�������

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public String getContactPreson() {
    return contactPreson;
  }

  public void setContactPreson(String contactPreson) {
    this.contactPreson = contactPreson;
  }

  public String getDataFormatVersion() {
    return dataFormatVersion;
  }

  public void setDataFormatVersion(String dataFormatVersion) {
    this.dataFormatVersion = dataFormatVersion;
  }

  public String getDataMechanism() {
    return dataMechanism;
  }

  public void setDataMechanism(String dataMechanism) {
    this.dataMechanism = dataMechanism;
  }

  public String getDataReportingAgencies() {
    return dataReportingAgencies;
  }

  public void setDataReportingAgencies(String dataReportingAgencies) {
    this.dataReportingAgencies = dataReportingAgencies;
  }

  public String getLocationSpace() {
    return locationSpace;
  }

  public void setLocationSpace(String locationSpace) {
    this.locationSpace = locationSpace;
  }
}
