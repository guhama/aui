package com.premierinc.informatics.qmr.adminui.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecutionId;
import com.premierinc.informatics.qmr.adminui.domain.ae.repository.JobExecutionRepo;
import com.premierinc.informatics.qmr.adminui.service.JobExecutionService;

/**
 * The Class JobExecutionServiceImpl.
 *
 * @author crowland
 */
@Service
@Transactional("aeTransactionManager")
public class JobExecutionServiceImpl implements JobExecutionService {

  @Autowired
  private JobExecutionRepo jobExecutionRepo;

  /**
   *  
   * Get page. Return a page of Job Execution entities.
   *
   * @param pageNumber the page number
   * @param pageSize the page size
   * @return the page
   */
  public Page<JobExecution> getPage(int pageNumber, int pageSize) {
    PageRequest request = new PageRequest(pageNumber - 1, pageSize, Sort.Direction.DESC, "id");
    return jobExecutionRepo.findAll(request);
  }

  /**
   * Find all. Return a page of Job Execution entities.
   *
   * @param pageRequest the page request
   * @return the page
   */
  public Page<JobExecution> findAll(Pageable pageRequest) {
    return jobExecutionRepo.findAll(pageRequest);
  }

  /**
   * Search with all. Return a page of Job Execution entities based on provided filters.
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
  public Page<JobExecution> searchWithAll(Long jobExecutionId, Timestamp startTime,
      Timestamp startTimePlusOneMin, Timestamp endTime, Timestamp endTimePlusOneMin, String status,
      String exitCode, String exitMessage, Pageable pageable) {
    return jobExecutionRepo.searchWithAll(jobExecutionId, startTime, startTimePlusOneMin, endTime,
        endTimePlusOneMin, status, exitCode, exitMessage, pageable);
  }

  /**
   * Find one. Return a single Job Execution entity.
   *
   * @param id the id
   * @return the job execution
   */
  public JobExecution findOne(JobExecutionId id) {
    return jobExecutionRepo.findOne(id);
  }

  /**
   * Gets the job execution repo.
   *
   * @return the job execution repo
   */
  public JobExecutionRepo getJobExecutionRepo() {
    return jobExecutionRepo;
  }

  /**
   * Sets the job execution repo.
   *
   * @param jobExecutionRepo the new job execution repo
   */
  public void setJobExecutionRepo(JobExecutionRepo jobExecutionRepo) {
    this.jobExecutionRepo = jobExecutionRepo;
  }
}
