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
    
    $Id: Collections7.java,v 1.3 2006/04/04 20:00:41 livshits Exp $
 */
package securibench.micro.collections;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import securibench.micro.BasicTestCase;
import securibench.micro.MicroTestCase;

/** 
 *  @servlet vuln_count = "2" 
 *  */
public class Collections7Map extends BasicTestCase implements MicroTestCase {
  private static final String FIELD_NAME = "name";

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String tainted = req.getParameter(FIELD_NAME);
    String safe = "I Am Safe";
    
    Map<String, String> m = new HashMap<String, String>();
    m.put("a", tainted);
    m.put("b", "NotTaintedString");
    m.put("c", safe);
    m.put("d", tainted);

    Paths.get(m.get("a")); /* BAD */
    Paths.get(m.get("b")); /* OK */
    Paths.get(m.get("c")); /* OK */
    Paths.get(m.get("d")); /* BAD */
  }

  public String getDescription() {
    return "tainted/not tainted data into a Map";
  }

  public int getVulnerabilityCount() {
    return 2;
  }
}
