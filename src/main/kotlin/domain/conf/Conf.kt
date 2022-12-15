package domain.conf

data class Conf(
    var driver: String = "",
    var jdbcUrl: String = "",
    var username: String = "",
    var password: String = "",
    var bulkExecuteRowCount: Int = 0,
    var solrSchemaUrl: String = "",
    var select: String = "",
) {
    // for xml parser
    constructor(): this("")

    fun trim() {
        this.driver = this.driver.trim()
        this.jdbcUrl = this.jdbcUrl.trim()
        this.username = this.username.trim()
        this.password = this.password.trim()
        this.solrSchemaUrl = this.solrSchemaUrl.trim()
    }

    fun validate() {
        if (bulkExecuteRowCount < 10 || bulkExecuteRowCount > 100000) {
            throw RuntimeException("bulkExecuteRowCount must be greater than 10 and less than 100000.")
        }
    }
}
