package de.bitnoise.sonferenz.service.v2.services.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.EventBus;

import de.bitnoise.sonferenz.service.v2.events.ApplicationStarted;
import de.bitnoise.sonferenz.service.v2.services.Eventing;

@Service
public class EventingImpl implements Eventing,
    ApplicationListener<ContextRefreshedEvent>, BeanPostProcessor
{
  EventBus bus = new EventBus();

  @Override
  public void post(Object event)
  {
    bus.post(event);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event)
  {
    bus.post(new ApplicationStarted());
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException
  {
    bus.register(bean);
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException
  {
    return bean;
  }

}
