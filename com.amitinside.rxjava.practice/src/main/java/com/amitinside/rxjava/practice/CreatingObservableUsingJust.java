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

import rx.Observable;

public final class CreatingObservableUsingJust {

	public static class User {
		private final String foreName;
		private final String lastName;

		public User(final String foreName, final String lastName) {
			super();
			this.foreName = foreName;
			this.lastName = lastName;
		}

		public String getForeName() {
			return this.foreName;
		}

		public String getLastName() {
			return this.lastName;
		}

	}

	public static void main(final String[] args) {
		observableJust();
		printFullName();
	}

	private static void observableJust() {
		final Observable<String> observable = Observable.<String> just("a", "b", "c", "d");
		observable.subscribe(System.out::println);
	}

	private static void printFullName() {
		Observable.just(new User("Amit", "Mondal")).map(user -> user.getForeName() + user.getLastName())
				.subscribe(System.out::println);
		;
	}

}
