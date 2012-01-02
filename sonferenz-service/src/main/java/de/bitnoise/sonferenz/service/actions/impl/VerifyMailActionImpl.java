package de.bitnoise.sonferenz.service.actions.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.repo.ActionRepository;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.KonferenzAction;
import de.bitnoise.sonferenz.service.actions.impl.VerifyMailActionImpl.VerifyMailActionData;
import de.bitnoise.sonferenz.service.v2.services.ActionService;
import de.bitnoise.sonferenz.service.v2.services.MailService;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService;

@Service
public class VerifyMailActionImpl implements KonferenzAction
{
  public static class VerifyMailActionData implements ActionData
  {

    public static final String ACTION_ID = "verifyMail";

    @Override
    public String getActionName()
    {
      return ACTION_ID;
    }

  }

  @Override
  public String getActionName()
  {
    return VerifyMailActionData.ACTION_ID;
  }

  @Autowired
  ActionRepository repo;

  @Autowired
  MailService mailer;

  SimpleMailMessage template;

  @Autowired
  StaticContentService texte;

  @Autowired
  ActionService actionService;

  @Override
  public boolean execute(ActionData data)
  {
    return false;
  }

  @PostConstruct
  public void initMail()
  {
    template = new SimpleMailMessage();
    template.setSubject(
        texte.text("action.verify.mail.subject",
            "Details for your new user account"));
  }

  public void createAction(UserModel user, String eMail)
  {
    VerifyMailActionData data = new VerifyMailActionData();
    ActionResult create = actionService.createAction(this, data);
    if (create.wasSuccessfull())
    {
      SimpleMailMessage message = new SimpleMailMessage(template);
      message.setTo(user.getEmail());
      StringBuffer body = new StringBuffer();
      body.append("Please confirm your email by open the folling link in your browser:");
      body.append("\r\n");
      body.append(ActionResult.ACTION_URL);
      message.setText(body.toString());

      mailer.sendMessage(create.getContentReplacer(), message);
    }
  }

  @Override
  public Class[] getModelClasses()
  {
    return new Class[] {VerifyMailActionData.class};
  }

}
