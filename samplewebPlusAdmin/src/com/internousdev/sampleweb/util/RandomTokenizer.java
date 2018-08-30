package com.internousdev.sampleweb.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.struts2.interceptor.SessionAware;


public class RandomTokenizer implements SessionAware{

	//クラス変数 16*2=32バイト
	private static int TOKEN_LENGTH = 16;

	private Map<String, Object> session;

	//乱数ジェネレーター
	public String getRandomToken() {

		//byte型の配列tokenの宣言
		byte token[] = new byte[TOKEN_LENGTH];

		//StrinBufferのインスタンス化
		StringBuffer buf = new StringBuffer();

		//SecureRandomのインスタンス化
		//暗号アルゴリズムのジェネレーター
		SecureRandom random = null;

		//連結した文字列を代入する

		@SuppressWarnings("unused")
		String tokenString = null;


		try {

			//暗号アルゴリズムを設定
			random = SecureRandom.getInstance("SHA1PRNG");

			//SecureRandom.nextBytes(byte[])を呼ぶとより安全な方法で乱数種を生成できる
			random.nextBytes(token);

			for(int i=0; i<token.length; i++) {

				//StringBufferにString.format～を追加
				//「00 16進数」の形でtoken[i]を出力
				buf.append(String.format("%02x", token[i]));

			}

			//連結済み文字列をtokenStringに代入
			tokenString = buf.toString();

			//アルゴリズム使用不可時にエラーを吐く
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();

		}

		return DatatypeConverter.printHexBinary(token);

	}

	//
	public boolean checkToken(Map<String, Object> session) {

		String token = null;

		String nextToken = null;

		if(session.containsKey("nextToken")) {

			token = String.valueOf(session.get("token"));

			nextToken = String.valueOf(session.get("nextToken"));

			if(token.equals(nextToken)) {

				return true;

			}

		} else {

			RandomTokenizer randomTokenizer = new RandomTokenizer();

			//乱数を生成してtokenに代入
			token = randomTokenizer.getRandomToken();

			session.put("token", token);

			return false;
		}

		return false;
	}

	public static int getTOKEN_LENGTH() {
		return TOKEN_LENGTH;
	}

	public static void setTOKEN_LENGTH(int tOKEN_LENGTH) {
		TOKEN_LENGTH = tOKEN_LENGTH;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}



}


