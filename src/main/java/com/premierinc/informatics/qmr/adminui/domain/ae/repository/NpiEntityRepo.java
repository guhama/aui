package com.premierinc.informatics.qmr.adminui.domain.ae.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;

/**
 * The Interface NpiEntityRepo.
 *
 * @author crowland
 */
@Repository
public interface NpiEntityRepo extends JpaRepository<NpiEntity, NpiEntityId> {

  /**
   * Search with all.
   *
   * @param entityType the entity type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @param pageable the pageable
   * @return the page
   */
  @Query("Select u from NpiEntity u "
      + "WHERE (UPPER(u.id.entityType) like '%' || UPPER(:entityType) || '%' "
      + "       OR :entityType is null)"
      + "AND (UPPER(u.id.entityCode) like '%' || UPPER(:entityCode) || '%' "
      + "     OR :entityCode is null)"
      + "AND (UPPER(u.id.npiNum) like '%' || UPPER(:npiNum) || '%' OR :npiNum is null)"
      + "AND (u.id.effDate = :effDate OR :effDate is null)"
      + "AND (u.expDate = :expDate OR :expDate is null)")
  Page<NpiEntity> searchWithAll(@Param("entityType") String entityType,
      @Param("entityCode") String entityCode, @Param("npiNum") String npiNum,
      @Param("effDate") Date effDate, @Param("expDate") Date expDate, Pageable pageable);
}
