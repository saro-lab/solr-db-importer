package domain.conf

class ConfVars {
    companion object {
        const val CONF_FILE_NAME_PURE: String = "db-import"
        const val CONF_FILE_NAME_EXT: String = "xml"
        const val CONF_FILE_NAME: String = "$CONF_FILE_NAME_PURE.$CONF_FILE_NAME_EXT"

    }
}