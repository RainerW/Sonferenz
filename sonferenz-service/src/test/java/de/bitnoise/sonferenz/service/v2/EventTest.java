package de.bitnoise.sonferenz.service.v2;

import static org.junit.Assert.*;
import static org.fest.assertions.Assertions.*;

import org.junit.Test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import de.bitnoise.sonferenz.service.v2.EventTest.Client;
import de.bitnoise.sonferenz.service.v2.events.ConfigReload;

public class EventTest
{
  EventBus bus = new EventBus();

  ConfigReload configReload;

  @Test
  public void test()
  {
    Client client = new Client();
    bus.register(client);

    bus.post(new Object());
    bus.post(new ConfigReload());
    assertThat(configReload).isNotNull();
  }

  class Client
  {
    @Subscribe
    public void recordCustomerChange(ConfigReload e)
    {
      configReload = e;
    }

  }

}
