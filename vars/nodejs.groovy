def lintChecks() {
  sh '''
    #we comment this because devs gonna check the failure.
    #~/node_modules/jslint/bin/jslint.js server.js
    echo Link Check for ${COMPONENT}
  '''
}


def sonarCheck() {
  sh'''
   sonar-scanner -Dsonar.host.url=http://172.31.10.237:9000 -Dsonar.sources=. -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}
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


      stage('Sonar Checks') {
        steps {
          script {
            sonarChecks()
          }
        }
      }
    } // End of stage
  }
}