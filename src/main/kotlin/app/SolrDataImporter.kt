package app

import domain.conf.Conf
import domain.conf.NewConfExample
import domain.conf.ReadConf
import domain.dbconn.DbConnector
import domain.dbimport.ImportData

fun main() {
    println("""
        ----------------------------------------------------
        Solr DB importer
        github: https://github.com/saro-lab/solr-db-importer
        ----------------------------------------------------
        
        Loading .....
        
    """.trimIndent())

    val conf: Conf? = ReadConf.handle()

    if (conf != null) {
        DbConnector.conn(conf) { rs ->
            ImportData(conf, rs).handle()
        }
    } else {
        NewConfExample.handle()
    }
}
