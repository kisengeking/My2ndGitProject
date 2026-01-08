pipeline {
  agent any

  environment {
    IMAGE_NAME = "kisengeking/my2ndgitproject"
    IMAGE_TAG  = "${BUILD_NUMBER}"
  }

  stages {

    stage('Build') {
      steps {
        sh '''
          chmod +x mvnw
          ./mvnw clean package -DskipTests
        '''
      }
    }

    stage('Test') {
      steps {
        sh './mvnw test'
      }
    }

    stage('Docker Build') {
      steps {
        sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-creds',
          usernameVariable: 'DOCKER_USER',
          passwordVariable: 'DOCKER_PASS'
        )]) {
          sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
            docker push $IMAGE_NAME:$IMAGE_TAG
            docker tag $IMAGE_NAME:$IMAGE_TAG $IMAGE_NAME:latest
            docker push $IMAGE_NAME:latest
          '''
        }
      }
    }

    stage('Deploy') {
      steps {
        sh '''
          docker rm -f springboot-app || true
          docker run -d -p 8081:8080 --name springboot-app $IMAGE_NAME:latest
        '''
      }
    }
  }

  post {
    success {
      echo '✅ CI/CD Pipeline completed successfully'
    }
    failure {
      echo '❌ Pipeline failed'
    }
  }
}
