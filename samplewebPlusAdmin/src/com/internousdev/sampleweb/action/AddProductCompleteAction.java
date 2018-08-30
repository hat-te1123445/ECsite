package com.internousdev.sampleweb.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleweb.dao.ProductInfoDAO;
import com.internousdev.sampleweb.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddProductCompleteAction extends ActionSupport implements SessionAware{
	private int productId;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private int categoryId;
	private int price;

	private String imageFilePath;
	private String imageFileName;
	private String imageFileName2;

	private String releaseDate;
	private String releaseCompany;

	private Map<String,Object>session;

	private int addProduct = 1;

	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

	public String execute(){
		String result = ERROR;

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		int count = productInfoDAO.createProduct(productId,productName,productNameKana,productDescription,categoryId,price,imageFilePath,imageFileName,releaseDate,releaseCompany);
		if(count > 0){
			result =SUCCESS;

			ProductInfoDAO productInfoDao = new ProductInfoDAO();
			productInfoDtoList = productInfoDao.getProductInfoListAll();
			session.put("productInfoDtoList", productInfoDtoList);

			session.remove("mCategoryDtoList");
		}
		return result;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageFileName2() {
		return imageFileName2;
	}

	public void setImageFileName2(String imageFileName2) {
		this.imageFileName2 = imageFileName2;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<ProductInfoDTO> getProductInfoDtoList() {
		return productInfoDtoList;
	}

	public void setProductInfoDtoList(List<ProductInfoDTO> productInfoDtoList) {
		this.productInfoDtoList = productInfoDtoList;
	}

	public int getAddProduct() {
		return addProduct;
	}

	public void setAddProduct(int addProduct) {
		this.addProduct = addProduct;
	}


}
