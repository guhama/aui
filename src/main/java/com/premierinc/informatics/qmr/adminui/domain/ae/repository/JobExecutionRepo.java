package com.premierinc.informatics.qmr.adminui.domain.ae.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecutionId;

/**
 * The Interface JobExecutionRepo.
 *
 * @author crowland
 */
@Repository
public interface JobExecutionRepo extends JpaRepository<JobExecution, JobExecutionId> {

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
  @Query("Select u from JobExecution u "
      + "WHERE (u.id.jobExecutionId = :jobExecutionId OR :jobExecutionId is null)"
      + "AND ((u.startTime >= :startTime AND u.startTime < :startTimePlusOneMin) "
      + "OR :startTime is null)"
      + "AND ((u.endTime >= :endTime AND u.endTime < :endTimePlusOneMin) OR :endTime is null)"
      + "AND (UPPER(u.status) like '%' || UPPER(:status) || '%' OR :status is null)"
      + "AND (UPPER(u.exitCode) like '%' || UPPER(:exitCode) || '%' OR :exitCode is null)"
      + "AND (UPPER(u.exitMessage) like '%' || UPPER(:exitMessage) || '%' OR :exitMessage is null)")
  Page<JobExecution> searchWithAll(@Param("jobExecutionId") Long jobExecutionId,
      @Param("startTime") Timestamp startTime,
      @Param("startTimePlusOneMin") Timestamp startTimePlusOneMin,
      @Param("endTime") Timestamp endTime, @Param("endTimePlusOneMin") Timestamp endTimePlusOneMin,
      @Param("status") String status, @Param("exitCode") String exitCode,
      @Param("exitMessage") String exitMessage, Pageable pageable);
}
