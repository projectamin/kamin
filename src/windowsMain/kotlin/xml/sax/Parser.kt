package xml.sax

import kotlinx.cinterop.*
import libxml2.*

/**
 * This essentially allows platform abstraction and for us to write XSB in common.
 * It looks very much like XSB but does nothing aside fire SAX callbacks.
 */
actual class Parser actual constructor(handler: Base) {
    actual fun parse(xml: String) {
    }
}