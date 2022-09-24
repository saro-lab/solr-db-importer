package domain.conf

import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class NewConf {
    fun handle() {
        FileOutputStream(File(Vars.CONF_FILE_NAME)).use { fos ->
            OutputStreamWriter(fos, Charsets.UTF_8).use { os ->
                os.write(
"""
/* mysql, mssql, oracle */
{
    dbms: "oracle",
    host: "localhost",
    port: 0,
    db: "test-db",
    trimField: "test-subject,test-content",
    removeTagField: "text-content",
    bulk-import-row-count: 1000,
    import-query: "select text_sn as id, title as subject, reg_dt from test_table"
}
""".trim()
                )
            }
        }

        println("""
            ${Vars.CONF_FILE_NAME} created !!
            please edit the "${Vars.CONF_FILE_NAME}" file and run it again.
        """.trimIndent())
    }
}