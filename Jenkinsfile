#!/usr/bin/env groovy

node {
    if (env.BRANCH_NAME.startsWith('PR')) {
        return;
    }

    def image;
    def currentStage;
    def entryPoint = '-d';
    try {
        stage('Cleanup and Checkout') {
            sh("echo Branch_name:${env.BRANCH_NAME}");
            cleanWs();
            checkout([
                $class: 'GitSCM',
                branches: [[name: "${env.BRANCH_NAME}"]],
                doGenerateSubmoduleConfigurations: false,
                extensions: [],
                submoduleCfg: [],
                userRemoteConfigs: [[credentialsId: 'Yorisoft', url: 'https://github.com/Yorisoft/advanced-java']]
            ])
        }

        currentStage = 'Build Image';
        stage(currentStage) {
            image = docker.build('advanced-java');
        }

        currentStage = 'Check Java Version';
        stage(currentStage) {
            image.inside(entryPoint) {
                sh('java -version');
            }
        }

        currentStage = 'Run CSC 239 Final Project';
        stage(currentStage) {
            image.inside(entryPoint) {
                dir('csc-239-final') {
                    try{ 
                        //echo("echo $TEST_USER_URL > $WORKSPACE/grr.txt"); //prints to test file (nick)
                        sh('javac WritePokeRec.java')
                        sh('java WritePokeRec')
                        sh ('sleep 30s')

                        sh('javac ReadPokeRec.java')
                        sh('java ReadPokeRec')
                        sh ('sleep 30s')
                    } catch (err){
                        echo err.getMessage()
                        throw err
                    }
                }
            }
        }
    }
    catch (e) {
        currentBuild.result = 'FAILURE';
        throw e
    }
    finally {
        stage('Create Archive'){
            archiveArtifacts allowEmptyArchive: true, artifacts: '**', excludes: '**/node_modules/', followSymlinks: false;
            //archiveArtifacts allowEmptyArchive: true, artifacts: 'record/screen_shots/newegg/*.png, record/screen_shots/bestbuy/*.png, record/screen_shots/target/*.png'; 
        }

        stage('cleanup'){
            cleanWs();
            sh('docker system prune -a -f');
        }
    }
}