package amin.parser

import kotlinx.io.core.BytePacketBuilder
import kotlinx.io.core.toByteArray
import xml.sax.Base
import xml.sax.Parser
import kotlin.test.Test

class SaxTests {

    @Test
    fun createParser() {
        val handler = Base()
        val parser = Parser(handler)
    }

    @ExperimentalStdlibApi
    @Test
    fun parseDoc() {
        println("Parsedoc Test")
        val xml = "<machine xmlns:amin='http://projectamin.org/ns/'><name>Amin::Machine::Dispatcher</name><filter name='Amin::Chroot'></filter></machine>"
        val handler = Base()
        val parser = Parser(handler)
        val input = BytePacketBuilder().append(xml).build()
        parser.parse(input)
    }
}