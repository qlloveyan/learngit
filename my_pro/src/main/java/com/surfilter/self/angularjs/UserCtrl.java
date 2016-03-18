package com.surfilter.self.angularjs;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.surfilter.self.angularjs.model.ExtJsObj;
import com.surfilter.self.angularjs.model.Page;
import com.surfilter.self.angularjs.model.User;

/**
 * Servlet implementation class UserCtrl
 */
public class UserCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExtJsObj result = new ExtJsObj(true, "Get user info success!",null);
		List<User> userList = new ArrayList<User>();
		Integer page = Integer.parseInt(request.getParameter("pageNum")==null?"1":request.getParameter("pageNum"));
		Integer size = Integer.parseInt(request.getParameter("pageSize")==null?"30":request.getParameter("pageSize"));
		for(int i = (page-1)*size ; i < (page*size > 97?97:page*size) ; i++){
			User user = new User();
			user.setId((long)i);
			user.setUsername("User"+i);
			user.setPassword(new Random().nextInt(300)+"");
			userList.add(user);
		}
		Page<User> data = new Page<User>();
		data.setPageNum(page);
		data.setPageSize(size);
		data.setTotal(97);
		data.setData(userList);
		
		result.setResult(data);
		
		Writer writer = response.getWriter();
		writer.write(JSON.toJSONString(result));
		writer.flush();
	}

}
