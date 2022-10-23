def call() {
    pipeline {
        agent any

        environment {
            SONAR = credentials('SONAR')
        }

        stages {

            // For each commit
            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }
                }
            }

            stage('SonarCheck') {
                steps {
                    script {
                        sh 'mvn clean compile'
                        env.ARGS = "-Dsonar.binaries=target/"
                        common.sonarCheck()
                    }
                }
            }
        } // End of stage
    }
}