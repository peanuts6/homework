package leader.tx;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;

/**
 * Created by xqy on 17/9/27.
 */

public class EmployeeTransactionManager extends DataSourceTransactionManager {
    private static final long serialVersionUID = 7938334635313866139L;
    public EmployeeTransactionManager(DataSource dataSource){
        super(dataSource);
    }
    @Override
    public DataSource getDataSource() {
        System.out.println("EmployeeTransactionManager  getDataSource called ");
        return super.getDataSource();
    }
    @Override
    protected Object doGetTransaction() {
        System.out.println("EmployeeTransactionManager  doGetTransaction called ");
        return super.doGetTransaction();
    }
    @Override
    protected boolean isExistingTransaction(Object transaction) {
        System.out.println("EmployeeTransactionManager  isExistingTransaction called ");
        return super.isExistingTransaction(transaction);
    }
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        System.out.println("EmployeeTransactionManager  doBegin called ");
        super.doBegin(transaction, definition);
    }
    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        System.out.println("EmployeeTransactionManager  doCommit called ");
        super.doCommit(status);
    }
    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        System.out.println("doRollback  doCommit called ");
        super.doRollback(status);
    }
    @Override
    protected void doSetRollbackOnly(DefaultTransactionStatus status) {
        System.out.println("doRollback  doSetRollbackOnly called ");
        super.doSetRollbackOnly(status);
    }
}
