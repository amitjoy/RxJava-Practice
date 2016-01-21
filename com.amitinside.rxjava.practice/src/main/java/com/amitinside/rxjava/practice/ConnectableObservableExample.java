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

import java.util.concurrent.TimeUnit;

import com.amitinside.rxjava.practice.util.Helpers;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

public final class ConnectableObservableExample {

	public static void main(final String[] args) {
		final Observable<Long> observable = Observable.interval(500, TimeUnit.MILLISECONDS);
		final ConnectableObservable<Long> published = observable.publish();
		final Subscription subscription1 = Helpers.subscribePrint(published, "First");
		final Subscription subscription2 = Helpers.subscribePrint(published, "Second");
		published.connect();
		Subscription subscription3 = null;
		try {
			TimeUnit.SECONDS.sleep(1L);
			subscription3 = Helpers.subscribePrint(observable, "Third");
			TimeUnit.SECONDS.sleep(1L);
			observable.replay();
		} catch (final Exception e) {
		}

		subscription1.unsubscribe();
		subscription2.unsubscribe();
		subscription3.unsubscribe();
	}

}
