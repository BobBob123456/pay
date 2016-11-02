package com.pay.service;

import com.pay.pojo.DlDate;


public interface IDlDateService extends ICommonService<DlDate> {
	DlDate getLastDlDate(Integer userId);
}
