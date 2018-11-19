pipeline {
  agent any
  stages {
    stage('war') {
      steps {
        tool 'gradle'
        sh 'gradle war'
      }
    }
  }
}