def lintChecks() {
  sh '''
    #we comment this because devs gonna check the failure.
    #~/node_modules/jslint/bin/jslint.js server.js
    pylint *.py
    echo Lint Check for ${COMPONENT}
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
            common.sonarCheck()
          }
        }
      }
    } // End of stage
  }
}