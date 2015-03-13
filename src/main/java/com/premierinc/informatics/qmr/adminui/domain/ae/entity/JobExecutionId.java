package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class JobExecutionId.
 *
 * @author crowland
 */
@Embeddable
public class JobExecutionId implements Serializable {

  private static final long serialVersionUID = 6392392061525859424L;

  @Column(name = "JOB_EXECUTION_ID", precision = 19, nullable = false)
  private Long jobExecutionId;

  /**
   * Instantiates a new job execution id.
   */
  public JobExecutionId() {}

  /**
   * Instantiates a new job execution id.
   *
   * @param jobExecutionId the job execution id
   */
  public JobExecutionId(Long jobExecutionId) {
    this.jobExecutionId = jobExecutionId;
  }

  /**
   * Gets the job execution id.
   *
   * @return the job execution id
   */
  public Long getJobExecutionId() {
    return jobExecutionId;
  }

  /**
   * Sets the job execution id.
   *
   * @param jobExecutionId the new job execution id
   */
  public void setJobExecutionId(Long jobExecutionId) {
    this.jobExecutionId = jobExecutionId;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "JobExecutionId [jobExecutionId=" + jobExecutionId + "]";
  }
}
