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
package net.sf.morph.transform;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import net.sf.morph.transform.converters.ConstantConverterTestCase;
import net.sf.morph.transform.converters.EvaluateExpressionConverterTestCase;
import net.sf.morph.transform.converters.IdentityConverterTestCase;
import net.sf.morph.transform.converters.NumberConverterTestCase;
import net.sf.morph.transform.converters.NumberToTimeConverterTestCase;
import net.sf.morph.transform.converters.ObjectToClassConverterTestCase;
import net.sf.morph.transform.converters.PrimitiveWrapperConverterTestCase;
import net.sf.morph.transform.converters.TextConverterTestCase;
import net.sf.morph.transform.converters.TextToNumberConverterTestCase;
import net.sf.morph.transform.converters.TextToTimeConverterTestCase;
import net.sf.morph.transform.converters.TimeConverterTestCase;
import net.sf.morph.transform.converters.TimeToNumberConverterTestCase;
import net.sf.morph.transform.copiers.ArrayCopierTestCase;
import net.sf.morph.transform.copiers.AssemblerCopierComponentsTestCase;
import net.sf.morph.transform.copiers.AssemblerCopierTestCase;
import net.sf.morph.transform.copiers.ConditionalCopierTestCase;
import net.sf.morph.transform.copiers.ContainerCopierTestCase;
import net.sf.morph.transform.copiers.CumulativeCopierTestCase;
import net.sf.morph.transform.copiers.DelegatingCopierTestCase;
import net.sf.morph.transform.copiers.DisassemblerCopierComponentsTestCase;
import net.sf.morph.transform.copiers.DisassemblerCopierTestCase;
import net.sf.morph.transform.copiers.MapCopierTestCase;
import net.sf.morph.transform.copiers.NOPCopierTestCase;
import net.sf.morph.transform.copiers.NestedDelegatingCopierTestCase;
import net.sf.morph.transform.copiers.PartialPropertyNameMatchingCopierTestCase;
import net.sf.morph.transform.copiers.PropertyNameMatchingCopierTestCase;
import net.sf.morph.transform.copiers.SetExpressionCopierTestCase;
import net.sf.morph.transform.copiers.TextToContainerCopierTestCase;
import net.sf.morph.transform.transformers.ChainedTransformerTestCase;
import net.sf.morph.transform.transformers.ExplicitChainedTransformerTestCase;
import net.sf.morph.transform.transformers.SimpleDelegatingTransformerTestCase;
import net.sf.morph.transform.transformers.TypeChangingGraphTransformerTestCase;

/**
 * Run tests for all converters
 *
 * @author Matt Sgarlata
 * @since Nov 7, 2004
 */
public class TransformersTestRunner extends TestRunner {
	public static Test suite() {
		TestSuite suite = new TestSuite();

		// COPIERS

		suite.addTestSuite(ContainerCopierTestCase.class);
		suite.addTestSuite(ArrayCopierTestCase.class);
		suite.addTestSuite(PropertyNameMatchingCopierTestCase.class);
		suite.addTestSuite(PartialPropertyNameMatchingCopierTestCase.class);
		suite.addTestSuite(AssemblerCopierTestCase.class);
		suite.addTestSuite(AssemblerCopierComponentsTestCase.class);
		suite.addTestSuite(CumulativeCopierTestCase.class);
		suite.addTestSuite(TextToContainerCopierTestCase.class);
		suite.addTestSuite(NOPCopierTestCase.class);
		suite.addTestSuite(SetExpressionCopierTestCase.class);
		suite.addTestSuite(ConditionalCopierTestCase.class);
		suite.addTestSuite(DisassemblerCopierTestCase.class);
		suite.addTestSuite(DisassemblerCopierComponentsTestCase.class);
		suite.addTestSuite(MapCopierTestCase.class);

		// CONVERTERS

		suite.addTest(NumberConvertersTestRunner.suite());
		suite.addTest(ToBooleanConvertersTestRunner.suite());
		suite.addTest(ToTextConvertersTestRunner.suite());

		suite.addTestSuite(ObjectToClassConverterTestCase.class);
		suite.addTestSuite(NumberConverterTestCase.class);
		suite.addTestSuite(TextToTimeConverterTestCase.class);
		suite.addTestSuite(TimeConverterTestCase.class);
		suite.addTestSuite(IdentityConverterTestCase.class);
		suite.addTestSuite(NumberToTimeConverterTestCase.class);
		suite.addTestSuite(TextConverterTestCase.class);
		suite.addTestSuite(TextToNumberConverterTestCase.class);
		suite.addTestSuite(TimeToNumberConverterTestCase.class);
		suite.addTestSuite(PrimitiveWrapperConverterTestCase.class);
		suite.addTestSuite(EvaluateExpressionConverterTestCase.class);
		suite.addTestSuite(ConstantConverterTestCase.class);

		// COMPOSITE CONVERTERS

		suite.addTestSuite(DelegatingCopierTestCase.class);
		suite.addTestSuite(NestedDelegatingCopierTestCase.class);

		// MISC TESTS

		suite.addTestSuite(SourceToDifferentDestinationsTestCase.class);
		suite.addTestSuite(CyclicTransformationTestCase.class);

		// TRANSFORMERS

		suite.addTestSuite(SimpleDelegatingTransformerTestCase.class);
		suite.addTestSuite(TypeChangingGraphTransformerTestCase.class);
		suite.addTestSuite(ChainedTransformerTestCase.class);
		suite.addTestSuite(ExplicitChainedTransformerTestCase.class);

		return suite;
	}
}