package domain.conf

import java.io.File

class ReadConf {
    fun handle(): Boolean {
        val file = File(Vars.CONF_FILE_NAME)

        if (!file.exists()) {
            NewConf().handle()
            return false
        }

        return true
    }
}