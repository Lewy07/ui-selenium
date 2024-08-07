pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Select browser to run tests')
    }

    stages {
        stage('Build and Test') {
            steps {
                bat "mvn clean test -Dbrowser=${params.BROWSER}"
            }
        }
    }

    post {
        always {
            allure includeProperties:
            false,
            jdk: '',
            results: [[path: 'target/allure-results']]
        }
    }
}