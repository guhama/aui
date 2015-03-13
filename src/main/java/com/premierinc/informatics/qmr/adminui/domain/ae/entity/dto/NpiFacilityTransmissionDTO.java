package com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.util.Constants;

/**
 * The Class NpiFacilityTransmissionDTO.
 *
 * @author crowland
 */
public class NpiFacilityTransmissionDTO implements Serializable {

  private static final long serialVersionUID = -8199950039864364366L;

  private static final String[] COLUMN_MAPPING = new String[] {
    "clientId",
    "facilityId",
    "initiativeId",
    "measureSetId",
    "msMeasureId",
    "entityType",
    "entityCode",
    "npiNum",
    "effDate" };

  private String id;

  private String clientId;

  private String facilityId;

  private Long initiativeId;

  private Long measureSetId;

  private Long msMeasureId;

  private String entityType;

  private String entityCode;

  private String npiNum;

  private String effDate;

  private String expDate;

  private Integer transmitInd;

  /**
   * Instantiates a new npi facility transmission dto.
   */
  public NpiFacilityTransmissionDTO() {}

  /**
   * Instantiates a new npi facility transmission dto.
   *
   * @param nft the NPI Facility Transmission entity
   */
  public NpiFacilityTransmissionDTO(NpiFacilityTransmission nft) {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    this.clientId = nft.getId().getClientId();
    this.facilityId = nft.getId().getFacilityId();
    this.initiativeId = nft.getId().getInitiativeId();
    this.measureSetId = nft.getId().getMeasureSetId();
    this.msMeasureId = nft.getId().getMsMeasureId();
    this.entityType = nft.getId().getEntityType();
    this.entityCode = nft.getId().getEntityCode();
    this.npiNum = nft.getId().getNpiNum();
    this.effDate = sdf.format(nft.getId().getEffDate());
    this.expDate = sdf.format(nft.getExpDate());
    this.transmitInd = nft.getTransmitInd();
    this.id = createId();
  }

  /**
   * Creates the composite ID string.
   *
   * @return the string
   */
  private String createId() {
    StringBuilder sb = new StringBuilder();
    sb.append(clientId).append(Constants.DELIMITER);
    sb.append(facilityId).append(Constants.DELIMITER);
    sb.append(initiativeId).append(Constants.DELIMITER);
    sb.append(measureSetId).append(Constants.DELIMITER);
    sb.append(msMeasureId).append(Constants.DELIMITER);
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
  public NpiFacilityTransmissionId getRealId() {
    try {
      DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
      tokenizer.setDelimiter(Constants.DELIMITER);
      tokenizer.setNames(COLUMN_MAPPING);
      FieldSet fs = tokenizer.tokenize(id);

      NpiFacilityTransmissionId nftId = new NpiFacilityTransmissionId();

      nftId.setClientId(fs.readString("clientId"));
      nftId.setFacilityId(fs.readString("facilityId"));
      nftId.setInitiativeId(fs.readLong("initiativeId"));
      nftId.setMeasureSetId(fs.readLong("measureSetId"));
      nftId.setMsMeasureId(fs.readLong("msMeasureId"));
      nftId.setEntityType(fs.readString("entityType"));
      nftId.setEntityCode(fs.readString("entityCode"));
      nftId.setNpiNum(fs.readString("npiNum"));
      nftId.setEffDate(fs.readDate("effDate", "yyyy-MM-dd"));
      return nftId;
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
  public NpiFacilityTransmissionId getRealId(String id) {
    try {
      DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
      tokenizer.setDelimiter(Constants.DELIMITER);
      tokenizer.setNames(COLUMN_MAPPING);
      FieldSet fs = tokenizer.tokenize(id);

      NpiFacilityTransmissionId nftId = new NpiFacilityTransmissionId();

      nftId.setClientId(fs.readString("clientId"));
      nftId.setFacilityId(fs.readString("facilityId"));
      nftId.setInitiativeId(fs.readLong("initiativeId"));
      nftId.setMeasureSetId(fs.readLong("measureSetId"));
      nftId.setMsMeasureId(fs.readLong("msMeasureId"));
      nftId.setEntityType(fs.readString("entityType"));
      nftId.setEntityCode(fs.readString("entityCode"));
      nftId.setNpiNum(fs.readString("npiNum"));
      nftId.setEffDate(fs.readDate("effDate", "yyyy-MM-dd"));
      return nftId;
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
   * Gets the client id.
   *
   * @return the client id
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * Sets the client id.
   *
   * @param clientId the new client id
   */
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  /**
   * Gets the facility id.
   *
   * @return the facility id
   */
  public String getFacilityId() {
    return facilityId;
  }

  /**
   * Sets the facility id.
   *
   * @param facilityId the new facility id
   */
  public void setFacilityId(String facilityId) {
    this.facilityId = facilityId;
  }

  /**
   * Gets the initiative id.
   *
   * @return the initiative id
   */
  public Long getInitiativeId() {
    return initiativeId;
  }

  /**
   * Sets the initiative id.
   *
   * @param initiativeId the new initiative id
   */
  public void setInitiativeId(Long initiativeId) {
    this.initiativeId = initiativeId;
  }

  /**
   * Gets the measure set id.
   *
   * @return the measure set id
   */
  public Long getMeasureSetId() {
    return measureSetId;
  }

  /**
   * Sets the measure set id.
   *
   * @param measureSetId the new measure set id
   */
  public void setMeasureSetId(Long measureSetId) {
    this.measureSetId = measureSetId;
  }

  /**
   * Gets the ms measure id.
   *
   * @return the ms measure id
   */
  public Long getMsMeasureId() {
    return msMeasureId;
  }

  /**
   * Sets the ms measure id.
   *
   * @param msMeasureId the new ms measure id
   */
  public void setMsMeasureId(Long msMeasureId) {
    this.msMeasureId = msMeasureId;
  }



  /**
   * @return the entityType
   */
  public String getEntityType() {
    return entityType;
  }

  /**
   * @param entityType the entityType to set
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
   * Sets the Entity Code.
   *
   * @param entityCode the new Entity Code
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
   * Gets the transmit ind.
   *
   * @return the transmit ind
   */
  public Integer getTransmitInd() {
    return transmitInd;
  }

  /**
   * Sets the transmit ind.
   *
   * @param transmitInd the new transmit ind
   */
  public void setTransmitInd(Integer transmitInd) {
    this.transmitInd = transmitInd;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "NpiFacilityTransmissionDTO [id=" + id + ", clientId=" + clientId + ", facilityId="
        + facilityId + ", initiativeId=" + initiativeId + ", measureSetId=" + measureSetId
        + ", msMeasureId=" + msMeasureId + ", entityType=" + entityType + ", entityCode="
        + entityCode + ", npiNum=" + npiNum + ", effDate=" + effDate + ", expDate=" + expDate
        + ", transmitInd=" + transmitInd + "]";
  }
}
