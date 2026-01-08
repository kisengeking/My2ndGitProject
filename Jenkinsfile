pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main',
            url: 'https://github.com/kisengeking/MyFirstGitProject.git',
            credentialsId: 'github-token'
      }
    }

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
}
