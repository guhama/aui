package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class NpiFacilityTransmissionId.
 *
 * @author crowland
 */
@Embeddable
public class NpiFacilityTransmissionId implements Serializable {

  private static final long serialVersionUID = -1923627613492553707L;

  @Column(name = "CLIENT_ID", length = 15, nullable = false)
  private String clientId;

  @Column(name = "FACILITY_ID", length = 15, nullable = false)
  private String facilityId;

  @Column(name = "INITIATIVE_ID", nullable = false)
  private Long initiativeId;

  @Column(name = "MEASURE_SET_ID", nullable = false)
  private Long measureSetId;

  @Column(name = "MS_MEASURE_ID", nullable = false)
  private Long msMeasureId;

  @Column(name = "ENTITY_TYPE", length = 10, nullable = false)
  private String entityType;

  @Column(name = "ENTITY_CODE", length = 15, nullable = false)
  private String entityCode;

  @Column(name = "NPI_NUM", length = 12, nullable = false)
  private String npiNum;

  @Column(name = "REPORT_PERIOD_EFF_DATE", nullable = false)
  private Date effDate;

  /**
   * Instantiates a new npi facility transmission id.
   */
  public NpiFacilityTransmissionId() {}

  /**
   * Instantiates a new npi facility transmission id.
   *
   * @param clientId the client id
   * @param facilityId the facility id
   * @param initiativeId the initiative id
   * @param measureSetId the measure set id
   * @param msMeasureId the ms measure id
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   */
  public NpiFacilityTransmissionId(String clientId, String facilityId, Long initiativeId,
      Long measureSetId, Long msMeasureId, String entityType, String entityCode, String npiNum,
      Date effDate) {
    this.clientId = clientId;
    this.facilityId = facilityId;
    this.initiativeId = initiativeId;
    this.measureSetId = measureSetId;
    this.msMeasureId = msMeasureId;
    this.entityType = entityType;
    this.entityCode = entityCode;
    this.npiNum = npiNum;
    this.effDate = new Date(effDate.getTime());
  }

  /**
   * Gets the initiative id.
   *
   * @return the initiative id
   */
  public Long getInitiativeId() {
    return initiativeId;
  }

  /**
   * Sets the initiative id.
   *
   * @param initiativeId the new initiative id
   */
  public void setInitiativeId(Long initiativeId) {
    this.initiativeId = initiativeId;
  }

  /**
   * Gets the measure set id.
   *
   * @return the measure set id
   */
  public Long getMeasureSetId() {
    return measureSetId;
  }

  /**
   * Sets the measure set id.
   *
   * @param measureSetId the new measure set id
   */
  public void setMeasureSetId(Long measureSetId) {
    this.measureSetId = measureSetId;
  }

  /**
   * Gets the ms measure id.
   *
   * @return the ms measure id
   */
  public Long getMsMeasureId() {
    return msMeasureId;
  }

  /**
   * Sets the ms measure id.
   *
   * @param msMeasureId the new ms measure id
   */
  public void setMsMeasureId(Long msMeasureId) {
    this.msMeasureId = msMeasureId;
  }

  /**
   * Gets the client id.
   *
   * @return the client id
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * Sets the client id.
   *
   * @param clientId the new client id
   */
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  /**
   * Gets the facility id.
   *
   * @return the facility id
   */
  public String getFacilityId() {
    return facilityId;
  }

  /**
   * Sets the facility id.
   *
   * @param facilityId the new facility id
   */
  public void setFacilityId(String facilityId) {
    this.facilityId = facilityId;
  }


  /**
   * @return the entityType
   **/
  public String getEntityType() {
    return entityType;
  }

  /**
   * @param entityType the entityType to set
   */
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  /**
   * Gets the Entity Code.
   *
   * @return the EntityCode
   */
  public String getEntityCode() {
    return entityCode;
  }

  /**
   * Sets the Entity Code.
   *
   * @param entityCode the new Entity Code
   */
  public void setEntityCode(String entityCode) {
    this.entityCode = entityCode;
  }

  /**
   * Gets the npi num.
   *
   * @return the npi num
   */
  public String getNpiNum() {
    return npiNum;
  }

  /**
   * Sets the npi num.
   *
   * @param npiNum the new npi num
   */
  public void setNpiNum(String npiNum) {
    this.npiNum = npiNum;
  }

  /**
   * Gets the eff date.
   *
   * @return the eff date
   */
  public Date getEffDate() {
    return new Date(effDate.getTime());
  }

  /**
   * Sets the eff date.
   *
   * @param effDate the new eff date
   */
  public void setEffDate(Date effDate) {
    this.effDate = new Date(effDate.getTime());
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "NpiMeasureTransmissionId [initiativeId=" + initiativeId + ", measureSetId="
        + measureSetId + ", msMeasureId=" + msMeasureId + ", clientId=" + clientId
        + ", facilityId=" + facilityId + ", entityType=" + entityType 
        + ", entityCode=" + entityCode + ", npiNum=" + npiNum 
        + ", effDate=" + effDate + "]";
  }
 
  
}
