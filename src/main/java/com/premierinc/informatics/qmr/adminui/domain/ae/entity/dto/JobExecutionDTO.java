package com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;

/**
 * The Class JobExecutionDTO.
 *
 * @author crowland
 */
public class JobExecutionDTO implements Serializable {

  private static final long serialVersionUID = 4138906225205436286L;

  private Long jobExecutionId;

  private String startTime;

  private String endTime;

  private String status;

  private String exitCode;

  private String exitMessage;

  /**
   * Instantiates a new job execution dto.
   */
  public JobExecutionDTO() {}

  /**
   * Instantiates a new job execution dto.
   *
   * @param je the jobExecution
   */
  @edu.umd.cs.findbugs.annotations.SuppressWarnings(
      value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE",
      justification = "It isn't a redundant check...")
  public JobExecutionDTO(JobExecution je) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    this.jobExecutionId = je.getId().getJobExecutionId();
    if (je.getStartTime() != null) {
      this.startTime = sdf.format(je.getStartTime());
    } else {
      this.startTime = null;
    }
    if (je.getEndTime() != null) {
      this.endTime = sdf.format(je.getEndTime());
    } else {
      this.endTime = null;
    }
    this.status = je.getStatus();
    this.exitCode = je.getExitCode();
    this.exitMessage = je.getExitMessage();
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
   * Gets the start time.
   *
   * @return the start time
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Sets the start time.
   *
   * @param startTime the new start time
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets the end time.
   *
   * @return the end time
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * Sets the end time.
   *
   * @param endTime the new end time
   */
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  /**
   * Gets the status.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status.
   *
   * @param status the new status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the exit code.
   *
   * @return the exit code
   */
  public String getExitCode() {
    return exitCode;
  }

  /**
   * Sets the exit code.
   *
   * @param exitCode the new exit code
   */
  public void setExitCode(String exitCode) {
    this.exitCode = exitCode;
  }

  /**
   * Gets the exit message.
   *
   * @return the exit message
   */
  public String getExitMessage() {
    return exitMessage;
  }

  /**
   * Sets the exit message.
   *
   * @param exitMessage the new exit message
   */
  public void setExitMessage(String exitMessage) {
    this.exitMessage = exitMessage;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "JobExecutionDTO [jobExecutionId=" + jobExecutionId + ", startTime=" + startTime
        + ", endTime=" + endTime + ", status=" + status + ", exitCode=" + exitCode
        + ", exitMessage=" + exitMessage + "]";
  }
}
