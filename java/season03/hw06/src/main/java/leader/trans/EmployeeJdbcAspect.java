package leader.trans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * Created by xqy on 17/9/20.
 */
@Aspect
@Component
public class EmployeeJdbcAspect {
    @Autowired
    MyDataSource myDataSource;

    @Around("execution(* leader.trans.*Service.get*(..)) " +
            "|| execution(* leader.trans.*Service.save*(..)) " +
            "|| execution(* leader.trans.*Service.update*(..)) " +
            "|| execution(* leader.trans.*Service.create*(..)) " +
            "|| execution(* leader.trans.*Service.delete*(..)) ")
    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("--- Around Advice: Before invoking method ---");
        Object value = null;
        Object target = null;
        // 数据库连接
        myDataSource.getConnection();
        // 开启事务
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = myDataSource.getTransactionManager().getTransaction(transactionDefinition);
        try {
            target = proceedingJoinPoint.getThis();
            value = proceedingJoinPoint.proceed();
            // 提交事务
            myDataSource.getTransactionManager().commit(status);
        } catch (Throwable e) {
            if(e instanceof RuntimeException){
                System.out.println("--- Found RuntimeException in "+target.getClass().getName()+". do rollback");
                // 回滚
                myDataSource.getTransactionManager().rollback(status);
            } else {
                e.printStackTrace();
                // 关闭连接
                myDataSource.closeConnection();
            }
        }
        System.out.println("--- Around Advice: After invoking method. Return value="+value+" ---");
        System.out.println();

        return value;
    }

    @Before("execution(* leader.trans.*Service.query*(..))")
    public void employeeBeforeAdvice(){
        System.out.println(" -- Accept Before Advice ...");
        myDataSource.getConnection();
    }

    @After("execution(* leader.trans.*Service.query*(..))")
    public void employeeAfterAdvice(){
        System.out.println(" -- Accept After Advice ...");
        System.out.println();
    }

}
