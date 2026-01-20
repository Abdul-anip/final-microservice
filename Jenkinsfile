pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    tools {
        maven 'Maven 3.9'
        jdk 'JDK 21'
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // Build services SEQUENTIAL (one by one) to save RAM
        stage('Build Eureka Server') {
            steps {
                dir('eureka_server') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('api_gateway') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build Anggota Service') {
            steps {
                dir('anggota') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build Buku Service') {
            steps {
                dir('buku') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build Peminjaman Service') {
            steps {
                dir('peminjaman') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build Pengembalian Service') {
            steps {
                dir('pengembalian') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build Email Service') {
            steps {
                dir('email') {
                    sh 'mvn clean package -DskipTests -B -q'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t eureka-server:latest ./eureka_server'
                sh 'docker build -t api-gateway:latest ./api_gateway'
                sh 'docker build -t anggota-service:latest ./anggota'
                sh 'docker build -t buku-service:latest ./buku'
                sh 'docker build -t peminjaman-service:latest ./peminjaman'
                sh 'docker build -t pengembalian-service:latest ./pengembalian'
                sh 'docker build -t email-service:latest ./email'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker network create latihan_perpustakaan_elk_hanif || true'
                sh 'docker compose -f docker-compose-app.yml up -d --force-recreate'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed!'
        }
        always {
            cleanWs()
        }
    }
}