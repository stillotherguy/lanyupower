package cn.lanyu.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.lanyu.base.dao.IGenericDao;
import cn.lanyu.base.page.Page;

@Transactional
public interface InsuranceDao extends IGenericDao<Insurance> {
	int countUnfinished();
	int countUnfinishedByClientno(String clientno);
	int countUnfinishedByUsername(String username);
	int countFinishedWithoutFeedback();
	int countFinishedWithoutFeedbackByClientno(String clientno);
	int countFinishedWithoutFeedbackByUsername(String username);
	int countFinishedWithFeedback();
	int countFinishedWithFeedbackByClientno(String clientno);
	int countFinishedWithFeedbackByUsername(String username);
	List<Insurance> pageUnfinished(Page page);
	List<Insurance> pageUnfinishedByUsername(String username, Page page);
	List<Insurance> pageUnfinishedByClientno(String clientno, Page page);
	List<Insurance> pageFinishedWithoutFeedback(Page page);
	List<Insurance> pageFinishedWithoutFeedbackByUsername(String username, Page page);
	List<Insurance> pageFinishedWithoutFeedbackByClientno(String clientno, Page page);
	List<Insurance> pageFinishedWithFeedback(Page page);
	List<Insurance> pageFinishedWithFeedbackByUsername(String username, Page page);
	List<Insurance> pageFinishedWithFeedbackByClientno(String clientno, Page page);
}
