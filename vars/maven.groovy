def lintChecks() {
  sh '''
    #we comment this because devs gonna check the failure.
    #~/node_modules/jslint/bin/jslint.js server.js
    mvn checkstyle:check
  '''
}

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
            env.ARGS= "-Dsonar.binaries=target/"
            common.sonarCheck()
          }
        }
      }
    } // End of stage
  }
}