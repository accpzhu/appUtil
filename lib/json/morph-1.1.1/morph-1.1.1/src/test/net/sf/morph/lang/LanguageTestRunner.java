/*
 * Copyright 2004-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.sf.morph.lang;

import junit.awtui.TestRunner;
import junit.framework.Test;
import junit.framework.TestSuite;
import net.sf.morph.lang.languages.SimpleLanguageTestCase;
import net.sf.morph.lang.languages.support.SimpleExpressionParserTestCase;

/**
 * @author Matt Sgarlata
 * @since Dec 4, 2004
 */
public class LanguageTestRunner extends TestRunner {
	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTestSuite(SimpleExpressionParserTestCase.class);
		suite.addTestSuite(SimpleLanguageTestCase.class);
		
		return suite;
	}
}
