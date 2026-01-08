pipeline {
  agent any

  environment {
    IMAGE_NAME = "yourdockerhub/app"
    IMAGE_TAG  = "${BUILD_NUMBER}"
    REGISTRY   = "docker.io"
  }

  stages {

    stage('Checkout') {
      steps {
        git branch: 'dev',
            url: 'https://github.com/kisengeking/My2ndGitProject.git',
            credentialsId: 'github-user'
      }
    }

    stage('Build') {
      steps {
        sh '''
          ./mvnw clean package -DskipTests
        '''
      }
    }

    stage('Test') {
      steps {
        sh '''
          ./mvnw test
        '''
      }
    }

    stage('Docker Build') {
      steps {
        sh '''
          docker build -t $IMAGE_NAME:$IMAGE_TAG .
        '''
      }
    }

    stage('Docker Login') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-creds',
          usernameVariable: 'DOCKER_USER',
          passwordVariable: 'DOCKER_PASS'
        )]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
          '''
        }
      }
    }

    stage('Docker Push') {
      steps {
        sh '''
          docker push $IMAGE_NAME:$IMAGE_TAG
          docker tag $IMAGE_NAME:$IMAGE_TAG $IMAGE_NAME:latest
          docker push $IMAGE_NAME:latest
        '''
      }
    }

    stage('Deploy') {
      steps {
        sh '''
          docker rm -f app || true
          docker run -d \
            --name app \
            -p 8081:8080 \
            $IMAGE_NAME:latest
        '''
      }
    }
  }

  post {
    success {
      echo "✅ Deployment successful"
    }
    failure {
      echo "❌ Pipeline failed"
    }
  }
}
