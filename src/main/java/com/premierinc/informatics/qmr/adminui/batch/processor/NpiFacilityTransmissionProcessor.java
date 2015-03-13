package com.premierinc.informatics.qmr.adminui.batch.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.premierinc.informatics.qmr.adminui.domain.ae.entity.NpiFacilityTransmission;


/**
 * The Class NpiFacilityTransmissionProcessor. Performs validations on each created NPI Facility
 * Transmission entity
 *
 * @author crowland
 */
public class NpiFacilityTransmissionProcessor implements
    ItemProcessor<NpiFacilityTransmission, NpiFacilityTransmission> {


  private static final Collection<Integer> VALID_TRANSMIT_INDS = new ArrayList<Integer>(
      Arrays.asList(0, 1, 2));

  /**
   * Instantiates a new npi facility transmission processor.
   */
  public NpiFacilityTransmissionProcessor() {}

  /**
   * Process Line. Takes a NPI Facility Transmission entity and validates it.
   * 
   * Currently checks: Date Boundaries, Valid Transmit Indicators
   *
   * @param item the item
   * @return the NPI Facility Transmission entity
   * @throws ValidationException the validation exception
   */
  @Override
  public NpiFacilityTransmission process(NpiFacilityTransmission item) throws ValidationException {

    try {
      DateTime effDate = new DateTime(item.getId().getEffDate());
      DateTime expDate = new DateTime(item.getExpDate());
      if (effDate.getDayOfYear() != 1) {
        throw new ValidationException("The Effective Date must be the first day of the year! "
            + item.toString());
      }
      if (expDate.getDayOfYear() != 365) {
        throw new ValidationException("The Expiration Date must be the last day of the year! "
            + item.toString());
      }
      if (!VALID_TRANSMIT_INDS.contains(item.getTransmitInd())) {
        throw new ValidationException(
            "The Transmit Indicator must be a valid value! Valid Values: [0, 1, 2] "
                + item.toString());
      }
    } catch (Exception e) {
      throw new ValidationException("Unknown validation error occured!", e);
    }
    return item;
  }

}
