package com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;
import com.premierinc.informatics.qmr.adminui.util.Constants;

/**
 * The Class NpiEntityDTO.
 *
 * @author crowland
 */
public class NpiEntityDTO implements Serializable {


  private static final long serialVersionUID = -6462197295122914104L;

  private static final String[] COLUMN_MAPPING = new String[] {
    "entityType",
    "entityCode",
    "npiNum",
    "effDate" };

  private String id;

  private String entityType;

  private String entityCode;

  private String npiNum;

  private String effDate;

  private String expDate;


  /**
   * Instantiates a new npi facility transmission dto.
   */
  public NpiEntityDTO() {}

  /**
   * Instantiates a new npi facility transmission dto.
   *
   * @param ne the NPI Facility Transmission entity
   */
  public NpiEntityDTO(NpiEntity ne) {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    this.entityType = ne.getId().getEntityType();
    this.entityCode = ne.getId().getEntityCode();
    this.npiNum = ne.getId().getNpiNum();
    this.effDate = sdf.format(ne.getId().getEffDate());
    this.expDate = sdf.format(ne.getExpDate());
    this.id = createId();
  }

  /**
   * Creates the composite ID string.
   *
   * @return the string
   */
  private String createId() {
    StringBuilder sb = new StringBuilder();
    sb.append(entityType).append(Constants.DELIMITER);
    sb.append(entityCode).append(Constants.DELIMITER);
    sb.append(npiNum).append(Constants.DELIMITER);
    sb.append(effDate);
    return sb.toString();
  }

  /**
   * Gets the real id.
   *
   * @return the real id
   */
  public NpiEntityId getRealId() {
    try {
      DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
      tokenizer.setDelimiter(Constants.DELIMITER);
      tokenizer.setNames(COLUMN_MAPPING);
      FieldSet fs = tokenizer.tokenize(id);

      NpiEntityId neId = new NpiEntityId();

      neId.setEntityType(fs.readString("entityType"));
      neId.setEntityCode(fs.readString("entityCode"));
      neId.setNpiNum(fs.readString("npiNum"));
      neId.setEffDate(fs.readDate("effDate", "yyyy-MM-dd"));
      return neId;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets the real id.
   *
   * @param id the composite Id string
   * @return the real id
   */
  public NpiEntityId getRealId(String id) {
    try {
      DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
      tokenizer.setDelimiter(Constants.DELIMITER);
      tokenizer.setNames(COLUMN_MAPPING);
      FieldSet fs = tokenizer.tokenize(id);

      NpiEntityId neId = new NpiEntityId();

      neId.setEntityType(fs.readString("entityType"));
      neId.setEntityCode(fs.readString("entityCode"));
      neId.setNpiNum(fs.readString("npiNum"));
      neId.setEffDate(fs.readDate("effDate", "yyyy-MM-dd"));
      return neId;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(String id) {
    this.id = id;
  }


  /**
   * Gets the entity type.
   * 
   * @return the entity type
   */
  public String getEntityType() {
    return entityType;
  }

  /**
   * Sets the entity type.
   * 
   * @param entityType
   */
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  /**
   * Gets the entity code.
   * 
   * @return the entity code
   */
  public String getEntityCode() {
    return entityCode;
  }

  /**
   * Sets the entity code.
   * 
   * @param entityCode
   */
  public void setEntityCode(String entityCode) {
    this.entityCode = entityCode;
  }


  /**
   * Gets the npi num.
   *
   * @return the npi num
   */
  public String getNpiNum() {
    return npiNum;
  }

  /**
   * Sets the npi num.
   *
   * @param npiNum the new npi num
   */
  public void setNpiNum(String npiNum) {
    this.npiNum = npiNum;
  }

  /**
   * Gets the eff date.
   *
   * @return the eff date
   */
  public String getEffDate() {
    return effDate;
  }

  /**
   * Sets the eff date.
   *
   * @param effDate the new eff date
   */
  public void setEffDate(String effDate) {
    this.effDate = effDate;
  }

  /**
   * Gets the exp date.
   *
   * @return the exp date
   */
  public String getExpDate() {
    return expDate;
  }

  /**
   * Sets the exp date.
   *
   * @param expDate the new exp date
   */
  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "NpiEntityDTO [id=" + id + ", entityType=" + entityType + ", entityCode=" + entityCode
        + ", npiNum=" + npiNum + ", effDate=" + effDate + ", expDate=" + expDate + "]";
  }
}
