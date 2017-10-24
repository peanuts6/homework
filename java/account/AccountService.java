package account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AccountService {

	private AccountDao accountDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void create(Account account) {
		getAccountDao().insert(account);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void transferFunds(final Account fromAccount,
			final Account toAccount, final Double transferAmount) {
				fromAccount.debit(transferAmount);
				toAccount.credit(transferAmount);
				getAccountDao().update(fromAccount);
				getAccountDao().update(toAccount);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void transferFundsException(final Account fromAccount,
			final Account toAccount, final Double transferAmount) throws Exception {
		fromAccount.debit(transferAmount);
		toAccount.credit(transferAmount);
		getAccountDao().update(fromAccount);
		getAccountDao().update(toAccount);
		// Simulate an exception that occurs during funds transfer
		throw new Exception();
	}

	public void createAccount(Account account) {
		getAccountDao().insert(account);
	}

	public Account getAccount(String accountNumber) {
		return getAccountDao().select(accountNumber);
	}
}
