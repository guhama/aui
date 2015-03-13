package com.premierinc.informatics.qmr.adminui.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecution;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.JobExecutionId;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto.JobExecutionDTO;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto.JobExecutionMapper;
import com.premierinc.informatics.qmr.adminui.service.JobExecutionService;
import com.premierinc.informatics.qmr.adminui.util.JqgridFilter;
import com.premierinc.informatics.qmr.adminui.util.JqgridObjectMapper;
import com.premierinc.informatics.qmr.adminui.util.JqgridResponse;

/**
 * The Class JobExecutionController.
 *
 * @author crowland
 */
@Controller
@RequestMapping(value = "/job-execution")
@SuppressWarnings("PMD.UnusedPrivateMethod")
public class JobExecutionController {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutionController.class);

  @Autowired
  private JobExecutionService jobExecutionService;

  /**
   * Npi_facility_tranmission_content. Serves the Job Execution status page.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String npi_facility_tranmission_content(Model model) {
    LOGGER.trace("Serving /file-uploads/job-execution.");
    return "/file-uploads/job-execution";
  }

  /**
   * Records. Returns a page of records from the Batch Job Execution table in JSON format. Can
   * filter the query based on pased in parameters.
   *
   * @param search a boolean value indicating whether the query should be filtered
   * @param filters the filters
   * @param page the page
   * @param rows the number of rows per page
   * @param sidx the sort index
   * @param sord the sort order
   * @return the jqgrid response
   */
  @RequestMapping(value = "/records", produces = "application/json")
  public @ResponseBody JqgridResponse<JobExecutionDTO> records(
      @RequestParam("_search") Boolean search,
      @RequestParam(value = "filters", required = false) String filters, @RequestParam(
          value = "page", required = false, defaultValue = "20") Integer page, @RequestParam(
          value = "rows", required = false, defaultValue = "20") Integer rows, @RequestParam(
          value = "sidx", required = false) String sidx, @RequestParam(value = "sord",
          required = false, defaultValue = "asc") String sord) {

    Object[] loggerObjects = new Object[] {
      search,
      filters,
      page,
      rows,
      sidx,
      sord };
    LOGGER.trace("Generating JSON for Batch Job Execution table. "
        + "Search:{} Filters:{} Page:{} Rows:{} Sidx:{} Sord:{}", loggerObjects);

    // Prefix id columns so hibernate is happy
    if ("jobExecutionId".equals(sidx)) {
      sidx = "id." + sidx;
    }

    Direction sortOrder = "asc".equalsIgnoreCase(sord) ? Sort.Direction.ASC : Sort.Direction.DESC;

    Pageable pageRequest = new PageRequest(page - 1, rows, sortOrder, sidx);

    if (search == true) {
      return getFilteredRecords(filters, pageRequest);
    }

    Page<JobExecution> jobExecutions = jobExecutionService.findAll(pageRequest);
    List<JobExecutionDTO> jobExecutionDTOs = JobExecutionMapper.map(jobExecutions);

    JqgridResponse<JobExecutionDTO> response = new JqgridResponse<JobExecutionDTO>();
    response.setRows(jobExecutionDTOs);
    response.setRecords(Long.toString(jobExecutions.getTotalElements()));
    response.setTotal(Integer.toString(jobExecutions.getTotalPages()));
    response.setPage(Integer.toString(jobExecutions.getNumber() + 1));

    return response;
  }

  /**
   * Helper method for returning filtered records.
   *
   * @param filters the filters
   * @param pageRequest the page request
   * @return the filtered records
   */
  private JqgridResponse<JobExecutionDTO> getFilteredRecords(String filters,
      Pageable pageRequest) {

    LOGGER.trace("Filtering query based on user provided restrictions: " + filters);

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    String status = null;
    String exitCode = null;
    String exitMessage = null;
    Long jobExecutionId = null;
    Timestamp startTime = null;
    Timestamp startTimePlusOneMin = null;
    Timestamp endTime = null;
    Timestamp endTimePlusOneMin = null;

    JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
    for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
      if (rule.getField().equals("JobExecutionId")) {
        jobExecutionId = Long.valueOf(rule.getData());
      } else if (rule.getField().equals("startTime")) {
        try {
          startTime = new Timestamp(sdf.parse(rule.getData()).getTime());
          Calendar cal = Calendar.getInstance();
          cal.setTimeInMillis(startTime.getTime());
          cal.add(Calendar.MINUTE, 1);
          startTimePlusOneMin = new Timestamp(cal.getTime().getTime());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      } else if (rule.getField().equals("endTime")) {
        try {
          endTime = new Timestamp(sdf.parse(rule.getData()).getTime());
          Calendar cal = Calendar.getInstance();
          cal.setTimeInMillis(endTime.getTime());
          cal.add(Calendar.MINUTE, 1);
          endTimePlusOneMin = new Timestamp(cal.getTime().getTime());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      } else if (rule.getField().equals("status")) {
        status = rule.getData();
      } else if (rule.getField().equals("exitCode")) {
        exitCode = rule.getData();
      } else if (rule.getField().equals("exitMessage")) {
        exitMessage = rule.getData();
      }
    }

    Page<JobExecution> jobExecutions =
        jobExecutionService.searchWithAll(jobExecutionId, startTime, startTimePlusOneMin, endTime,
            endTimePlusOneMin, status, exitCode, exitMessage, pageRequest);

    List<JobExecutionDTO> jobExecutionDTOs = JobExecutionMapper.map(jobExecutions);
    JqgridResponse<JobExecutionDTO> response = new JqgridResponse<JobExecutionDTO>();
    response.setRows(jobExecutionDTOs);
    response.setRecords(Long.toString(jobExecutions.getTotalElements()));
    response.setTotal(Integer.toString(jobExecutions.getTotalPages()));
    response.setPage(Integer.toString(jobExecutions.getNumber() + 1));
    return response;
  }

  /**
   * Gets a single Job Execution record.
   *
   * @param jobExecution the job execution
   * @return the job execution dto
   */
  @RequestMapping(value = "/get", produces = "application/json")
  public @ResponseBody JobExecutionDTO get(@RequestBody JobExecutionDTO jobExecution) {

    LOGGER.trace("Generating JSON for single Job Execution: " + jobExecution);

    return JobExecutionMapper.map(jobExecutionService.findOne(new JobExecutionId(jobExecution
        .getJobExecutionId())));
  }
}
