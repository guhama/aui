package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class NpiFacilityTransmission.
 *
 * @author crowland
 */
@Entity
@Table(schema = "AREA1", name = "NPI_FACILITY_TRANSMISSION")
public class NpiFacilityTransmission implements Serializable {

  private static final long serialVersionUID = 7061857175721224618L;

  @EmbeddedId
  private NpiFacilityTransmissionId id;

  @Column(name = "REPORT_PERIOD_EXP_DATE", nullable = false)
  private Date expDate;

  @Column(name = "TRANSMIT_IND", precision = 1, nullable = false)
  private Integer transmitInd;

  /**
   * Instantiates a new npi facility transmission.
   */
  public NpiFacilityTransmission() {}

  /**
   * Instantiates a new npi facility transmission.
   *
   * @param id the id
   * @param expDate the exp date
   * @param transmitInd the transmit ind
   */
  public NpiFacilityTransmission(NpiFacilityTransmissionId id, Date expDate, Integer transmitInd) {
    this.id = id;
    this.expDate = new Date(expDate.getTime());
    this.transmitInd = transmitInd;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public NpiFacilityTransmissionId getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(NpiFacilityTransmissionId id) {
    this.id = id;
  }

  /**
   * Gets the exp date.
   *
   * @return the exp date
   */
  public Date getExpDate() {
    return new Date(expDate.getTime());
  }

  /**
   * Sets the exp date.
   *
   * @param expDate the new exp date
   */
  public void setExpDate(Date expDate) {
    this.expDate = new Date(expDate.getTime());
  }

  /**
   * Gets the transmit ind.
   *
   * @return the transmit ind
   */
  public Integer getTransmitInd() {
    return transmitInd;
  }

  /**
   * Sets the transmit ind.
   *
   * @param transmitInd the new transmit ind
   */
  public void setTransmitInd(Integer transmitInd) {
    this.transmitInd = transmitInd;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "NpiMeasureTransmission [id=" + id + ", expDate=" + expDate + ", transmitInd="
        + transmitInd + "]";
  }
}
