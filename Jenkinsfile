pipeline {
    agent any
    tools {
        jdk 'JDK_INSTALLER'
        maven 'Mvn_3_6_3'
    }

    environment {
        TELEGRAM_CHAT_ID = 1894835556
        TELEGRAM_BOT_TOKEN = "${env.TELEGRAM_BOT_TOKEN}"
        ALLURE_RESULTS = 'target/allure-results'
        TOKEN_PUBLIC = credentials('TOKEN_PUBLIC')
        TOKEN_ADMIN = credentials('TOKEN_ADMIN')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: 'master']],
                    userRemoteConfigs: [[url: 'https://github.com/Rirog/Testiki.git']]
                )
            }
        }

        stage('Build and Test in Docker') {
            steps {
                sh "mvn clean compile test -DAPI_KEY=${env.API_KEY} -DTOKEN_ADMIN=${TOKEN_ADMIN} -DTOKEN_PUBLIC=${TOKEN_PUBLIC}"
            }
        }
    }

    post {
        always {
            script {
                allure(
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']],
                    reportBuildPolicy: 'ALWAYS',
                    report: 'allure-report'
                )

                def summaryFile = 'allure-report/widgets/summary.json'
                if (fileExists(summaryFile)) {
                    def summary = readFile summaryFile
                    def result = new groovy.json.JsonSlurper().parseText(summary)

                    total = result.statistic.total.toString()
                    passed = result.statistic.passed.toString()
                    failed = result.statistic.failed.toString()
                    skipped = result.statistic.skipped.toString()
                } else {
                    echo "Allure summary not found, using defaults"
                    total = 'N/A'
                    passed = 'N/A'
                    failed = 'N/A'
                    skipped = 'N/A'
                }

                def allureReportUrl = "${env.BUILD_URL}allure/"
                def message = """
                            Тесты завершены!
                            \nПроект: ${env.JOB_NAME}
                            \nСборка: ${env.BUILD_NUMBER}
                            \nКолечество тестов: ${TOTAL_TESTS}
                            \nУспешные: ${PASSED_TESTS}
                            \nПропущенные: ${SKIPPED_TESTS}
                            \nПроваленные: ${FAILED_TESTS}
                            \nОтчёт: ${allureReportUrl}
                            """
                sh """
                    curl -s -X POST "https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage" \
                    -d chat_id=${TELEGRAM_CHAT_ID} \
                    -d text="${message}"
                """
            }
        }
    }
}