properties([pipelineTriggers([githubPush()])])
def tag=''
pipeline {
	agent {
		label 'docker-agent'
	}

	stages {
		stage("Compilacion") {
		    agent{
				docker {
					label 'docker-agent'
					image 'gradle:6.7.0-jdk11-hotspot'
				}
			}
		   steps {
		       script{
    		       git url: 'https://github.com/ASG-BPM/cosmonaut_common',branch:'main',credentialsId: 'winter_user'
    		       tag = sh(script:'git describe --tags --always `git rev-list --tags` | grep DEV | head -1',returnStdout: true ).trim()
    		       sh "git checkout $tag"
    		       sh './gradlew assemble'
		       }
		   }
		   post {
				success {
					archiveArtifacts artifacts: 'lib/build/libs/*.jar', fingerprint: true
				}
		   }
		}
		stage('Sonarqube') {
			agent{
				docker {
					label 'docker-agent'
					image 'gradle:6.7.0-jdk11-hotspot'
					args '--dns 192.168.0.154'
				}
			}
			steps{
				withSonarQubeEnv(installationName:'Asg Wintermute Server') {
				   git url: 'https://github.com/ASG-BPM/cosmonaut_common',branch:'main',credentialsId: 'winter_user'    
				   sh "git checkout $tag"
    		       sh './gradlew sonarqube --stacktrace'
				}
			}
		}
		stage('Deploy to Nexus') {
			agent{
				docker {
					label 'docker-agent'
					image 'gradle:6.7.0-jdk11-hotspot'
					args '--dns 192.168.0.154'
				}
			}
			steps{
				withCredentials([usernamePassword(credentialsId: 'nexus_keys', passwordVariable: 'NEXUS_PASS', usernameVariable: 'NEXUS_USER')]) {
				    git url: 'https://github.com/ASG-BPM/cosmonaut_common',branch:'main',credentialsId: 'winter_user'    
				    sh "git checkout $tag"
					sh './gradlew clean build publishMavenPublicationToNexusRepository -x test  -PnexusUsername=${NEXUS_USER} -PnexusPassword=${NEXUS_PASS}'
				}
			}
		}
	}
}
