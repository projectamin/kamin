package xml.sax

class Base {

    var handler: Base? = null

    private val parser: Parser = Parser(this)

    fun parse(xml: String) {
        this.parser.parse(xml)
    }

    fun startDocument() {
        println("Start Document")
    }

    fun startElement(
        element: String,
        namespaceURI: String?,
        qualifiedName: String?,
        attributes: Map<String?, String?>) {
        println("Start Element")
        println(element)
    }

    fun endDocument() {
        println("End Document")
    }
}