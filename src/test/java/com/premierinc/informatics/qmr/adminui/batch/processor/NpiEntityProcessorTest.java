package com.premierinc.informatics.qmr.adminui.batch.processor;

import static org.junit.Assert.assertSame;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.batch.item.validator.ValidationException;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

public class NpiEntityProcessorTest {

  @Test
  public void testProcessNpiEntitySuccess() throws Exception {
    NpiEntityProcessor processor = new NpiEntityProcessor();

    SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.DATE_FORMAT);
    Date effDate = sdf.parse(TestConstants.JAN_01_2014);
    Date expDate = sdf.parse(TestConstants.DEC_31_2014);

    NpiEntityId id =
        new NpiEntityId(TestConstants.TEST_ENTITY_TYPE_TIN,TestConstants.TEST_ENTITY_CODE,
            TestConstants.TEST_NPI, effDate);
    NpiEntity neBefore = new NpiEntity(id, expDate);
    NpiEntity neAfter = processor.process(neBefore);
    assertSame(neBefore, neAfter);
  }
  
  @Test(expected=ValidationException.class)
  public void testProcessNpiEntityFailure() throws Exception {
    NpiEntityProcessor processor = new NpiEntityProcessor();

    SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.DATE_FORMAT);
    Date effDate = sdf.parse(TestConstants.JAN_01_2014);
    Date expDate = sdf.parse(TestConstants.DEC_31_2014);

    NpiEntityId id =
        new NpiEntityId(TestConstants.TEST_ENTITY_TYPE,TestConstants.TEST_ENTITY_CODE,
            TestConstants.TEST_NPI, effDate);
    NpiEntity neBefore = new NpiEntity(id, expDate);
    NpiEntity neAfter = processor.process(neBefore);
  }

}
