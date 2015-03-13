package com.premierinc.informatics.qmr.adminui.util;

import java.io.Serializable;
import java.util.List;

/**
 * The Class JqgridResponse.

 * @param <T> the generic type
 */
public class JqgridResponse<T extends Serializable> {

  /**
   * Current page
   */
  private String page;

  /**
   * Total pages
   */
  private String total;

  /**
   * Total number of records
   */
  private String records;

  /**
   * Contains the actual data
   */
  private List<T> rows;

  /**
   * Instantiates a new jqgrid response.
   */
  public JqgridResponse() {}

  /**
   * Instantiates a new jqgrid response.
   *
   * @param page the page
   * @param total the total
   * @param records the records
   * @param rows the rows
   */
  public JqgridResponse(String page, String total, String records, List<T> rows) {
    super();
    this.page = page;
    this.total = total;
    this.records = records;
    this.rows = rows;
  }

  /**
   * Gets the page.
   *
   * @return the page
   */
  public String getPage() {
    return page;
  }

  /**
   * Sets the page.
   *
   * @param page the new page
   */
  public void setPage(String page) {
    this.page = page;
  }

  /**
   * Gets the total.
   *
   * @return the total
   */
  public String getTotal() {
    return total;
  }

  /**
   * Sets the total.
   *
   * @param total the new total
   */
  public void setTotal(String total) {
    this.total = total;
  }

  /**
   * Gets the records.
   *
   * @return the records
   */
  public String getRecords() {
    return records;
  }

  /**
   * Sets the records.
   *
   * @param records the new records
   */
  public void setRecords(String records) {
    this.records = records;
  }

  /**
   * Gets the rows.
   *
   * @return the rows
   */
  public List<T> getRows() {
    return rows;
  }

  /**
   * Sets the rows.
   *
   * @param rows the new rows
   */
  public void setRows(List<T> rows) {
    this.rows = rows;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "JqgridResponse [page=" + page + ", total=" + total + ", records=" + records + "]";
  }
}
