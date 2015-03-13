package com.premierinc.informatics.qmr.adminui.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;

/**
 * The Interface NpiFacilityTransmissionService.
 *
 * @author crowland
 */
public interface NpiFacilityTransmissionService {

  /**
   * Gets the page.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the page
   */
  Page<NpiFacilityTransmission> getPage(int pageNumber, int pageSize);

  /**
   * Find all.
   *
   * @param pageRequest the page request
   * @return the page
   */
  Page<NpiFacilityTransmission> findAll(Pageable pageRequest);

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
  Page<NpiFacilityTransmission> searchWithAll(String clientId, String facilityId,
       Long initiativeId, Long measureSetId, Long msMeasureId, String entityType,
       String entityCode, String npiNum, Date effDate, Date expDate, 
       Integer transmitInd, Pageable pageable);

  /**
   * Find one.
   *
   * @param id the id
   * @return the npi facility transmission
   */
  NpiFacilityTransmission findOne(NpiFacilityTransmissionId id);

  /**
   * Save.
   *
   * @param npi the npi
   * @return the npi facility transmission
   */
  NpiFacilityTransmission save(NpiFacilityTransmission npi);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(NpiFacilityTransmissionId id);

}
