package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class NpiEntity.
 *
 * @author crowland
 */
@Entity
@Table(schema = "AREA1", name = "NPI_ENTITY")
public class NpiEntity implements Serializable {

  private static final long serialVersionUID = 5801657298742015059L;

  @EmbeddedId
  private NpiEntityId id;

  @Column(name = "EXP_DATE", nullable = true)
  private Date expDate;

  /**
   * Instantiates a new npi entity.
   */
  public NpiEntity() {}

  /**
   * Instantiates a new npi entity.
   *
   * @param id the id
   * @param expDate the exp date
   */
  public NpiEntity(NpiEntityId id, Date expDate) {
    this.id = id;
    this.expDate = new Date(expDate.getTime());
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public NpiEntityId getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(NpiEntityId id) {
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "NpiEntity [id=" + id + ", expDate=" + expDate + "]";
  }
}
