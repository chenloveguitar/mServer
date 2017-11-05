package com.magicmoble.luzhouapp.singleLogin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.swing.text.AbstractDocument.Content;

import com.magicmoble.luzhouapp.json.core.DataObject;
import com.magicmoble.luzhouapp.json.responseUtils.ResponseUtils;
import com.magicmoble.luzhouapp.json.status.StatusHouse;
import com.magicmoble.luzhouapp.json.utils.JackJsonUtils;

public class SingleUtil implements HttpSessionListener {

	// 保存sessionID和username的映射
	private static HashMap hUserName = new HashMap();

	/** 以下是实现HttpSessionListener中的方法* */
	public void sessionCreated(HttpSessionEvent se) {
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		hUserName.remove(se.getSession().getId());
	}

	/**
	 * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
	 * 
	 * @param sUserName
	 *            String-登录的用户名称
	 * @return boolean-该用户是否已经登录过的标志
	 */
	public static boolean isAlreadyEnter(HttpSession session, String sUserName, HttpServletResponse response) {
		boolean flag = false;
		// 如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)
		if (hUserName.containsValue(sUserName)) {
			flag = true;
			// 遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)
			Iterator iter = hUserName.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (((String) val).equals(sUserName)) {
					// 给原id用户发送消息：另一台客户端登录

					DataObject dataObject = new DataObject();
					dataObject.setdata("您的用户已掉线，请重新登录");
					dataObject.setStatusObject(StatusHouse.COMMON_STATUS_ERROR);
					String responseText = JackJsonUtils.toJson(dataObject);
					ResponseUtils.renderJson(response, responseText);

					hUserName.remove(key);

				}
			}
			// 添加现在的sessionID和username
			hUserName.put(session.getId(), sUserName);
			System.out.println("hUserName   =   " + hUserName);
			System.out.println("session.getId()   =   " + session.getId());
		} else {// 如果该用户没登录过，直接添加现在的sessionID和username
			flag = false;
			hUserName.put(session.getId(), sUserName);
			System.out.println("session.getId()   =   " + session.getId());
			System.out.println("hUserName   =   " + hUserName);
		}
		return flag;
	}

	/**
	 * isOnline-用于判断用户是否在线
	 * 
	 * @param session
	 *            HttpSession-登录的用户名称
	 * @return boolean-该用户是否在线的标志
	 */
	public static boolean isOnline(HttpSession session) {
		boolean flag = true;
		if (hUserName.containsKey(session.getId())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean isLoginout(HttpSession session) {
		boolean flag = true;
		hUserName.remove(session.getId());

		return flag;
	}

}
