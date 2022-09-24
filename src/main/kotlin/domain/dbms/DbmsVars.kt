package domain.dbms

class DbmsVars {
    companion object {
        val dbmsMap = mapOf<String, Dbms>(
            "mysql" to Dbms.create("mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/dbname"),
            "oracle10" to Dbms.create("oracle10", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe"),
            "oracle8" to Dbms.create("oracle8", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe"),
            "oracle6" to Dbms.create("oracle6", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe"),
            "postgresql" to Dbms.create("postgresql", "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/dbname"),
            "mssql" to Dbms.create("mssql", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://localhost:1433;databaseName=dbname"),
            "mariadb" to Dbms.create("mariadb", "org.mariadb.jdbc.Driver", "jdbc:mariadb://localhost:3306/dbname"),
            "h2" to Dbms.create("h2", "org.h2.Driver", "jdbc:h2:file:dbFilePath"),

//            "db2-app" to Dbms.create("db2-app", "com.ibm.db2.jdbc.app.DB2Driver", "jdbc:db2:dbname"),
//            "db2-net" to Dbms.create("db2-net", "com.ibm.db2.jdbc.net.DB2Driver", "jdbc:db2://localhost:6789/dbname"),
//            "sap-hana" to Dbms.create("sap-hana", "com.sap.cloud.db.jdbc.Driver", ""),
//            "informix" to Dbms.create("informix", "", ""),
//            "firebird" to Dbms.create("firebird", "", ""),
//            "hsqldb" to Dbms.create("hsqldb", "", ""),
//            "derby" to Dbms.create("derby", "", ""),
        )
    }
}