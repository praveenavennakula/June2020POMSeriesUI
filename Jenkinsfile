pipeline
{
    agent any

    tools{
    	maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                /*  sh "mvn -Dmaven.test.failure.ignore=true clean package" */
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }



        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }

        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/praveenavennakula/June2020POMSeriesUI.git'
                    /* sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml" */
                    bat "allure report clean"
                    bat "allure report clean -o allure-report"
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/runners/testng_regression.xml"

                }
            }
        }


        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }


        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'reports',
                                  reportFiles: 'TestExecutionReport.html',
                                  reportName: 'HTML Extent Report',
                                  reportTitles: ''])
            }
        }

        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/praveenavennakula/June2020POMSeriesUI.git'
                    /* sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml" */
                    bat "allure report clean"
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/runners/testng_sanity.xml"

                }
            }
        }

        stage('Publish sanity Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'reports',
                                  reportFiles: 'TestExecutionReport.html',
                                  reportName: 'HTML Sanity Extent Report',
                                  reportTitles: ''])
            }
        }


    }
}