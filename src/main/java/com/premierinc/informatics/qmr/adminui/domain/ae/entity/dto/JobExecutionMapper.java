package com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;

/**
 * The Class JobExecutionMapper.
 *
 * @author crowland
 */
public class JobExecutionMapper {

  /**
   * Map. Map a Job Execution entity to a DTO.
   *
   * @param record the record
   * @return the job execution dto
   */
  public static JobExecutionDTO map(JobExecution record) {
    JobExecutionDTO dto = new JobExecutionDTO(record);
    return dto;
  }

  /**
   * Map. Map a list of Job Execution entity to a DTO.
   *
   * @param records the records
   * @return the list
   */
  public static List<JobExecutionDTO> map(Page<JobExecution> records) {
    List<JobExecutionDTO> dtos = new ArrayList<JobExecutionDTO>();
    for (JobExecution user : records) {
      dtos.add(map(user));
    }
    return dtos;
  }
}
