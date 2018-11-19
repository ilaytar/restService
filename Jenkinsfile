pipeline {
  agent {
    docker {
      image 'tomcat'
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