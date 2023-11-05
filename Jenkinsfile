pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/feature/addlayers']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/appmen/taf_advanced.git']]])
            }
        }
//         stage('Build') {
//             steps {
//                 bat 'mvn clean package'
//             }
//         }
        stage('Test') {
            steps {
                bat 'gradle :ui:test -Pconfig=prod'
            }
        }
//         stage('Deploy') {
//             steps {
//                 bat 'mvn deploy'
//             }
//         }
    }
}