# Notification service

## Prerequisites

### Mongodb
Install Mongodb from Docker Hub

`docker pull bitnami/mongodb:7.0.11`

Start Mongodb server at port 27017 with root username and password: root/root

Với các bản bitnami: (dùng được cho Win)
`docker run -d --name mongodb-7.0.11 
-p 27017:27017 
-e MONGODB_ROOT_USER=root 
-e MONGODB_ROOT_PASSWORD=root 
bitnami/mongodb:7.0.11`

Với các bản enterpirse: (dùng được cho Mac)
`docker run -d --name mongodb-7.0.11 \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=root \
-e MONGO_INITDB_ROOT_PASSWORD=root \
mongodb/mongodb-enterprise-server:7.0.11-ubuntu2204`