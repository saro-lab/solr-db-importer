package domain.conf.ports

import domain.conf.ConfVars
import domain.dbms.Dbms
import domain.dbms.DbmsVars
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class NewConfExample {
    private val examples = "examples"
    fun handle() {
        File(examples).mkdirs()

        println("create example files")

        DbmsVars.dbmsMap.values.forEach(this::makeExampleFile)

        println()
        println("""create ${ConfVars.CONF_FILE_NAME} referring to the example file and run it again.""")
    }

    private fun makeExampleFile(dbms: Dbms) {
        val file = File("$examples/${ConfVars.CONF_FILE_NAME_PURE}-${dbms.name}.${ConfVars.CONF_FILE_NAME_EXT}")
        if (file.exists()) {
            println("$examples/${file.name} - exists")
        } else {
            FileOutputStream(file).use { fos ->
                OutputStreamWriter(fos, Charsets.UTF_8).use { os ->
                    os.write("""
                        <conf>
                            <driver>${dbms.driver}</driver>
                            <url>${dbms.url}</url>
                            <username>username</username>
                            <password>password</password>
                            <bulkExecuteRowCount>1000</bulkExecuteRowCount>
                            <removeXmlTagFields>field1, field2</removeXmlTagFields>
                            <select><![CDATA[
                                SELECT
                                    text_sn as id,
                                    title as subject,
                                    reg_dt
                                FROM test_table
                                WHERE text_sn > 0
                            ]]></select>
                        </conf>""".trimIndent()
                    )
                }
            }
            println("$examples/${file.name} - created")
        }
    }
}