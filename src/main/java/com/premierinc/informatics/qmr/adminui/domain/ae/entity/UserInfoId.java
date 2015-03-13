package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class UserInfoId.
 *
 * @author crowland
 */
@Embeddable
public class UserInfoId implements Serializable {

  private static final long serialVersionUID = -5165928191629777712L;

  @Column(name = "USER_ID", length = 15, nullable = false)
  private String userId;

  /**
   * Instantiates a new user info id.
   */
  public UserInfoId() {}

  /**
   * Instantiates a new user info id.
   *
   * @param userId the user id
   */
  public UserInfoId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets the user id.
   *
   * @return the user id
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the user id.
   *
   * @param userId the new user id
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "UserInfoId [userId=" + userId + "]";
  }
}
