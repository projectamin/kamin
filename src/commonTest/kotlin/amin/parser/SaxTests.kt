package amin.parser

import xml.sax.Base
import xml.sax.Parser
import kotlin.test.Test
import kotlin.test.assertTrue

class SaxTests {

    @Test
    fun createParser() {
        val handler = Base()
        val parser = Parser(handler)
    }

    @Test
    fun parseDoc() {
        val xml = "<machine xmlns:amin='http://projectamin.org/ns/'></machine>"
        val handler = Base()
        val parser = Parser(handler)
        parser.parse(xml)
    }
}