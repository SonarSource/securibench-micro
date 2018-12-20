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
    
    $Id: Collections3.java,v 1.5 2006/04/04 20:00:41 livshits Exp $
 */
package securibench.micro.collections;

import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import securibench.micro.BasicTestCase;
import securibench.micro.MicroTestCase;

/** 
 *  @servlet description = "collection of collections" 
 *  @servlet vuln_count = "2" 
 *  */
public class Collections3 extends BasicTestCase implements MicroTestCase {
    private static final String FIELD_NAME = "name";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s1 = req.getParameter(FIELD_NAME);
        LinkedList<String> ll1 = new LinkedList();
        LinkedList<Object> ll2 = new LinkedList();
        ll2.addLast(s1);
        ll2.addLast(ll1);
        
        LinkedList<String> c = (LinkedList) ll2.getLast();
        String s2 = (String) c.getLast(); 

        Paths.get(s2);                    /* BAD */
        // this is because the print out of c includes the test of s1
        Paths.get(c.getFirst());                     /* BAD */
    }
    
    public String getDescription() {
        return "collection of collections";
    }
    
    public int getVulnerabilityCount() {
        return 2;
    }
}