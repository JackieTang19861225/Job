package com.mini.code.test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 使用Sunday算法进行字符串模式的高效匹配
 *
 */

public class SundayTest {

	public static void main(String[] args) {
		String input="ilikesamsungmobile";
		List<String> dictionary = new ArrayList<>();
		dictionary.add("i");
		dictionary.add("like");
		dictionary.add("sam");
		dictionary.add("sung");
		dictionary.add("samsung");
		dictionary.add("mobile");
		dictionary.add("ice");
		dictionary.add("cream");
		dictionary.add("man");
		dictionary.add("go");
		
		getIndexMappingByKey(input,dictionary);
		
	}
	
	public static void getIndexMappingByKey(String input,List<String> dictionary) {
		TreeMap<String, List<Integer>> pairs = new TreeMap<>();
		
		dictionary.forEach(pattern->{
			Sunday(input, pattern,pairs);
		});
	
		pairs.forEach((k,v)->{
			System.out.println("Index =》 "+ k +" : " + v);
			System.out.println();
		});
		
//		Index =》 i : [0, 2, 15]
//		Index =》 like : [1]
//		Index =》 mobile : [12]
//		Index =》 sam : [5]
//		Index =》 samsung : [5]
//		Index =》 sung : [8]
	}

	/**
	 * 
	 * @param str
	 * @param ch
	 * @return
	 */
	public static int contains(char[] str,char ch){
		for(int i=str.length-1;i>=0;i--){
			if(str[i]==ch){
				return i;
			}
		}
		return -1;
	}

	/**
	 * match the pattern
	 * @param s
	 * @param p
	 */

	public static void Sunday(String s,String p,TreeMap<String, List<Integer>> pairs){
		char[] sarray = s.toCharArray();
		char[] parray = p.toCharArray();
		int slen=s.length();
		int plen=p.length();
		int i=0,j=0;
		while(i<=slen-plen+j){
			if(sarray[i]!=parray[j]){
				if(i==slen-plen+j){
					break;
				}
				int pos=contains(parray, sarray[i+plen-j]);
				if(pos==-1){
					i=i+plen+1-j;
					j=0;
				}else{
					i=i+plen-pos-j;
					j=0;
				}
			}else{
				if(j==plen-1){
					System.out.println("the start pos is "+(i-j)+" the end pos is "+i);
					//hack
					List<Integer> list = null;
					if(!pairs.containsKey(p)) {
						list = new ArrayList<>();
					}else {
						list = pairs.get(p);
					}
					list.add(i-j);
					pairs.put(p,list);
					//hack
					i=i-j+1;
					j=0;
				}else{
					i++;
					j++;
				}
			}
		}
	}
}
