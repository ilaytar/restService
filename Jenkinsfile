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
        sh 'gradle cargoDeployRemote'
      }
    }
  }
  tools {
    gradle 'gradle'
  }
}