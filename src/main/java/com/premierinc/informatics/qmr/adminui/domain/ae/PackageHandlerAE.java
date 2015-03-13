package com.premierinc.informatics.qmr.adminui.domain.ae;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 * The Class PackageHandlerAE. Utility class for all Package/Procedure calls.
 *
 * @author crowland
 */
@Repository
public class PackageHandlerAE {
  Logger logger = LoggerFactory.getLogger(PackageHandlerAE.class);

  @Autowired
  @Qualifier("aeJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  /**
   * Merge npi facility transmission.
   *
   * @param id the id
   * @return the map
   * @throws Exception the exception
   */
  public Map<String, Object> mergeNpiFacilityTransmission(Long id) throws Exception {

    if (id == null) {
      throw new RuntimeException(
          "Unable to call merge for NpiFacilityTransmission. Provided Job Execution Id was null.");
    }

    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);

    jdbcCall.withSchemaName("AREA1");
    jdbcCall.withCatalogName("BATCH");
    jdbcCall.withProcedureName("MERGE_NPI_FACILITY_TRANS");

    jdbcCall.addDeclaredParameter(new SqlInOutParameter("P_RETURN", java.sql.Types.VARCHAR));

    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("P_ID", id);

    return jdbcCall.execute(params);
  }
  
  /**
   * Merge npi entity.
   *
   * @param id the id
   * @return the map
   * @throws Exception the exception
   */
  public Map<String, Object> mergeNpiEntity(Long id) throws Exception {

    if (id == null) {
      throw new RuntimeException(
          "Unable to call merge for NpiEntity. Provided Job Execution Id was null.");
    }

    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);

    jdbcCall.withSchemaName("AREA1");
    jdbcCall.withCatalogName("BATCH");
    jdbcCall.withProcedureName("MERGE_NPI_ENTITY");

    jdbcCall.addDeclaredParameter(new SqlInOutParameter("P_RETURN", java.sql.Types.VARCHAR));

    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("P_ID", id);

    return jdbcCall.execute(params);
  }
}
