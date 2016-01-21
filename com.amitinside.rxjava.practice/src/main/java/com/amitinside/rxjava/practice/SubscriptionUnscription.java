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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.amitinside.rxjava.practice.util.Helpers;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;

public final class SubscriptionUnscription {

	private static <T> Observable<T> fromIterable(final Iterable<T> iterable) {
		return Observable.create(subscriber -> {
			try {
				final Iterator<T> iterator = iterable.iterator();
				while (iterator.hasNext()) {
					if (subscriber.isUnsubscribed()) {
						return;
					}
					subscriber.onNext(iterator.next());
				}
				if (!subscriber.isUnsubscribed()) {
					subscriber.onCompleted();
				}
			} catch (final Exception e) {
				if (!subscriber.isUnsubscribed()) {
					subscriber.onError(e);
				}
			}
		});
	}

	public static void main(final String[] args) throws IOException {
		final Path path = Paths.get("src", "main", "resources", "lorem_big.txt");
		final List<String> data = Files.readAllLines(path);
		final Observable<String> observable = fromIterable(data).subscribeOn(Schedulers.computation());
		final Subscription subscription = Helpers.subscribePrint(observable, "File");
		System.out.println("Before Unsubscribe");
		subscription.unsubscribe();
		System.out.println("After Unsubscribe");
	}

}
