package de.bitnoise.sonferenz.service.v2.exceptions;

public class GeneralConferenceException extends RuntimeException
{

  public GeneralConferenceException(Throwable t)
  {
    super(t);
  }

  public GeneralConferenceException(String message)
  {
    super(message);
  }

  public GeneralConferenceException(String message, Throwable t)
  {
    super(message, t);
  }

}
