package de.bitnoise.sonferenz.service.v2.exceptions;

public class RepositoryException extends GeneralConferenceException
{

  public RepositoryException(Throwable t)
  {
    super(t);
  }

  public RepositoryException(String message)
  {
    super(message);
  }

}
