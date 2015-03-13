package com.premierinc.informatics.qmr.adminui.batch.writer;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntityId;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;
import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmissionId;
import com.premierinc.informatics.qmr.adminui.util.TestConstants;

@RunWith(MockitoJUnitRunner.class)
public class NpiEntityWriterTest {

  private static final Date JAN_01_2014;
  private static final Date DEC_31_2014;

  static {
    JAN_01_2014 = new GregorianCalendar(2014, 0, 1).getTime();
    DEC_31_2014 = new GregorianCalendar(2014, 11, 31).getTime();
  }

  final private Long jobExecutionId = Long.valueOf(12345);

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private NpiEntityWriter npiEntityWriter;

  @Before
  public void setup() {
    // Process mock annotations
    MockitoAnnotations.initMocks(this);

    JobExecution jobExecution = new JobExecution(jobExecutionId);
    StepExecution stepExecution = new StepExecution("TestExecution", jobExecution);
    npiEntityWriter.saveStepExecution(stepExecution);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testMergeSuccess() throws Exception {

	  NpiEntityId id =
			  new NpiEntityId(TestConstants.TEST_ENTITY_TYPE_TIN,TestConstants.TEST_ENTITY_CODE,
			            TestConstants.TEST_NPI, JAN_01_2014);
	  NpiEntity npi = new NpiEntity(id, DEC_31_2014);

    List<NpiEntity> list = new ArrayList<NpiEntity>(1);
    list.add(npi);

    npiEntityWriter.write(list);

    Mockito.verify(jdbcTemplate, Mockito.times(1)).execute(Mockito.any(String.class),
        Mockito.any(PreparedStatementCallback.class));
    Mockito.verifyNoMoreInteractions(jdbcTemplate);
  }

  @SuppressWarnings("unchecked")
  @Test(expected = DataAccessException.class)
  public void testMergeFailure() throws Exception {

    when(
        jdbcTemplate.execute(Mockito.any(String.class),
            Mockito.any(PreparedStatementCallback.class))).thenThrow(
        TransientDataAccessResourceException.class);

    NpiEntityId id =
			  new NpiEntityId(TestConstants.TEST_ENTITY_TYPE_TIN,TestConstants.TEST_ENTITY_CODE,
			            TestConstants.TEST_NPI, JAN_01_2014);
	  NpiEntity npi = new NpiEntity(id, DEC_31_2014);

    List<NpiEntity> list = new ArrayList<NpiEntity>(1);
    list.add(npi);

    npiEntityWriter.write(list);
  }

  @SuppressWarnings("unchecked")
  @Test(expected = Exception.class)
  public void testEmptyList() throws Exception {

    when(
        jdbcTemplate.execute(Mockito.any(String.class),
            Mockito.any(PreparedStatementCallback.class))).thenThrow(Exception.class);

    List<NpiEntity> list = new ArrayList<NpiEntity>(1);

    npiEntityWriter.write(list);
  }
}
