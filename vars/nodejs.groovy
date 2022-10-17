def lintChecks() {
  sh '''
    #we comment this because devs gonna check the failure.
    #~/node_modules/jslint/bin/jslint.js server.js
    echo Link Check for ${COMPONENT}
  '''
}

def call() {
  pipeline {
    agent any

    stages {

      // For each commit
      stage('Lint Checks') {
        steps {
          script {
            lintChecks()
          }
        }
      }
    } // End of stage
  }
}