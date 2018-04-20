/*
 * Copyright 2007 the original author or authors.
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
package net.sf.morph.integration.commons.collections;

import junit.framework.TestCase;

/**
 *
 */
public class DecoratedConverterToTransformerAdapterTestCase extends TestCase {
	public void testMe() throws Exception {
		DecoratedConverterToTransformerAdapter t = new DecoratedConverterToTransformerAdapter();
		t.setDestinationClass(String.class);
		assertEquals("foo", t.transform("foo"));
		assertEquals("1", t.transform(new Integer(1)));
		assertEquals("foo", t.transform(new char[] { 'f', 'o', 'o' }));
	}
}
