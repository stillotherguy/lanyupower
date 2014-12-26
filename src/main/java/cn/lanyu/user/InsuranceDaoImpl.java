package cn.lanyu.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lanyu.base.dao.GenericDao;
import cn.lanyu.base.page.Page;


@Repository
public class InsuranceDaoImpl extends GenericDao<Insurance> implements InsuranceDao {

	public InsuranceDaoImpl() {
		super(Insurance.class);
	}

	@Override
	public int countUnfinished() {
		return queryForInt("select count(*) from Insurance i where i.finished=?", new Object[]{false});
	}

	@Override
	public int countUnfinishedByUsername(String username) {
		return queryForInt("select count(*) from Insurance i where i.finished=? and i.user.username=?", new Object[]{false, username});
	}

	@Override
	public int countFinishedWithoutFeedback() {
		return queryForInt("select count(*) from Insurance i where i.finished=? and feedback=?", new Object[]{true,false});
	}

	@Override
	public int countFinishedWithoutFeedbackByUsername(String username) {
		return queryForInt("select count(*) from Insurance i where i.finished=? and i.feedback=? and i.user.username=?", new Object[]{true, false, username});
	}

	@Override
	public int countFinishedWithFeedback() {
		return queryForInt("select count(*) from Insurance i where i.finished=? and feedback=?", new Object[]{true,true});
	}

	@Override
	public int countFinishedWithFeedbackByUsername(String username) {
		return queryForInt("select count(*) from Insurance i where i.finished=? and i.feedback=? and i.user.username=?", new Object[]{true, true, username});
	}

	@Override
	public List<Insurance> pageUnfinished(Page page) {
		return queryForList("select * from Insurance i where i.finished=?", new Object[]{false}, page);
	}

	@Override
	public List<Insurance> pageUnfinishedByUsername(String username, Page page) {
		return queryForList("select * from Insurance i where i.finished=? and i.user.username=?", new Object[]{false, username}, page);
	}

	@Override
	public List<Insurance> pageFinishedWithoutFeedback(Page page) {
		return queryForList("select * from Insurance i where i.finished=? and feedback=?", new Object[]{true,false}, page);
	}

	@Override
	public List<Insurance> pageFinishedWithoutFeedbackByUsername(String username, Page page) {
		return queryForList("select * from Insurance i where i.finished=? and i.feedback=? and i.user.username=?", new Object[]{true, false, username}, page);
	}

	@Override
	public List<Insurance> pageFinishedWithFeedback(Page page) {
		return queryForList("select * from Insurance i where i.finished=? and feedback=?", new Object[]{true,true}, page);
	}

	@Override
	public List<Insurance> pageFinishedWithFeedbackByUsername(String username, Page page) {
		return queryForList("select * from Insurance i where i.finished=? and i.feedback=? and i.user.username=?", new Object[]{true, true, username}, page);
	}

}
