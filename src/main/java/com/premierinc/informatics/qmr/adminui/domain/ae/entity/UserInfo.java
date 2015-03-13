package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class UserInfo.
 *
 * @author crowland
 */
@Entity
@Table(schema = "ODMT", name = "USER_INFO")
public class UserInfo implements Serializable {

  private static final long serialVersionUID = 5724104727916776454L;

  @EmbeddedId
  private UserInfoId id;

  @Column(name = "CLIENT_ID", length = 15, nullable = true)
  private String clientId;

  @Column(name = "ACCESS_LEVEL", length = 3, nullable = false)
  private String accessLevel;

  @Column(name = "LAST_NAME", length = 50, nullable = false)
  private String lastName;

  @Column(name = "FIRST_NAME", length = 30, nullable = true)
  private String firstName;

  @Column(name = "MIDDLE_NAME", length = 30, nullable = true)
  private String middleName;

  @Column(name = "LMS_USER_ID", precision = 15, nullable = false)
  private Long lmsId;

  @Column(name = "LDAP_USERNAME", length = 120, nullable = false)
  private String ldapUsername;

  @Column(name = "PROXY_USERNAME", length = 120, nullable = false)
  private String proxyUsername;

  /**
   * Instantiates a new user info.
   */
  public UserInfo() {}

  /**
   * Instantiates a new user info.
   *
   * @param id the id
   */
  public UserInfo(UserInfoId id) {
    this.id = id;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public UserInfoId getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(UserInfoId id) {
    this.id = id;
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
   * Gets the access level.
   *
   * @return the access level
   */
  public String getAccessLevel() {
    return accessLevel;
  }

  /**
   * Sets the access level.
   *
   * @param accessLevel the new access level
   */
  public void setAccessLevel(String accessLevel) {
    this.accessLevel = accessLevel;
  }

  /**
   * Gets the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the middle name.
   *
   * @return the middle name
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Sets the middle name.
   *
   * @param middleName the new middle name
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * Gets the lms id.
   *
   * @return the lms id
   */
  public Long getLmsId() {
    return lmsId;
  }

  /**
   * Sets the lms id.
   *
   * @param lmsId the new lms id
   */
  public void setLmsId(Long lmsId) {
    this.lmsId = lmsId;
  }

  /**
   * Gets the ldap username.
   *
   * @return the ldap username
   */
  public String getLdapUsername() {
    return ldapUsername;
  }

  /**
   * Sets the ldap username.
   *
   * @param ldapUsername the new ldap username
   */
  public void setLdapUsername(String ldapUsername) {
    this.ldapUsername = ldapUsername;
  }

  /**
   * Gets the proxy username.
   *
   * @return the proxy username
   */
  public String getProxyUsername() {
    return proxyUsername;
  }

  /**
   * Sets the proxy username.
   *
   * @param proxyUsername the new proxy username
   */
  public void setProxyUsername(String proxyUsername) {
    this.proxyUsername = proxyUsername;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "UserInfo [id=" + id + ", clientId=" + clientId + ", accessLevel=" + accessLevel
        + ", lastName=" + lastName + ", firstName=" + firstName + ", middleName=" + middleName
        + ", lmsId=" + lmsId + ", ldapUsername=" + ldapUsername + ", proxyUsername="
        + proxyUsername + "]";
  }
}
