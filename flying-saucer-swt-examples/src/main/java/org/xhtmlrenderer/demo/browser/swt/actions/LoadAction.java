/*
 * {{{ header & license
 * Copyright (c) 2007 Vianney le Clément
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */
package org.xhtmlrenderer.demo.browser.swt.actions;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.xhtmlrenderer.demo.browser.swt.Browser;

public class LoadAction extends AbstractAction {

    private final String _url;

    public LoadAction(String url, String text) {
        this(url, text, SWT.NONE, null);
    }

    public LoadAction(String url, String text, int shortcut) {
        this(url, text, shortcut, null);
    }

    public LoadAction(String url, String text, String icon) {
        this(url, text, SWT.NONE, icon);
    }

    public LoadAction(String url, String text, int shortcut, String icon) {
        super(text, SWT.PUSH, shortcut, icon);
        _url = url;
    }

    public void run(Browser browser, MenuItem mi) {
        browser.load(_url);
    }

}
