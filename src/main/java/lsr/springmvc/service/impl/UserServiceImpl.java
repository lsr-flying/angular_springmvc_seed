package lsr.springmvc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lsr.springmvc.common.PageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lsr.springmvc.dao.UserDAO;
import lsr.springmvc.model.User;
import lsr.springmvc.service.UserService;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public int insertUser(User user) {
		return userDAO.insertUser(user);
	}

	public JSONObject getUserList(Map params) {

		PageConfig pageConfig = getPageConfig(params);
		PageHelper.startPage(pageConfig.getPageNum(), pageConfig.getPageSize());
		List<User> userList = userDAO.getUserList(params);
		PageInfo<User> userPageInfo = new PageInfo<User>(userList);
		return convertPageInfo(userPageInfo);
	}

	public User queryUserDetailById(Map params) {
		return userDAO.queryUserDetailById(params);
	}

	public int updateUserById(Map params) {
		return userDAO.updateUserById(params);
	}

	public int deleteUserById(Map params) {
		return userDAO.deleteUserById(params);
	}

	/**
	 * 此方法从入参中提取分页参数
	 * 默认第一页，10条记录
	 * @param params
	 * @return
	 */
	private PageConfig getPageConfig(Map params){
		PageConfig pageConfig = new PageConfig();

		Object pageNumObj = params.get("pageNum");
		if(!StringUtils.isEmpty(pageNumObj)){
			Integer pageNum = Integer.parseInt(pageNumObj.toString());
			pageConfig.setPageNum(pageNum);
			params.remove("pageNum");
		}
		else{
			pageConfig.setPageNum(1);
		}

		Object pageSizeObj = params.get("pageSize");
		if(!StringUtils.isEmpty(pageSizeObj)){
			Integer pageSize = Integer.parseInt(pageSizeObj.toString());
			pageConfig.setPageSize(pageSize);
			params.remove("pageSize");
		}
		else{
			pageConfig.setPageSize(10);
		}

		return pageConfig;
	}

	/**
	 * 格式化PageInfo
	 * 信息为特定JSONObject
	 * @param pageInfo
	 * @return
	 */
	private JSONObject convertPageInfo(PageInfo pageInfo){
		JSONObject pageListJSON = new JSONObject();
		pageListJSON.put("list",pageInfo.getList());

		JSONObject pageSummary = new JSONObject();
		pageSummary.put("pageNum",pageInfo.getPageNum());
		pageSummary.put("pageSize",pageInfo.getPageSize());
		pageSummary.put("pageCount",pageInfo.getPages());
		pageSummary.put("recordCount",pageInfo.getTotal());
		pageSummary.put("prePageNum",pageInfo.getPrePage());

		pageListJSON.put("pageSummary",pageSummary);

		return pageListJSON;
	}
}
