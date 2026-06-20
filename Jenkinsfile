pipeline {
    agent any

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
                bat '"C:\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t "src\\test\\jmeter\\performance-test.jmx" -l "src\\test\\jmeter\\results.jtl" -e -o "src\\test\\jmeter\\report"'
            }
        }

        stage('Update Jira') {
            steps {
                jiraComment(
                    issueKey: 'SCRUM-7',
                    body: 'Jenkins pipeline built, tested, and performance tested successfully!'
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