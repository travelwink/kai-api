pipeline {
    agent any

    environment {
        // 定义分支到环境的映射关系
        BRANCH_ENV_MAPPING = [
            'master': 'prod',
            'main': 'prod',
            'prd': 'prod',
            'uat': 'uat',
            'test': 'test',
            'develop': 'dev'
        ]

        // 默认环境，未匹配到分支时使用
        DEFAULT_ENV = 'dev'

        IMAGE_NAME = "slm-admin-api:${BUILD_NUMBER}"
        HARBOR_URL = "harbor.foton.com.cn"
        HARBOR_PROJECT_NAME = "pjbom"

        // 设置Kubernetes集群相关信息
        K8S_NAME_SPACE = "epc-admin"
        K8S_KIND = "deployments"
        K8S_NAME = "epc-admin-api-deployment"

        // Kuboard信息
        KUBOARD_SERVER_URL = "http://10.100.6.136:81"
        KUBOARD_USERNAME = "admin"
        KUBOARD_ACCESS_KEY = "be377r2kft3e.wkkcyzyd3xckjwkpzxt5d6sdd7zwtfpf"
    }

    stages {
        stage('Setup Environment') {
            steps {
                script {
                    def currentBranch = env.BRANCH_NAME

                    // 根据分支名称确定环境
                    def targetEnv = env.BRANCH_ENV_MAPPING.get(currentBranch, env.DEFAULT_ENV)
                    env.DEPLOY_ENV = targetEnv
                    if (targetEnv ==  'prod') {
                        env.K8S_CLUSTER_CONTEXT = "kai-cluster-prd"
                    } else if (targetEnv == 'uat') {
                        env.K8S_CLUSTER_CONTEXT = "kai-cluster"
                    }

                    echo "部署环境：${env.DEPLOY_ENV}"
                    echo "K8s集群名称：${env.K8S_CLUSTER_CONTEXT}"
                }
            }
        }
        stage('Use gradle wrapper compile src') {
            steps {
                sh "chmod +x gradlew"
                sh "./gradlew clean build -x test"
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // 构建镜像时添加环境标签
                    def dockerImage = docker.build("${IMAGE_NAME}", "--build-arg PROFILE=${env.DEPLOY_ENV} .")
                }
            }
        }
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'harbor_credential', passwordVariable: 'HARBOR_PASSWORD', usernameVariable: 'HARBOR_USERNAME')]) {
                    sh "docker login ${HARBOR_URL} -u ${HARBOR_USERNAME} -p ${HARBOR_PASSWORD}"
                }
            }
        }
        stage('Push image') {
            steps {
                sh "docker tag ${IMAGE_NAME} ${HARBOR_URL}/${HARBOR_PROJECT_NAME}/${IMAGE_NAME}"
                sh "docker push ${HARBOR_URL}/${HARBOR_PROJECT_NAME}/${IMAGE_NAME}"
            }
        }
        stage('Kuboard deployment') {
            steps {
                // 更新镜像
                sh """
                    curl -X PUT \
                        -H "content-type: application/json" \
                        -H "Cookie: KuboardUsername=${KUBOARD_USERNAME}; KuboardAccessKey=${KUBOARD_ACCESS_KEY}" \
                        -d '{"kind":${K8S_KIND},"namespace":${K8S_NAME_SPACE},"name":${K8S_NAME},"images":{"${HARBOR_URL}/${HARBOR_PROJECT_NAME}/${IMAGE_NAME}"}}' \
                        "${KUBOARD_SERVER_URL}/kuboard-api/cluster/${K8S_CLUSTER_CONTEXT}/kind/CICDApi/admin/resource/updateImageTag"
                """
                // 重启工作负载
                sh """
                    curl -X PUT \
                        -H "Content-Type: application/yaml" \
                        -H "Cookie: KuboardUsername=${KUBOARD_USERNAME}; KuboardAccessKey=${KUBOARD_ACCESS_KEY}" \
                        -d '{"kind":${K8S_KIND},"namespace":${K8S_NAME_SPACE},"name":${K8S_NAME}}' \
                        "${KUBOARD_SERVER_URL}/kuboard-api/cluster/${K8S_CLUSTER_CONTEXT}/kind/CICDApi/admin/resource/restartWorkload"
                """
            }
        }
    }
}