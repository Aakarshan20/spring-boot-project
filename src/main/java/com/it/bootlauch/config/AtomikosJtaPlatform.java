package com.it.bootlauch.config;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @author User
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {
	private static final long serialVersionUID = 1;
	
	static TransactionManager transactionManager;
	static UserTransaction transaction;
	
	@Override
	protected TransactionManager locateTransactionManager(){
		return transactionManager;
	}
	
	@Override
	protected UserTransaction locateUserTransaction(){
		return transaction;
	}
}