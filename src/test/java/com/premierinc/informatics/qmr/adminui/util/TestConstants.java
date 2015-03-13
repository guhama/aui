package com.premierinc.informatics.qmr.adminui.util;

import org.springframework.http.MediaType;

public class TestConstants {

  public static final MediaType APPLICATION_JSON = new MediaType(
      MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
  public static final String JAN_01_2014 = "01/01/2014";
  public static final String DEC_31_2014 = "12/31/2014";
  public static final String DATE_FORMAT = "MM/dd/yyyy";
  public static final String TEST_CLIENT = "TestClient";
  public static final String TEST_FACILITY = "TestFacility";
  public static final String TEST_ENTITY_TYPE = "TestEntityType";
  public static final String TEST_ENTITY_TYPE_TIN="TIN";
  public static final String TEST_ENTITY_CODE = "TestEntityCode";
  public static final String TEST_ENTITY_CODE_WITH_SPCL_CHARACTERS = "#TestEntityCode";
  public static final String TEST_NPI = "TestNPI";
  public static final String TEST_USER = "TestUser";

}
