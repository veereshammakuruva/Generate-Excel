pipeline {
    agent any
    environment {
        registry = "my-registry"
        tag = "latest"
        newContainerName = "[container-name]-${BUILD_NUMBER}"
        previousBuildNumber = "${BUILD_NUMBER.toInteger() - 1}"
        oldContainerName = "[container-name]-${previousBuildNumber}"
    }
    stages {
        stage('Checkout') {
            steps {
                bat 'git checkout main'
		        bat 'git pull'
            }
        }
        stage('Build') {
            steps {
                dir('college-management') {
                    bat 'mvn clean install'
                }
            }
        }
    }
}