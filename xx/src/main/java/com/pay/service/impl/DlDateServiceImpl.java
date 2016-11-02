package com.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.dao.impl.DlDateDaoImpl;
import com.pay.pojo.DlDate;
import com.pay.service.IDlDateService;


@Service("dlDateService")
public class DlDateServiceImpl implements IDlDateService {

	@Resource
	private DlDateDaoImpl dlDateDaoImpl;
	
	public DlDate getLastDlDate(Integer userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		List<DlDate> dlDates=this.dlDateDaoImpl.getObjectListBySelectSqlId(map, 1, 1, "getLastDlDate");
		return dlDates.size()==0?null:dlDates.get(0);
	}

	public int add(DlDate t) {
		int result = this.dlDateDaoImpl.add(t);
		
		return result;
	}

	public int addSelective(DlDate t) {
	
		return 0;
	}

	public int update(DlDate t) {
		return 0;
	}
	public int updateSelective(DlDate t) {
	
		return 0;
	}

	public int delete(DlDate t) {
		return 0;
	}

}
