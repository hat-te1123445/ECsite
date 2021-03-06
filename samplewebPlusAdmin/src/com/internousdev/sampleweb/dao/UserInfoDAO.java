package com.internousdev.sampleweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.internousdev.sampleweb.dto.UserInfoDTO;
import com.internousdev.sampleweb.util.DBConnector;

public class UserInfoDAO {

	//新規ユーザー登録
	public int createUser(String familyName,String firstName,String familyNameKana,String firstNameKana,String sex,String email,String loginId,String password){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int count =0;

		String sql="insert into user_info(user_id,password, family_name, first_name, family_name_kana,"
				+ " first_name_kana, sex, email, status, logined, regist_date, update_date)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), 0)";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, familyName);
			preparedStatement.setString(4, firstName);
			preparedStatement.setString(5, familyNameKana);
			preparedStatement.setString(6, firstNameKana);
			preparedStatement.setString(7, sex);
			preparedStatement.setString(8, email);
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, 1);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}

		return count;
	}

	//同一のuserIdとpasswordが1つ以上存在するか
	public boolean isExistsUserInfo(String loginId, String password) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		boolean result = false;
		String sql = "SELECT count(*) as count from user_info where user_id=? and password=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				if(resultSet.getInt("count")>0){
					result = true;
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	//idとpasswordからユーザー情報を取得する
	public UserInfoDTO getUserInfo(String loginId, String password){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		String sql ="select * from user_info where user_id=? and password=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				userInfoDTO.setId(resultSet.getInt("id"));
				userInfoDTO.setUserId(resultSet.getString("user_id"));
				userInfoDTO.setPassword(resultSet.getString("password"));
				userInfoDTO.setFamilyName(resultSet.getString("family_name"));
				userInfoDTO.setFirstName(resultSet.getString("first_name"));
				userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
				userInfoDTO.setSex(resultSet.getInt("sex"));
				userInfoDTO.setEmail(resultSet.getString("email"));
				userInfoDTO.setStatus(resultSet.getString("status"));
				userInfoDTO.setLogined(resultSet.getInt("status"));
				userInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				userInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
			}
		} catch (SQLException e){

		}
		try{
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return userInfoDTO;
	}

	public UserInfoDTO getUserInfo(String loginId) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		String sql = "SELECT * FROM user_info where user_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				userInfoDTO.setId(resultSet.getInt("id"));
				userInfoDTO.setUserId(resultSet.getString("user_id"));
				userInfoDTO.setPassword(resultSet.getString("password"));
				userInfoDTO.setFamilyName(resultSet.getString("family_name"));
				userInfoDTO.setFirstName(resultSet.getString("first_name"));
				userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
				userInfoDTO.setFirstNameKana(resultSet.getString("first_name_kana"));
				userInfoDTO.setSex(resultSet.getInt("sex"));
				userInfoDTO.setEmail(resultSet.getString("email"));
				userInfoDTO.setStatus(resultSet.getString("status"));
				userInfoDTO.setLogined(resultSet.getInt("logined"));
				userInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				userInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return userInfoDTO;
	}

	//passwordの更新
	public int resetPassword(String loginId, String password) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "update user_info set password=? where user_id=?";
		int result = 0;
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, loginId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	//loginIdとpasswordでユーザー特定し、
	//loginedの値を1にする（ログインする）
	public int login(String loginId, String password){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int result=0;
		String sql="update user_info set logined=1 where user_id=? and password=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	//loginedを0にする（＝ログアウトする）
	public int logout(String loginId){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		int result=0;
		String sql = "UPDATE user_info set logined=0 where user_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	//文字列の先頭だけ入力文字を表示する
	public String concealPassword(String password){
		int beginIndex = 0;
		int endIndex = 1;
		if(password.length() > 1){
			endIndex = 2;
		}
		int n =password.length();
		String rep= StringUtils.repeat("*", n);

		StringBuilder stringBuilder = new StringBuilder(rep);

		String concealPassword = stringBuilder.replace(beginIndex,  endIndex,  password.substring(beginIndex,endIndex)).toString();
		return concealPassword;
	}

}