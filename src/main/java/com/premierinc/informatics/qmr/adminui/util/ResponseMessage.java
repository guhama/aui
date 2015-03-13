package com.premierinc.informatics.qmr.adminui.util;

/**
 * The Class ResponseMessage.
 *
 * @author crowland
 */
public class ResponseMessage {

  private String messageType;

  private String messageContent;

  /**
   * Instantiates a new response message.
   *
   * @param messageType the message type
   * @param messageContent the message content
   */
  public ResponseMessage(String messageType, String messageContent) {
    super();
    this.messageType = messageType;
    this.messageContent = messageContent;
  }

  /**
   * Gets the message type.
   *
   * @return the message type
   */
  public String getMessageType() {
    return messageType;
  }

  /**
   * Sets the message type.
   *
   * @param messageType the new message type
   */
  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  /**
   * Gets the message content.
   *
   * @return the message content
   */
  public String getMessageContent() {
    return messageContent;
  }

  /**
   * Sets the message content.
   *
   * @param messageContent the new message content
   */
  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ResponseMessage [messageType=" + messageType + ", messageContent=" + messageContent
        + "]";
  }
}
