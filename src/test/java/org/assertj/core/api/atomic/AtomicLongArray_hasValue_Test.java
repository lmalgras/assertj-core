/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api.atomic;

import org.assertj.core.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLongArray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.assertj.core.error.AtomicShouldContain.shouldContain;
import static org.assertj.core.util.FailureMessages.actualIsNull;


public class AtomicLongArray_hasValue_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void should_fail_when_atomicLongArray_is_null() throws Exception {
    thrown.expectAssertionError(actualIsNull());

    assertThat((AtomicLongArray) null).hasValue(1234L, atIndex(1));
  }

  @Test
  public void should_fail_if_expected_value_is_null() throws Exception {
    thrown.expectIllegalArgumentException("The expected value should not be <null>.");

    assertThat(new AtomicLongArray(new long[] {1234L})).hasValue(null, atIndex(0));
  }

  @Test
  public void should_fail_if_atomicLongArray_does_not_contain_expected_value() throws Exception {
    AtomicLongArray actual = new AtomicLongArray(new long[] {1234L, 5678L, 3456L});
    long expectedValue = 2345;

    thrown.expectAssertionError(shouldContain(actual.get(1), expectedValue).create());

    assertThat(actual).hasValue(expectedValue, atIndex(1));
  }

  @Test
  public void should_pass_if_atomicLongArray_contains_expected_value() throws Exception {
    assertThat(new AtomicLongArray(new long[] {1234L, 5678L, 3456L})).hasValue(5678L, atIndex(1));
  }
}