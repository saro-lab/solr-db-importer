import domain.conf.ReadConf

fun main(args: Array<String>) {
    println("""
        ----------------------------------------------------
        Solr DB importer
        github: https://github.com/saro-lab/solr-db-importer
        ----------------------------------------------------
    """.trimIndent())

    val conf = ReadConf().handle()

}