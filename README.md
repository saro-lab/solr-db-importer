# Solr DB Importer
- Support Solr 9.0 or later
  - Alternative solr DIH (deprecated in Solr 9.0)
- Support DB
  - mariadb
  - oracle
  - mssql
  - mysql
  - postgresql
  - h2
- JDK 11 or later

# Quick Start
- download [solr-db-importer-1.4.jar](https://github.com/saro-lab/solr-db-importer/releases/download/1.4/solr-db-importer-1.4.jar)
  ```
  java -jar solr-db-importer-1.4.jar
  # created examples
  ```
- copy db-import.xml
  ```
  # example
  cp ./examples/db-import-mariadb.xml ./db-import.xml
  ```
- modify db-import.xml
  ```
  <conf>
    <driver>org.mariadb.jdbc.Driver</driver>
    <jdbcUrl>jdbc:mariadb://localhost:3306/dbname</jdbcUrl>
    <username>username</username>
    <password>password</password>
    <bulkExecuteRowCount>1000</bulkExecuteRowCount>
    <solrSchemaUrl>http://localhost:8983/solr/schema_name</solrSchemaUrl>
    <select><![CDATA[
        SELECT
            text_sn as id,
            title as subject,
            reg_dt
        FROM test_table
        WHERE text_sn > 0
    ]]></select>
  </conf>
  ```
- execute again
  ```
  java -jar solr-db-importer-1.4.jar
  ```

# how to build
```
gradlew jar
```
- build/libs/solr-db-importer-{version}.jar