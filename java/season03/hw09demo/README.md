# homework 09

edu.ldcollege为根

edu.ldcollege.service.LdHomeworkService 为服务
edu.ldcollege.domain.LdHomeWork为学员作业域对象：
数据库表映射：
id(自增字段，作业ID),userId（学员ID，Integer）、classId（班级ID，Integer），lessionId（课程编号,Integer），
homeworkFilePath（作业路径,String），homeworkFileName （作业文件名），上传时间(createDate), 
correctFlag（integer,批改标志，0 未批改，1已批改），starCount（点赞数），negativeCount(差评数），BestFlag(优秀作业，布尔值） 
edu.ldcollege.domain.LdHomeMapping为Mybatis的映射关系

edu.ldcollege.domain.LdHomeWorkFB为学员作业互批的域对象：
数据库表映射：
id(自增长主键），myid（批改的学员ID），userId(被批的学员ID），homeworkId（LdHomeWork的ID），LevelFlag（char，为A，B，C，D 之一），mark（ varchar， 200字以内）评语。

数据库为ＭＹＳＱＬ数据库
用样板工程，完成bootstrap+jquery+spring boot的功能

可以选作如下的几个题目，本作业要同时提交完整的源码（不含二进制编译的结果）工程，优秀作业纳入平台中，并且署名

#### 作业1 ：
学员上传自己的作业：
edu.ldcollege.ctrl.LdHomeworkUploadController为控制器（url路径为/ldhomework)
ldhomework-upload.html为主页面
参数为 userId（学员ID，Integer）、classId（班级ID，Integer），lessionId（课程编号,Integer），
上传的文件只能是ＰＰＴ，ＰＤＦ类型，大小在５Ｍ以内，如果不符合则删除服务器上的保存的文件，并对话框提示具体的错误，
如果userId＋classId　＋lessionId对应的作业在数据库中已经存在，则删除原来的文件（不存在也不报错），上传新文件，并且更新原有的数据库记录。
成功后弹出对话框，提示完成。


#### 作业2 ：
学员互相批改作业
edu.ldcollege.ctrl.LdHomeworkFeedBackController为控制器（url路径为/ldhomeworkfb)
首先查询和展示出一个班级某个课程编号对应的所有已提交的作业LdHomeWork，按照提交时间倒排，最早的在最前面（我以评价的在最后）
对应页面ldhomework-fb-list.html
以表格方式展示：
  学员ID，作业文件名(可以点击链接下载或在线看（两个图标连接最好）），提交时间，starCount（点赞数），negativeCount(差评数），是否优秀作业，是否批改过，
  点击“我要评价” 弹出对话框，展示如下内容：
          作业等级：A，B，C，D选一：
          点赞 或者 差评，或者弃权评价 。。
          我对同学有话说：200字以内
      提交，插入记录到LdHomeWorkFB中，提交成功，弹出对话框，提示感谢语，刷新列表，
      
  点击“查看所有评价”，弹出对话框，给出大家的评价。。  



