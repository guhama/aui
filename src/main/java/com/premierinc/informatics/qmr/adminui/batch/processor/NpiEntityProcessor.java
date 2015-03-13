package com.premierinc.informatics.qmr.adminui.batch.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiEntity;


/**
 * The Class NpiEntityProcessor. Performs validations on each created in NPI_ENTITY Table
 *
 * @author crowland
 */
public class NpiEntityProcessor implements ItemProcessor<NpiEntity, NpiEntity> {

  private static final Collection<String> Valid_Entity_Types = new ArrayList<String>(
      Arrays.asList("TIN"));

  /**
   * Instantiates a new npi facility Entity processor.
   */
  public NpiEntityProcessor() {}

  /**
   * Process Line. Takes a NpiEntity and validates it.
   * 
   * Currently checks: Date Boundaries, Valid Transmit Indicators
   * 
   * @param item
   * @return the NpiEntity
   * @throws ValidationException
   */
  @Override
  public NpiEntity process(NpiEntity item) throws ValidationException {

    try {

      String entityType = item.getId().getEntityType();

      if (!Valid_Entity_Types.contains(entityType)) {
        throw new ValidationException(
            "The Entity Type  must be a valid value! Valid Values: [TIN] " + item.toString());
      }


    } catch (Exception e) {
      throw new ValidationException("Unknown validation error occured!", e);
    }
    return item;
  }

}
