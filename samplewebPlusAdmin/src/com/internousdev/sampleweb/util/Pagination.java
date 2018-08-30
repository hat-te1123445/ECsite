package com.internousdev.sampleweb.util;

import java.util.ArrayList;
import java.util.List;

import com.internousdev.sampleweb.dto.PaginationDTO;
import com.internousdev.sampleweb.dto.ProductInfoDTO;

public class Pagination {

	//
	public PaginationDTO initialize(List<ProductInfoDTO> list, int pageSize){
		PaginationDTO paginationDTO = new PaginationDTO();
		paginationDTO.setTotalPageSize((int)(Math.ceil(list.size() / pageSize)));	//全ページ数
		paginationDTO.setCurrentPageNo(1);											//現在のページ数
		paginationDTO.setTotalRecordSize(list.size() - 1);							//全レコード数
		paginationDTO.setStartRecordNo(pageSize * (paginationDTO.getCurrentPageNo() -1));	//現在のページ番号に対する開始レコード番号（オフセット）
		paginationDTO.setEndRecordNo(Math.min(paginationDTO.getStartRecordNo() + (pageSize - 1),paginationDTO.getTotalRecordSize()));	//現在のページ番号に対する終了レコード番号

		//１から最大ページ数までの数字をpageNumberListに格納する
		List<Integer> pageNumberList = new ArrayList<Integer>();
		for(int pageNumber = 1;pageNumber <= paginationDTO.getTotalPageSize(); pageNumber++) {
			pageNumberList.add(pageNumber);
		}

		//開始レコード番号から終了レコード番号までの数字をproductInfoPagesに格納する
		List<ProductInfoDTO> productInfoPages = new ArrayList<ProductInfoDTO>();
		for(int pageNumberOffset = paginationDTO.getStartRecordNo(); pageNumberOffset <= paginationDTO.getEndRecordNo(); pageNumberOffset++) {
			productInfoPages.add(list.get(pageNumberOffset));
		}

		//１ページ分の商品情報を格納
		paginationDTO.setCurrentProductInfoPage(productInfoPages);

		//開始レコード番号が1以下ならば、previousPageはfalseにする。
		//そうでなければ、previousPageはTRUEにし、previousPageNoはcurrentPageNo()-1にする。
		if((paginationDTO.getStartRecordNo() - 1) <= 0){
			paginationDTO.setPreviousPage(false);
		}else {
			paginationDTO.setPreviousPage(true);
			paginationDTO.setPreviousPageNo(paginationDTO.getCurrentPageNo() - 1);
		}

		//終了レコードと全レコードが同じであれば
		//nextPageはfalseにする。
		//そうでなれば、nextPageはtrueにし、nextPageNoはcurrentPageNo()+1にする。
		if(paginationDTO.getEndRecordNo() == paginationDTO.getTotalRecordSize()) {
			paginationDTO.setNextPage(false);
		} else {
			paginationDTO.setNextPage(true);
			paginationDTO.setNextPageNo(paginationDTO.getCurrentPageNo() + 1);
		}
		return paginationDTO;
	}

	//
	public PaginationDTO getPage(List<ProductInfoDTO> list, int pageSize, String pageNo){

		PaginationDTO paginationDTO = new PaginationDTO();
		paginationDTO.setTotalPageSize((int)(Math.ceil(list.size() / pageSize)));	//全ページ数
		paginationDTO.setCurrentPageNo(Integer.parseInt(pageNo));	//現在のページ番号
		paginationDTO.setTotalRecordSize(list.size() - 1);	//全レコード数
		paginationDTO.setStartRecordNo(pageSize * (paginationDTO.getCurrentPageNo() - 1));	//現在のページ番号に対する開始レコード番号（オフセット）
//		paginationDTO.setStartRecordNo(pageSize * paginationDTO.getCurrentPageNo() + 1);	//現在のページ番号に対する開始レコード番号
		paginationDTO.setEndRecordNo(Math.min(paginationDTO.getStartRecordNo() + (pageSize - 1),paginationDTO.getTotalRecordSize()));	//現在のページ番号に対する終了レコード番号

		//pageNumberListに１から全ページ数までの数字を格納する。
		List<Integer> pageNumberList = new ArrayList<Integer>();
		for(int pageNumber = 1; pageNumber <= paginationDTO.getTotalPageSize(); pageNumber++) {
			pageNumberList.add(pageNumber);
		}

		//productInfoPagesにそのページの最初の番号から最後の番号までの数字を格納する。
		List<ProductInfoDTO> productInfoPages = new ArrayList<ProductInfoDTO>();
		for(int pageNumberOffset=paginationDTO.getStartRecordNo(); pageNumberOffset <= paginationDTO.getEndRecordNo(); pageNumberOffset++) {
			productInfoPages.add(list.get(pageNumberOffset));
		}

		//currentProductInfoPageに全ページまでの数字を格納する。
		paginationDTO.setCurrentProductInfoPage(productInfoPages);

		//開始レコード番号がpageSizeより小さい以下ならば、previousPageをfalseにする
		//そうでなければ、TRUEにし、previousPageNoにcurrentPageNo-1を入れる
		if(paginationDTO.getStartRecordNo() < pageSize){
			paginationDTO.setPreviousPage(false);
		} else {
			paginationDTO.setPreviousPage(true);
			paginationDTO.setPreviousPageNo(paginationDTO.getCurrentPageNo() - 1);
		}

		//終了レコード番号がtotalRecordSize()と同じならば、nextPageをfalseにする
		//そうでなければ、TRUEにし、にcurrentPageNo+1を入れる
		if(paginationDTO.getEndRecordNo() == paginationDTO.getTotalRecordSize()) {
			paginationDTO.setNextPage(false);
		} else {
			paginationDTO.setNextPage(true);
			paginationDTO.setNextPageNo(paginationDTO.getCurrentPageNo() + 1);
		}

		return paginationDTO;
	}

}
