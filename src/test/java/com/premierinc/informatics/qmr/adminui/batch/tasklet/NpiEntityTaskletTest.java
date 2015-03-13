package com.premierinc.informatics.qmr.adminui.batch.tasklet;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.premierinc.informatics.qmr.adminui.domain.ae.PackageHandlerAE;

@RunWith(MockitoJUnitRunner.class)
public class NpiEntityTaskletTest {

  @Mock
  private PackageHandlerAE packageHandler;

  @InjectMocks
  private NpiEntityTasklet npiEntityTasklet;

  @Before
  public void setup() {
    // Process mock annotations
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testMergeSuccess() throws Exception {
    
    final Long jobExecutionId = Long.valueOf(12345);
    JobExecution jobExecution = new JobExecution(jobExecutionId);
    StepExecution stepExecution = new StepExecution("TestExecution", jobExecution);
    StepContext stepContext = new StepContext(stepExecution);
    ChunkContext chunkContext = new ChunkContext(stepContext);
    
    Map<String, Object> results = new HashMap<String, Object>(1);
    results.put("P_RETURN", "SUCCESS");
    
    when(packageHandler.mergeNpiEntity(Mockito.any(Long.class))).thenReturn(results);
    
    RepeatStatus repeatStatus = npiEntityTasklet.execute(null, chunkContext);
    
    assertSame(repeatStatus, RepeatStatus.FINISHED);
    Mockito.verify(packageHandler, Mockito.times(1)).mergeNpiEntity(Mockito.any(Long.class));
    Mockito.verifyNoMoreInteractions(packageHandler);
  }
  
  @Test(expected=Exception.class)
  public void testMergeFailure() throws Exception {
    
    final Long jobExecutionId = Long.valueOf(12345);
    JobExecution jobExecution = new JobExecution(jobExecutionId);
    StepExecution stepExecution = new StepExecution(null, jobExecution);
    StepContext stepContext = new StepContext(stepExecution);
    ChunkContext chunkContext = new ChunkContext(stepContext);
    
    //Merge won't return SUCCESS message. Should throw exception with returned String.
    
    npiEntityTasklet.execute(null, chunkContext);
  }

}
