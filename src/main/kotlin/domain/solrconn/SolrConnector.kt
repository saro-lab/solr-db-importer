package domain.solrconn

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class SolrConnector(
    private val solrSchemaUrl: String,
    private val restTemplate: RestTemplate = RestTemplate()
) {

    fun update(sb: StringBuilder) {
        if (sb.length <= 2) {
            return
        }

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val url = "$solrSchemaUrl/update?commit=true"
        val data = sb.toString()

        restTemplate.postForObject(url, HttpEntity<String>(data, headers), String::class.java)
    }
}