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
package net.sf.morph.reflect.reflectors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.morph.reflect.Reflector;
import net.sf.morph.util.ClassUtils;

/**
 * @author Matt Sgarlata
 * @since Dec 24, 2004
 */
public class SimpleInstantiatingReflectorTestCase extends BaseReflectorTestCase {

	protected Reflector createReflector() {
		return new SimpleInstantiatingReflector();
	}

	protected List createReflectableObjects() {
		List list = new ArrayList();

		// by default, calendar objects should be reflectable
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		list.add(new GregorianCalendar());
		list.add(calendar);
		
		// by default, CharSequences should be reflectable
		list.add(new StringBuffer());
		list.add("");
		
		return list;
	}

	protected List createNonReflectableObjects() {
		List list = new ArrayList();
		list.add(new Integer(2));
		list.add(new Long(4));
		list.add(new Object[] { "something" });
		list.add(new int[] { 1 });
		return list;
	}
	
	public void testNewInstance() throws Exception {
		// no exception should be thrown
		getInstantiatingReflector().newInstance(Calendar.class, null);
		getInstantiatingReflector().newInstance(GregorianCalendar.class, null);
		
		if (ClassUtils.isJdk14OrHigherPresent()) {
			getInstantiatingReflector().newInstance(Class.forName("java.lang.CharSequence"), null);
		}
	}


}
