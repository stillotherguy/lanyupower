package cn.lanyu.user;

import java.util.List;

import cn.lanyu.base.dao.IGenericDao;
import cn.lanyu.base.page.Page;

public interface InsuranceDao extends IGenericDao<Insurance> {
	int countUnfinished();
	int countUnfinishedByUsername(String username);
	int countFinishedWithoutFeedback();
	int countFinishedWithoutFeedbackByUsername(String username);
	int countFinishedWithFeedback();
	int countFinishedWithFeedbackByUsername(String username);
	List<Insurance> pageUnfinished(Page page);
	List<Insurance> pageUnfinishedByUsername(String username, Page page);
	List<Insurance> pageFinishedWithoutFeedback(Page page);
	List<Insurance> pageFinishedWithoutFeedbackByUsername(String username, Page page);
	List<Insurance> pageFinishedWithFeedback(Page page);
	List<Insurance> pageFinishedWithFeedbackByUsername(String username, Page page);
}
