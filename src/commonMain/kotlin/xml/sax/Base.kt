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

    fun startElement(namespaceURI: String?,
                     qualifiedName: String?,
                     attributes: Map<String?, String?>) {
        println("Start Element")
        println(qualifiedName)
    }

    fun endDocument() {
        println("End Document")
    }
}