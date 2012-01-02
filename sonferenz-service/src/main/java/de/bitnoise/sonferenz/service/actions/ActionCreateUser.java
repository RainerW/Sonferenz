package de.bitnoise.sonferenz.service.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ActionCreateUser")
public class ActionCreateUser implements ActionData, IncrementUseCountOnToken
{
  String loginName;

  public String getLoginName()
  {
    return loginName;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  String userName;

  String password;

  List<String> groups;

  String mail;

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public void setMail(String mail)
  {
    this.mail = mail;
  }

  public void setTokenId(Integer tokenId)
  {
    this.tokenId = tokenId;
  }

  Integer tokenId;

  public void setLoginName(String value)
  {
    loginName = value;
  }

  public void setPassword(String value)
  {
    password = value;
  }

  public void addGroupo(String groupName)
  {
    getGroups().add(groupName);
  }

  public List<String> getGroups()
  {
    if (groups == null)
    {
      groups = new ArrayList<String>();
    }
    return groups;
  }

  public String getEMail()
  {
    return mail;
  }

  @Override
  public List<Integer> getTokensToIncrementUseage()
  {
    return Arrays.asList(tokenId);
  }

  public String getActionName()
  {
    return "subscribe";
  }

}
