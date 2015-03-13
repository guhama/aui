package com.premierinc.informatics.qmr.adminui.domain.ae.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;

/**
 * The Interface NpiFacilityTransmissionRepo.
 *
 * @author crowland
 */
@Repository
public interface NpiFacilityTransmissionRepo extends
    JpaRepository<NpiFacilityTransmission, NpiFacilityTransmissionId> {

  /**
   * Search with all.
   *
   * @param clientId the client id
   * @param facilityId the facility id
   * @param initiativeId the initiative id
   * @param measureSetId the measure set id
   * @param msMeasureId the ms measure id
   * @param entityType the entity type
   * @param entityCode the entity Code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @param transmitInd the transmit ind
   * @param pageable the pageable
   * @return the page
   */
  @Query("Select u from NpiFacilityTransmission u "
      + "WHERE (UPPER(u.id.clientId) like '%' || UPPER(:clientId) || '%' OR :clientId is null)"
      + "AND (UPPER(u.id.facilityId) like '%' || UPPER(:facilityId) || '%' OR :facilityId is null)"
      + "AND (u.id.initiativeId = :initiativeId OR :initiativeId is null)"
      + "AND (u.id.measureSetId = :measureSetId OR :measureSetId is null)"
      + "AND (u.id.msMeasureId = :msMeasureId OR :msMeasureId is null)"
      + "AND (UPPER(u.id.entityType) like '%' || UPPER(:entityType) || '%' OR :entityType is null)"
      + "AND (UPPER(u.id.entityCode) like '%' || UPPER(:entityCode) || '%' OR :entityCode is null)"
      + "AND (UPPER(u.id.npiNum) like '%' || UPPER(:npiNum) || '%' OR :npiNum is null)"
      + "AND (u.id.effDate = :effDate OR :effDate is null)"
      + "AND (u.expDate = :expDate OR :expDate is null)"
      + "AND (u.transmitInd = :transmitInd OR :transmitInd is null)")
  Page<NpiFacilityTransmission> searchWithAll(@Param("clientId") String clientId,
      @Param("facilityId") String facilityId, @Param("initiativeId") Long initiativeId,
      @Param("measureSetId") Long measureSetId, @Param("msMeasureId") Long msMeasureId,
      @Param("entityType") String entityType, @Param("entityCode") String entityCode,
      @Param("npiNum") String npiNum, @Param("effDate") Date effDate,
      @Param("expDate") Date expDate, @Param("transmitInd") Integer transmitInd, Pageable pageable);
}
