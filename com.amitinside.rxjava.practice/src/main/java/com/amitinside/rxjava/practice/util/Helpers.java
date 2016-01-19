package com.amitinside.rxjava.practice.util;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import rx.Notification;
import rx.Observable;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action1;

public final class Helpers {

	/**
	 * Subscribes to an observable, printing all its emissions. Blocks until the
	 * observable calls onCompleted or onError.
	 */
	public static <T> void blockingSubscribePrint(final Observable<T> observable, final String name) {
		final CountDownLatch latch = new CountDownLatch(1);
		subscribePrint(observable.finallyDo(() -> latch.countDown()), name);
		try {
			latch.await();
		} catch (final InterruptedException e) {
		}
	}

	public static Observable<String> createFileOnNotFound(final Throwable t) {
		final Throwable error = Exceptions.getFinalCause(t);
		if (error instanceof NoSuchFileException) {
			final Path path = Paths.get(((NoSuchFileException) error).getFile());
			try {
				Files.createDirectories(path.getParent());
				Files.createFile(path);

				return Observable.never();
			} catch (final Exception e) {
				return Observable.error(e);
			}
		}
		return Observable.error(error);
	}

	public static <T> Action1<Notification<? super T>> debug(final String description) {
		return debug(description, "");
	}

	public static <T> Action1<Notification<? super T>> debug(final String description, final String offset) {
		final AtomicReference<String> nextOffset = new AtomicReference<String>(">");

		return (final Notification<? super T> notification) -> {
			switch (notification.getKind()) {
			case OnNext:

				System.out.println(Thread.currentThread().getName() + "|" + description + ": " + offset
						+ nextOffset.get() + notification.getValue());
				break;
			case OnError:
				System.err.println(Thread.currentThread().getName() + "|" + description + ": " + offset
						+ nextOffset.get() + " X " + notification.getThrowable());
				break;
			case OnCompleted:
				System.out.println(
						Thread.currentThread().getName() + "|" + description + ": " + offset + nextOffset.get() + "|");
				break;
			default:
				break;
			}
			nextOffset.getAndUpdate(p -> "-" + p);
		};
	}

	public static <T> Subscription subscribePrint(final Observable<T> observable, final String name) {
		return observable.subscribe(
				(v) -> System.out.println(Thread.currentThread().getName() + "|" + name + " : " + v), (e) -> {
					System.err.println("Error from " + name + ":");
					System.err.println(e);
					System.err.println(Arrays.stream(e.getStackTrace()).limit(5L).map(stackEl -> "  " + stackEl)
							.collect(Collectors.joining("\n")));
				} , () -> System.out.println(name + " ended!"));
	}
}