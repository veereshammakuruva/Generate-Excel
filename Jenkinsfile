pipeline {
    agent { label 'windows-agent' }
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
                bat 'git checkout <branch_name>'
		bat 'git pull'
            }
        }
        stage('Build') {
            steps {
                dir('tsm') {
                    bat 'mvn clean package'
                }
            }
        }
        stage('Stop Container') {
            steps {
                script {
                    try {
                        bat "docker stop ${oldContainerName}"
                    } catch (Exception e) {
                        // Ignore errors if the container is not running or already removed
                    }
                }
            }
        }
        stage('Deploy in Docker') {
            steps {
                dir('tsm') {
                    bat 'docker build -t [image-name] .'
                    bat "docker run -d -p 9090:9090 --name ${newContainerName} [image-name]"
                }
            }
        }
    }
}