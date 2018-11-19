pipeline {
  agent any
  stages {
    stage('useGradle') {
      steps {
        sh 'gradle --version'
      }
    }
  }
  tools {
    gradle 'gradle'
  }
}