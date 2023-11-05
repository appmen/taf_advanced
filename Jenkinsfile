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
        stage('Publish Test Report') {
            steps {
                step([$class: 'Publisher', reportFilenamePattern: '**/reports/tests/test/testng-results.xml'])
            }
        }
    }
}