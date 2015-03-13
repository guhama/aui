package com.premierinc.informatics.qmr.adminui.batch.mapper;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

public class NpiEntityLineMapperTest {
  
  @Test
  public void testMapLineSuccess() throws Exception {
	  NpiEntityLineMapper mapper = new NpiEntityLineMapper();
    
    SimpleDateFormat sdf = new SimpleDateFormat(TestConstants.DATE_FORMAT);
    Date effDate = sdf.parse(TestConstants.JAN_01_2014);
    Date expDate = sdf.parse(TestConstants.DEC_31_2014);
    
    String[] lineItems = new String[] {
      TestConstants.TEST_ENTITY_TYPE_TIN,
      TestConstants.TEST_ENTITY_CODE,
      TestConstants.TEST_NPI,
      "01012014",
      "12312014"
    };
    Collection<String> lineItemsCol = new ArrayList<String>(Arrays.asList(lineItems));
    
    String line = StringUtils.join(lineItemsCol, "|");
    
    NpiEntity nft = mapper.mapLine(line, 1);
    assertEquals(nft.getId().getEntityType(), TestConstants.TEST_ENTITY_TYPE_TIN);
    assertEquals(nft.getId().getEntityCode(), TestConstants.TEST_ENTITY_CODE);
    assertEquals(nft.getId().getNpiNum(), TestConstants.TEST_NPI);
    assertEquals(nft.getId().getEffDate(), effDate);
    assertEquals(nft.getExpDate(), expDate);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testMapLineInvalidDate() throws Exception {
	  NpiEntityLineMapper mapper = new NpiEntityLineMapper();
	  
	  String[] lineItems = new String[] {
		      TestConstants.TEST_ENTITY_TYPE_TIN,
		      TestConstants.TEST_ENTITY_CODE,
		      TestConstants.TEST_NPI,
		      "20140101", //REVERSED DATE STRING
		      "12312014"
		    };
    
    Collection<String> lineItemsCol = new ArrayList<String>(Arrays.asList(lineItems));
    
    String line = StringUtils.join(lineItemsCol, "|");
    
    mapper.mapLine(line, 1);
  }
  
 

}
