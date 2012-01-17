package de.bitnoise.sonferenz.service.v2.services.userdata.crowd;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class CrowdUser
{

  private String name;

  private String firstname;

  private String lastname;

  private String displayname;

  private String email;

  @XmlAttribute
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @XmlElement(name = "first-name")
  public String getFirstname()
  {
    return firstname;
  }

  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }

  @XmlElement(name = "last-name")
  public String getLastname()
  {
    return lastname;
  }

  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }

  @XmlElement(name = "display-name")
  public String getDisplayname()
  {
    return displayname;
  }

  public void setDisplayname(String displayname)
  {
    this.displayname = displayname;
  }

  @XmlElement
  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

}
