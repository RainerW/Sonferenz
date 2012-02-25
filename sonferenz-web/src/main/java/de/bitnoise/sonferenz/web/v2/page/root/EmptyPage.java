package de.bitnoise.sonferenz.web.v2.page.root;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

import de.bitnoise.sonferenz.web.v2.panels.navigation.TopNavigationPanel;

/**
 * The "bare metal" empty HTML5 template that is used by all pages in the
 * application. Provides {@code <head>} elements like global CSS and JavaScript,
 * favicon and "Apple touch" graphics, plus very minimal markup that every page
 * will need.
 * <p>
 * Note that this base class does not provide a page {@code <title>}. It is up
 * to each individual page to provide one, using {@code <wicket:head>}.
 * <p>
 * Also exposes a {@link #getBody} method to subclasses that can be used to add
 * <code>id</code> or <code>class</code> attributes to the {@code <body>}
 * element. For example, to add an {@code id}, do this:
 * 
 * <pre class="example">
 * getBody().setMarkupId(&quot;myId&quot;);
 * </pre>
 * <p>
 * To add a CSS class (using {@link fiftyfive.wicket.util.Shortcuts#cssClass}):
 * 
 * <pre class="example">
 * getBody().add(cssClass(&quot;myClass&quot;));
 * </pre>
 */
public   class EmptyPage extends WebPage
{
  @Override
  protected void onInitialize()
  {
    super.onInitialize();
  }
 
}
