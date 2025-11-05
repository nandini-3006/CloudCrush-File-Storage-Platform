pipeline {
    agent any

    environment {
        DOCKER_HUB_USER = 'nandini3006'
        IMAGE_PREFIX = "${DOCKER_HUB_USER}/cloudcrush"
    }

    stages {

        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['eureka-server', 'api-gateway', 'auth-service', 'metadata-service', 'storage-service']
                    for (service in services) {
                        sh """
                        echo "Building image for ${service}..."
                        docker build -t ${IMAGE_PREFIX}-${service}:latest ${service}
                        """
                    }
                }
            }
        }

        stage('Push Images to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    for service in eureka-server api-gateway auth-service metadata-service storage-service; do
                        docker push ${IMAGE_PREFIX}-\${service}:latest
                    done
                    """
                }
            }
        }

        stage('Run Containers like Docker Compose') {
            steps {
                script {
                    sh """
                    # Stop and remove any old containers
                    docker rm -f redis-server eureka-server api-gateway auth-service metadata-service storage-service || true
                    docker network rm cloudcrush-net || true

                    # Create network
                    docker network create cloudcrush-net

                    # Run Redis
                    docker run -d --name redis-server --network cloudcrush-net -p 6379:6379 redis:7

                    # Run Eureka Server
                    docker run -d --name eureka-server --network cloudcrush-net -p 8761:8761 ${IMAGE_PREFIX}-eureka-server:latest

                    # Run API Gateway
                    docker run -d --name api-gateway --network cloudcrush-net -p 8080:8080 ${IMAGE_PREFIX}-api-gateway:latest

                    # Run Auth Service
                    docker run -d --name auth-service --network cloudcrush-net -p 8081:8081 -e SPRING_DATA_REDIS_HOST=redis-server -e SPRING_DATA_REDIS_PORT=6379 ${IMAGE_PREFIX}-auth-service:latest

                    # Run Metadata Service
                    docker run -d --name metadata-service --network cloudcrush-net -p 8082:8082 -e SPRING_DATA_REDIS_HOST=redis-server -e SPRING_DATA_REDIS_PORT=6379 ${IMAGE_PREFIX}-metadata-service:latest

                    # Run Storage Service
                    docker run -d --name storage-service --network cloudcrush-net -p 8083:8083 ${IMAGE_PREFIX}-storage-service:latest
                    """
                }
            }
        }
    }
}
