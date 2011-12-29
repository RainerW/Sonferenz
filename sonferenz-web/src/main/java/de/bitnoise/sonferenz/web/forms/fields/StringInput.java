package de.bitnoise.sonferenz.web.forms.fields;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class StringInput implements Field
{
  List _label;
  TextField inputField;
  Label InputLabel;
  IModel<String> inputModel;

  public StringInput(String labelName)
  {
    inputModel=new Model<String>();
    inputField = new TextField("inputField", inputModel);
    InputLabel = new Label("inputLabel", Model.of(labelName));
  }

  public Component createPanel(String id)
  {
    return new MyPanel(id);
  }

  public TextField getInput()
  {
    return inputField;
  }
  
  class MyPanel extends Panel
  {

    public MyPanel(String id)
    {
      super(id);
    }

    @Override
    protected void onInitialize()
    {
      super.onInitialize();
      add(InputLabel);
      add(inputField);
    }
  }

  public String getValue()
  {
    return inputField.getValue();
  }

}
