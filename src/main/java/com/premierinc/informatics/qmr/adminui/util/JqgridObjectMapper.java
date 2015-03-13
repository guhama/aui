package com.premierinc.informatics.qmr.adminui.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class JqgridObjectMapper.
 */
public class JqgridObjectMapper {

  /**
   * Map.
   *
   * @param jsonString the json string
   * @return the jqgrid filter
   */
  public static JqgridFilter map(String jsonString) {

    if (jsonString != null) {
      ObjectMapper mapper = new ObjectMapper();

      try {
        return mapper.readValue(jsonString, JqgridFilter.class);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    return null;
  }
}
