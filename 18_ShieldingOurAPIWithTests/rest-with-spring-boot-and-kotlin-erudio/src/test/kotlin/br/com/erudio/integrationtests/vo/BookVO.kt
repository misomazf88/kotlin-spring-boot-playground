package br.com.erudio.integrationtests.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.xml.bind.annotation.XmlRootElement
import java.util.*

@XmlRootElement
@JsonIgnoreProperties
data class BookVO (

    var id: Long = 0,
    var author: String = "",
    var launchDate: Date? = null,
    var price: Double = 0.0,
    var title: String = ""
)