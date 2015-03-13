package com.premierinc.informatics.qmr.adminui.batch.processor;

import static org.junit.Assert.assertSame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.batch.item.validator.ValidationException;

import com.premierinc.informatics.qmr.adminui.batch.mapper.NpiFacilityTransmissionLineMapper;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

public class NpiFacilityTransmissionProcessorTest {

  @Test
  public void testProcessEntitySuccess() throws Exception {
    NpiFacilityTransmissionProcessor processor = new NpiFacilityTransmissionProcessor();

    SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.DATE_FORMAT);
    Date effDate = sdf.parse(TestConstants.JAN_01_2014);
    Date expDate = sdf.parse(TestConstants.DEC_31_2014);

    NpiFacilityTransmissionId id =
        new NpiFacilityTransmissionId(TestConstants.TEST_CLIENT, TestConstants.TEST_FACILITY,
            Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), TestConstants.TEST_ENTITY_TYPE,
            TestConstants.TEST_ENTITY_CODE, TestConstants.TEST_NPI, effDate);
    NpiFacilityTransmission nftBefore = new NpiFacilityTransmission(id, expDate, Integer.valueOf(1));

    NpiFacilityTransmission nftAfter = processor.process(nftBefore);
    assertSame(nftBefore, nftAfter);
  }

  @Test(expected = ValidationException.class)
  public void testProcessEntityInvalidDate() throws Exception {
    NpiFacilityTransmissionProcessor processor = new NpiFacilityTransmissionProcessor();

    SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.DATE_FORMAT);
    Date effDate = sdf.parse(TestConstants.JAN_01_2014);
    Date expDate = sdf.parse("03/30/2014"); //QUARTER END DATE (Should be year end date)

    NpiFacilityTransmissionId id =
        new NpiFacilityTransmissionId(TestConstants.TEST_CLIENT, TestConstants.TEST_FACILITY,
            Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), TestConstants.TEST_ENTITY_TYPE,
            TestConstants.TEST_ENTITY_CODE, TestConstants.TEST_NPI, effDate);
    NpiFacilityTransmission nftBefore = new NpiFacilityTransmission(id, expDate, Integer.valueOf(1));

    processor.process(nftBefore);
  }

  @Test(expected = ValidationException.class)
  public void testProcessEntityInvalidTransmitInd() throws Exception {
    NpiFacilityTransmissionProcessor processor = new NpiFacilityTransmissionProcessor();

    SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.DATE_FORMAT);
    Date effDate = sdf.parse(TestConstants.JAN_01_2014);
    Date expDate = sdf.parse(TestConstants.DEC_31_2014);

    NpiFacilityTransmissionId id =
        new NpiFacilityTransmissionId(TestConstants.TEST_CLIENT, TestConstants.TEST_FACILITY,
            Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), TestConstants.TEST_ENTITY_TYPE,
            TestConstants.TEST_ENTITY_CODE, TestConstants.TEST_NPI, effDate);
    NpiFacilityTransmission nftBefore = new NpiFacilityTransmission(id, expDate, Integer.valueOf(3)); //3 IS NOT A VALID TRANSMIT IND

    processor.process(nftBefore);
  }

}
