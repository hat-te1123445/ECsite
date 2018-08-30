package com.internousdev.sampleweb.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleweb.dao.ProductInfoDAO;
import com.internousdev.sampleweb.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport implements SessionAware{
	private Collection<String> checkList;
	private String id;
	private String productId;
	private String productName;

	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		List<String> checkListErrorMessageList = new ArrayList<String>();

		if(checkList==null){
			checkListErrorMessageList.add("チェックされていません。");
			if(!(session.containsKey("checkListErrorMessageList"))){
				session.put("checkListErrorMessageList", checkListErrorMessageList);
			}
			return ERROR;
		}

		for(String id:checkList){
			System.out.println(id);
			productInfoDAO.delete(id);
		}


		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

		productInfoDtoList = productInfoDAO.getProductInfoList();


		Iterator<ProductInfoDTO> iterator = productInfoDtoList.iterator();

		if(!(iterator.hasNext())){
			productInfoDtoList = null;
		}

		session.put("productInfoDtoList", productInfoDtoList);
		session.put("productInfoDtoList", productInfoDtoList);

		result = SUCCESS;
		return result;
	}

	public Collection<String> getCheckList() {
		return checkList;
	}

	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
