package de.bitnoise.sonferenz.service.v2.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralConferenceException extends RuntimeException
{
  Logger logger = LoggerFactory.getLogger(GeneralConferenceException.class);

  public GeneralConferenceException(Throwable t)
  {
    super(t);
    logger.error("A Error Occured", t);
  }

  public GeneralConferenceException(String message)
  {
    super(message);
    logger.error(message);
  }

  public GeneralConferenceException(String message, Throwable t)
  {
    super(message, t);
    logger.error(message, t);

  }

}
