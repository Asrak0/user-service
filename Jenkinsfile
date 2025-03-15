pipeline {
    agent any
    environment {
        DATABASE_URL = "${env.DATABASE_URL}"
        DATABASE_USER = "${env.DATABASE_USER}"
        DATABASE_PASSWORD = "${env.DATABASE_PASSWORD}"
        SPRING_APPLICATION_NAME = 'user-service'
        SPRING_JPA_HIBERNATE_DDL_AUTO = 'update'
        SPRING_DATASOURCE_DRIVER_CLASS_NAME = 'com.mysql.cj.jdbc.Driver'
    }
    stages {
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