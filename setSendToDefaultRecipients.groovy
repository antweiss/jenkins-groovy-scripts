import hudson.plugins.emailext.*
for(item in Hudson.instance.items) {
// Find current recipients defined in project
	if(item instanceof FreeStyleProject && item.name.contains("Release"))
	{
    		for(publisher in item.publishersList) {
// Or for Extended Email Publisher
			if(publisher instanceof ExtendedEmailPublisher) {

				println("JOB : "+item.name);
                          	for (trigger in publisher.configuredTriggers){
					
					println(">>> trigger : "+trigger+" : "+trigger.getEmail().sendToRecipientList);
                                  	//trigger.getEmail().sendToRecipientList = false;

				}
			}
     		}
          	item.save()
   	}
 }import hudson.plugins.emailext.*
for(item in Hudson.instance.items) {
// Find current recipients defined in project
	if(item instanceof FreeStyleProject && item.name.contains("Release"))
	{
    		for(publisher in item.publishersList) {
// Or for Extended Email Publisher
			if(publisher instanceof ExtendedEmailPublisher) {

				println("JOB : "+item.name);
                          	for (trigger in publisher.configuredTriggers){
					
					println(">>> trigger : "+trigger+" : "+trigger.getEmail().sendToRecipientList);
                                  	//trigger.getEmail().sendToRecipientList = false;

				}
			}
     		}
          	item.save()
   	}
 }