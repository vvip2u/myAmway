package com.myAmway.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myAmway.dao.IDao;
import com.myAmway.dao.exception.DataOperationException;
import com.myAmway.frm.orm.Page;
import com.myAmway.model.Goods;
import com.myAmway.service.BaseServiceSupport;
import com.myAmway.service.GoodsService;

@Service(value="goodsService")
public class GoodsServiceImpl extends BaseServiceSupport implements GoodsService {
	
	@Resource(name="goodsDao")
	private IDao<Goods> goodsDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Goods getGoods(Integer id) {
		try {
			return goodsDao.get(id);
		} catch (DataOperationException e) {
			log.error("get Goods error [goodsId: "+ id + "]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveGoods(Goods goods) {
		try {
			goodsDao.save(goods);
		} catch (DataOperationException e) {
			log.error("save Goods error", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Goods updateGoods(Goods goods) {
		try {
			return goodsDao.update(goods);
		} catch (DataOperationException e) {
			log.error("update Goods error [goodsId: " + goods.getGoodsId() + " ]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteGoods(Goods goods) {
		try {
			goodsDao.delete(goods);
		} catch (DataOperationException e) {
			log.error("delete Goods error [goodsId: " + goods.getGoodsId() + " ]", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Goods> getGoodsList() {
		try {
			return goodsDao.getAll(null);
		} catch (DataOperationException e) {
			log.error("load GoodsList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Goods> getGoodsList(Page page) {
		Map<String, Object> criteriaMap;
		try {
			criteriaMap = new HashMap<String, Object>();
			criteriaMap.put("max", page.getRows());
			criteriaMap.put("first", (page.getPage() - 1) * page.getRows());
			return goodsDao.getAll(criteriaMap);
		} catch (DataOperationException e) {
			log.error("load GoodsList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Integer getAllGoodsCount() {
		try {
			return goodsDao.getCount();
		} catch (DataOperationException e) {
			log.error("load GoodsList error ", e);
			return 0;
		}
	}

}
