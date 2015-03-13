package com.premierinc.informatics.qmr.adminui.batch.mapper;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;

/**
 * The Class NpiEntityLineMapper. Takes one line from a file and maps it to a NPI_ENTITY Table
 * 
 * @author crowland
 */
public class NpiEntityLineMapper implements LineMapper<NpiEntity> {

  private NpiEntity npi;

  private NpiEntityId id;

  /** The Constant COLUMN_MAPPING. Ordered array of line parameter names. */
  private static final String[] COLUMN_MAPPING = new String[] {
    "entityType",
    "entityCode",
    "npiNum",
    "effDate",
    "expDate" };

  /**
   * Map Line. Takes a file line, tokenizes it and maps it to a NPI Facility Entity entity.
   *
   * @return the NPI Facility Entity entity
   */
  public NpiEntity mapLine(String line, int lineNumber) throws Exception {

    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setDelimiter("|");
    tokenizer.setNames(COLUMN_MAPPING);
    FieldSet fs = tokenizer.tokenize(line);

    npi = new NpiEntity();
    id = new NpiEntityId();

    id.setEntityType(fs.readString("entityType"));
    id.setEntityCode(fs.readString("entityCode"));
    id.setNpiNum(fs.readString("npiNum"));
    id.setEffDate(fs.readDate("effDate", "MMddyyyy"));
    npi.setExpDate(fs.readDate("expDate", "MMddyyyy"));
    npi.setId(id);

    return npi;
  }

}
