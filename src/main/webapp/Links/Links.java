package io.jenkins.plugins.designlibrary;

import hudson.Extension;
import jenkins.model.ModelObjectWithChildren;
import jenkins.model.ModelObjectWithContextMenu;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class Links extends UISample implements ModelObjectWithContextMenu, ModelObjectWithChildren {
  @Override
  public String getIconFileName() {
    return "symbol-at-outline plugin-ionicons-api";
  }

  /**
   * This method is called via AJAX to obtain the context menu for this model object.
   */
  public ContextMenu doContextMenu(StaplerRequest request, StaplerResponse response) throws Exception {
    if (false) {
      /*
        this implementation is sufficient for most ModelObjects. It uses sidepanel.jelly to
        generate the context menu
      */
      return new ContextMenu().from(this,request,response);
    } else {
      /*
       otherwise you can also programatically create them.
       see the javadoc for various convenience methods to add items
       */
      return new ContextMenu()
              .add(new MenuItem().withUrl("https://www.jenkins.io/")
                      .withDisplayName("Jenkins project")
                      .withIconClass("symbol-jenkins plugin-ionicons-api"))
              .add(new MenuItem().withUrl("https://plugins.jenkins.io/")
                      .withDisplayName("Plugin Documentation")
                      .withIconClass("symbol-extension-puzzle-outline plugin-ionicons-api"));
    }
  }

  public ContextMenu doChildrenContextMenu(StaplerRequest request, StaplerResponse response) throws Exception {
    /* You implement this method in much the same way you do doContextMenu */
    return new ContextMenu()
            .add("https://yahoo.com/","Yahoo")
            .add("https://google.com/","Google")
            .add("https://microsoft.com/","Microsoft");
  }

  @Extension
  public static final class DescriptorImpl extends UISampleDescriptor {
  }
}


