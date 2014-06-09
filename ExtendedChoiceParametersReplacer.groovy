// Extended choice property file replacer
import com.cwctravel.hudson.plugins.extended__choice__parameter.*

for(item in Hudson.instance.items) {
  properties = item.getProperty(ParametersDefinitionProperty.class)
  if(properties != null) {
    parameterDefinitionsList = properties.getParameterDefinitions()
    
	for(parameterDefinition in parameterDefinitionsList) {
	
		println "Job: " + item.name
		if (parameterDefinition instanceof com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition) {
		
			  oldPropertyFileName = parameterDefinition.getPropertyFile()
			  newPropertyFileName = oldPropertyFileName.replaceAll(/netapp04/,"iw-nas-svm-nc1");
			  
			  println "Old property file name"
			  println oldPropertyFileName
			  
			  println "New property file name"
			  println newPropertyFileName
			  println()
			  parameterDefinition.setPropertyFile(newPropertyFileName)
		}
	  }
    }
    item.save()  
}
