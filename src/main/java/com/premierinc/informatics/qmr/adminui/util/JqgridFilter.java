package com.premierinc.informatics.qmr.adminui.util;

import java.util.ArrayList;

/**
 * The Class JqgridFilter.
 */
public class JqgridFilter {

  private String source;
  private String groupOp;
  private ArrayList<Rule> rules;

  /**
   * Instantiates a new jqgrid filter.
   */
  public JqgridFilter() {
    super();
  }

  /**
   * Instantiates a new jqgrid filter.
   *
   * @param source the source
   */
  public JqgridFilter(String source) {
    super();
    this.source = source;
  }

  /**
   * Gets the source.
   *
   * @return the source
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets the source.
   *
   * @param source the new source
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * Gets the group op.
   *
   * @return the group op
   */
  public String getGroupOp() {
    return groupOp;
  }

  /**
   * Sets the group op.
   *
   * @param groupOp the new group op
   */
  public void setGroupOp(String groupOp) {
    this.groupOp = groupOp;
  }

  /**
   * Gets the rules.
   *
   * @return the rules
   */
  public ArrayList<Rule> getRules() {
    return rules;
  }

  /**
   * Sets the rules.
   *
   * @param rules the new rules
   */
  public void setRules(ArrayList<Rule> rules) {
    this.rules = rules;
  }

  /**
   * Inner class containing field rules.
   *
   * @author crowland
   */
  public static class Rule {
    private String junction;
    private String field;
    private String op;
    private String data;

    /**
     * Instantiates a new rule.
     */
    public Rule() {}

    /**
     * Instantiates a new rule.
     *
     * @param junction the junction
     * @param field the field
     * @param op the op
     * @param data the data
     */
    public Rule(String junction, String field, String op, String data) {
      super();
      this.junction = junction;
      this.field = field;
      this.op = op;
      this.data = data;
    }

    /**
     * Gets the junction.
     *
     * @return the junction
     */
    public String getJunction() {
      return junction;
    }

    /**
     * Sets the junction.
     *
     * @param junction the new junction
     */
    public void setJunction(String junction) {
      this.junction = junction;
    }

    /**
     * Gets the field.
     *
     * @return the field
     */
    public String getField() {
      return field;
    }

    /**
     * Sets the field.
     *
     * @param field the new field
     */
    public void setField(String field) {
      this.field = field;
    }

    /**
     * Gets the op.
     *
     * @return the op
     */
    public String getOp() {
      return op;
    }

    /**
     * Sets the op.
     *
     * @param op the new op
     */
    public void setOp(String op) {
      this.op = op;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public String getData() {
      return data;
    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(String data) {
      this.data = data;
    }
  }


}
