package com.internousdev.sampleweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.internousdev.sampleweb.dto.MCategoryDTO;
import com.internousdev.sampleweb.util.DBConnector;

public class MCategoryDAO {
	public List<MCategoryDTO> getMCategoryList(){
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
		String sql = "select * from m_category";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			//SQL文の結果をリスト"mCategoryDtoList"に格納する
			while(resultSet.next()){
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(resultSet.getInt("id"));
				mCategoryDTO.setCategoryId(resultSet.getInt("category_id"));
				mCategoryDTO.setCategoryName(resultSet.getString("category_name"));
				mCategoryDTO.setCategoryDescription(resultSet.getString("category_description"));
				mCategoryDTO.setInsertDate(resultSet.getDate("insert_date"));
				mCategoryDTO.setUpdateDate(resultSet.getDate("update_date"));
				mCategoryDtoList.add(mCategoryDTO);
			}

			//iterator⇒順番を持ったリスト
			Iterator<MCategoryDTO> iterator = mCategoryDtoList.iterator();
			//iteratorに何もなければリスト"mCategoryDtoList"を空にする
			if(!(iterator.hasNext())){
				mCategoryDtoList = null;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		try{
			connection.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return mCategoryDtoList;
	}

	public MCategoryDTO getMCategory(String categoryId){
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		MCategoryDTO MCategoryDTO = new MCategoryDTO();

		String sql = "SELECT * FROM m_category WHERE category_id = ? ";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				MCategoryDTO.setCategoryName(rs.getString("category_name"));
				MCategoryDTO.setCategoryId(rs.getInt("category_id"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		try{
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return MCategoryDTO;
	}

}
