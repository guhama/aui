package com.premierinc.informatics.qmr.adminui.domain.ae.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Class JobExecution.
 *
 * @author crowland
 */
@Entity
@Table(schema = "HOPUSER", name = "BATCH_JOB_EXECUTION")
public class JobExecution implements Serializable {

  private static final long serialVersionUID = 8997092341801617540L;

  @EmbeddedId
  private JobExecutionId id;

  @Column(name = "START_TIME", nullable = true)
  private Timestamp startTime;

  @Column(name = "END_TIME", nullable = true)
  private Timestamp endTime;

  @Column(name = "STATUS", length = 10, nullable = true)
  private String status;

  @Column(name = "EXIT_CODE", length = 2500, nullable = true)
  private String exitCode;

  @Column(name = "EXIT_MESSAGE", length = 2500, nullable = true)
  private String exitMessage;

  /**
   * Instantiates a new job execution.
   */
  public JobExecution() {}

  /**
   * Instantiates a new job execution.
   *
   * @param id the id
   * @param startTime the start time
   * @param endTime the end time
   * @param status the status
   * @param exitCode the exit code
   * @param exitMessage the exit message
   */
  public JobExecution(JobExecutionId id, Timestamp startTime, Timestamp endTime, String status,
      String exitCode, String exitMessage) {
    this.id = id;
    this.startTime = new Timestamp(startTime.getTime());
    this.endTime = new Timestamp(endTime.getTime());
    this.status = status;
    this.exitCode = exitCode;
    this.exitMessage = exitMessage;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public JobExecutionId getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(JobExecutionId id) {
    this.id = id;
  }

  /**
   * Gets the start time.
   *
   * @return the start time
   */
  public Timestamp getStartTime() {
    return new Timestamp(startTime.getTime());
  }

  /**
   * Sets the start time.
   *
   * @param startTime the new start time
   */
  public void setStartTime(Timestamp startTime) {
    this.startTime = new Timestamp(startTime.getTime());
  }

  /**
   * Gets the end time.
   *
   * @return the end time
   */
  public Timestamp getEndTime() {
    return new Timestamp(endTime.getTime());
  }

  /**
   * Sets the end time.
   *
   * @param endTime the new end time
   */
  public void setEndTime(Timestamp endTime) {
    this.endTime = new Timestamp(endTime.getTime());
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "JobExecution [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime
        + ", status=" + status + ", exitCode=" + exitCode + ", exitMessage=" + exitMessage + "]";
  }
}
