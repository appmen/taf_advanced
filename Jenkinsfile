pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/feature/addlayers']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/appmen/taf_advanced.git']]])
            }
        }
        stage('Test') {
            steps {
                bat 'gradle clean :ui:test -Pconfig=prod'
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