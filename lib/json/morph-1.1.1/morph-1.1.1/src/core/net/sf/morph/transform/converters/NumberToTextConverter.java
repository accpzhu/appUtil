/*
 * Copyright 2004-2005, 2008 the original author or authors.
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
package net.sf.morph.transform.converters;

import java.text.NumberFormat;
import java.util.Locale;

import net.sf.morph.Defaults;
import net.sf.morph.transform.Converter;
import net.sf.morph.transform.DecoratedConverter;
import net.sf.morph.transform.ImpreciseTransformer;
import net.sf.morph.transform.transformers.BaseTransformer;
import net.sf.morph.util.TransformerUtils;

/**
 * Converts {@link java.lang.Number}s into basic text types ({@link java.lang.String},
 * {@link java.lang.StringBuffer} or {@link java.lang.Character}).
 * 
 * @author Matt Sgarlata
 * @since Jan 26, 2006
 */
public class NumberToTextConverter extends BaseTransformer implements DecoratedConverter,
		ImpreciseTransformer {

	private Converter textConverter;
	private Converter numberConverter;

	/**
	 * {@inheritDoc}
	 */
	protected Object convertImpl(Class destinationClass, Object source, Locale locale) throws Exception {
		String formatted = getNumberFormat(locale).format(source);
		return getTextConverter().convert(destinationClass, formatted, locale);
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class[] getSourceClassesImpl() throws Exception {
		return getNumberConverter().getDestinationClasses();
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class[] getDestinationClassesImpl() throws Exception {
		return getTextConverter().getSourceClasses();
	}

	/**
	 * {@inheritDoc}
	 */
	protected boolean isImpreciseTransformationImpl(Class destinationClass, Class sourceClass) {
		return TransformerUtils.isImpreciseTransformation(getTextConverter(), destinationClass, String.class);
	}

	/**
	 * Get the number converter used by this NumberToTextConverter.
	 * @return Converter
	 */
	public Converter getNumberConverter() {
		if (numberConverter == null) {
			setNumberConverter(Defaults.createNumberConverter());
		}
		return numberConverter;
	}

	/**
	 * Sets the <code>numberConverter</code> to be used. Note that this method
	 * should be called before the transformer is used. Otherwise, if another
	 * thread is in the middle of transforming an object graph and this method
	 * is called, the behavior of the transformer can change partway through the
	 * transformation.
	 * 
	 * @param numberConverter
	 *            the <code>numberConverter</code> to be used
	 */
	public synchronized void setNumberConverter(Converter numberConverter) {
		this.numberConverter = numberConverter;
	}

	/**
	 * Get the text converter used by this NumberToTextConverter.
	 * @return Converter
	 */
	public Converter getTextConverter() {
		if (textConverter == null) {
			setTextConverter(Defaults.createTextConverter());
		}
		return textConverter;
	}

	/**
	 * Sets the <code>textConverter</code> to be used. Note that this method
	 * should be called before the transformer is used. Otherwise, if another
	 * thread is in the middle of transforming an object graph and this method
	 * is called, the behavior of the transformer can change partway through the
	 * transformation.
	 * 
	 * @param textConverter
	 *            the <code>textConverter</code> to be used
	 */
	public synchronized void setTextConverter(Converter textConverter) {
		this.textConverter = textConverter;
	}

	/**
	 * Retrieves the {@link NumberFormat} instance to be used to in the given
	 * locale to format numbers as text. Subclasses can override this method to
	 * customize the behavior of this converter.
	 * 
	 * @param locale
	 *            the locale
	 * @return the {@link NumberFormat} instance to be used to in the given
	 *         locale to format numbers as text
	 */
	protected NumberFormat getNumberFormat(Locale locale) {
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		numberFormat.setGroupingUsed(false);
		numberFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
		return numberFormat;
	}

	/**
	 * {@inheritDoc}
	 */
	protected boolean isWrappingRuntimeExceptions() {
	    return true;
    }

}
