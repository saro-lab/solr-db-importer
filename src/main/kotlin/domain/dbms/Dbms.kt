package domain.dbms

class Dbms(
    val name: String,
    val driver: String,
    val url: String,
) {
    companion object {
        fun create(name: String, driver: String, url: String): Dbms =
            Dbms(name, driver, url)
    }
}