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
package net.sf.morph.reflect;

import net.sf.composite.util.ObjectUtils;
import net.sf.morph.util.ClassUtils;

/**
 * Exception thrown to indicate a suitable reflector for a reflection operation
 * could not be found.
 * 
 * @author Matt Sgarlata
 * @since Oct 25, 2007
 */
public class NoReflectorFoundException extends ReflectionException {

	public NoReflectorFoundException(Object reflectedObject, Class reflectorType) {
		super("Could not find a "
		        + ClassUtils.getUnqualifiedClassName(reflectorType)
		        + " that can reflect "
		        + ObjectUtils.getObjectDescription(reflectedObject));
	}

	public NoReflectorFoundException(Class reflectedType, Class reflectorType) {
		super("Could not find a "
				+ ClassUtils.getUnqualifiedClassName(reflectorType)
				+ " that can reflect objects of type "
				+ ObjectUtils.getObjectDescription(reflectedType));
	}
	
}
