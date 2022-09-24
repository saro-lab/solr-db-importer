package domain.conf.ports

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import domain.conf.Conf
import domain.conf.ConfVars
import java.io.File


class ReadConf {
    fun handle(): Conf? {
        val file = File(ConfVars.CONF_FILE_NAME)

        if (!file.exists()) {
            return null
        }

        val xmlMapper: ObjectMapper = XmlMapper()

        val conf = xmlMapper.readValue(file.readText(Charsets.UTF_8), object: TypeReference<Conf>(){})
        conf?.validate()

        return conf
    }
}