package de.bitnoise.sonferenz.service.v2.services.idp.provider.crowd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "password")
public class CrowdPassword
{
  
    
    private String value;

    @XmlElement
    public String getValue()
    {
      return value;
    }

    public void setValue(String value)
    {
      this.value = value;
    }
  
  
  
}
