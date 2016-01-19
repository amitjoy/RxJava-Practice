package com.amitinside.rxjava.practice;

import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import com.amitinside.rxjava.practice.util.CreateObservable;

import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

public class ReactiveSum {

	public static void main(final String[] args) {
		new ReactiveSum().run();
	}

	public static Observable<Double> varStream(final String varName, final Observable<String> input) {
		final Pattern pattern = Pattern.compile("\\s*" + varName + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)");

		return input.map(pattern::matcher).filter(matcher -> matcher.matches() && (matcher.group(1) != null))
				.map(matcher -> matcher.group(1)).map(Double::parseDouble);
	}

	private final CountDownLatch latch = new CountDownLatch(1);

	public String name() {
		return "Reactive Sum with lambdas)";
	}

	public void reactiveSum(final Observable<Double> a, final Observable<Double> b) {

		Observable.combineLatest(a.startWith(0.0), b.startWith(0.0), (x, y) -> x + y).subscribeOn(Schedulers.io())
				.subscribe(sum -> System.out.println("update : a + b = " + sum), error -> {
					System.out.println("Got an error!");
					error.printStackTrace();
				} , () -> {
					System.out.println("Exiting...");
					this.latch.countDown();
				});

	}

	public void run() {
		final ConnectableObservable<String> input = CreateObservable.from(System.in);

		final Observable<Double> a = varStream("a", input);
		final Observable<Double> b = varStream("b", input);

		this.reactiveSum(a, b);

		input.connect();

		try {
			this.latch.await();
		} catch (final InterruptedException e) {
		}
	}
}