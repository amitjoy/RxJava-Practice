package com.amitinside.rxjava.practice;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public final class OtherObservableFactoryMethods {

	public static void main(final String[] args) {
		subscribePrint(Observable.interval(500L, TimeUnit.MILLISECONDS), "Interval Observable");
		subscribePrint(Observable.timer(500L, TimeUnit.MILLISECONDS), "Timer Observable");
		subscribePrint(Observable.error(new Exception("Test Error")), "Error Observable");
		subscribePrint(Observable.empty(), "Empty Observable");
		subscribePrint(Observable.never(), "Never Observable");
		subscribePrint(Observable.range(1, 3), "Range Observable");
	}

	private static <T> void subscribePrint(final Observable<T> observable, final String name) {
		observable.subscribe(v -> System.out.println(name + " : " + v), e -> {
			System.err.println("Error From ==>" + name + " : ");
			System.err.println(e.getMessage());
		} , () -> System.out.println(name + " ended!"));
	}

}
