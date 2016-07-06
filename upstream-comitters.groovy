// this script can be used in email-ext plugin for recipient list field as ${SCRIPT, script="upstream-committers.groovy"}

def upstreamBuild = null
def committers = []

def cause = build.causes.find { it instanceof
    hudson.model.Cause.UpstreamCause }

try {
        //recursively get to the top-most originating build
        while(cause != null) {
                upstreamBuild = hudson.model.Hudson.instance.getItem(cause.upstreamProject).getBuildByNumber(cause.upstreamBuild)
                if(upstreamBuild == null) {
                        break;
                }
                //add all upstream build comitters to the recipient list
                if(upstreamBuild.changeSet != null) {
                    upstreamBuild.changeSet.each() { cs ->
                            if(cs.user != null) {
                                    committers.add(cs.user)
                                }
                        }
                }
                cause = upstreamBuild.causes.find {it instanceof
                    hudson.model.Cause.UpstreamCause }
        }
} catch(e) {
    // do nothing
}

committers.unique().join(',')
