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

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import rx.Observable;

public final class CreatingObservablesUsingFrom {

	public static void main(final String[] args) {
		final List<String> strings = Lists.newArrayList("a", "b", "c", "d");
		final Observable<String> observable = Observable.from(strings);
		observable.subscribe(System.out::println);
		observable.subscribe(alphabet -> System.out.println(alphabet + "+"), System.out::println, System.out::println);
		pathPbservable();
	}

	private static void pathPbservable() {
		final Path resources = Paths.get("src", "main", "java");

		try (final DirectoryStream<Path> dStream = Files.newDirectoryStream(resources);) {
			final Observable<Path> dirObservable = Observable.from(dStream);
			dirObservable.subscribe(System.out::println);
		} catch (final Exception e) {
			Throwables.propagate(e);
		}
	}

}
