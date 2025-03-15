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
        stage('Deploy') {
            steps {
                echo 'Deploying user-service...'
                // Add deployment steps here
            }
        }
    }
}