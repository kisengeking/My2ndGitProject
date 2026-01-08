pipeline {
  agent any

  stages {

    stage('Build') {
      steps {
        echo 'Building project...'
      }
    }

    stage('Test') {
      steps {
        echo 'Running tests...'
      }
    }

  }

  post {
    success {
      echo '✅ Build succeeded'
    }
    failure {
      echo '❌ Build failed'
    }
  }
}
