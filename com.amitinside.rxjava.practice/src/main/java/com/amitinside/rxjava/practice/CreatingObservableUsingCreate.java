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

import java.util.Iterator;

import rx.Observable;

public final class CreatingObservableUsingCreate {

	private <T> Observable<T> fromIterable(final Iterable<T> iterable) {
		return Observable.create(subscriber -> {
			try {
				final Iterator<T> iterator = iterable.iterator();
				while (iterator.hasNext()) {
					subscriber.onNext(iterator.next());
				}
				subscriber.onCompleted();
			} catch (final Exception e) {
				subscriber.onError(e);
			}
		});
	}

}
