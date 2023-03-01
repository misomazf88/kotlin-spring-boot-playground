timeout(15){
  node(label: '!master') {
    def flow = load "/scripts/util.groovy";

    flow.init()

    flow.wstage("Build", {
      sh 'docker-compose build'
    })

    flow.wstage("Test", {
      sh 'docker-compose run --rm test'
    })

    flow.wstage("Teardown", {
      sh 'docker-compose down -v'
    })

    flow.wstage("Aggregate", {
      jacoco(execPattern: './build/jacoco/*.exec')
      junit 'rpp-*/build/test-results/test/*.xml'
    })

    flow.wstage("Sonar", {
      flow.sonarAnalysis();
    })

    flow.wstage("Quality Gate", {
      timeout(time: 1, unit: 'HOURS') {
        def qg = waitForQualityGate()
        if (qg.status != 'OK') {
          error "Pipeline aborted due to quality gate failure: ${qg.status}"
        }
      }
    })

    flow.end()
  }
}