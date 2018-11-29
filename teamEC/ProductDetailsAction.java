package com.internousdev.mimosa.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.mimosa.dao.ProductInfoDAO;
import com.internousdev.mimosa.dto.ProductInfoDTO;
import com.internousdev.mimosa.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{

	private int productId;
	private Map<String,Object> session;

	public String execute(){

		List<ProductInfoDTO> productInfoDtoList =new ArrayList<ProductInfoDTO>();

		String result=ERROR;
		CommonUtility commonUtility = new CommonUtility();
		if(!commonUtility.checkSession(session)) {
			return "sessionTimeOut";
		}

		ProductInfoDAO productInfoDAO= new ProductInfoDAO();
		ProductInfoDTO productInfoDTO= new ProductInfoDTO();
		productInfoDTO=productInfoDAO.getProductInfo(productId);

		session.put("id", productInfoDTO.getId());
		session.put("productId", productInfoDTO.getProductId());
		session.put("productName", productInfoDTO.getProductName());
		session.put("productNameKana", productInfoDTO.getProductNameKana());
		session.put("imageFilePath", productInfoDTO.getImageFilePath());
		session.put("imageFileName", productInfoDTO.getImageFileName());
		session.put("price", productInfoDTO.getPrice());
		session.put("releaseCompany", productInfoDTO.getReleaseCompany());
		session.put("releaseDate", productInfoDTO.getReleaseDate());
		session.put("productDescription", productInfoDTO.getProductDescription());

		List<Integer> productCountList= new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		session.put("productCountList", productCountList);
		productInfoDtoList = productInfoDAO.getProductInfoListByCategoryId(productInfoDTO.getCategoryId(),productInfoDTO.getProductId(),0,3);
		Iterator<ProductInfoDTO> iterator = productInfoDtoList.iterator();

		if(!(iterator.hasNext())){
			productCountList=null;
		}

		if(!productInfoDtoList.isEmpty() || productCountList== null){
			session.put("productInfoDtoList",productInfoDtoList);
			result=SUCCESS;
		}

		return result;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}