package com.amitinside.rxjava.practice;

import java.util.List;

import com.google.common.collect.Lists;

public final class Externaliteration {

	public static void main(final String[] args) {
		final List<String> names = Lists.newArrayList("Java", "C", "C++", "PHP", "Go");
		names.stream().filter(language -> language.contains("C")).forEach(System.out::println);
	}

}
