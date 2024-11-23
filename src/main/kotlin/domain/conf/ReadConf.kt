package domain.conf

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File


class ReadConf {
    companion object {
        fun handle(path: String): Conf? {
            val file = File(path)

            if (!file.exists()) {
                println("not found conf file: ${file.canonicalPath}")
                println()
                return null
            }

            val xmlMapper: ObjectMapper = XmlMapper()

            val conf = xmlMapper.readValue(file.readText(Charsets.UTF_8), object: TypeReference<Conf>(){})
            conf?.validate()

            return conf
        }
    }
}