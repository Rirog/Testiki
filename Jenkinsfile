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
        MAVEN_OPTS = '-Xmx2048m -Xms512m'
        JAVA_HOME = "${tool 'JDK_INSTALLER'}"
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
                    allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/allure-results']],
                        reportBuildPolicy: 'ALWAYS'
                    ])
            }
        }

        stage('Send Report to Telegram') {
            steps {
                    script {
                        def allureReportUrl = "${env.BUILD_URL}allure/"
                        def message = "✅ Тесты завершены!\nПроект: ${env.JOB_NAME}\nСборка: ${env.BUILD_NUMBER}\nОтчёт: ${allureReportUrl}"
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