package de.bitnoise.sonferenz.service.v2.services.userdata.beans;

public class UserData
{
  private String id;

  private String firstName;

  private String lastName;

  private String displayName;

  private String email;

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getDisplayName()
  {
    return displayName;
  }

  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  @Override
  public String toString()
  {
    return "UserData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", displayName="
        + displayName + ", email=" + email + "]";
  }

}
