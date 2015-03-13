package com.premierinc.informatics.qmr.adminui.util;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;

/**
 * The Class Rows.
 *
 * @param <T> the generic type
 */
@XmlRootElement
@XmlSeeAlso({ NpiFacilityTransmission.class })
public class Rows<T> {

  private List<T> records;

  private int page;
  private int total;

  /**
   * Instantiates a new rows.
   */
  public Rows() {}

  /**
   * Instantiates a new rows.
   *
   * @param records the records
   * @param page the page
   * @param total the total
   */
  public Rows(List<T> records, int page, int total) {
    this.records = records;
    this.page = page;
    this.total = total;
  }

  /**
   * Gets the records.
   *
   * @return the records
   */
  public List<T> getRecords() {
    return records;
  }

  /**
   * Sets the records.
   *
   * @param records the new records
   */
  public void setRecords(List<T> records) {
    this.records = records;
  }

  /**
   * Gets the page.
   *
   * @return the page
   */
  public int getPage() {
    return page;
  }

  /**
   * Sets the page.
   *
   * @param page the new page
   */
  public void setPage(int page) {
    this.page = page;
  }

  /**
   * Gets the total.
   *
   * @return the total
   */
  public int getTotal() {
    return total;
  }

  /**
   * Sets the total.
   *
   * @param total the new total
   */
  public void setTotal(int total) {
    this.total = total;
  }
}
