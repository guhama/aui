package com.premierinc.informatics.qmr.adminui.batch.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;

/**
 * The Class NpiFacilityTransmissionWriter. Using batch JDBC this class will insert the records from
 * a file upload, after being translated to entities and validated, to a temporary table.
 *
 * @author crowland
 */
public class NpiFacilityTransmissionWriter implements ItemWriter<NpiFacilityTransmission> {

  private static final Logger LOGGER = LoggerFactory.getLogger(NpiFacilityTransmissionWriter.class);

  private static final String INSERT_ROW = "insert into AREA1.npi_facility_transmission_stg "
      + "(JOB_ID, CLIENT_ID, FACILITY_ID, INITIATIVE_ID, MEASURE_SET_ID, "
      + "MS_MEASURE_ID, ENTITY_TYPE, NPI_NUM, REPORT_PERIOD_EFF_DATE, "
      + "REPORT_PERIOD_EXP_DATE, TRANSMIT_IND, ENTITY_CODE) values(?,?,?,?,?,?,?,?,?,?,?,?)";

  /**
   * Instantiates a new npi facility transmission writer.
   */
  public NpiFacilityTransmissionWriter() {}

  @Autowired
  @Qualifier("aeJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  private StepExecution stepExecution;

  /**
   * Inject the Execution from the previous step.
   *
   * @param stepExecution the step execution
   */
  @BeforeStep
  public void saveStepExecution(StepExecution stepExecution) {
    this.stepExecution = stepExecution;
  }

  /**
   * Write line. Takes a list of NPI Facility Transmission entities and writes them to a temporary
   * table in batch.
   *
   * @param items the items
   * @throws Exception the exception
   */
  public void write(final List<? extends NpiFacilityTransmission> items) throws Exception {

    final Long id = stepExecution.getJobExecutionId();

    if (!items.isEmpty()) {

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("Executing batch with " + items.size() + " items.");
      }

      jdbcTemplate.execute(INSERT_ROW, new PreparedStatementCallback<int[]>() {
        @Override
        public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException,
            DataAccessException {
          for (NpiFacilityTransmission item : items) {
            ps.setLong(1, id);
            ps.setString(2, item.getId().getClientId());
            ps.setString(3, item.getId().getFacilityId());
            ps.setLong(4, item.getId().getInitiativeId());
            ps.setLong(5, item.getId().getMeasureSetId());
            ps.setLong(6, item.getId().getMsMeasureId());
            ps.setString(7, item.getId().getEntityType());
            ps.setString(8, item.getId().getNpiNum());
            ps.setDate(9, new java.sql.Date(item.getId().getEffDate().getTime()));
            ps.setDate(10, new java.sql.Date(item.getExpDate().getTime()));
            ps.setInt(11, item.getTransmitInd());
            ps.setString(12, item.getId().getEntityCode());
            ps.addBatch();
          }
          return ps.executeBatch();
        }
      });

      LOGGER.debug("Batch Complete.");
    } else {
      LOGGER.error("No NPI Facility Transmission objects we're in provided list.");
      throw new Exception(
          "After mapping and validations, there were no valid rows to write to the table.");
    }
  }

  /**
   * Gets the jdbc template.
   *
   * @return the jdbc template
   */
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  /**
   * Sets the jdbc template.
   *
   * @param jdbcTemplate the new jdbc template
   */
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
}
