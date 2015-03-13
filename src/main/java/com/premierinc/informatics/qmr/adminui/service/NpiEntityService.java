package com.premierinc.informatics.qmr.adminui.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;

/**
 * The Interface NpiEntityService.
 *
 * @author crowland
 */
public interface NpiEntityService {

  /**
   * Gets the page.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the page
   */
  Page<NpiEntity> getPage(int pageNumber, int pageSize);

  /**
   * Find all.
   *
   * @param pageRequest the page request
   * @return the page
   */
  Page<NpiEntity> findAll(Pageable pageRequest);

  /**
   * Search with all.
   *
   * @param entityCode the entityCode
   * @param entityType the entityType
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp dates
   * @param pageable the pageable
   * @return the page
   */
  Page<NpiEntity> searchWithAll(String entityCode, String entityType, String npiNum,
      Date effDate, Date expDate, Pageable pageable);

  /**
   * Find one.
   *
   * @param id the id
   * @return the npi entity
   */
  NpiEntity findOne(NpiEntityId id);

  /**
   * Save.
   *
   * @param npi the npi
   * @return the npi entity
   */
  NpiEntity save(NpiEntity npi);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(NpiEntityId id);

}
