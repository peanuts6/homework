# homework 04

 * 用Spring内嵌的消息通知机制，完成一个DEMO ：订单服务(LdOrderService）产生一个新订单(LDOrder）的时候，如果订单金额大于500，则VIPUserDetector服务发现，并且调动VipUserService的方法，通知增加一个新VIP用户.
   当新VIP用户产生时，此外，UserCareService监听到此事件，于是通知EmailService，发送一封邮件给用户。不用数据库，也不真正发邮件，只要打印日志证明流程走通即可。
    尽量用上所学各种技能。
    
 * 可选题 ，完成支持Java注解+泛型注入的一个Ioc框架，
           LdIocFactory factory=new LdIocFactory();
           factory.scanPackages(new String[]{xx,xxxx}});
           factory.getBean(xxx.class);
           
            完成课程上的User,Dao,Service 泛型扫描注入的能力

 