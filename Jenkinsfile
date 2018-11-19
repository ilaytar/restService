pipeline {
  agent any
  stages {
    stage('war') {
      steps {
        sh 'gradle war'
      }
    }
    stage('docker-compose') {
      steps {
        sh 'docker-compose --version'
      }
    }
  }
  tools {
    gradle 'gradle'
  }
}