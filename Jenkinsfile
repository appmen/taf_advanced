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
        stage ('Scan'){
            steps{
                withSonarQubeEnv(installationName:'sonar'){
                    sh './gradlew sonar -Dsonar.projectKey=TAF_advanced -Dsonar.projectName="TAF_advanced" -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_0a9da578a38098d2a9ec05c81207db49df40f085'
                }
            }
        }
        stage ('Quality gate'){
            steps{
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Test') {
            steps {
                cmd 'gradle test -Pconfig=prod'
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
                reportDir: 'api/build/reports/tests/test',
                reportFiles: 'index.html',
                reportName: 'TAF Report',
                reportTitles: 'TAF Report'
            ])
        }
    }
}