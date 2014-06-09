import hudson.plugins.emailext.*
import hudson.model.*
import hudson.maven.*
import hudson.maven.reporters.*
import hudson.tasks.*

// For each project
for(item in Hudson.instance.items) {
 println("JOB : "+item.name);
 // Find current recipients defined in project
 if(item instanceof FreeStyleProject) {
 println(">FREESTYLE PROJECT");
 println(">>Publishers");
 for(publisher in item.publishersList) {
 // Or for Extended Email Publisher
    if(publisher instanceof ExtendedEmailPublisher) {
      for (trigger in publisher.configuredTriggers){
        if (trigger instanceof hudson.plugins.emailext.plugins.trigger.SuccessTrigger){
               println(">>> trigger : "+trigger+" : "+trigger.getEmail().getRecipientList());
         }
      }
    }  
 }
 } 
 println("\n=======\n");
}
