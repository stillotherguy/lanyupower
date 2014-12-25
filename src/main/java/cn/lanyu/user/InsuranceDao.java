package cn.lanyu.user;

import cn.lanyu.base.dao.IGenericDao;

public interface InsuranceDao extends IGenericDao<Insurance> {
	int countUnfinished();
	int countUnfinishedByUsername(String username);
	int countFinishedWithoutFeedback();
	int countFinishedWithoutFeedbackByUsername(String username);
	int countFinishedWithFeedback();
	int countFinishedWithFeedbackByUsername(String username);
}
