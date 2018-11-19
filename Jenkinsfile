pipeline {
  agent any
  tools {
    gradle 'gradle'
  }
  stages {
    stage('useGradle') {
      steps {
        sh 'gradle -version'
      }
    }
  }
}