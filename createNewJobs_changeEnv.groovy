import hudson.model.*;
import org.jenkinsci.plugins.envinject.*
import hudson.plugins.nested_view.*
 
def str_view = "_CM_/view/SVN flow"
def str_search = "SVN"
def str_replace = "SubVersion"
 
def view = Hudson.instance.getView("_CM_").getView("SVN flow")

  println (view.getDisplayName());
 
//copy all projects of a view
for(item in view.getItems())
{

  //create the new project name
  newName = item.getName().replace(str_search, str_replace)
  
  //copy the job, disable and save it
  def job = Hudson.instance.copy(item, newName)
    job.disabled = true
  job.save()
  
  view.add(job);
  
  for(wrapper in job.getBuildWrappersList()) {
  
     if(wrapper instanceof EnvInjectBuildWrapper) {
  
      info = wrapper.getInfo().getPropertiesContent();
      println(">>> info : "+info);
       testString = info.replaceAll(/(.*)21(.*)/,'\$122\$2');
       println(">>> info : "+testString);
       wrapper.setInfo(new EnvInjectJobPropertyInfo("",testString,"","","",false));
     }
  }

}