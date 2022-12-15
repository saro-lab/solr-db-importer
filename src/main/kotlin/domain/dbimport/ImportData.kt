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
    private lateinit var md: ResultSetMetaData
    private lateinit var columns: List<String>
    private lateinit var solr: SolrConnector
    private val maxLen: Int = Short.MAX_VALUE.toInt() * 10
    private val om = ObjectMapper()

    fun handle() {
        init()

        //val list = mutableListOf<String>()
        val sb = StringBuilder(maxLen).append('[')
        val flushSize = conf.bulkExecuteRowCount
        var index = 0

        while (rs.next()) {
            bindJson(sb)

            if (++index % flushSize == 0 || sb.length > maxLen) {
                solr.update(sb.append(']'))
                sb.setLength(0)
                sb.append('[')
                println("update row $index")
            }
        }

        solr.update(sb.append(']'))
        println("update row $index - done")
    }

    private fun init() {
        md = rs.metaData
        val columnCount = md.columnCount

        columns = (1 .. columnCount).map { md.getColumnLabel(it) }

        solr = SolrConnector(conf.solrSchemaUrl)
    }

    private fun bindJson(sb: StringBuilder) {

        if (sb.length > 1) {
            sb.append(',')
        }
        sb.append('{')

        var first = true;

        columns.forEachIndexed { i, name ->
            val v = rs.getString(i + 1)
            if (!first) {
                sb.append(',');
            } else {
                first = false;
            }
            if (v != null) {

                sb.append('"').append(name).append("\":").append(om.writeValueAsString(v))
            }
        }

        sb.append('}')
    }
}