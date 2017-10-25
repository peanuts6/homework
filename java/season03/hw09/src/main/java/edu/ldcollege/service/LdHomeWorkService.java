package edu.ldcollege.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.domain.LdHomeWorkFB;
import edu.ldcollege.mapping.LdHomeWorkFBMapper;
import edu.ldcollege.mapping.LdHomeWorkMapper;

@Component
public class LdHomeWorkService {
	@Autowired
	private LdHomeWorkMapper ldHomeWorkMapper;
	
	@Autowired
	private LdHomeWorkFBMapper ldHomeWorkFBMapper;
	
//	@Autowired
//	private SqlSessionTemplate sqlSession;
	
	// 上传作业
	public void upload(LdHomeWork hw){
		ldHomeWorkMapper.insert(hw);
	}
	// 更新作业
	public void update(LdHomeWork hw){
		ldHomeWorkMapper.update(hw);
	}
	// 保存作业
	public void saveUploadedFile(MultipartFile file) throws IOException{
		byte[] bytes = file.getBytes();
		Path path = Paths.get("/Users/xqy/projects/homework/java/season03/hw09/target/classes/static/files/"+file.getOriginalFilename());
		Files.write(path, bytes);
	}
	// 删除某个作业
	public void deleteHomework(Integer userid,Integer classid,Integer lessionid){
		ldHomeWorkMapper.delete(userid,classid,lessionid);
	}
	// 获取一个作业
	public LdHomeWork getHomework(Integer id){
		return ldHomeWorkMapper.get(id);
	}
	
	// 某个班级所有作业
	public List<LdHomeWork> getHomeWorksOfClass(Integer classid){
		return ldHomeWorkMapper.selectByClassId(classid);
	}
	
	// 班级所有作业
	public List<LdHomeWork> getAllHomeWorks(){
		return ldHomeWorkMapper.selectAll();
	}
	
	
	
	// 查看某一作业所有评论
	public List<LdHomeWorkFB> getAllFeedBacks(Integer hwid){
		return ldHomeWorkFBMapper.selectAll(hwid);
	}
	
	// 提交作业互评
	public void feedback(LdHomeWorkFB hwfb){
		ldHomeWorkFBMapper.insert(hwfb);
	}
	
	// 更改评价
	public void updateFeedBack(LdHomeWorkFB fb) {
		ldHomeWorkFBMapper.update(fb);
	}
}
