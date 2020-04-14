package xml.sax

import kotlinx.io.core.Input
import kotlinx.io.core.readBytes

/**
 * This essentially allows platform abstraction and for us to write XSB in common.
 * It looks very much like XSB but does nothing aside fire SAX callbacks.
 */
class Parser(handler: Base) {
    fun parse(input: ByteReadChannel) {

        // input.
    }
}