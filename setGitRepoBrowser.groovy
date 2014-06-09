import hudson.model.*;
import hudson.plugins.git.*
import hudson.plugins.nested_view.*
import hudson.plugins.git.browser.*

//def view = Hudson.instance.getView("UC")
def view = Hudson.instance.getView("UC")

  println (view.getDisplayName());

for (subview in view.getViews())
{ 
   if ( subview.getDisplayName() =~ /iOS/ )
   {  
    for(item in subview.getItems())
    {
	
        def SCM = item.getScm()
        if(SCM instanceof GitSCM ) {
               println (item.getName());
               def browser = SCM.getBrowser();
               if (browser){
                 println browser.getUrl()
               }
               else
               {
                 browser = new GitLab( "http://git-pilot/gitlist/UC-iOS.git/", "5.4");
                 SCM.browser = browser
               }
               //item.save();
           }
        }
    }
}
