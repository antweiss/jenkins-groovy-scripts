// this script copies jobs from source view to destination view while changing their names, 
// env variables, triggered job names and custom workspace

import hudson.model.*;
import org.jenkinsci.plugins.envinject.*
import hudson.plugins.nested_view.*
import hudson.plugins.parameterizedtrigger.*
 
def str_search = 'smthng'
def str_replace = "smthng else"
 
// source view 
def view = Hudson.instance.getView("parentView").getView("viewname")
// 
def newView = Hudson.instance.getView("parentView").getView("newviewname")

  println (view.getDisplayName());
  println (newView.getDisplayName());
//copy all projects of a view
for(item in view.getItems())
{

  //create the new project name
  newName = item.getName().replace(str_search,str_replace)
  newName = newName.replace(str_search1,str_replace)
  println (">>"+newName);  
  
  //copy the job, disable and save it
  def job = Hudson.instance.copy(item, newName)
  job.disabled = true
  job.save()
  
  newView.add(job);
  
  for(publisher in job.publishersList) {
    if(publisher instanceof BuildTrigger ) {
      for (config in publisher.getConfigs())
      {
          projects=config.getProjects();
          projects = projects.replace(str_search,str_replace);
         projects = projects.replace(str_search1,str_replace);
          println projects;
          config.projects = projects;
          
      }
        }
  }
  
  for(wrapper in job.getBuildWrappersList()) {
  
     if(wrapper instanceof EnvInjectBuildWrapper) {
  
      info = wrapper.getInfo().getPropertiesContent();
      println(">>> info : "+info);
       testString = info.replaceAll(/(.*)SOME_STRING(.*)/,'\$1ANOTHER_STRING\$2');
       println(">>> info : "+testString);
       wrapper.setInfo(new EnvInjectJobPropertyInfo("",testString,"","","",false));
     }
  }
  
  // replace workspace
  workspace = job.getCustomWorkspace();
  workspace = workspace.replaceAll('ATTMessages_AUI_CDLS_V3', "ATTMessages_AUI_V3.0")
  println workspace;
  job.setCustomWorkspace(workspace);
  
}

