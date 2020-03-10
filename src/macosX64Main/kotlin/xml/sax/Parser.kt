package xml.sax

import kotlinx.cinterop.*
import platform.Foundation.*
import platform.darwin.NSObject

actual class Parser actual constructor(val handler: Base) : NSObject(), NSXMLParserDelegateProtocol {

    private lateinit var parser: NSXMLParser

    actual fun parse(xml: String) {

        val bytes = xml.encodeToByteArray()

        // Make sure we pin the parser to prevent it vanishing while parsing is in progress.
        this.usePinned { _ ->
            // Pin bytes to allow data to remain available during parsing and give us a static ref for interop.
            bytes.usePinned { bytesRef ->
                val data = NSData.dataWithBytes(bytesRef.addressOf(0), bytes.size.convert())
                this.parser = NSXMLParser(data = data)
                this.parser.delegate = this
                this.parser.parse()
            }
        }

        this.parser.setDelegate(null)

        println("Parsing Done")
    }

    override fun parserDidStartDocument(parser: NSXMLParser) {
        this.handler.startDocument()
    }

    override fun parserDidEndDocument(parser: NSXMLParser) {
        this.handler.endDocument()
    }

    override fun parser(
        parser: NSXMLParser,
        didStartElement: String,
        namespaceURI: String?,
        qualifiedName: String?,
        attributes: Map<Any?, *>
    ) {
        this.handler.startElement(didStartElement, namespaceURI, qualifiedName, attributes as Map<String, String>)
    }

    override fun parser(parser: NSXMLParser, foundCharacters: String) {
        this.handler.characters(foundCharacters)
    }

    override fun parser(parser: NSXMLParser, didEndElement: String, namespaceURI: String?, qualifiedName: String?) {
        this.handler.endElement(didEndElement, namespaceURI, qualifiedName)
    }

    override fun parser(parser: NSXMLParser, parseErrorOccurred: NSError) {
        println(parseErrorOccurred.localizedDescription)
    }
}