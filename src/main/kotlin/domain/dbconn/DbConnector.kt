package domain.dbconn

import domain.conf.Conf
import java.sql.DriverManager
import java.sql.ResultSet

class DbConnector {
    companion object {
        fun conn(conf: Conf, result: (ResultSet) -> Unit) {
            Class.forName(conf.driver)
            DriverManager.getConnection(conf.jdbcUrl, conf.username, conf.password).use { conn ->
                conn.prepareStatement(conf.select, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).use { stmt ->
                    stmt.executeQuery().use { rs ->
                        result(rs)
                    }
                }
            }
        }
    }
}