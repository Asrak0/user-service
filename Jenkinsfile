pipeline {
    agent any
    environment {
        SPRING_DATASOURCE_URL = "jdbc:mysql://user-db:3306/user_db?allowPublicKeyRetrieval=true&useSSL=false"
        SPRING_DATASOURCE_USERNAME = "user_service"
        SPRING_DATASOURCE_PASSWORD = credentials('SPRING_DATASOURCE_PASSWORD')
        SPRING_DATASOURCE_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver"
    }
    stages {
        stage('Check Environment') {
             steps {
                sh 'echo "SPRING_DATASOURCE_URL is: $SPRING_DATASOURCE_URL"'
             }
        }
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Asrak0/user-service.git'
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x mvnw'  // Grant execute permission
                sh './mvnw clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        script {
                    echo 'Deploying user-service...'

                    // Stop and remove the old container if it's running
                    sh '''
                        docker stop user-service || true
                        docker rm user-service || true
                    '''

                    // Build and tag the new Docker image
                    sh '''
                        docker build -t asrak0/user-service:latest .
                    '''

                    // Push the image to Docker Hub (or another registry)
                    sh '''
                        docker login -u asrak0 -p ${DOCKER_HUB_PASSWORD}
                        docker push asrak0/user-service:latest
                    '''

                    // Run the new container
                    sh '''
                        docker run -d --name user-service --network movierecommendation_default \
                            -p 8080:8080 \
                            -e SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL \
                            -e SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME \
                            -e SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD \
                            -e SPRING_DATASOURCE_DRIVER_CLASS_NAME=$SPRING_DATASOURCE_DRIVER_CLASS_NAME \
                            asrak0/user-service:latest
                    '''
                }
    }
}