package com.premierinc.informatics.qmr.adminui.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;
import com.premierinc.informatics.qmr.adminui.domain.ae.repository.NpiEntityRepo;
import com.premierinc.informatics.qmr.adminui.service.NpiEntityService;

/**
 * The Class NpiEntityServiceImpl.
 *
 * @author crowland
 */
@Service
@Transactional("aeTransactionManager")
public class NpiEntityServiceImpl implements NpiEntityService {

  @Autowired
  private NpiEntityRepo npiEntityRepo;

  /**
   * Get page. Return a page of NPI Entity entities.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the page
   */
  public Page<NpiEntity> getPage(int pageNumber, int pageSize) {
    PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "id");
    return npiEntityRepo.findAll(request);
  }

  /**
   * Find all. Return a page of NPI Entity entities.
   *
   * @param pageRequest the page request
   * @return the page
   */
  public Page<NpiEntity> findAll(Pageable pageRequest) {
    return npiEntityRepo.findAll(pageRequest);
  }

  /**
   * Search with all. Return a page of NPI Entity entities based on provided filters.
   *
   * @param entityType the entity type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @param pageable the pageable
   * @return the page
   */
  public Page<NpiEntity> searchWithAll(String entityCode, String entityType, String npiNum,
      Date effDate, Date expDate, Pageable pageable) {
    return npiEntityRepo.searchWithAll(entityCode, entityType, npiNum, effDate, expDate, pageable);
  }

  /**
   * Find one. Return a single NPI Entity entity.
   *
   * @param id the id
   * @return the npi facility transmission
   */
  public NpiEntity findOne(NpiEntityId id) {
    return npiEntityRepo.findOne(id);
  }

  /**
   * Save. Save a new or update an existing NPI Entity entity.
   *
   * @param npi the npi
   * @return the npi facility transmission
   */
  public NpiEntity save(NpiEntity npi) {
    return npiEntityRepo.save(npi);
  }

  /**
   * Delete. Delete a NPI Entity entity.
   *
   * @param id the id
   */
  public void delete(NpiEntityId id) {
    npiEntityRepo.delete(id);
  }

  /**
   * Gets the NPI Entity repo.
   *
   * @return the NPI Entity repo
   */
  public NpiEntityRepo getNpiEntityRepo() {
    return npiEntityRepo;
  }

  /**
   * Sets the NPI Entity repo.
   *
   * @param nftRepo the new NPI Entity repo
   */
  public void setNpiEntityRepo(NpiEntityRepo nftRepo) {
    this.npiEntityRepo = nftRepo;
  }
}
