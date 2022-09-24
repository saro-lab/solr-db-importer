import domain.conf.ports.NewConfExample
import domain.conf.ports.ReadConf
import domain.dbimport.ports.ImportData

fun main(args: Array<String>) {
    println("""
        ----------------------------------------------------
        Solr DB importer
        github: https://github.com/saro-lab/solr-db-importer
        ----------------------------------------------------
    """.trimIndent())

    val conf = ReadConf().handle()

    if (conf != null) {
        ImportData().handle(conf)
    } else {
        NewConfExample().handle()
    }
}