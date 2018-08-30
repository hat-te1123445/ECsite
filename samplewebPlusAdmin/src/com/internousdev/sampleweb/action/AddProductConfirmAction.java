package com.internousdev.sampleweb.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleweb.dao.MCategoryDAO;
import com.internousdev.sampleweb.dao.ProductInfoDAO;
import com.internousdev.sampleweb.dto.MCategoryDTO;
import com.internousdev.sampleweb.util.CommonUtility;
import com.internousdev.sampleweb.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;


public class AddProductConfirmAction extends ActionSupport implements SessionAware{
	private String productId;
	private int maxIdPlusOne;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private String categoryId;
	private String price;

	private String releaseDate;
	private String releaseCompany;


	private String imageFilePath;
	private String imageFileName;
	private String imageFileName2;


	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private String fileExtension;

	private File userImage2;
	private String userImageContentType2;
	private String userImage2FileName;
	private String fileExtension2;

	private Map<String,Object>session;

	private String priceError;
	private String imageFilePathError;

	private List<String> productNameErrorMessageList = new ArrayList<String>();
	private List<String> productNameKanaErrorMessageList = new ArrayList<String>();
	private List<String> productDescriptionErrorMessageList = new ArrayList<String>();
	private List<String> categoryIdErrorMessageList = new ArrayList<String>();
	private List<String> priceErrorMessageList = new ArrayList<String>();

	private List<String> releaseDateErrorMessageList = new ArrayList<String>();
	private List<String> releaseCompanyErrorMessageList = new ArrayList<String>();

	private List<String> imageFilePathErrorMessageList = new ArrayList<String>();
	private List<String> identical_productNameErrorMessageList = new ArrayList<String>();
	private List<String> identical_productNameKanaErrorMessageList = new ArrayList<String>();

	private List<String> imageFileNameErrorMessageList = new ArrayList<String>();

	public String execute(){
		String result = ERROR;

		InputChecker inputChecker = new InputChecker();

		productNameErrorMessageList = inputChecker.doCheck("商品名", productName, 1, 40, true, true, true, false, true, false, true);
		productNameKanaErrorMessageList = inputChecker.doCheck("商品名かな", productNameKana, 1, 40, false, false, true, false, false, false, true);
		productDescriptionErrorMessageList = inputChecker.doCheck("商品詳細", productDescription, 1, 100, true, true, true, true, true, false, true);
		categoryIdErrorMessageList = inputChecker.doCheck("カテゴリID", categoryId, 1, 8, false, false, false, true, false, false, false);
		priceErrorMessageList = inputChecker.doCheck("価格", price, 1, 8, false, false, false, true, false, false, false);

		releaseDateErrorMessageList = checkDate("発売年月", releaseDate);
		releaseCompanyErrorMessageList = inputChecker.doCheck("発売会社", releaseCompany, 1, 50, true, true, true, true, true, false, true);


		//画像ファイルの選択チェック
		if(userImage != null){
			imageFilePathError = null;
			imageFileNameErrorMessageList = inputChecker.doCheck("ファイル名", userImageFileName, 1, 30, true, true, true, true, true, true, true);
		} else {
			imageFilePathErrorMessageList.add("画像ファイルを選んでください");
		}

		//IDの自動生成
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		maxIdPlusOne = productInfoDAO.maxIdPlusOne();

		//入力情報をsessionに保存
		session.put("productId",  maxIdPlusOne);
		session.put("productName",  productName);
		session.put("productNameKana",  productNameKana);
		session.put("productDescription",  productDescription);
		session.put("categoryId", categoryId);
		session.put("price", price);

		session.put("releaseDate", releaseDate);
		session.put("releaseCompany", releaseCompany);

		MCategoryDAO mCategoryDAO = new MCategoryDAO();
		MCategoryDTO mCategoryDTO = mCategoryDAO.getMCategory(categoryId);

		session.put("categoryName", mCategoryDTO.getCategoryName());

		if(productNameErrorMessageList.size()==0
			&& productNameKanaErrorMessageList.size()==0
			&& productDescriptionErrorMessageList.size()==0
			&& categoryIdErrorMessageList.size()==0
			&& priceErrorMessageList.size()==0
			&& releaseDateErrorMessageList.size()==0
			&& releaseCompanyErrorMessageList.size()==0
			&& imageFilePathErrorMessageList.size()==0
			&& imageFileNameErrorMessageList.size()==0){

			//画像をサーバーに保存
			String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("images/");

			CommonUtility commonUtility = new CommonUtility();
			userImageFileName = commonUtility.getRandomValue() + userImageFileName;

			//サーバー上に保存した画像をimageフォルダにコピー
			File fileToCreate = new File(filePath,userImageFileName);

			try{
				FileUtils.copyFile(userImage, fileToCreate);
				session.put("imageFileName", userImageFileName);
				session.put("imageFilePath", "./images");
				session.put("image_flg", userImageFileName);
			} catch (IOException e){
				e.printStackTrace();
			}
			result = SUCCESS;

		} else{
			session.put("productNameErrorMessageList", productNameErrorMessageList);
			session.put("productNameKanaErrorMessageList", productNameKanaErrorMessageList);
			session.put("productDescriptionErrorMessageList", productDescriptionErrorMessageList);
			session.put("categoryIdErrorMessageList", categoryIdErrorMessageList);
			session.put("priceErrorMessageList", priceErrorMessageList);
			session.put("releaseDateErrorMessageList", releaseDateErrorMessageList);
			session.put("releaseCompanyErrorMessageList", releaseCompanyErrorMessageList);
			session.put("imageFilePathErrorMessageList", imageFilePathErrorMessageList);
			session.put("imageFileNameErrorMessageList", imageFileNameErrorMessageList);
			session.put("identical_productNameErrorMessageList",identical_productNameErrorMessageList);
			session.put("identical_productNameKanaErrorMessageList",identical_productNameKanaErrorMessageList);
			result = ERROR;
		}
		return result;
	}

