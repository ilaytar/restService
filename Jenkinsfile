pipeline {
  agent any
  stages {
    stage('war') {
      steps {
        sh 'gradle war'
      }
    }
  }
  tools {
    gradle 'gradle'
  }
}