package app

import domain.conf.Conf
import domain.conf.NewConfExample
import domain.conf.ReadConf
import domain.dbconn.DbConnector
import domain.dbimport.ImportData

fun main(args: Array<String>) {
    println("""
        ----------------------------------------------------
        Solr DB importer
        github: https://github.com/saro-lab/solr-db-importer
        ----------------------------------------------------
        
        Loading .....
        
    """.trimIndent())

    when (args.size) {
        0 -> NewConfExample.handle()
        1 -> {
            ReadConf.handle(args.first())
                ?.run {
                    DbConnector.conn(this) { rs ->
                        ImportData(this, rs).handle()
                    }
                }
        }
        else -> println("The parameter allows only a single configuration file path.")
    }
}
