package cn.lanyu.user;

import org.springframework.stereotype.Repository;

import cn.lanyu.base.dao.GenericDao;


@Repository
public class InsuranceDaoImpl extends GenericDao<Insurance> implements InsuranceDao {

	public InsuranceDaoImpl() {
		super(Insurance.class);
	}

}
