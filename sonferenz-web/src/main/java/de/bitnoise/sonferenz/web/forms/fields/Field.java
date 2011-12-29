package de.bitnoise.sonferenz.web.forms.fields;

import java.io.Serializable;

import org.apache.wicket.Component;

public interface Field extends Serializable
{

  Component createPanel(String next);

}
