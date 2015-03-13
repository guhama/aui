package com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;

/**
 * The Class NpiFacilityTransmissionMapper.
 *
 * @author crowland
 */
public class NpiFacilityTransmissionMapper {

  /**
   * Map. Map a NPI Facility Transmission entity to a DTO.
   *
   * @param record the record
   * @return the npi facility transmission dto
   */
  public static NpiFacilityTransmissionDTO map(NpiFacilityTransmission record) {
    NpiFacilityTransmissionDTO dto = new NpiFacilityTransmissionDTO(record);
    return dto;
  }

  /**
   * Map. Map a list of NPI Facility Transmission entities to a DTO.
   *
   * @param records the records
   * @return the list
   */
  public static List<NpiFacilityTransmissionDTO> map(Page<NpiFacilityTransmission> records) {
    List<NpiFacilityTransmissionDTO> dtos = new ArrayList<NpiFacilityTransmissionDTO>();
    for (NpiFacilityTransmission user : records) {
      dtos.add(map(user));
    }
    return dtos;
  }
}
