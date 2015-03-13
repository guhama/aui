package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class NpiEntityId.
 *
 * @author crowland
 */

@Embeddable
public class NpiEntityId implements Serializable {

  private static final long serialVersionUID = -1923627613492553707L;

  @Column(name = "ENTITY_TYPE", length = 10, nullable = false)
  private String entityType;

  @Column(name = "ENTITY_CODE", length = 15, nullable = false)
  private String entityCode;

  @Column(name = "NPI_NUM", length = 12, nullable = false)
  private String npiNum;

  @Column(name = "EFF_DATE", nullable = false)
  private Date effDate;


  /**
   * Instantiates a new npi Entity id.
   */
  public NpiEntityId() {}

  /**
   * Instantiates a new npi Entity id.
   *
   * @param entityType the entityType
   * @param entityCode the entityCode
   * @param npiNum the npiNum
   * @param effDate the effDate
   */
  public NpiEntityId(String entityType, String entityCode, String npiNum, Date effDate) {
    this.entityType = entityType;
    this.entityCode = entityCode;
    this.npiNum = npiNum;
    this.effDate = new Date(effDate.getTime());
  }

  /**
   * Gets the entity type.
   * 
   * @return the entity type
   */
  public String getEntityType() {
    return entityType;
  }

  /**
   * Sets the entity type.
   * 
   * @param entityType the entity type
   */
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  /**
   * Gets the entity Code.
   * 
   * @return the entity code
   */
  public String getEntityCode() {
    return entityCode;
  }

  /**
   * Sets the entity code.
   * 
   * @param entityCode the entity code
   */
  public void setEntityCode(String entityCode) {
    this.entityCode = entityCode;
  }

  /**
   * Gets the npi num.
   * 
   * return the npi num
   */
  public String getNpiNum() {
    return npiNum;
  }

  /**
   * Sets the npi num.
   * 
   * @param npiNum the npi num
   */
  public void setNpiNum(String npiNum) {
    this.npiNum = npiNum;
  }

  /**
   * Gets the effective date.
   * 
   * @return the effective date
   */
  public Date getEffDate() {
    return new Date(effDate.getTime());
  }

  /**
   * Sets the effective date.
   * 
   * @param effDate the effective date
   */
  public void setEffDate(Date effDate) {
    this.effDate = new Date(effDate.getTime());
  }

  @Override
  public String toString() {
    return "NpiEntityId [entityType=" + entityType + ", entityCode=" + entityCode + ", npiNum="
        + npiNum + ", effDate=" + effDate + "]";
  }

}
