package de.bitnoise.sonferenz.service.v2.services.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import de.bitnoise.sonferenz.service.v2.actions.ContentReplacement;
import de.bitnoise.sonferenz.service.v2.exceptions.ValidationException;
import de.bitnoise.sonferenz.service.v2.services.ConfigurationService;
import de.bitnoise.sonferenz.service.v2.services.MailService;

@Service
public class MailServiceImpl implements MailService
{
  @Autowired
  ConfigurationService config;

  // set in initMail()
  String baseUrl;

  // set in initMail()
  JavaMailSenderImpl sender;

  String from;

  @PostConstruct
  public void initMail()
  {
    JavaMailSenderImpl tmp = new JavaMailSenderImpl();
    tmp.setHost(config.getStringValue("smtp.host"));
    if (config.isAvaiable("smtp.username"))
    {
      tmp.setUsername(config.getStringValue("smtp.username"));
      tmp.setPassword(config.getStringValue("smtp.password"));
    }

    baseUrl = config.getStringValue("baseUrl");

    sender = tmp;
    from = config.getStringValue("mail.create.from");
  }

  @Override
  public void sendMessage(ContentReplacement params, SimpleMailMessage msgToSend)
    {
    SimpleMailMessage msg = new SimpleMailMessage(msgToSend);
    String body = msg.getText();
    body = body.replace("${url.base}", baseUrl);
    body = params.process(body);
    msg.setText(body);
    msg.setFrom(from);

    try
    {
      sender.send(msg);
    }
    catch (MailSendException t)
    {
      throw new ValidationException("Error while sending the mail Mail. "
          + t.getMessage());
    }
  }

}
