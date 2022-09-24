package domain.solrconn

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class SolrConnector(
    private val solrSchemaUrl: String,
    private val restTemplate: RestTemplate = RestTemplate()
) {

    fun update(list: MutableList<String>) {
        if (list.isEmpty()) {
            return
        }

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val url = "$solrSchemaUrl/update?commit=true"
        val data = list.joinToString(",", "[", "]")

        val rest: String? = restTemplate.postForObject(url, HttpEntity<String>(data, headers), String::class.java)

        list.clear()
    }
}