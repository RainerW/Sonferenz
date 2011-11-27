package de.bitnoise.sonferenz.web.component.table;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import de.bitnoise.sonferenz.web.pages.voting.UserVoteItem;

public abstract class VoteColumnPanel extends Panel
{

  IModel<UserVoteItem> _rowModel;

  public VoteColumnPanel(String componentId, IModel<UserVoteItem> rowModel)
  {
    super(componentId);
    _rowModel = rowModel;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    AjaxFallbackLink linkVote = new AjaxFallbackLink("doVote") {
      @Override
      public void onClick(AjaxRequestTarget target)
      {
        onVote(target,_rowModel);
      }
    };
    Label voteValue = new Label("voteVal", createRate(_rowModel));

    AjaxFallbackLink linkUnVote = new AjaxFallbackLink("doUnVote") {

      @Override
      public void onClick(AjaxRequestTarget target)
      {
        onUnVote(target,_rowModel);
      }
    };

    add(linkVote);
    add(voteValue);
    add(linkUnVote);
  }

  protected abstract void onUnVote(AjaxRequestTarget target,IModel<UserVoteItem> row);

  protected abstract void onVote(AjaxRequestTarget target,IModel<UserVoteItem> row);

  IModel<String> createRate(IModel<UserVoteItem> row)
  {
    Integer i = row.getObject().rateing;
    if (i == null)
    {
      i = 0;
    }
    return new ResourceModel("vote.text." + i , ""+i);
  }
}
