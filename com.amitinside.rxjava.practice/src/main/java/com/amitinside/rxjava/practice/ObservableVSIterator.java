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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rx.Observable;

public final class ObservableVSIterator {

	public static void main(final String[] args) {
		new ObservableVSIterator().run();
	}

	private static void usingIteratorExample() {
		final List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");

		final Iterator<String> iterator = list.iterator();

		// While there is a next element, PULL it from the source and print it.
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private static void usingObservableExample() {
		final List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");

		final Observable<String> observable = Observable.from(list);

		// Subscribe to the Observable. It will PUSH it's values to the
		// Subscriber, and it will be printed.
		observable.subscribe(element -> System.out.println(element), t -> System.err.println(t),
				() -> System.out.println("We've finnished!"));
	}

	public void run() {
		System.out.println("Running Iterator example:");
		usingIteratorExample();

		System.out.println("Running Observable example:");
		usingObservableExample();
	}
}