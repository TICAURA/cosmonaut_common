
podTemplate(containers:[
    containerTemplate(name: 'gradle', image: 'gradle:6.7.0-jdk11-hotspot', command: 'sleep', args: '99d')
  ], volumes: [
  persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'maven-repo', readOnly: false),
  persistentVolumeClaim(mountPath: '/root/.gradle', claimName: 'gradle-repo', readOnly: false)
  ] ) {
  def tag=''
  node(POD_LABEL) {
    stage("Compilacion") {
        container('gradle'){
            script{
		       git url: 'https://github.com/ASG-BPM/cosmonaut_common',branch:'main',credentialsId: 'winter_user'
		       tag = sh(script:'git describe --tags --always `git rev-list --tags` | grep DEV | head -1',returnStdout: true ).trim()
		       sh "git checkout $tag"
		       sh './gradlew assemble publishToMavenLocal'
	       }
	       
        }       
    }    
  }
}

