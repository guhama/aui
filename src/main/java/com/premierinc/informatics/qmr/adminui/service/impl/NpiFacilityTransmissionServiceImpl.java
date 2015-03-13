package com.premierinc.informatics.qmr.adminui.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.domain.ae.repository.NpiFacilityTransmissionRepo;
import com.premierinc.informatics.qmr.adminui.service.NpiFacilityTransmissionService;

/**
 * The Class NpiFacilityTransmissionServiceImpl.
 *
 * @author crowland
 */
@Service
@Transactional("aeTransactionManager")
public class NpiFacilityTransmissionServiceImpl implements NpiFacilityTransmissionService {

  @Autowired
  private NpiFacilityTransmissionRepo npiFacilityTransmissionRepo;

  /**
   * Get page. Return a page of NPI Facility Transmission entities.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the page
   */
  public Page<NpiFacilityTransmission> getPage(int pageNumber, int pageSize) {
    PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "id");
    return npiFacilityTransmissionRepo.findAll(request);
  }

  /**
   * Find all. Return a page of NPI Facility Transmission entities.
   *
   * @param pageRequest the page request
   * @return the page
   */
  public Page<NpiFacilityTransmission> findAll(Pageable pageRequest) {
    return npiFacilityTransmissionRepo.findAll(pageRequest);
  }

  /**
   * Search with all. Return a page of NPI Facility Transmission entities based on provided filters.
   *
   * @param clientId the client id
   * @param facilityId the facility id
   * @param initiativeId the initiative id
   * @param measureSetId the measure set id
   * @param msMeasureId the ms measure id
   * @param entityType the entity type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @param transmitInd the transmit ind
   * @param pageable the pageable
   * @return the page
   */
  public Page<NpiFacilityTransmission> searchWithAll(String clientId, String facilityId,
              Long initiativeId, Long measureSetId, Long msMeasureId, String entityType, 
              String entityCode, String npiNum, Date effDate, Date expDate, 
              Integer transmitInd, Pageable pageable) {
    return npiFacilityTransmissionRepo.searchWithAll(clientId, facilityId, initiativeId,
                                      measureSetId, msMeasureId, entityType, entityCode, 
                                      npiNum, effDate, expDate, transmitInd, pageable);
  }

  /**
   * Find one. Return a single NPI Facility Transmission entity.
   *
   * @param id the id
   * @return the npi facility transmission
   */
  public NpiFacilityTransmission findOne(NpiFacilityTransmissionId id) {
    return npiFacilityTransmissionRepo.findOne(id);
  }

  /**
   * Save. Save a new or update an existing NPI Facility Transmission entity.
   *
   * @param npi the npi
   * @return the npi facility transmission
   */
  public NpiFacilityTransmission save(NpiFacilityTransmission npi) {
    return npiFacilityTransmissionRepo.save(npi);
  }

  /**
   * Delete. Delete a NPI Facility Transmission entity.
   *
   * @param id the id
   */
  public void delete(NpiFacilityTransmissionId id) {
    npiFacilityTransmissionRepo.delete(id);
  }

  /**
   * Gets the npi facility transmission repo.
   *
   * @return the npi facility transmission repo
   */
  public NpiFacilityTransmissionRepo getNpiFacilityTransmissionRepo() {
    return npiFacilityTransmissionRepo;
  }

  /**
   * Sets the npi facility transmission repo.
   *
   * @param nftRepo the new npi facility transmission repo
   */
  public void setNpiFacilityTransmissionRepo(NpiFacilityTransmissionRepo nftRepo) {
    this.npiFacilityTransmissionRepo = nftRepo;
  }
}
