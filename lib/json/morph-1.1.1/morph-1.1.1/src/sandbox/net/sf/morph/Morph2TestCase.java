/*
 * Copyright 2004-2005, 2007-2008 the original author or authors.
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
package net.sf.morph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import net.sf.morph.reflect.ReflectionException;
import net.sf.morph.transform.TransformationException;
import net.sf.morph.util.TestObjects;
import net.sf.morph.util.TestUtils;


/**
 * @author Matt Sgarlata
 * @since Morph 1.1 (Oct 31, 2007)
 */
public class Morph2TestCase extends MorphTestCase {

	protected static interface ITestInterface { }
	protected static class TestImplementation implements ITestInterface { }

	public void testGetLongObject() {
		Map map = new HashMap();
		map.put("longObject", "2");
		TestUtils.assertEquals(new Long(2), Morph2.getLongObject(map, "longObject"));		
	}
	
	public void testRuntimeArrayTypeOfConversion() {
		List list = new ArrayList();
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new BigDecimal(3));
		
		Object converted = Morph2.convert(Integer[].class, list);
		assertTrue(converted instanceof Integer[]);
		
		list = new ArrayList();
		list.add(new HashMap());
		list.add(new HashMap());
		Map[] result = new Map[] { new HashMap(), new HashMap() } ;
		
		converted = Morph2.convert(Map[].class, list);
		assertSame(Map[].class, converted.getClass());
		TestUtils.assertEquals(result, converted);
		
		list = new ArrayList();
		list.add(new Integer(2));
		list.add(new Integer(3));
		converted = Morph2.convert(Set.class, list);
		assertSame(HashSet.class, converted.getClass());
		TestUtils.assertEquals(converted, new HashSet(list));
		
		list = new ArrayList();
		list.add(new TestImplementation());
		list.add(new TestImplementation());
		converted = Morph2.convert(ITestInterface[].class, list);
		assertEquals(ITestInterface[].class, converted.getClass());
		TestUtils.assertEquals(converted, new ITestInterface[] { new TestImplementation(), new TestImplementation()});
		
		list = new ArrayList();
		converted = Morph2.convert(ITestInterface[].class, list);
		assertEquals(ITestInterface[].class, converted.getClass());
		TestUtils.assertEquals(converted, new ITestInterface[] { });

