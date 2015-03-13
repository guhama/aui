package com.premierinc.informatics.qmr.adminui.service;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecutionId;

/**
 * The Interface JobExecutionService.
 *
 * @author crowland
 */
public interface JobExecutionService {

  /**
   * Gets the page.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the page
   */
  Page<JobExecution> getPage(int pageNumber, int pageSize);

  /**
   * Find all.
   *
   * @param pageRequest the page request
   * @return the page
   */
  Page<JobExecution> findAll(Pageable pageRequest);

  /**
   * Search with all.
   *
   * @param jobExecutionId the job execution id
   * @param startTime the start time
   * @param startTimePlusOneMin the start time plus one min
   * @param endTime the end time
   * @param endTimePlusOneMin the end time plus one min
   * @param status the status
   * @param exitCode the exit code
   * @param exitMessage the exit message
   * @param pageable the pageable
   * @return the page
   */
  Page<JobExecution> searchWithAll(Long jobExecutionId, Timestamp startTime,
      Timestamp startTimePlusOneMin, Timestamp endTime, Timestamp endTimePlusOneMin, String status,
      String exitCode, String exitMessage, Pageable pageable);

  /**
   * Find one.
   *
   * @param id the id
   * @return the job execution
   */
  JobExecution findOne(JobExecutionId id);

}
