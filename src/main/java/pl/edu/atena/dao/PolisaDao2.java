package pl.edu.atena.dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import pl.edu.atena.entities.Polisa;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PolisaDao2 {

	@Inject
	private PolisaDao dao;;

	@Resource
	UserTransaction ut;

	public Polisa updateUbezpieczajacy(Polisa polisa) {
		try {
			ut.begin();
			dao.update(polisa);
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return polisa;
	}

}
