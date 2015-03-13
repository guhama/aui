package com.premierinc.informatics.qmr.adminui.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class StatusResponse.
 */
@XmlRootElement
public class StatusResponse {

  private Boolean success;
  private List<String> message;

  /**
   * Instantiates a new status response.
   */
  public StatusResponse() {
    this.message = new ArrayList<String>();
  }

  /**
   * Instantiates a new status response.
   *
   * @param success the success
   */
  public StatusResponse(Boolean success) {
    super();
    this.success = success;
    this.message = new ArrayList<String>();
  }

  /**
   * Instantiates a new status response.
   *
   * @param success the success
   * @param message the message
   */
  public StatusResponse(Boolean success, String message) {
    super();
    this.success = success;
    this.message = new ArrayList<String>();
    this.message.add(message);
  }

  /**
   * Instantiates a new status response.
   *
   * @param success the success
   * @param message the message
   */
  public StatusResponse(Boolean success, List<String> message) {
    super();
    this.success = success;
    this.message = message;
  }

  /**
   * Gets the success.
   *
   * @return the success
   */
  public Boolean getSuccess() {
    return success;
  }

  /**
   * Sets the success.
   *
   * @param success the new success
   */
  public void setSuccess(Boolean success) {
    this.success = success;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public List<String> getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the new message
   */
  public void setMessage(List<String> message) {
    this.message = message;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String mess : message) {
      sb.append(mess + ", ");
    }

    return "StatusResponse [success=" + success + ", message=" + sb.toString() + "]";
  }
}
