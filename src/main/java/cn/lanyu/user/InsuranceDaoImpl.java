package cn.lanyu.user;

import org.springframework.stereotype.Repository;

import cn.lanyu.base.dao.GenericDao;


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

}
