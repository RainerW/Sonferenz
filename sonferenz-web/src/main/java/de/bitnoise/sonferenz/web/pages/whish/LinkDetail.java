package de.bitnoise.sonferenz.web.pages.whish;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import com.visural.wicket.behavior.beautytips.BeautyTipBehavior;
import com.visural.wicket.behavior.beautytips.TipPosition;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.UserModel;

public class LinkDetail extends LikeColumn<ModelWhishList> implements
    Serializable
{
  private UiFacade facade;
  private String _name;
  private String _hint;

  public LinkDetail(String name, UiFacade facade, String hint)
  {
    super(new ResourceModel(name));
    _name = name;
    _hint = hint;
    this.facade = facade;
  }

  @Override
  protected void onChange(AjaxRequestTarget target,
      IModel<ModelWhishList> rowModel, Model<Boolean> newValue)
  {
    ModelWhishList object = rowModel.getObject();
    UserModel user = KonferenzSession.get().getCurrentUser();
    if (newValue.getObject())
    {
      facade.likeWhish(user, object.whish);
    }
    else
    {
      facade.unLikeWhish(user, object.whish);
    }
    RequestCycle.get().setResponsePage(target.getPage());
  }

  @Override
  protected boolean getLike(IModel<ModelWhishList> rowModel)
  {
    ModelWhishList obj = rowModel.getObject();
    return obj.like != null && obj.like == 1;
  }

  @Override
  protected Integer getCount(IModel<ModelWhishList> rowModel)
  {
    ModelWhishList obj = rowModel.getObject();
    return obj.sumLike;
  }

  @Override
  public Component getHeader(String componentId)
  {
    Component hdr = super.getHeader(componentId);
    if (_hint != null)
    {
      hdr.add(new BeautyTipBehavior(new ResourceModel(_hint))
          .setPositionPreference(TipPosition.bottom));
    }
    return hdr;
  }

}
