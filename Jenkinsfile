pipeline {
  agent {
      docker {
          image 'tomcat:9.0.13-jre8'
          }
      }
  stages {
    stage('war') {
      steps {
        sh 'gradle war'
      }
    }
    stage('deploy') {
      steps {
        sh 'sudo curl -L "https://github.com/docker/compose/releases/download/1.23.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose'
      }
    }
  }
  tools {
    gradle 'gradle'
  }
}