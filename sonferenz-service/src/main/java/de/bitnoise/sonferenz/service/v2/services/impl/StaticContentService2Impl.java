package de.bitnoise.sonferenz.service.v2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.bitnoise.sonferenz.model.StaticContentModel;
import de.bitnoise.sonferenz.repo.StaticContentRepository;
import de.bitnoise.sonferenz.service.v2.exceptions.RepositoryException;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService;

@Service
public class StaticContentService2Impl implements StaticContentService
{
  @Autowired
  StaticContentRepository repo;

  @Override
  public String text(String key)
  {
    try
    {
      return doText(key);
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  String doText(String key)
  {
    StaticContentModel text = repo.findByName(key);
    if (text == null)
    {
      return null;
    }
    return text.getHtml();
  }

  @Override
  public String text(String key, String defaultValue)
  {
    try
    {
      String text = doText(key);
      if (text == null)
      {
        return defaultValue;
      }
      return text;
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  @Override
  public void storeText(String key, String text)
  {
    // Authorize.has(roles, UserRights.Admin.StaticContent.ANY);
    try
    {
      doStoreText(key, text);
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  void doStoreText(String key, String text)
  {
    StaticContentModel item = repo.findByName(key);
    if (item == null)
    {
      item = new StaticContentModel();
      item.setName(key);
    }
    item.setHtml(text);
    repo.saveAndFlush(item);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<StaticContentModel> getAll(PageRequest request)
  {
    return repo.findAll(request);
  }

}
