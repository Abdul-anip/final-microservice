pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Services (Maven)') {
            parallel {
                stage('Anggota Service') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('anggota') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Buku Service') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('buku') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Peminjaman Service') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('peminjaman') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Pengembalian Service') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('pengembalian') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Email Service') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('email') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Eureka Server') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('eureka_server') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('API Gateway') {
                    agent {
                        docker {
                            image 'maven:3.9.6-eclipse-temurin-17'
                            args '--memory=1g --memory-swap=1g'
                        }
                    }
                    steps {
                        dir('api_gateway') {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker compose -f docker-compose-app.yml build'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker network create latihan_perpustakaan_elk || true'
                sh 'docker compose -f docker-compose-app.yml up -d'
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