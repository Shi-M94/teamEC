package com.internousdev.mimosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.mimosa.dto.ProductInfoDTO;
import com.internousdev.mimosa.util.DBConnector;

public class ProductInfoDAO {

	//商品一覧取得
	public List<ProductInfoDTO> getProductInfoList(){
		DBConnector dbconnector=new DBConnector();
		Connection connection=dbconnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList=new ArrayList<ProductInfoDTO>();
		String sql="select * from product_info order by product_id ASC";

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDto=new ProductInfoDTO();
				productInfoDto.setId(resultSet.getInt("id"));
				productInfoDto.setProductId(resultSet.getInt("product_id"));
				productInfoDto.setProductName(resultSet.getString("product_name"));
				productInfoDto.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDto.setProductDescription(resultSet.getString("product_description"));
				productInfoDto.setCategoryId(resultSet.getInt("category_id"));
				productInfoDto.setPrice(resultSet.getInt("price"));
				productInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDto.setStatus(resultSet.getInt("status"));
				productInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	//商品詳細情報取得
	public ProductInfoDTO getProductInfo(int productId){
		DBConnector dbconnector=new DBConnector();
		Connection connection=dbconnector.getConnection();
		ProductInfoDTO productInfoDTO =new ProductInfoDTO();
		String sql="select * from product_info where product_id=?";

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			connection.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		return productInfoDTO;
	}

	//関連商品取得
	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId,int productId,int limitedOffset,int limitedRowCount){
		DBConnector dbconnector=new DBConnector();
		Connection connection=dbconnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList =new ArrayList<ProductInfoDTO>();
		String sql="select * from product_info where category_id=? and product_id !=? order by rand() limit ?,?";

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, limitedOffset);
			preparedStatement.setInt(4, limitedRowCount);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDTO =new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			connection.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	//検索ワードで検索
	public List<ProductInfoDTO> getProductInfoListAll(String[] keywordList){
		DBConnector dbconnector=new DBConnector();
		Connection connection=dbconnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList =new ArrayList<ProductInfoDTO>();
		boolean initializeFlag=true;
		String sql="select * from product_info where ";

		for(String keyword : keywordList){
			if(initializeFlag){
				sql += "(product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag=false;
			}else{
				sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDTO =new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			connection.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	//検索ワードとカテゴリ選択で検索
	public List<ProductInfoDTO> getProductInfoListByKeywords(String[] keywordList,String categoryId){
		DBConnector dbconnector=new DBConnector();
		Connection connection=dbconnector.getConnection();
		List<ProductInfoDTO> productInfoDtoList =new ArrayList<ProductInfoDTO>();
		boolean initializeFlag=true;
		String sql="select * from product_info where ";

		for(String keyword : keywordList){
			if(initializeFlag){
				sql += "category_id = " + categoryId + " and ((product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag=false;
			}else{
				sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		sql += ")";

		System.out.println(sql);
		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				ProductInfoDTO productInfoDTO =new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				productInfoDTO.setStatus(resultSet.getInt("status"));
				productInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				productInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
			connection.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}
}
