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
        TOTAL = 0
        PASSED = 0
        FAILED = 0
        SKIPPED = 0
    }

    stages {
        stage('Checkout') {
            steps {
                 checkout scmGit(branches: [[name: 'master']],
                     userRemoteConfigs: [[url: 'https://github.com/Rirog/Testiki.git']])
            }
        }

        stage('Build and Test in Docker') {
            steps {
                    sh "mvn clean compile test -DAPI_KEY=${env.API_KEY}"

            }
        }

        stage('Generate Allure Report') {
            steps {
                    script {
                        def allureResults = allure([
                            includeProperties: false,
                            jdk: '',
                            results: [[path: 'target/allure-results']],
                            reportBuildPolicy: 'ALWAYS',
                            report: 'allure-report'
                        ])

                        env.TOTAL_TESTS = allureReport.getTotal()
                        env.PASSED_TESTS = allureReport.getPassed()
                        env.FAILED_TESTS = allureReport.getFailed()
                        env.SKIPPED_TESTS = allureReport.getSkipped()
                    }
            }
        }

        stage('Send Report to Telegram') {
            steps {
                    script {
                        def allureReportUrl = "${env.BUILD_URL}allure/"
                        def message = """
                                        Тесты завершены!
                                        \nПроект: ${env.JOB_NAME}
                                        \nСборка: ${env.BUILD_NUMBER}
                                        Колечество тестов: ${TOTAL_TESTS}
                                        Успешные: ${PASSED_TESTS}
                                        Пропущенные: ${SKIPPED_TESTS}
                                        Проваленные: ${FAILED_TESTS}
                                        \nОтчёт: ${allureReportUrl}
                                        """
                        try {
                            sh """
                                curl -s -X POST "https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage" \
                                -d chat_id=${TELEGRAM_CHAT_ID} \
                                -d text="${message}"
                            """
                        } catch (e) {
                            echo e.message
                        }
                    }
            }
        }
    }


    post {
        always {
            echo 'Pipeline завершён'
        }
        failure {
            script {
                try {
                    sh """
                        curl -s -X POST "https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendMessage" \
                        -d chat_id=${TELEGRAM_CHAT_ID} \
                        -d text="❌ Тесты упали! ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                    """
                } catch(e){
                    echo e.message
                }
            }
        }
    }
}