package com.premierinc.informatics.qmr.adminui.domain.ae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfo;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.UserInfoId;

/**
 * The Interface UserInfoRepo.
 *
 * @author crowland
 */
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, UserInfoId> {

  /**
   * Find by lmsId.
   *
   * @param lmsId the lmsId
   * @return the user info
   */
  @Query("Select u from UserInfo u where u.lmsId = :lmsId")
  UserInfo findByLmsId(@Param("lmsId") Long lmsId);
  
  /**
   * Find by ldap username.
   *
   * @param ldapUsername the ldap username
   * @return the user info
   */
  @Query("Select u from UserInfo u where u.ldapUsername = :ldapUsername")
  UserInfo findByldapUsername(@Param("ldapUsername") String ldapUsername);

}
