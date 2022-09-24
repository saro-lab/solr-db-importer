package domain.dbimport

import com.fasterxml.jackson.databind.ObjectMapper
import domain.conf.Conf
import domain.solrconn.SolrConnector
import java.sql.ResultSet
import java.sql.ResultSetMetaData

class ImportData(
    private val conf: Conf,
    private val rs: ResultSet
) {
    private var objectMapper: ObjectMapper = ObjectMapper()
    private var removeTagIndexList: List<Boolean> = listOf()
    private lateinit var md: ResultSetMetaData
    private lateinit var columns: List<String>
    private lateinit var solr: SolrConnector

    fun handle() {
        init()

        val list = mutableListOf<String>()
        val flushSize = conf.bulkExecuteRowCount
        var index = 0

        while (rs.next()) {
            list.add(toJson())

            if (++index % flushSize == 0) {
                solr.update(list)
                println("update row $index")
            }
        }

        solr.update(list)
        println("update row $index - done")
    }

    private fun init() {
        md = rs.metaData
        val columnCount = md.columnCount

        columns = (1 .. columnCount).map { md.getColumnName(it) }
        solr = SolrConnector(conf.solrSchemaUrl)
        val removeXmlTagFields = conf.removeXmlTagFields.split(",").map { it.trim() }.filter { it.isNotBlank() }
        removeTagIndexList = columns.map { removeXmlTagFields.contains(it) }
    }

    private fun toJson(): String {
        val map = mutableMapOf<String, String>()

        columns.forEachIndexed { i, name ->
            if (removeTagIndexList[i]) {
                map[name] = removeTag(rs.getString(i + 1))
            } else {
                map[name] = rs.getString(i + 1)
            }
        }

        return objectMapper.writeValueAsString(map)
    }

    private fun removeTag(text: String): String {
        return text
    }
}