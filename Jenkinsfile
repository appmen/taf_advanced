pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scmGit([branches: [[name: '*/feature/module9']],
                extensions: [submodule(parentCredentials: true, recursiveSubmodules: true, reference: '', trackingSubmodules: false)],
                userRemoteConfigs: [[credentialsId: '75c8e9ce-efeb-4baf-8840-5e5267560da8', url: 'https://github.com/appmen/taf_advanced.git']]])
            }
        }
        stage('Test') {
            steps {
                bat 'gradle clean test -Pconfig=prod'
            }
        }
    }
    post {
        always {
                //This creates ugly reports
                //step([$class: 'Publisher', reportFilenamePattern: 'build/reports/tests/test/testng-results.xml'])
                publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'ui/build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: 'TestNG Report',
                reportTitles: 'TestNG Report'
            ])
        }
    }
}