package pl.edu.atena.dao;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

}
