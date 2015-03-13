package com.premierinc.informatics.qmr.adminui.domain.ae.entity.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;

/**
 * The Class NpiEntityMapper.
 *
 * @author crowland
 */
public class NpiEntityMapper {

  /**
   * Map. Map a NPI Facility Transmission entity to a DTO.
   *
   * @param record the record
   * @return the npi facility transmission dto
   */
  public static NpiEntityDTO map(NpiEntity record) {
    NpiEntityDTO dto = new NpiEntityDTO(record);
    return dto;
  }

  /**
   * Map. Map a list of NPI Facility Transmission entities to a DTO.
   *
   * @param records the records
   * @return the list
   */
  public static List<NpiEntityDTO> map(Page<NpiEntity> records) {
    List<NpiEntityDTO> dtos = new ArrayList<NpiEntityDTO>();
    for (NpiEntity user : records) {
      dtos.add(map(user));
    }
    return dtos;
  }
}
