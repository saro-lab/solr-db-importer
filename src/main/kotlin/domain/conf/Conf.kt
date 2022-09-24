package domain.conf

data class Conf(
    var driver: String = "",
    var url: String = "",
    var username: String = "",
    var password: String = "",
    var bulkExecuteRowCount: Int = 0,
    var removeXmlTagFields: String = "",
    var select: String = ""
) {
    // for xml parser
    constructor(): this("")

    fun trim() {
        this.driver = this.driver.trim()
        this.url = this.url.trim()
        this.username = this.username.trim()
        this.password = this.password.trim()
        this.removeXmlTagFields = this.removeXmlTagFields.trim()
    }

    fun validate() {
        if (bulkExecuteRowCount < 100 || bulkExecuteRowCount > 100000) {
            throw RuntimeException("bulkExecuteRowCount must be greater than 100 and less than 100000.")
        }
    }
}
