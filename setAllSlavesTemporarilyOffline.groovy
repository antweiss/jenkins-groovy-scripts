Jenkins.instance.slaves.each() { slave ->
  println "Stopping " + slave.name
  slave.getComputer().setTemporarilyOffline(true, null);
                             }
