import hudson.model.*;
import org.jenkinsci.plugins.envinject.*
  
def jobs = hudson.model.Hudson.instance.items
count = 0
str_search = something
str_replace = something_else
jobs.each { job ->
  
  job.getBuildWrappersList().each { wrapper ->
    if(wrapper instanceof EnvInjectBuildWrapper) {
  
      info = wrapper.getInfo().getPropertiesContent();
     
      println job.name
      println info
      job.save();
      if (info ) netapp =  info.findAll(".*something.*");
      if (netapp){
        count++
        println job.name
        println netapp
        testString = info.replaceAll(/(.*)something(.*)/,'\$1something_else\$2');
        println(">>> new : "+testString);
        wrapper.setInfo(new EnvInjectJobPropertyInfo("",testString,"","","",false));
       job.save();
          }
    }
}
}
println count

