/*
   Copyright 2006 Benjamin Livshits

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/**
   @author Benjamin Livshits <livshits@cs.stanford.edu>
   
   $Id: Basic35.java,v 1.2 2006/04/04 20:00:40 livshits Exp $
 */
package securibench.micro.basic;

import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import securibench.micro.BasicTestCase;
import securibench.micro.MicroTestCase;

/**
 * @servlet description="values obtained from HttpServletRequest"
 * @servlet vuln_count = "6"
 */
public class Basic35 extends BasicTestCase implements MicroTestCase {
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration e = req.getHeaderNames();
        while(e.hasMoreElements()) {
            // I believe these can be forged also
            // TODO: double-check this
            Paths.get(req.getProtocol());                /* BAD */
            Paths.get(req.getScheme());                  /* BAD */
            Paths.get(req.getAuthType());                /* BAD */
            Paths.get(req.getQueryString());             /* BAD */
            Paths.get(req.getRemoteUser());              /* BAD */
            Paths.get(req.getRequestURL().toString());              /* BAD */
        }        
    }

    public String getDescription() {
        return "values obtained from headers";
    }

    public int getVulnerabilityCount() {
        return 6;
    }
}