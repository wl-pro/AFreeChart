/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * ----------------
 * RootHandler.java
 * ----------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2003-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 23-Jan-2003 : Version 1 (DG);
 *
 */

package org.afree.data.xml;

import java.util.Stack;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A SAX handler that delegates work to sub-handlers.
 */
public class RootHandler extends DefaultHandler implements DatasetTags {

    /** The sub-handlers. */
    private Stack subHandlers;

    /**
     * Creates a new handler.
     */
    public RootHandler() {
        this.subHandlers = new Stack();
    }

    /**
     * Returns the stack of sub handlers.
     *
     * @return The sub-handler stack.
     */
    public Stack getSubHandlers() {
        return this.subHandlers;
    }

    /**
     * Receives some (or all) of the text in the current element.
     *
     * @param ch  character buffer.
     * @param start  the start index.
     * @param length  the length of the valid character data.
     *
     * @throws SAXException for errors.
     */
    public void characters(char[] ch, int start, int length)
        throws SAXException {
        DefaultHandler handler = getCurrentHandler();
        if (handler != this) {
            handler.characters(ch, start, length);
        }
    }

    /**
     * Returns the handler at the top of the stack.
     *
     * @return The handler.
     */
    public DefaultHandler getCurrentHandler() {
        DefaultHandler result = this;
        if (this.subHandlers != null) {
            if (this.subHandlers.size() > 0) {
                Object top = this.subHandlers.peek();
                if (top != null) {
                    result = (DefaultHandler) top;
                }
            }
        }
        return result;
    }

    /**
     * Pushes a sub-handler onto the stack.
     *
     * @param subhandler  the sub-handler.
     */
    public void pushSubHandler(DefaultHandler subhandler) {
        this.subHandlers.push(subhandler);
    }

    /**
     * Pops a sub-handler from the stack.
     *
     * @return The sub-handler.
     */
    public DefaultHandler popSubHandler() {
        return (DefaultHandler) this.subHandlers.pop();
    }

}
