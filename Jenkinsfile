pipeline {
    agent any

    stages {

        stage('Clone Repository') {
            steps {
                git credentialsId: 'github-credentials',
                    url: 'https://github.com/Abdulrahman-Daney/studentrecords',
                    branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        stage('Update Jira') {
            steps {
                jiraComment(
                    issueKey: 'SCRUM-7',
                    body: 'Jenkins pipeline built and tested successfully!'
                )
            }
        }

    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}