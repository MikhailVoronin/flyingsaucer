/*
 * {{{ header & license
 * Copyright (c) 2004 Joshua Marinacci
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */
package org.xhtmlrenderer.demo.browser;

import org.xhtmlrenderer.util.GeneralUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DirectoryLister {

    public static String list(File file) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Directory listing for ");
        sb.append(file.getPath());
        sb.append("</title>");
        sb.append("<style>");
        sb.append("body { font-family: monospaced; }");
        sb.append("ul { background-color: #ddffdd; }");
        sb.append("li { list-style-type: none; }");
        sb.append("a { text-decoration: none; }");
        sb.append(".dir { font-weight: bold; color: #ff9966; }");
        sb.append(".file { font-weight: normal; color: #003333; }");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h2>Index of ");
        sb.append(file);
        sb.append("</h2>");
        sb.append("<hr />");

        if (file.isDirectory()) {
            try {
                File parent = file.getParentFile();
                if ( parent != null ) {
                    String loc = GeneralUtil.htmlEscapeSpace(file.getAbsoluteFile().getParentFile().toURL().toExternalForm()).toString();
                    sb.append("<a class='dir' href='").append(loc).append("'>Up to higher level directory</a>");
                }
            } catch (MalformedURLException e) {
                // skip
            }
            sb.append("<table style='width: 75%'>");
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isHidden()) continue;
                long len = f.length();
                String lenDesc = (len > 1024 ? new DecimalFormat("#,###KB").format(len / 1024) : "");
                String lastMod = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date(f.lastModified()));
                sb.append("<tr>");

                String cls;
                if (f.isDirectory()) {
                    cls = "dir";
                } else {
                    cls = "file";
                }
                try {
                    String loc = GeneralUtil.htmlEscapeSpace(f.toURL().toExternalForm()).toString();
                    sb.append("<td><a class='").append(cls).append("' href='").append(loc).append("'>").append(f.getName()).append("</a></td>");
                    sb.append("<td>").append(lenDesc).append("</td>").append("<td>").append(lastMod).append("</td>");
                } catch (MalformedURLException e) {
                    sb.append(f.getAbsolutePath());
                }
                sb.append("</tr>");
            }
            sb.append("</table>");
        }

        sb.append("<hr />");
        sb.append("</body></html>");

        return sb.toString();
    }

}

/*
 * $Id$
 *
 * $Log$
 * Revision 1.12  2008/05/30 16:07:06  pdoubleya
 * Issue 228: when setting document from a file, use file.getAbsoluteFile().getParentFile() to find the parent, in case the file provided has no directory or path; otherwise, file.getParentFile() returns null, and we have no way of determining a base URI. Covers at least the (reproducible) part of the issue.
 *
 * Revision 1.11  2007/01/29 21:41:46  pdoubleya
 * revert checkin
 *
 * Revision 1.9  2006/07/31 14:20:54  pdoubleya
 * Bunch of cleanups and fixes. Now using a toolbar for actions, added Home button, next/prev navigation actions to facilitate demo file browsing, loading demo pages from a list, about dlg and link to user's manual.
 *
 * Revision 1.8  2005/06/25 15:33:44  tobega
 * fixed Directory listings in browser
 *
 * Revision 1.7  2005/06/16 07:24:44  tobega
 * Fixed background image bug.
 * Caching images in browser.
 * Enhanced LinkListener.
 * Some house-cleaning, playing with Idea's code inspection utility.
 *
 * Revision 1.6  2005/06/15 10:56:13  tobega
 * cleaned up a bit of URL mess, centralizing URI-resolution and loading to UserAgentCallback
 *
 * Revision 1.5  2004/12/12 03:33:07  tobega
 * Renamed x and u to avoid confusing IDE. But that got cvs in a twist. See if this does it
 *
 * Revision 1.4  2004/12/12 02:55:10  tobega
 * Making progress
 *
 * Revision 1.3  2004/10/23 14:38:58  pdoubleya
 * Re-formatted using JavaStyle tool.
 * Cleaned imports to resolve wildcards except for common packages (java.io, java.util, etc.)
 * Added CVS log comments at bottom.
 *
 *
 */

