package com.amitinside.rxjava.practice.util;

public interface CheckedAction1<T> {
	void call(T arg) throws Exception;
}