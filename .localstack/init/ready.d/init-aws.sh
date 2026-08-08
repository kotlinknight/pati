#!/bin/bash
echo "Initializing LocalStack RDS..."

awslocal rds create-db-instance \
    --db-instance-identifier patidb-instance \
    --db-name patidb \
    --engine postgres \
    --master-username postgres \
    --master-user-password postgres \
    --allocated-storage 20 \
    --db-instance-class db.t3.micro

echo "Waiting for RDS instance to become available..."
awslocal rds wait db-instance-available --db-instance-identifier patidb-instance

echo "LocalStack Initialization Complete!"
