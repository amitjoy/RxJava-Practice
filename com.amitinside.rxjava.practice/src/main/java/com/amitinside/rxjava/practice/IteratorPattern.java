/*******************************************************************************
 * Copyright 2016 Amit Kumar Mondal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.amitinside.rxjava.practice;

import java.util.List;

import com.google.common.collect.Lists;

public final class IteratorPattern {

	public static void main(final String[] args) {
		final List<String> names = Lists.newArrayList("Java", "C", "C++", "PHP", "Go");
		for (final Object element : names) {
			final String language = (String) element;
			System.out.println(language);
		}
	}

}