	private List<String> checkDate(String propertyName, String value){
		List<String> errorList = new ArrayList<String>();

		if(StringUtils.isEmpty(value)){
			errorList.add(propertyName + "を入力してください。");
		} else {
			try{
				DateUtils.parseDateStrictly(value,  new String[] { "yyyy-MM-dd"});
			} catch (ParseException e){
				e.printStackTrace();
				errorList.add("yyyy-MM-ddで入力してください。");
			}
		}
		return errorList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getMaxIdPlusOne() {
		return maxIdPlusOne;
	}

	public void setMaxIdPlusOne(int maxIdPlusOne) {
		this.maxIdPlusOne = maxIdPlusOne;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public File getUserImage2() {
		return userImage2;
	}

	public void setUserImage2(File userImage2) {
		this.userImage2 = userImage2;
	}

	public String getUserImageContentType2() {
		return userImageContentType2;
	}

	public void setUserImageContentType2(String userImageContentType2) {
		this.userImageContentType2 = userImageContentType2;
	}

	public String getUserImage2FileName() {
		return userImage2FileName;
	}

	public void setUserImage2FileName(String userImage2FileName) {
		this.userImage2FileName = userImage2FileName;
	}

	public String getFileExtension2() {
		return fileExtension2;
	}

	public void setFileExtension2(String fileExtension2) {
		this.fileExtension2 = fileExtension2;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getPriceError() {
		return priceError;
	}

	public void setPriceError(String priceError) {
		this.priceError = priceError;
	}

	public String getImageFilePathError() {
		return imageFilePathError;
	}

	public void setImageFilePathError(String imageFilePathError) {
		this.imageFilePathError = imageFilePathError;
	}

	public List<String> getProductNameErrorMessageList() {
		return productNameErrorMessageList;
	}

	public void setProductNameErrorMessageList(List<String> productNameErrorMessageList) {
		this.productNameErrorMessageList = productNameErrorMessageList;
	}

	public List<String> getProductNameKanaErrorMessageList() {
		return productNameKanaErrorMessageList;
	}

	public void setProductNameKanaErrorMessageList(List<String> productNameKanaErrorMessageList) {
		this.productNameKanaErrorMessageList = productNameKanaErrorMessageList;
	}

	public List<String> getProductDescriptionErrorMessageList() {
		return productDescriptionErrorMessageList;
	}

	public void setProductDescriptionErrorMessageList(List<String> productDescriptionErrorMessageList) {
		this.productDescriptionErrorMessageList = productDescriptionErrorMessageList;
	}

	public List<String> getCategoryIdErrorMessageList() {
		return categoryIdErrorMessageList;
	}

	public void setCategoryIdErrorMessageList(List<String> categoryIdErrorMessageList) {
		this.categoryIdErrorMessageList = categoryIdErrorMessageList;
	}

	public List<String> getPriceErrorMessageList() {
		return priceErrorMessageList;
	}

	public void setPriceErrorMessageList(List<String> priceErrorMessageList) {
		this.priceErrorMessageList = priceErrorMessageList;
	}

	public List<String> getReleaseDateErrorMessageList() {
		return releaseDateErrorMessageList;
	}

	public void setReleaseDateErrorMessageList(List<String> releaseDateErrorMessageList) {
		this.releaseDateErrorMessageList = releaseDateErrorMessageList;
	}

	public List<String> getReleaseCompanyErrorMessageList() {
		return releaseCompanyErrorMessageList;
	}

	public void setReleaseCompanyErrorMessageList(List<String> releaseCompanyErrorMessageList) {
		this.releaseCompanyErrorMessageList = releaseCompanyErrorMessageList;
	}

	public List<String> getImageFilePathErrorMessageList() {
		return imageFilePathErrorMessageList;
	}

	public void setImageFilePathErrorMessageList(List<String> imageFilePathErrorMessageList) {
		this.imageFilePathErrorMessageList = imageFilePathErrorMessageList;
	}

	public List<String> getIdentical_productNameErrorMessageList() {
		return identical_productNameErrorMessageList;
	}

	public void setIdentical_productNameErrorMessageList(List<String> identical_productNameErrorMessageList) {
		this.identical_productNameErrorMessageList = identical_productNameErrorMessageList;
	}

	public List<String> getIdentical_productNameKanaErrorMessageList() {
		return identical_productNameKanaErrorMessageList;
	}

	public void setIdentical_productNameKanaErrorMessageList(List<String> identical_productNameKanaErrorMessageList) {
		this.identical_productNameKanaErrorMessageList = identical_productNameKanaErrorMessageList;
	}

	public List<String> getImageFileNameErrorMessageList() {
		return imageFileNameErrorMessageList;
	}

	public void setImageFileNameErrorMessageList(List<String> imageFileNameErrorMessageList) {
		this.imageFileNameErrorMessageList = imageFileNameErrorMessageList;
	}



}
