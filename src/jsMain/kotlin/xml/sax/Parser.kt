package xml.sax

import saxophone.Saxophone
import saxophone.saxophone

/**
 * This essentially allows platform abstraction and for us to write XSB in common.
 * It looks very much like XSB but does nothing aside fire SAX callbacks.
 */
actual class Parser actual constructor(handler: Base) {

    val parser: Saxophone = saxophone()

    actual fun parse(xml: String) {
    }
}