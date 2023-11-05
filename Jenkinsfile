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
        stage('Publish TestNG Report') {
            steps {
                testReport testResults: '**/reports/tests/test/testng-results.xml', failIfNoResults: true
            }
        }
    }
}