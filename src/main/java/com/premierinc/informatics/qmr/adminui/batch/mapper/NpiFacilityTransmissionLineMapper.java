package com.premierinc.informatics.qmr.adminui.batch.mapper;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;

/**
 * The Class NpiFacilityTransmissionLineMapper. Takes one line from a file and maps it to a NPI
 * Facility Transmission entity.
 * 
 * @author crowland
 */
public class NpiFacilityTransmissionLineMapper implements LineMapper<NpiFacilityTransmission> {

  private NpiFacilityTransmission npi;

  private NpiFacilityTransmissionId id;

  /** The Constant COLUMN_MAPPING. Ordered array of line parameter names. */
  private static final String[] COLUMN_MAPPING = new String[] {
    "clientId",
    "facilityId",
    "initiativeId",
    "measureSetId",
    "msMeasureId",
    "entityType",
    "entityCode",
    "npiNum",
    "effDate",
    "expDate",
    "transmitInd" };

  /**
   * Map Line. Takes a file line, tokenizes it and maps it to a NPI Facility Transmission entity.
   *
   * @return the NPI Facility Transmission entity
   */
  public NpiFacilityTransmission mapLine(String line, int lineNumber) throws Exception {

    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setDelimiter("|");
    tokenizer.setNames(COLUMN_MAPPING);
    FieldSet fs = tokenizer.tokenize(line);

    npi = new NpiFacilityTransmission();
    id = new NpiFacilityTransmissionId();

    id.setClientId(fs.readString("clientId"));
    id.setFacilityId(fs.readString("facilityId"));
    id.setInitiativeId(fs.readLong("initiativeId"));
    id.setMeasureSetId(fs.readLong("measureSetId"));
    id.setMsMeasureId(fs.readLong("msMeasureId"));
    id.setEntityType(fs.readString("entityType"));
    id.setEntityCode(fs.readString("entityCode"));
    id.setNpiNum(fs.readString("npiNum"));
    id.setEffDate(fs.readDate("effDate", "MMddyyyy"));
    npi.setExpDate(fs.readDate("expDate", "MMddyyyy"));
    npi.setTransmitInd(fs.readInt("transmitInd"));
    npi.setId(id);

    return npi;
  }

}
