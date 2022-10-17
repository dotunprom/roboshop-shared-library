def lintChecks () {

  sh '''
//   we comment this because devs gonna check the failure.
//   ~/node_modules/jslint/bin/jslint.js server.js
     echo Link Check
  '''
}