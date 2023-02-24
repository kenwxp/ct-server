#!/usr/bin/env groovy

// pipeline命令参考资料:
// [语法](https://www.jenkins.io/doc/book/pipeline/syntax/)
// [GIT命今](https://www.jenkins.io/doc/pipeline/steps/git/)
// [ssh命今](https://www.jenkins.io/doc/pipeline/steps/ssh-steps/)
// [工作流]https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/

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

    // 环境变量设置
    environment {
        REMOTE_WORK_DIR = '/home/cloudtimes-server'
    }

    stages {
        // 1. 拉最新的代码
        stage('Git CLone') {
            steps {
                git credentialsId: 'ct-git', branch: 'dev', url: 'http://10.1.65.235/ct/ct-server.git'
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
                sh 'bash ./build/replace_test_env.sh'
            }
        }

        // 3. 部署准备
        stage("Prepare Deploy") {
            steps {
                // 3.1 创建目录, 确保各级目录存在
                sshCommand remote: remote234, command: "mkdir -p $REMOTE_WORK_DIR/{backup,admin,detectionserver,business,socketserver}"

                // 3.2 上传(./build/)脚本
                // sshPut remote: remote234, from: './build/{start_all.sh,stop_all.sh,backup_all.sh}', into: "$REMOTE_WORK_DIR/"
                sshPut remote: remote234, from: './build/stop_all.sh', into: "$REMOTE_WORK_DIR/"
                sshPut remote: remote234, from: './build/backup_all.sh', into: "$REMOTE_WORK_DIR/"

                // 3.3 停止所有服务
                sshCommand remote: remote234, command: "bash $REMOTE_WORK_DIR/stop_all.sh"

                // 3.4 备份目录
                sshCommand remote: remote234, command: "bash $REMOTE_WORK_DIR/backup_all.sh"
            }
        }

        // 4. 上传部署
        stage('Deploy') {
            steps {
                script {
                    // 上传各个服务的文件
                    def services = ['admin', 'business', 'socketserver', 'detectionserver']
                    services.each { service ->
                        def remote_dir = "${REMOTE_WORK_DIR}/${service}/"
                        sshPut remote: remote234, from: "cloudtimes-${service}/target/cloudtimes-${service}.jar", into: remote_dir
                        sshPut remote: remote234, from: "cloudtimes-${service}/target/lib", into: remote_dir
                        sshPut remote: remote234, from: "cloudtimes-${service}/target/resources", into: remote_dir
                        sshPut remote: remote234, from: 'build/run.sh', into: remote_dir
                    }
                }
            }
        }

        // 5. 重启服务
        stage('Restart') {
            steps {
                sshCommand remote: remote234, command: "bash $REMOTE_WORK_DIR/start_all.sh"
                sshCommand remote: remote234, command: 'systemctl restart nginx.service'
            }
        }
    }
}
