package xml.sax

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import javax.xml.parsers.SAXParserFactory

/**
 * This essentially allows platform abstraction and for us to write XSB in common.
 * It looks very much like XSB but does nothing aside fire SAX callbacks.
 */
actual class Parser actual constructor(val handler: Base): DefaultHandler() {

    val parser = SAXParserFactory.newInstance().newSAXParser()

    actual fun parse(xml: String) {
        val stream = ByteArrayInputStream(xml.toByteArray())
        this.parser.parse(stream, this)
    }

    override fun startDocument() {
        this.handler.startDocument()
    }

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        // TODO handle attributes, map to common definition.
        this.handler.startElement(qName!!, uri, localName, mapOf())
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        val section = ch!!.slice(start..length + 1).toCharArray()
        this.handler.characters(section.let { String(it) })
    }

    override fun endDocument() {
        this.handler.endDocument()
    }
}