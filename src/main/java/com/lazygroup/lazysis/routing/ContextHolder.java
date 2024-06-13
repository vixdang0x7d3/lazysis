package com.lazygroup.lazysis.routing;

public class ContextHolder {

	private static ThreadLocal<String> context = new ThreadLocal<>();

	public static String get() {
		return context.get();
	}

	public static void set(String site) {
		context.set(site);
	}

	public static void clear() {
		context.remove();
	}
}
