package com.premierinc.informatics.qmr.adminui.batch.tasklet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.premierinc.informatics.qmr.adminui.domain.ae.PackageHandlerAE;

/**
 * The Class NpiFacilityTransmissionTasklet. This tasklet will take a Job Execution Id and perform a
 * merge from the temporary table to the real one.
 *
 * @author crowland
 */
public class NpiFacilityTransmissionTasklet implements Tasklet {

  Logger logger = LoggerFactory.getLogger(NpiFacilityTransmissionTasklet.class);

  @Autowired
  PackageHandlerAE packageHandler;

  /**
   * Execute tasklet. Takes a Job Execution Id and calles a PL/SQL procedure to merge the temporary
   * date into the actual table.
   *
   * @param contribution the contribution
   * @param chunkContext the chunk context
   * @return the status of the tasklet
   * @throws Exception the exception
   */
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    final Long id = chunkContext.getStepContext().getStepExecution().getJobExecution().getId();
    Long start = System.currentTimeMillis();

    if (logger.isDebugEnabled()) {
      logger.debug("Starting merge for Job Execution Id of: " + id);
    }

    Map<String, Object> results = packageHandler.mergeNpiFacilityTransmission(id);
    String resultMessage = (String) results.get("P_RETURN");

    if (!"SUCCESS".equals(resultMessage)) {
      throw new Exception(resultMessage);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("Merge for Job Execution Id " + id + " has returned. Call took "
          + (System.currentTimeMillis() - start) + "ms.");
    }

    return RepeatStatus.FINISHED;
  }

}
