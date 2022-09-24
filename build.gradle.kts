import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "me.saro"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    // drivers
    implementation("com.oracle.database.jdbc:ojdbc10:19.16.0.0")
    implementation("com.oracle.database.jdbc:ojdbc8:21.7.0.0")
    implementation("com.oracle.database.jdbc:ojdbc6:11.2.0.4")
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("com.microsoft.sqlserver:mssql-jdbc:11.2.1.jre11")
    implementation("com.ibm.db2:jcc:11.5.7.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.0.6")
    implementation("com.sap.cloud.db.jdbc:ngdbc:2.14.7")
    implementation("com.ibm.informix:jdbc:4.50.8")
    implementation("org.firebirdsql.jdbc:jaybird:4.0.6.java11")
    implementation("org.hsqldb:hsqldb:2.7.0")
    implementation("com.h2database:h2:2.1.214")
    implementation("org.apache.derby:derby:10.15.2.0")

    // RestTemplate
    implementation("org.springframework:spring-web:5.3.23")

    // xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.4")
    implementation("com.fasterxml.woodstox:woodstox-core:6.3.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}

