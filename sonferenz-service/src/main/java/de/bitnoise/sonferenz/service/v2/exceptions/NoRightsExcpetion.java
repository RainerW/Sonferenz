package de.bitnoise.sonferenz.service.v2.exceptions;

public class NoRightsExcpetion extends GeneralConferenceException
{

  public NoRightsExcpetion(Throwable t)
  {
    super(t);
  }

  public NoRightsExcpetion(String message)
  {
    super(message);
  }

}
