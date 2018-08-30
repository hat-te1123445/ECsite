package com.internousdev.sampleweb.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtility {
	public String getRandomValue(){

		/*仮の値を代入する？
		HomeActionでは"TempUserId"に適当な値を代入*/
		String value="";
		double d;
		for(int i=1; i<=16; i++){
			d=Math.random() * 10;
			value=value+((int)d);
		}
		return value;
	}

	//文字列sを", "で区切る
	public String[] parseArrayList(String s) {
		return s.split(", ",0);
	}

	//listがnull、空、サイズが０以下であればnullを返す
	//
	public <E> List<List<E>> devideList(List<E> list, int size){
		if (list == null || list.isEmpty() || size <= 0) {
			return null;
		}
		//listの大きさをsizeで割った値（小数点以下繰り上げ）をblockに代入
		//
		int block = list.size() / size + (list.size() % size > 0 ? 1 : 0 );
		List<List<E>> devidedList = new ArrayList<List<E>>(block);
		for (int i = 0; i < block; i ++) {
			int start = i * size;
			int end = Math.min(start + size,  list.size());
			devidedList.add(new ArrayList<E>(list.subList(start,  end)));
		}
		return devidedList;
	}

}
