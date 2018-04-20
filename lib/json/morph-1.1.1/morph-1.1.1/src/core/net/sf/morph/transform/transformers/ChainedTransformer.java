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
package net.sf.morph.transform.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import net.sf.composite.util.ObjectUtils;
import net.sf.morph.transform.Converter;
import net.sf.morph.transform.Copier;
import net.sf.morph.transform.DecoratedConverter;
import net.sf.morph.transform.DecoratedCopier;
import net.sf.morph.transform.ExplicitTransformer;
import net.sf.morph.transform.ImpreciseTransformer;
import net.sf.morph.transform.TransformationException;
import net.sf.morph.transform.Transformer;
import net.sf.morph.transform.copiers.CopierDecorator;
import net.sf.morph.util.Assert;
import net.sf.morph.util.ClassUtils;
import net.sf.morph.util.TransformerUtils;

/**
 * Runs one or more transformers in a chain.
 *
 * @author Matt Sgarlata
 * @author Matt Benson
 * @since Nov 24, 2004
 */
public class ChainedTransformer extends BaseCompositeTransformer implements
		DecoratedConverter, DecoratedCopier, ExplicitTransformer, ImpreciseTransformer {

	private Converter copyConverter;

	/**
	 * Create a new ChainedTransformer.
	 */
	public ChainedTransformer() {
	}

	/**
	 * Create a new ChainedTransformer.
	 * @param chain
	 */
	public ChainedTransformer(Transformer[] chain) {
		setComponents(chain);
	}

	/**
	 * {@inheritDoc}
	 * @see net.sf.morph.transform.transformers.BaseTransformer#isTransformableImpl(java.lang.Class, java.lang.Class)
	 */
	protected boolean isTransformableImpl(Class destinationType, Class sourceType) throws Exception {
		return getConversionPath(destinationType, sourceType) != null;
	}

	/**
	 * {@inheritDoc}
	 */
	protected boolean isImpreciseTransformationImpl(Class destinationClass, Class sourceClass) {
		List conversionPath = getConversionPath(destinationClass, sourceClass);
		return !isPrecise(conversionPath, sourceClass, 0);
	}

	/**
	 * Get the converter used when using a ChainedTransformer as a Copier.
	 * @return
	 */
	protected synchronized Converter getCopyConverter() {
		if (copyConverter == null) {
			Transformer[] chain = getChain();
			Assert.notNull(chain, "components");
			if (chain.length == 2) {
				copyConverter = getConverter(chain[0]);
			} else {
				Transformer[] newChain = new Transformer[chain.length - 1];
				System.arraycopy(chain, 0, newChain, 0, newChain.length);
				copyConverter = new ChainedTransformer(newChain);
			}
		}
		return copyConverter;
	}

	//TODO is this overstepping our bounds?  Should we just throw an exception if we need a converter and don't have one?
	private Converter getConverter(Transformer t) {
		if (t instanceof Converter) {
			return (Converter) t;
		}
		if (t instanceof Copier) {
			return new CopierDecorator((Copier) t);
		}
		throw new IllegalArgumentException("Don't know how to use " + t + " as a Converter");
	}

	/**
	 * {@inheritDoc}
	 * @see net.sf.morph.transform.transformers.BaseTransformer#convertImpl(java.lang.Class, java.lang.Object, java.util.Locale)
	 */
	protected Object convertImpl(Class destinationClass, Object source, Locale locale)
			throws Exception {
		if (log.isTraceEnabled()) {
			log.trace("Using chain to convert "
				+ ObjectUtils.getObjectDescription(source) + " to "
				+ ObjectUtils.getObjectDescription(destinationClass));
		}
		Transformer[] chain = getChain();
		Class sourceType = ClassUtils.getClass(source);
		List conversionPath = getConversionPath(destinationClass, sourceType);
		if (conversionPath == null) {
			throw new TransformationException(destinationClass, sourceType, null,
					"Chained conversion path could not be determined");
		}
		if (log.isDebugEnabled()) {
			log.debug("Using chained conversion path " + conversionPath);
		}
		Object o = source;
		for (int i = 0; i < conversionPath.size(); i++) {
			o = getConverter(chain[i]).convert((Class) conversionPath.get(i), o, locale);
			logConversion(i + 1, source, o);
		}
		return o;
	}

	/**
	 * {@inheritDoc}
	 * @see net.sf.morph.transform.transformers.BaseTransformer#copyImpl(java.lang.Object, java.lang.Object, java.util.Locale, java.lang.Integer)
	 */
	protected void copyImpl(Object destination, Object source, Locale locale, Integer preferredTransformationType) throws Exception {
		if (log.isTraceEnabled()) {
			log.trace("Using chain to copy "
				+ ObjectUtils.getObjectDescription(source) + " to "
				+ ObjectUtils.getObjectDescription(destination));
		}
		Class destinationClass = ClassUtils.getClass(destination);
		Transformer[] chain = getChain();
		Transformer copier = chain[chain.length - 1];
		if (!(copier instanceof Copier)) {
			throw new TransformationException(destinationClass, source, null,
					"Last chain component must be a Copier");
		}
		Class sourceType = ClassUtils.getClass(source);
		List conversionPath = getConversionPath(destinationClass, sourceType);
		if (conversionPath == null) {
			throw new TransformationException(destinationClass, source, null,
					"Chained conversion path could not be determined");
		}
		if (log.isDebugEnabled()) {
			log.debug("Using chained conversion path " + conversionPath);
		}
		Object last = getCopyConverter().convert((Class) conversionPath.get(chain.length - 2), source, locale);
		((Copier) copier).copy(destination, last, locale);
	}

	/**
	 * Log one conversion in the chain.
	 * @param conversionNumber
	 * @param source
	 * @param destination
	 */
	protected void logConversion(int conversionNumber, Object source, Object destination) {
		if (log.isTraceEnabled()) {
			log.trace("Conversion "
				+ conversionNumber
				+ " of "
				+ getComponents().length
				+ " was from "
				+ ObjectUtils.getObjectDescription(source)
				+ " to "
				+ ObjectUtils.getObjectDescription(destination)
				+ " and was performed by "
				+ ObjectUtils.getObjectDescription(getComponents()[conversionNumber - 1]));
		}
	}

	/**
	 * {@inheritDoc}
	 * @see net.sf.morph.transform.transformers.BaseTransformer#getDestinationClassesImpl()
	 */
	protected Class[] getDestinationClassesImpl() throws Exception {
		return getChain()[getChain().length - 1].getDestinationClasses();
	}

	/**
	 * {@inheritDoc}
	 * @see net.sf.morph.transform.transformers.BaseTransformer#getSourceClassesImpl()
	 */
	protected Class[] getSourceClassesImpl() throws Exception {
		return getChain()[0].getSourceClasses();
	}

	/**
	 * Get the List of destination classes on the conversion path.
	 * @param destinationType
	 * @param sourceType
	 * @return List
	 */
	protected List getConversionPath(Class destinationType, Class sourceType) {
		return getConversionPath(destinationType, sourceType, 0);
	}

	/**
	 * Get a conversion path by investigating possibilities recursively.
	 * @param destinationType
	 * @param sourceType should be non-null if !allowNull
	 * @param chain
	 * @param index
	 * @param allowNull
	 * @return List
	 */
	private List getConversionPath(Class destinationType, Class sourceType, int index) {
		Transformer[] chain = getChain();
		Transformer c = chain[index];
		if (index + 1 == chain.length) {
			if (TransformerUtils.isTransformable(c, destinationType, sourceType)) {
				List result = new ArrayList();
				result.add(destinationType);
				return result;
			}
			return null;
		}
		List possibleResult = null;
		Class[] available = c.getDestinationClasses();
		for (int i = 0; i < available.length; i++) {
			if (TransformerUtils.isTransformable(c, available[i], sourceType)) {
				List tail = getConversionPath(destinationType, available[i], index + 1);
				if (tail != null) {
					tail.add(0, available[i]);
					if (isPrecise(tail, sourceType, index)) {
						return tail;
					}
					possibleResult = tail;
				}
			}
		}
		return possibleResult;
	}

	private boolean isPrecise(List conversionPath, Class sourceType, int index) {
		Transformer[] chain = getChain();
		Class currentSource = sourceType;
		int i = 0;
		for (Iterator iter = conversionPath.iterator(); iter.hasNext(); i++) {
			Class currentDest = (Class) iter.next();
			if (TransformerUtils.isImpreciseTransformation(chain[index + i], currentDest,
					currentSource)) {
				return false;
			}
			currentSource = currentDest;
		}
		return true;
	}

	/**
	 * Get the components array narrowed to a Transformer[].
	 * @return Transformer[]
	 */
	protected synchronized Transformer[] getChain() {
		return (Transformer[]) getComponents();
	}

}
