pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git branch: 'dev',
            url: 'https://github.com/kisengeking/MyFirstGitProject.git',
            credentialsId: 'github-user'
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
