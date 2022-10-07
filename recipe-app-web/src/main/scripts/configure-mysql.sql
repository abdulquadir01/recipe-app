## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE recipedb_dev;
CREATE DATABASE recipedb_prod;

#Create database service accounts
CREATE USER 'recipe_dev_user'@'localhost' IDENTIFIED BY 'pwd@123';
CREATE USER 'recipe_prod_user'@'localhost' IDENTIFIED BY 'pwd@123';
CREATE USER 'recipe_dev_user'@'%' IDENTIFIED BY 'pwd@123';
CREATE USER 'recipe_prod_user'@'%' IDENTIFIED BY 'pwd@123';

#Database grants
GRANT SELECT ON recipedb_dev.* to 'recipe_dev_user'@'localhost';
GRANT INSERT ON recipedb_dev.* to 'recipe_dev_user'@'localhost';
GRANT DELETE ON recipedb_dev.* to 'recipe_dev_user'@'localhost';
GRANT UPDATE ON recipedb_dev.* to 'recipe_dev_user'@'localhost';
GRANT SELECT ON recipedb_prod.* to 'recipe_prod_user'@'localhost';
GRANT INSERT ON recipedb_prod.* to 'recipe_prod_user'@'localhost';
GRANT DELETE ON recipedb_prod.* to 'recipe_prod_user'@'localhost';
GRANT UPDATE ON recipedb_prod.* to 'recipe_prod_user'@'localhost';
GRANT SELECT ON recipedb_dev.* to 'recipe_dev_user'@'%';
GRANT INSERT ON recipedb_dev.* to 'recipe_dev_user'@'%';
GRANT DELETE ON recipedb_dev.* to 'recipe_dev_user'@'%';
GRANT UPDATE ON recipedb_dev.* to 'recipe_dev_user'@'%';
GRANT SELECT ON recipedb_prod.* to 'recipe_prod_user'@'%';
GRANT INSERT ON recipedb_prod.* to 'recipe_prod_user'@'%';
GRANT DELETE ON recipedb_prod.* to 'recipe_prod_user'@'%';
GRANT UPDATE ON recipedb_prod.* to 'recipe_prod_user'@'%';