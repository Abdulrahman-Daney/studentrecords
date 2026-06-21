pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = 'daney5311'
        IMAGE_NAME = 'studentrecords'
        IMAGE_TAG = 'latest'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git credentialsId: 'github-credentials',
                    url: 'https://github.com/Abdulrahman-Daney/studentrecords.git',
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

        stage('Performance Test') {
            steps {
                bat 'if exist "src\\test\\jmeter\\report" rmdir /s /q "src\\test\\jmeter\\report"'
                bat 'if exist "src\\test\\jmeter\\results.jtl" del /f /q "src\\test\\jmeter\\results.jtl"'
                bat '"C:\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t "src\\test\\jmeter\\performance-test.jmx" -l "src\\test\\jmeter\\results.jtl" -e -o "src\\test\\jmeter\\report"'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %DOCKERHUB_USERNAME%/%IMAGE_NAME%:%IMAGE_TAG% ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    bat "docker push %DOCKERHUB_USERNAME%/%IMAGE_NAME%:%IMAGE_TAG%"
                }
            }
        }

        stage('Update Jira') {
            steps {
                jiraComment(
                    issueKey: 'SCRUM-7',
                    body: 'Jenkins pipeline built, tested, and Docker image pushed to Docker Hub successfully!'
                )
            }
        }

    }

    post {
        always {
            perfReport sourceDataFiles: 'src/test/jmeter/results.jtl'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}