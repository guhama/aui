package com.premierinc.informatics.qmr.adminui.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.multipart.MultipartFile;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto.NpiEntityDTO;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto.NpiEntityMapper;
import com.premierinc.informatics.qmr.adminui.service.NpiEntityService;
import com.premierinc.informatics.qmr.adminui.util.JqgridFilter;
import com.premierinc.informatics.qmr.adminui.util.JqgridObjectMapper;
import com.premierinc.informatics.qmr.adminui.util.JqgridResponse;
import com.premierinc.informatics.qmr.adminui.util.ResponseMessage;
import com.premierinc.informatics.qmr.adminui.util.StatusResponse;

/**
 * The Class NpiEntityController.
 *
 * @author crowland
 */
@Controller
@RequestMapping(value = "/npi-entity")
@SuppressWarnings("PMD.UnusedPrivateMethod")
public class NpiEntityController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(NpiFacilityTranmissionController.class);

  @Autowired
  @Qualifier("jobLauncher")
  private JobLauncher jobLauncher;

  @Autowired
  @Qualifier("loadNpiEntity")
  private Job job;

  @Autowired
  private NpiEntityService npiEntityService;

  /**
   * Npi_measure_tranmission_content. Serves file upload page for NPI Entity.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/file-upload", method = RequestMethod.GET)
  public String npi_measure_tranmission_content(Model model) {
    LOGGER.trace("Serving /file-uploads/npi-entity.");
    return "/file-uploads/npi-entity";
  }

  /**
   * Handle file upload. Takes a file and kicks off a new batch job in a separate thread.
   *
   * @param name the name
   * @param file the file
   * @param model the model
   * @return the response message
   */
  @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
  @edu.umd.cs.findbugs.annotations.SuppressWarnings("PATH_TRAVERSAL_IN")
  public @ResponseBody ResponseMessage handleFileUpload(@RequestParam(value = "name",
      required = false) String name, @RequestParam("file") MultipartFile file, Model model) {

    LOGGER.trace("Processing file upload request for NPI Entity.");

    if (!file.isEmpty()) {
      try {
        String filePath = System.getProperty("batch.tmpdir");
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        LOGGER.debug("UPLOADED FILE: " + filePath + fileName);
        final File tmpFile = new File(filePath + fileName);
        file.transferTo(tmpFile);

        Thread thread = new Thread() {
          @Override
          public void run() {
            try {
              LOGGER.debug("Starting NPI Entity batch job for file: " + tmpFile.getAbsolutePath());
              JobExecution jobExecution =
                  jobLauncher.run(
                      job,
                      new JobParametersBuilder().addString("input.file",
                          "file:" + tmpFile.getAbsolutePath()).toJobParameters());
              LOGGER.debug("NPI Entity batch job with Execution Id " + jobExecution.getJobId()
                  + " finished with status: " + jobExecution.getExitStatus());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
        thread.start();

        LOGGER.trace("NPI Entity file upload request complete. "
            + "Batch job started with no issues.");
        return new ResponseMessage("SUCCESS",
            "Your file was uploaded successfully and the job has started.");
      } catch (Exception e) {
        e.printStackTrace();
        LOGGER.error("NPI Entity batch job failed due to unknown error: " + e.getMessage());
        return new ResponseMessage("ERROR",
            "Your file was NOT uploaded successfully. Unknown error occurred.");
      }
    } else {
      LOGGER.error("NPI Entity batch job failed because uploaded file was empty.");
      return new ResponseMessage("ERROR", "Your file was uploaded successfully, but it was empty. "
          + "Please upload a file with stuffs in it. :)");
    }
  }

  /**
   * Npi_entity_content. Serves table interface for the NPI Entity table.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/table-interface", method = RequestMethod.GET)
  public String npi_entity_content(Model model) {
    LOGGER.trace("Serving /table-interfaces/npi-entity.");
    return "/table-interfaces/npi-entity";
  }

  /**
   * Records. Returns a page of records from the NPI Entity table in JSON format. Can filter the
   * query based on pased in parameters.
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
  public @ResponseBody JqgridResponse<NpiEntityDTO> records(
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
    LOGGER.trace("Generating JSON for NPI Entity table. "
        + "Search:{} Filters:{} Page:{} Rows:{} Sidx:{} Sord:{}", loggerObjects);

    // Default sort to npi num
    if ("id".equals(sidx)) {
      sidx = "npiNum";
    }

    // Prefix id columns so hibernate is happy
    if ("entityType".equals(sidx) || "entityCode".equals(sidx) || "npiNum".equals(sidx)
        || "effDate".equals(sidx)) {
      sidx = "id." + sidx;
    }

    Direction sortOrder = "asc".equalsIgnoreCase(sord) ? Sort.Direction.ASC : Sort.Direction.DESC;

    Pageable pageRequest = new PageRequest(page - 1, rows, sortOrder, sidx);

    if (search == true) {
      return getFilteredRecords(filters, pageRequest);
    }

    Page<NpiEntity> npiEntity = npiEntityService.findAll(pageRequest);
    List<NpiEntityDTO> npiEntityDTOs = NpiEntityMapper.map(npiEntity);

    JqgridResponse<NpiEntityDTO> response = new JqgridResponse<NpiEntityDTO>();
    response.setRows(npiEntityDTOs);
    response.setRecords(Long.toString(npiEntity.getTotalElements()));
    response.setTotal(Integer.toString(npiEntity.getTotalPages()));
    response.setPage(Integer.toString(npiEntity.getNumber() + 1));

    return response;
  }

  /**
   * Helper method for returning filtered records.
   *
   * @param filters the filters
   * @param pageRequest the page request
   * @return the filtered records
   */
  private JqgridResponse<NpiEntityDTO> getFilteredRecords(String filters, Pageable pageRequest) {

    LOGGER.trace("Filtering query based on user provided restrictions: " + filters);

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String entityType = null;
    String entityCode = null;
    String npiNum = null;
    Date effDate = null;
    Date expDate = null;

    JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
    for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
      if (rule.getField().equals("entityType")) {
        entityType = rule.getData();
      } else if (rule.getField().equals("entityCode")) {
        entityCode = rule.getData();
      } else if (rule.getField().equals("npiNum")) {
        npiNum = rule.getData();
      } else if (rule.getField().equals("effDate")) {
        try {
          effDate = sdf.parse(rule.getData());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      } else if (rule.getField().equals("expDate")) {
        try {
          expDate = sdf.parse(rule.getData());
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
    }

    Page<NpiEntity> npiEntitys =
        npiEntityService.searchWithAll(entityType, entityCode, npiNum, effDate, expDate,
            pageRequest);

    List<NpiEntityDTO> npiEntityDTOs = NpiEntityMapper.map(npiEntitys);
    JqgridResponse<NpiEntityDTO> response = new JqgridResponse<NpiEntityDTO>();
    response.setRows(npiEntityDTOs);
    response.setRecords(Long.toString(npiEntitys.getTotalElements()));
    response.setTotal(Integer.toString(npiEntitys.getTotalPages()));
    response.setPage(Integer.toString(npiEntitys.getNumber() + 1));
    return response;
  }

  /**
   * Gets a single NPI Entity record.
   *
   * @param npiEntity the npi Entity
   * @return the npi Entity dto
   */
  @RequestMapping(value = "/get", produces = "application/json")
  public @ResponseBody NpiEntityDTO get(@RequestBody NpiEntityDTO npiEntity) {
    LOGGER.trace("Generating JSON for single Job Execution: " + npiEntity);
    return NpiEntityMapper.map(npiEntityService.findOne(npiEntity.getRealId()));
  }

  /**
   * Creates a new NPI Entity record and persist it to the DB.
   *
   * @param id a composite id string
   * @param entityType the entity type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @return the status response
   */
  @RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
  public @ResponseBody StatusResponse create(@RequestParam String id,
      @RequestParam String entityType, @RequestParam String entityCode,
      @RequestParam String npiNum, @RequestParam Date effDate, @RequestParam Date expDate) {

    Object[] loggerObjects = new Object[] {
      entityType,
      entityCode,
      npiNum,
      effDate,
      expDate };
    LOGGER.debug("Creating new NPI Entity record. " + "EntityType:{} EntityCode:{}"
        + "NpiNum:{} EffDate:{} ExpDate:{} TransmitInd:{}", loggerObjects);

    NpiEntityId nftId = new NpiEntityId(entityType, entityCode, npiNum, effDate);
    NpiEntity newNpiEntity = new NpiEntity(nftId, expDate);
    NpiEntity result = npiEntityService.save(newNpiEntity);
    return new StatusResponse(result == null ? false : true);
  }

  /**
   * Update an existing NPI Entity record in the DB.
   *
   * @param id a composite id string
   * @param entityType the entity type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @return the status response
   */
  @RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
  public @ResponseBody StatusResponse update(@RequestParam String id,
      @RequestParam String entityType, @RequestParam String entityCode,
      @RequestParam String npiNum, @RequestParam Date effDate, @RequestParam Date expDate) {

    Object[] loggerObjects = new Object[] {
      entityType,
      entityCode,
      npiNum,
      effDate,
      expDate };
    LOGGER.debug("Updating NPI Entity record. " + "EntityType:{} EntityCode:{} "
        + "NpiNum:{} EffDate:{} ExpDate:{}", loggerObjects);

    NpiEntityId nftId = new NpiEntityId(entityType, entityCode, npiNum, effDate);
    NpiEntity newNpiEntity = new NpiEntity(nftId, expDate);
    NpiEntity result = npiEntityService.save(newNpiEntity);
    return new StatusResponse(result == null ? false : true);
  }

  /**
   * Delete an existing NPI Entity record in the DB.
   *
   * @param id a composite id string
   * @return the status response
   */
  @RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
  public @ResponseBody StatusResponse delete(@RequestParam String id) {

    LOGGER.debug("Deleting NPI Entity record with id: {}", new Object[] { id });

    npiEntityService.delete(new NpiEntityDTO().getRealId(id));
    return new StatusResponse(true);
  }
}
