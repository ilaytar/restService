pipeline {
  agent any
  stages {
    stage('useGradle') {
      steps {
        tool(name: 'gradle', type: 'gradle')
        sh 'gradle -version'
      }
    }
  }
}