		list = new ArrayList();
		converted = Morph2.convert(String[].class, list);
		assertEquals(String[].class, converted.getClass());
		TestUtils.assertEquals(converted, new String[] { });
	}
	
	public void testNumberAndCalendarConversion() {
		Date date = new Date(2005, 0, 1);
		Calendar newYearsDay2005 = new GregorianCalendar();
		newYearsDay2005.setTime(date);
		TestUtils.assertEquals(newYearsDay2005, Morph2.convertToCalendar(new Long(date.getTime())));		
		TestUtils.assertEquals(new Long(date.getTime()), Morph2.convertToLongObject(date));		
	}

	public void testStringToDoubleConversion() {
		assertEquals(2.0d, Morph2.convertToDouble("2"), 0.001d);
		assertEquals(2.0d, Morph2.convertToDouble("2.0"), 0.001d);
		assertEquals(2.1d, Morph2.convertToDouble("2.10000"), 0.001d);
		assertEquals(2.5d, Morph2.convertToDouble("2.5"), 0.001d);
		assertEquals(2.9d, Morph2.convertToDouble("2.9"), 0.001d);
	}
	

	public void testLocaleSpecificConversions() throws Exception {
		double precision = 0.000001d;
		
		// text in English
		
		assertEquals(4444.44d, Morph2.convertToDouble("$4,444.44", Locale.US), precision);
		assertEquals(4444.44d, Morph2.convertToDouble("  $ 4,444.44", Locale.US), precision);
		assertEquals(.35, Morph2.convertToFloat("35%", Locale.US), precision);
		assertEquals(.0035, Morph2.convertToFloat(".35%", Locale.US), precision);
		assertEquals(3.5, Morph2.convertToFloat("350%", Locale.US), precision);
		assertEquals(3500000.1234d, Morph2.convertToDouble("3,500,000.1234", Locale.US), precision);
		
		assertEquals("3500000", Morph2.convertToString(new Double(3500000), Locale.US));
		
		// test in Dutch
		
		Locale dutch = new Locale("nl", "");
		
		assertEquals(4444.44d, Morph2.convertToDouble("\u20ac4.444,44", dutch), precision);
		assertEquals(4444.44d, Morph2.convertToDouble("\u20ac 4.444,44", dutch), precision);
		assertEquals(.35, Morph2.convertToFloat("35%", dutch), precision);
		assertEquals(.0035, Morph2.convertToFloat(",35%", dutch), precision);
		assertEquals(3.5, Morph2.convertToFloat("350%", dutch), precision);
		assertEquals(3500000.1234d, Morph2.convertToDouble("3.500.000,1234", dutch), precision);
		assertEquals("3500000", Morph2.convertToString(new Double(3500000), dutch));
	}
	
	public static final class DestinationWithoutGetter {
		Object testProperty;
//		public void setTestProperty(String object) {
//			testProperty = object;
//		}
		public void setTestProperty(Object object) {
			testProperty = object;
		}
	}
	
	public void testCopyToDestWithoutGetter() throws Exception {
		Map source = new HashMap();
		source.put("testProperty", new Integer(123));
		DestinationWithoutGetter dest = new DestinationWithoutGetter();
		Morph2.copy(dest, source);
		assertTrue(dest.testProperty.equals(new Integer(123)));		
	}
	
	public void testGetBooleanObject() throws Exception {
		Map map = new HashMap();
		
		map.put("true", Boolean.TRUE);
		map.put("false", Boolean.FALSE);

		map.put("trueStr", "true");
		map.put("falseStr", "false");
		
		map.put("emptyStr", "");		
		map.put("null", null);

		assertEquals(Boolean.TRUE, Morph2.getBooleanObject(map, "true"));
		assertEquals(Boolean.FALSE, Morph2.getBooleanObject(map, "false"));

		assertEquals(Boolean.TRUE, Morph2.getBooleanObject(map, "trueStr"));
		assertEquals(Boolean.FALSE, Morph2.getBooleanObject(map, "falseStr"));

		assertEquals(null, Morph2.getBooleanObject(map, "emptyStr"));
		assertEquals(null, Morph2.getBooleanObject(map, "null"));
	}
	
	public void testConvertToBoolean() throws Exception {
		assertTrue(Morph2.convertToBoolean(Boolean.TRUE));
		assertFalse(Morph2.convertToBoolean(Boolean.FALSE));
		try {
			Morph2.convertToBoolean(null);
			fail("Should not have been able to convert null to a boolean");
		}
		catch (TransformationException e) { }
	}
	
	public void testConvertToBooleanObject() throws Exception {
		assertEquals(Boolean.TRUE, Morph2.convertToBooleanObject(Boolean.TRUE));
		assertEquals(Boolean.FALSE, Morph2.convertToBooleanObject(Boolean.FALSE));
		assertNull(Morph2.convertToBooleanObject(null));
	}
	
	public static class CopiableObject {
		private Boolean booleanProperty;

		public Boolean getBooleanProperty() {
			return booleanProperty;
		}

		public void setBooleanProperty(Boolean booleanProperty) {
			this.booleanProperty = booleanProperty;
		}
	}
	
	public void testCopy() {
		CopiableObject source = new CopiableObject();
		source.setBooleanProperty(Boolean.TRUE);
		CopiableObject destination = new CopiableObject();
		
		Morph2.copy(destination, source);
		
		assertEquals(destination.getBooleanProperty(), Boolean.TRUE);
	}
	
	public void testGetSize() {
		try {
			Morph2.getSize(null);
			fail("Exception should be thrown when retrieving the size of a null object");
		}
		catch (ReflectionException e) {
			// this is the expected behavior
		}
		assertEquals(0, Morph2.getSize(new StringTokenizer("")));
		assertEquals(1, Morph2.getSize(new StringTokenizer("1")));
		assertEquals(2, Morph2.getSize(new StringTokenizer("two words")));
	}
	
	// has to be public or Morph barphs
	public static final class DomainObject {
		Integer[] array;
		
		public Integer[] getArray() {
        	return array;
        }

		public void setArray(Integer[] array) {
        	this.array = array;
        }
	}
	
	public void testWebLikeDataBinding() {
		Map map = new HashMap();
		map.put("array", "1,2");
		DomainObject object = new DomainObject();
		Morph2.copy(object, map);
		
		assertEquals(new Integer(1), object.getArray()[0]);
		assertEquals(new Integer(2), object.getArray()[1]);
	}

// this test currently fails	
//	public void testGetRequestParameter() {
//		TestObjects to = new TestObjects();
//		ServletRequest request = to.servletRequest;
//		assertEquals("paramValue", Morph2.get(request, "inBothParamsAndAttrs"));
//	}
	
	public void testGetInterfaceType() {
		TestObjects to = new TestObjects();
		// just don't want these calls don't blow up
		assertEquals(new Long(1), Morph2.get(to.multiElementEmptyPrimitiveArray, "0", Comparable.class));
		assertEquals(new Long(1), Morph2.get(to.oneTwoThreeNumberArray, "0", Comparable.class));
		assertEquals(new Long(1), Morph2.get(to.oneTwoThreeObjectArray, "0", Comparable.class));
	}

}
