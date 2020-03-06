package xml.sax

import kotlinx.cinterop.StableRef
import platform.Foundation.*
import platform.darwin.NSObject

actual class Parser actual constructor(val handler: Base) : NSObject(), NSXMLParserDelegateProtocol {

    private lateinit var parser: NSXMLParser

    actual fun parse(xml: String) {

        val bytes = xml.encodeToByteArray()
        val pointer = StableRef.create(bytes)
        val data = NSData.dataWithBytes(pointer.asCPointer(), bytes.size.toULong())
        this.parser = NSXMLParser(data)
        this.parser.delegate = this
        println("Parsing")
        this.parser.parse()
        println("Parsing Done")

    }

    override fun parserDidStartDocument(parser: NSXMLParser) {
        println("native start doc")
        this.handler.startDocument()
    }

    override fun parserDidEndDocument(parser: NSXMLParser) {
        println("native end doc")
        this.handler.endDocument()
    }

    override fun parser(
        parser: NSXMLParser,
        didStartElement: String,
        namespaceURI: String?,
        qualifiedName: String?,
        attributes: Map<Any?, *>
    ) {
        // TODO map attributes
        println("native start element")
        this.handler.startElement(namespaceURI, qualifiedName, mapOf())
    }

    override fun parser(parser: NSXMLParser, parseErrorOccurred: NSError) {
        println(parseErrorOccurred.localizedDescription)
    }
}