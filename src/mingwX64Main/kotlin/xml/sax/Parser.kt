package xml.sax

import kotlinx.cinterop.*
import libxml2.*

/**
 * This essentially allows platform abstraction and for us to write XSB in common.
 * It looks very much like XSB but does nothing aside fire SAX callbacks.
 */
actual class Parser actual constructor(val handler: Base) {

    var libxmlSaxHandler: CValue<xmlSAXHandler>? = null

    actual fun parse(xml: String) {

        println("PARSE!")

        memScoped {
            libxmlSaxHandler = cValue {
                initialized = XML_SAX2_MAGIC
                startDocument = staticCFunction { _ ->
                    println("Libxml2 Start Document")
                }
            }
            println(libxmlSaxHandler)

            val parserContext = xmlCreatePushParserCtxt(
                libxmlSaxHandler!!.getPointer(this),
                StableRef.create(this@Parser).asCPointer(), xml, xml.length.convert(), null)

            xmlParseChunk(parserContext,xml, xml.length.convert(), 0)
        }

    }
}