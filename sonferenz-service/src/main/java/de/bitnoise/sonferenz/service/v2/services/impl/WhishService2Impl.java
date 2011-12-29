package de.bitnoise.sonferenz.service.v2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import de.bitnoise.sonferenz.model.LikeModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.WhishModel;
import de.bitnoise.sonferenz.repo.LikeRepository;
import de.bitnoise.sonferenz.repo.WhishRepository;
import de.bitnoise.sonferenz.service.v2.services.AuthenticationService;
import de.bitnoise.sonferenz.service.v2.services.WhishService;

@Service
public class WhishService2Impl implements WhishService
{

  @Autowired
  WhishRepository whishRepo;

  @Autowired
  LikeRepository likeRepo;

  @Autowired
  AuthenticationService authService;

  @Override
  @Transactional(readOnly = true)
  public int getCount()
  {
    return (int) whishRepo.count();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<WhishModel> getWhishes(PageRequest request)
  {
    return whishRepo.findAll(request);
  }

  @Override
  public void unWhish(WhishModel whish)
  {
    UserModel user = authService.getCurrentUser();
  }

  @Override
  public void deleteWhish(WhishModel whish)
  {
    whishRepo.delete(whish);
  }

  @Override
  public void saveWhish(WhishModel whish)
  {
    if (whish.getOwner() == null)
    {
      UserModel user = authService.getCurrentUser();
      whish.setOwner(user);
    }
    whishRepo.save(whish);
  }

  @Override
  public WhishModel getWhishById(int id)
  {
    return whishRepo.findOne(id);
  }

  @Override
  public void like(UserModel user, WhishModel whish)
  {
    LikeModel current = likeRepo.findByUserAndWhish(user, whish);
    if (current == null)
    {
      current = new LikeModel();
      current.setUser(user);
      current.setWhish(whish);
    }

    current.setLikes(1);
    likeRepo.save(current);

    Integer old = whish.getLikes();
    whish.setLikes(old == null ? 1 : old + 1);
    whishRepo.save(whish);
  }

  @Override
  public void unLike(UserModel user, WhishModel whish)
  {
    LikeModel current = likeRepo.findByUserAndWhish(user, whish);
    if (current == null)
    {
      return;
    }

    current.setLikes(0);
    likeRepo.save(current);

    Integer old = whish.getLikes();
    whish.setLikes(old == null ? 0 : old - 1);
    whishRepo.save(whish);
  }

  @Override
  public Integer getWhishLikeCount(UserModel user, WhishModel whish)
  {
    LikeModel current = likeRepo.findByUserAndWhish(user, whish);
    if (current == null)
    {
      return null;
    }
    return current.getLikes();
  }

}
