// this script copies jobs from source view to destination view while changing their names, 
// env variables, triggered job names and custom workspace

import hudson.model.*;
import org.jenkinsci.plugins.envinject.*
import hudson.plugins.nested_view.*
import hudson.plugins.parameterizedtrigger.*
 
def str_search = 'master'
def str_replace = branchName
 
// source view 
def view = Hudson.instance.getView("Media SDK").getView("Media SDK Connect - Git (master)")
// 
def newView = Hudson.instance.getView("Media SDK").getView(newMediaView)

  println (view.getDisplayName());
  println (newView.getDisplayName());
//copy all projects of a view
for(item in view.getItems())
{

  //create the new project name
  newName = item.getName().replace(str_search,str_replace)
 // newName = newName.replace(str_search1,str_replace)
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
        // projects = projects.replace(str_search1,str_replace);
          println projects;
          config.projects = projects;
          
      }
        }
  }
  
  for(wrapper in job.getBuildWrappersList()) {
  
     if(wrapper instanceof EnvInjectBuildWrapper) {
  
      info = wrapper.getInfo().getPropertiesContent();
      println(">>> info : "+info);
	  
       testString = info.replaceAll(str_search,str_replace);
       println(">>> info : "+testString);
       wrapper.setInfo(new EnvInjectJobPropertyInfo("",testString,"","","",false));
     }
  }
  
  // replace workspace
  workspace = job.getCustomWorkspace();
  workspace = workspace.replaceAll(str_search,str_replace)
  println workspace;
  job.setCustomWorkspace(workspace);
  //change git branches
  SCM = job.getScm()
  if(SCM instanceof GitSCM ) {
 	 branches = SCM.getBranches();
  	 new_branch = branches.getAt(0).getName().replaceAll(str_search,str_replace)
 	 println branches.getAt(0).getName()
     println new_branch
 	 SCM.branches.getAt(0).setName(new_branch)
      print "local: "
      println SCM.getLocalBranch()
      if ( ! SCM.getLocalBranch().equals(null))
           {
             println "replacing" 
             SCM.localBranch = SCM.getLocalBranch().replaceAll(str_search,str_replace)
           }
     }
      
   
  
}

