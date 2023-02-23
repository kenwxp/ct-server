#!/usr/bin/env groovy

// pipeline命令参考资料:
// https://www.jenkins.io/doc/pipeline/steps/git/
// https://www.jenkins.io/doc/pipeline/steps/ssh-steps/
// https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/

// 定义ssh远程服务器参数
def remote234 = [
        name         : 'test remote 234',
        host         : '10.1.65.234',
        user         : 'root',
        password     : 'htzee123',
        allowAnyHosts: true
]

pipeline {
    agent { label 'java' }


    // 自动丢弃历史构建记录
    options {
      buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '6', daysToKeepStr: '7', numToKeepStr: '6')
    }

    stages {
        // :TODO: 停止所有服务
        // stage("Stop Services") {
        //     steps {
        //         // 执行脚本, 创建目录
        //         sshScript remote: remote234, script: 'build/stop_all.sh'
        //     }
        // }

        // 1. 拉最新的代码
        stage('Git CLone') {
            steps {
                git credentialsId: 'ct-git',
                        branch: 'dev',
                        url: 'http://10.1.65.235/ct/ct-server.git'
            }
        }

        // 2. 打包
        stage('Maven Build') {
            steps {
                sh 'mvn clean compile package -Dmaven.test.skip=true'
            }
        }

        // 3. 替换环境变量
        stage('Replace Env') {
            steps {
                sh './build/replace_test_env.sh'
            }
        }

        // 4. 上传部署
        stage('Update Results') {
            steps {
                // 1. 执行脚本, 创建目录
                sshCommand remote: remote234, command: 'mkdir -p /home/cloudtimes-server/{admin,detectionserver,business,socketserver}'

                // 2. 放文件 (admin)
                sshPut remote: remote234, from: 'cloudtimes-admin/target/cloudtimes-admin.jar', into: '/home/cloudtimes-server/admin/'
                sshPut remote: remote234, from: 'cloudtimes-admin/target/lib', into: '/home/cloudtimes-server/admin/'
                sshPut remote: remote234, from: 'cloudtimes-admin/target/resources', into: '/home/cloudtimes-server/admin/'
                sshPut remote: remote234, from: 'build/run.sh', into: '/home/cloudtimes-server/admin/'

                // 3. 放文件 (business)
                sshPut remote: remote234, from: 'cloudtimes-business/target/cloudtimes-business.jar', into: '/home/cloudtimes-server/business/'
                sshPut remote: remote234, from: 'cloudtimes-business/target/lib', into: '/home/cloudtimes-server/business/'
                sshPut remote: remote234, from: 'cloudtimes-business/target/resources', into: '/home/cloudtimes-server/business/'
                sshPut remote: remote234, from: 'build/run.sh', into: '/home/cloudtimes-server/business/'

                // 4. 放文件 (socketserver)
                sshPut remote: remote234, from: 'cloudtimes-socketserver/target/cloudtimes-socketserver.jar', into: '/home/cloudtimes-server/socketserver/'
                sshPut remote: remote234, from: 'cloudtimes-socketserver/target/lib', into: '/home/cloudtimes-server/socketserver/'
                sshPut remote: remote234, from: 'cloudtimes-socketserver/target/resources', into: '/home/cloudtimes-server/socketserver/'
                sshPut remote: remote234, from: 'build/run.sh', into: '/home/cloudtimes-server/socketserver/'

                // 5. 放文件 (detectionserver)
                sshPut remote: remote234, from: 'cloudtimes-detectionserver/target/cloudtimes-detectionserver.jar', into: '/home/cloudtimes-server/detectionserver/'
                sshPut remote: remote234, from: 'cloudtimes-detectionserver/target/lib', into: '/home/cloudtimes-server/detectionserver/'
                sshPut remote: remote234, from: 'cloudtimes-detectionserver/target/resources', into: '/home/cloudtimes-server/detectionserver/'
                sshPut remote: remote234, from: 'build/run.sh', into: '/home/cloudtimes-server/detectionserver/'
            }
        }

        // :TODO: 启动所有服务
    }
}
