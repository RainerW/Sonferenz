package de.bitnoise.sonferenz.service.v2.services;

import org.springframework.mail.SimpleMailMessage;

import de.bitnoise.sonferenz.service.actions.impl.ContentReplacement;

public interface MailService
{
  public void sendMessage(ContentReplacement params, SimpleMailMessage msg);
}
