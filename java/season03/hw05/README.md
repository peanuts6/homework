# homework 05

 * 定义２套Profile，一套供单元测试，　大概有以下的Component：
   　　　　DataSource
   　　　　MyCourseService 访问datasource，获取JDBC连接，执行select id, name, mark（分数） from course；
   　　　　　　public List＜TCourse＞ getAllCourse()；
   　　当用生产环境的Profile时候，DataSource采用Spring　Datasource或者ＤＢＣＰ等某个常见的，数据库则用ＭＹＳＱＬ
   　　当测试环境时候时，　　DataSource用Mock的，Connection也是Mock的，结果集也是Mock出来的。　
 
 * 可选题
   　　　开发一个BeanPostProcessor，检查Bean的定义的属性，属性超过４个的，或者接口超过２个，打印出来详情，（属性列表或者接口类名）：
   　　　　　　　　waring xxxx has 5 properties 
   　　　　　　　　　　　　　　　　　property xxx type yyyy
 