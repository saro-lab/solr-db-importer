import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "me.saro"
version = "1.4"

repositories {
    mavenCentral()
}

dependencies {

    // drivers
    implementation("com.oracle.database.jdbc:ojdbc10:19.16.0.0")
    implementation("com.oracle.database.jdbc:ojdbc8:21.7.0.0")
    implementation("com.oracle.database.jdbc:ojdbc6:11.2.0.4")
    implementation("com.mysql:mysql-connector-j:9.1.0")

    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.microsoft.sqlserver:mssql-jdbc:11.2.1.jre11")
    implementation("com.ibm.db2:jcc:11.5.7.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.0.6")
    implementation("com.sap.cloud.db.jdbc:ngdbc:2.14.7")
    implementation("com.ibm.informix:jdbc:4.50.8")
    implementation("org.firebirdsql.jdbc:jaybird:4.0.6.java11")
    implementation("org.hsqldb:hsqldb:2.7.1")
    implementation("com.h2database:h2:2.2.220")
    implementation("org.apache.derby:derby:10.17.1.0")

    // RestTemplate
    implementation("org.springframework:spring-web:6.1.12")

    // xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.1")
    implementation("com.fasterxml.woodstox:woodstox-core:6.4.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.jar {
    manifest {
        attributes(mapOf("Main-Class" to "app.SolrDataImporterKt"))
    }
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")

}

application {
    mainClass.set("app.SolrDataImporterKt")
}
