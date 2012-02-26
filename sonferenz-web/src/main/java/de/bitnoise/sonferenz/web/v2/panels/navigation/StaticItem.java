package de.bitnoise.sonferenz.web.v2.panels.navigation;

public class StaticItem
{

  String _name;
  String _key;

  public StaticItem(String name, String key)
  {
    _name = name;
    _key = key;
  }

  public String getLinkName()
  {
    return _name;
  }

  public String getLinkTarget()
  {
    return _key;
  }

}
