# BAZA

- potrebno je kreirati bazu imena: assignment-product
- potrebno je promijeniti svog lokalnog POSTGRES usera unutar prifila: application-local.yml
- liquibase migracijska skripta ce prilikom startanja aplikacije kreirati bazu i insertati neke testne producte u bazu

# JAVA IntelliJ

- Java 11 se koristi, potrebno je setupirati unutar: File -> Project Structure -> Java JDK 11

# BUILD

- potrebno je unutar IntelliJa pokrenuti build ili unutar terminala pokrenuti komandu: ./gradlew build

# POKRETANJE

- potrebno je samo pokrenuti aplikaciju kao spring boot app

# TESTIRANJE

- otvoriti swagger ui unutar browsera: http://localhost:8888/assignment-product-service/swagger/webjars/swagger-ui/index.html
