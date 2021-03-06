package com.internousdev.sampleweb.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class InputChecker {

	public List<String> doCheck(String propertyName,String value,int minLength, int maxLength,boolean availableAlphabeticCharacters,boolean availableKanji,boolean availableHiragana,boolean availableHalfWidthDigit,boolean availableHalfWidthSymbols,boolean availableKatakana,boolean availableFullWidthSymbols){

		//検証した結果を入れるList
		List<String> stringList = new ArrayList<String>();
		List<String> characterTypeList = new ArrayList<String>();

		//StringUtils.isEmpty(～)⇒文字列「～」が空がどうか判定
		if(StringUtils.isEmpty(value)){
			stringList.add(propertyName + "を入力してください。");
		}

		//文字列が最小文字数以上、最大文字数以下かどうかを検証
		if(value.length() < minLength || value.length() > maxLength){
			stringList.add(propertyName + "は" + minLength + "文字以上" + maxLength + "文字以下で入力してください。");
		}

		String regularExpression = "";
		String errorExpression = "";


		//文字が入力されていれば、regularExpression/errorExpressionに"[^"を入れる
		if(availableAlphabeticCharacters || availableKanji || availableHiragana || availableHalfWidthDigit || availableHalfWidthSymbols || availableKatakana || availableFullWidthSymbols){
			regularExpression="[^";
		}
		if(!(availableAlphabeticCharacters) || !(availableKanji) || !(availableHiragana) || !(availableHalfWidthDigit) || !(availableHalfWidthSymbols) || !(availableKatakana) || !(availableFullWidthSymbols)){
			errorExpression="[^";
		}

		//アルファベットであればregularExpression に"a-zA-Z"を追加。characterTypeListに"半角英字"を追加。
		//そうでなければ、errorExpressionに"a-zA-Z"を追加
		if(availableAlphabeticCharacters){
			regularExpression +="a-zA-Z";
			characterTypeList.add("半角英字");
		} else {
			errorExpression +="a-zA-Z";
		}

		//漢字であればregularExpression に"一-龯"を追加。characterTypeListに"漢字"を追加。
		//そうでなければ、errorExpressionに"一-龯"を追加
		if(availableKanji){
			regularExpression +="一-龯";
			characterTypeList.add("漢字");
		} else {
			errorExpression +="一-龯";
		}

		//ひらがなであればregularExpression に"ぁ-ん"を追加。characterTypeListに"ひらがな"を追加。
		//そうでなければ、errorExpressionに"ぁ-ん"を追加
		if(availableHiragana){
			regularExpression +="ぁ-ん";
			characterTypeList.add("ひらがな");
		}else {
			errorExpression +="ぁ-ん";
		}

		//半角数字であればregularExpression に"0-9"を追加。characterTypeListに"半角数字"を追加。
		//そうでなければ、errorExpressionに"0-9"を追加
		if(availableHalfWidthDigit){
			regularExpression +="0-9";
			characterTypeList.add("半角数字");
		} else {
			errorExpression +="0-9";
		}

		//半角記号であればregularExpression に"@.,;:!#$%&'*+-/=?^_`{|}~"を追加。characterTypeListに"半角記号"を追加。
		//そうでなければ、errorExpressionに"@.,;:!#$%&'*+-/=?^_`{|}~"を追加
		if(availableHalfWidthSymbols){
			regularExpression +="@.,;:!#$%&'*+-/=?^_`{|}~";
			characterTypeList.add("半角記号");
		} else {
			errorExpression +="@.,;:!#$%&'*+-/=?^_`{|}~";
		}

		//カタカナであればregularExpression に"ァ-ヲ"を追加。characterTypeListに"カタカナ"を追加。
		//そうでなければ、errorExpressionに"ァ-ヲ"を追加
		if(availableKatakana){
			regularExpression +="ァ-ヺ";
			characterTypeList.add("カタカナ");
		} else {
			errorExpression +="ァ-ヺ";
		}

		//全角記号であればregularExpression に"＠．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～"を追加。characterTypeListに"全角記号"を追加。
		//そうでなければ、errorExpressionに"＠．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～"を追加
		if(availableFullWidthSymbols){
			regularExpression +="＠．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～";
			characterTypeList.add("全角記号");
		} else {
			errorExpression +="＠．，；：！＃＄％＆’＊＋―／＝？＾＿｀｛｜｝～";
		}

		if(!StringUtils.isEmpty(regularExpression)){
			regularExpression +="]+";
		}
		if(!StringUtils.isEmpty(errorExpression)){
			errorExpression +="]+";
		}


		String characterType = "";

		//characterTypeに入力した文字種類名（アルファベットなど）を入れる。
		for(int i=0; i<characterTypeList.size();i++){
			if(i==0){
				characterType +=characterTypeList.get(i).toString();
			}else{
				characterType +="、" + characterTypeList.get(i).toString();
			}
		}

		//A.matches(B) ⇒ AとBの文字列が完全に一致するか。
		//errorExpression/regularExpressionの [～]+ は[]内の一文字、+の前には文字が存在する、という意味で、
		//指定した文字列の中に～が含まれるか、という問いになっている。
		//errorExpressionが空欄、かつ入力文字列がregularExpressionと一致するならば、/しないならば。
		if(errorExpression.equals("")){
			if(value.matches(regularExpression)){
				stringList.add(propertyName + "は" + characterType+"で入力してください");
			}
		} else {
			if(value.matches(regularExpression) || (!value.matches(errorExpression) &&!value.equals(""))){
				stringList.add(propertyName + "は" + characterType + "で入力してください");
			}
		}

		return stringList;
	}

	//一度目のパスワードと二度目のパスワードが同じか
	public List<String> doPasswordCheck(String password,String reConfirmationPassword){
		List<String> stringList = new ArrayList<String>();
		//PasswordがreConfirmationPasswordと異なるならば、
		if(!(password.equals(reConfirmationPassword))){
			stringList.add("入力されたパスワードが異なります。");
		}
		return stringList;
	}

}
