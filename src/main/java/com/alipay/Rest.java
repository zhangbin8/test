package com.alipay;

import java.util.HashSet;
import java.util.Set;

public class Rest {
	public static void main(String[] args) {
		Set<Integer> xin = new HashSet<Integer>();
		xin.add(2);
		xin.add(3);
		xin.add(4);
		Set<Integer> old = new HashSet<Integer>();
		old.add(1);
		old.add(2);
		old.add(3);
		System.out.println(xin.removeAll(old));
		for (Integer i : xin) {
			System.out.println(i);
		}
		
	}
}
