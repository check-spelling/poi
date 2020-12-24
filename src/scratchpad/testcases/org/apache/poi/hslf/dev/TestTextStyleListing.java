/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.hslf.dev;

import org.apache.poi.EmptyFileException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTextStyleListing extends BasePPTIteratingTest {
    private static Set<String> FAILING = new HashSet<>();
    static {
        FAILING.add("empty_textbox.ppt");
    }

    @Test
    public void testMain() throws IOException {
        // calls System.exit(): TextStyleListing.main(new String[0]);
        assertThrows(EmptyFileException.class, () -> TextStyleListing.main(new String[]{"invalidfile"}));
    }

    @Override
    void runOneFile(File pFile) throws Exception {
        try {
            TextStyleListing.main(new String[]{pFile.getAbsolutePath()});
        } catch (ArrayIndexOutOfBoundsException e) {
            // some corrupted documents currently can cause this exception
            if (!FAILING.contains(pFile.getName())) {
                throw e;
            }
        }
    }
}