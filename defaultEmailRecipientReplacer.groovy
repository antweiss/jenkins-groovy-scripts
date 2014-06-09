import hudson.plugins.emailext.*

 counter = 0;
// For each project
for(item in Hudson.instance.items) {
 
 // Find current recipients defined in project
 if(item instanceof FreeStyleProject && item.name.contains("Media SDK")) {

 for(publisher in item.publishersList) {
 // Or for Extended Email Publisher
    if(publisher instanceof ExtendedEmailPublisher) {
     // for (trigger in publisher.configuredTriggers){
     //   if (trigger instanceof hudson.plugins.emailext.plugins.trigger.SuccessTrigger){
     //          println(">>> trigger : "+trigger+" : "+trigger.getEmail().getRecipientList());
     //    }
     // }
      if (publisher.recipientList.contains("jgilevich"))
      {
         println("JOB : "+item.name);
         counter++;
        println(">>> publisher : "+publisher+" : "+publisher.recipientList);
        testString=publisher.recipientList;
        testString = testString.replaceAll(/,jgilevich|,rsperer/,"");
        println testString;
       //publisher.recipientList = testString;
        
      }
    } 
    }  
    
 } 

}
println("\n==="+counter+"====\n");