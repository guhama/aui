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

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto.NpiFacilityTransmissionDTO;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto.NpiFacilityTransmissionMapper;
import com.premierinc.informatics.qmr.adminui.service.NpiFacilityTransmissionService;
import com.premierinc.informatics.qmr.adminui.util.JqgridFilter;
import com.premierinc.informatics.qmr.adminui.util.JqgridObjectMapper;
import com.premierinc.informatics.qmr.adminui.util.JqgridResponse;
import com.premierinc.informatics.qmr.adminui.util.ResponseMessage;
import com.premierinc.informatics.qmr.adminui.util.StatusResponse;

/**
 * The Class NpiFacilityTranmissionController.
 *
 * @author crowland
 */
@Controller
@RequestMapping(value = "/npi-facility-transmission")
@SuppressWarnings("PMD.UnusedPrivateMethod")
public class NpiFacilityTranmissionController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(NpiFacilityTranmissionController.class);

  @Autowired
  @Qualifier("jobLauncher")
  private JobLauncher jobLauncher;

  @Autowired
  @Qualifier("loadNpiFacilityTransmission")
  private Job job;

  @Autowired
  private NpiFacilityTransmissionService npiFacilityTransmissionService;

  /**
   * Npi_measure_tranmission_content. Serves file upload page for NPI Facility Transmission.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/file-upload", method = RequestMethod.GET)
  public String npi_measure_tranmission_content(Model model) {
    LOGGER.trace("Serving /file-uploads/npi-facility-tranmission.");
    return "/file-uploads/npi-facility-tranmission";
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

    LOGGER.trace("Processing file upload request for NPI Facility Transmission.");

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
              LOGGER.debug("Starting NPI Facility Transmission batch job for file: "
                  + tmpFile.getAbsolutePath());
              JobExecution jobExecution =
                  jobLauncher.run(
                      job,
                      new JobParametersBuilder().addString("input.file",
                          "file:" + tmpFile.getAbsolutePath()).toJobParameters());
              LOGGER.debug("NPI Facility Transmission batch job with Execution Id "
                  + jobExecution.getJobId() + " finished with status: "
                  + jobExecution.getExitStatus());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
        thread.start();

        LOGGER.trace("NPI Facility Transmission file upload request complete. "
            + "Batch job started with no issues.");
        return new ResponseMessage("SUCCESS",
            "Your file was uploaded successfully and the job has started.");
      } catch (Exception e) {
        e.printStackTrace();
        LOGGER.error("NPI Facility Transmission batch job failed due to unknown error: "
            + e.getMessage());
        return new ResponseMessage("ERROR",
            "Your file was NOT uploaded successfully. Unknown error occurred.");
      }
    } else {
      LOGGER.error("NPI Facility Transmission batch job failed because uploaded file was empty.");
      return new ResponseMessage("ERROR", "Your file was uploaded successfully, but it was empty. "
          + "Please upload a file with stuffs in it. :)");
    }
  }

  /**
   * Npi_facility_tranmission_content. Serves table interface for the NPI Facility Transmission
   * table.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/table-interface", method = RequestMethod.GET)
  public String npi_facility_tranmission_content(Model model) {
    LOGGER.trace("Serving /table-interfaces/npi-facility-tranmission.");
    return "/table-interfaces/npi-facility-tranmission";
  }

  /**
   * Records. Returns a page of records from the NPI Facility Transmission table in JSON format. Can
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
  public @ResponseBody JqgridResponse<NpiFacilityTransmissionDTO> records(
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
    LOGGER.trace("Generating JSON for NPI Facility Transmission table. "
        + "Search:{} Filters:{} Page:{} Rows:{} Sidx:{} Sord:{}", loggerObjects);

    // Default sort to client id
    if ("id".equals(sidx)) {
      sidx = "clientId";
    }

    // Prefix id columns so hibernate is happy
    if ("clientId".equals(sidx) || "facilityId".equals(sidx) || "initiativeId".equals(sidx)
        || "measureSetId".equals(sidx) || "msMeasureId".equals(sidx) 
        || "entityType".equals(sidx)   || "entityCode".equals(sidx) 
        || "npiNum".equals(sidx) || "effDate".equals(sidx)) {
      sidx = "id." + sidx;
    }

    Direction sortOrder = "asc".equalsIgnoreCase(sord) ? Sort.Direction.ASC : Sort.Direction.DESC;

    Pageable pageRequest = new PageRequest(page - 1, rows, sortOrder, sidx);

    if (search == true) {
      return getFilteredRecords(filters, pageRequest);
    }

    Page<NpiFacilityTransmission> npiFacilityTransmissions =
        npiFacilityTransmissionService.findAll(pageRequest);
    List<NpiFacilityTransmissionDTO> npiFacilityTransmissionDTOs =
        NpiFacilityTransmissionMapper.map(npiFacilityTransmissions);

    JqgridResponse<NpiFacilityTransmissionDTO> response =
        new JqgridResponse<NpiFacilityTransmissionDTO>();
    response.setRows(npiFacilityTransmissionDTOs);
    response.setRecords(Long.toString(npiFacilityTransmissions.getTotalElements()));
    response.setTotal(Integer.toString(npiFacilityTransmissions.getTotalPages()));
    response.setPage(Integer.toString(npiFacilityTransmissions.getNumber() + 1));

    return response;
  }

  /**
   * Helper method for returning filtered records.
   *
   * @param filters the filters
   * @param pageRequest the page request
   * @return the filtered records
   */
  private JqgridResponse<NpiFacilityTransmissionDTO> getFilteredRecords(String filters,
      Pageable pageRequest) {

    LOGGER.trace("Filtering query based on user provided restrictions: " + filters);

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String clientId = null;
    String facilityId = null;
    String entityType = null;
    String entityCode = null;
    String npiNum = null;
    Long initiativeId = null;
    Long measureSetId = null;
    Long msMeasureId = null;
    Date effDate = null;
    Date expDate = null;
    Integer transmitInd = null;

    JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
    for (JqgridFilter.Rule rule : jqgridFilter.getRules()) {
      if (rule.getField().equals("clientId")) {
        clientId = rule.getData();
      } else if (rule.getField().equals("facilityId")) {
        facilityId = rule.getData();
      } else if (rule.getField().equals("initiativeId")) {
        initiativeId = Long.valueOf(rule.getData());
      } else if (rule.getField().equals("measureSetId")) {
        measureSetId = Long.valueOf(rule.getData());
      } else if (rule.getField().equals("msMeasureId")) {
        msMeasureId = Long.valueOf(rule.getData());
      } else if (rule.getField().equals("entityType")) {
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
      } else if (rule.getField().equals("transmitInd")) {
        transmitInd = Integer.valueOf(rule.getData());
      }
    }

    Page<NpiFacilityTransmission> npiFacilityTransmissions =
        npiFacilityTransmissionService
            .searchWithAll(clientId, facilityId, initiativeId, measureSetId, msMeasureId, 
             entityType, entityCode, npiNum, effDate, expDate, transmitInd, pageRequest);

    List<NpiFacilityTransmissionDTO> npiFacilityTransmissionDTOs =
        NpiFacilityTransmissionMapper.map(npiFacilityTransmissions);
    JqgridResponse<NpiFacilityTransmissionDTO> response =
        new JqgridResponse<NpiFacilityTransmissionDTO>();
    response.setRows(npiFacilityTransmissionDTOs);
    response.setRecords(Long.toString(npiFacilityTransmissions.getTotalElements()));
    response.setTotal(Integer.toString(npiFacilityTransmissions.getTotalPages()));
    response.setPage(Integer.toString(npiFacilityTransmissions.getNumber() + 1));
    return response;
  }

  /**
   * Gets a single NPI Facility Transmission record.
   *
   * @param npiFacilityTransmission the npi facility transmission
   * @return the npi facility transmission dto
   */
  @RequestMapping(value = "/get", produces = "application/json")
  public @ResponseBody NpiFacilityTransmissionDTO get(
      @RequestBody NpiFacilityTransmissionDTO npiFacilityTransmission) {
    LOGGER.trace("Generating JSON for single Job Execution: " + npiFacilityTransmission);
    return NpiFacilityTransmissionMapper.map(npiFacilityTransmissionService
        .findOne(npiFacilityTransmission.getRealId()));
  }

  /**
   * Creates a new NPI Facility Transmission record and persist it to the DB.
   *
   * @param id a composite id string
   * @param clientId the client id
   * @param facilityId the facility id
   * @param initiativeId the initiative id
   * @param measureSetId the measure set id
   * @param msMeasureId the ms measure id
   * @param entityType the entity Type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @param transmitInd the transmit ind
   * @return the status response
   */
  @RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
  public @ResponseBody StatusResponse create(@RequestParam String id,
      @RequestParam String clientId, @RequestParam String facilityId,
      @RequestParam Long initiativeId, @RequestParam Long measureSetId,
      @RequestParam Long msMeasureId, @RequestParam String entityType,
      @RequestParam String entityCode, @RequestParam String npiNum, 
      @RequestParam Date effDate, @RequestParam Date expDate,
      @RequestParam Integer transmitInd) {

    Object[] loggerObjects = new Object[] {
      clientId,
      facilityId,
      initiativeId,
      measureSetId,
      msMeasureId,
      entityType,
      entityCode,
      npiNum,
      effDate,
      expDate,
      transmitInd };
    LOGGER.debug("Creating new NPI Facility Transmission record. "
        + "ClientId:{} FacilityId:{} InitiativeId:{} "
        + "MeasureSetId:{} MsMeasureId:{} entityType:{} entityCode:{} "
        + "NpiNum:{} EffDate:{} ExpDate:{} TransmitInd:{}", loggerObjects);

    NpiFacilityTransmissionId nftId =
        new NpiFacilityTransmissionId(clientId, facilityId, initiativeId, measureSetId,
            msMeasureId, entityType, entityCode, npiNum, effDate);
    NpiFacilityTransmission newNpiFacilityTransmission =
        new NpiFacilityTransmission(nftId, expDate, transmitInd);
    NpiFacilityTransmission result =
        npiFacilityTransmissionService.save(newNpiFacilityTransmission);
    return new StatusResponse(result == null ? false : true);
  }

  /**
   * Update an existing NPI Facility Transmission record in the DB.
   *
   * @param id a composite id string
   * @param clientId the client id
   * @param facilityId the facility id
   * @param initiativeId the initiative id
   * @param measureSetId the measure set id
   * @param msMeasureId the ms measure id
   * @param entityType the entity Type
   * @param entityCode the entity code
   * @param npiNum the npi num
   * @param effDate the eff date
   * @param expDate the exp date
   * @param transmitInd the transmit ind
   * @return the status response
   */
  @RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
  public @ResponseBody StatusResponse update(@RequestParam String id,
      @RequestParam String clientId, @RequestParam String facilityId,
      @RequestParam Long initiativeId, @RequestParam Long measureSetId,
      @RequestParam Long msMeasureId, @RequestParam String entityType, 
      @RequestParam String entityCode, @RequestParam String npiNum,
      @RequestParam Date effDate, @RequestParam Date expDate,
      @RequestParam Integer transmitInd) {

    Object[] loggerObjects = new Object[] {
      clientId,
      facilityId,
      initiativeId,
      measureSetId,
      msMeasureId,
      entityType,
      entityCode,
      npiNum,
      effDate,
      expDate,
      transmitInd };
    LOGGER.debug("Updating NPI Facility Transmission record. "
        + "ClientId:{} FacilityId:{} InitiativeId:{} "
        + "MeasureSetId:{} MsMeasureId:{} EntityType:{} EntityCode:{} "
        + "NpiNum:{} EffDate:{} ExpDate:{} TransmitInd:{}", loggerObjects);

    NpiFacilityTransmissionId nftId =
        new NpiFacilityTransmissionId(clientId, facilityId, initiativeId, measureSetId,
            msMeasureId, entityType,entityCode, npiNum, effDate);
    NpiFacilityTransmission newNpiFacilityTransmission =
        new NpiFacilityTransmission(nftId, expDate, transmitInd);
    NpiFacilityTransmission result =
        npiFacilityTransmissionService.save(newNpiFacilityTransmission);
    return new StatusResponse(result == null ? false : true);
  }

  /**
   * Delete an existing NPI Facility Transmission record in the DB.
   *
   * @param id a composite id string
   * @return the status response
   */
  @RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
  public @ResponseBody StatusResponse delete(@RequestParam String id) {

    LOGGER.debug("Deleting NPI Facility Transmission record with id: {}", new Object[] { id });

    npiFacilityTransmissionService.delete(new NpiFacilityTransmissionDTO().getRealId(id));
    return new StatusResponse(true);
  }
}
