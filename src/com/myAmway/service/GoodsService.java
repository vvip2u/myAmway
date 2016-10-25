package com.myAmway.service;

import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.model.Goods;

public interface GoodsService {
	
	Goods getGoods(Integer id);
	
	void saveGoods(Goods goods);
	
	Goods updateGoods(Goods goods);
	
	void deleteGoods(Goods goods);

	List<Goods> getGoodsList();

	List<Goods> getGoodsList(Page page);

	Integer getAllGoodsCount();


}
