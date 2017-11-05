package com.magicmoble.luzhouapp.json.status;

public class StatusHouse {
	public static StatusObject COMMON_STATUS_OK = new StatusObject(StatusCode.CODE_SUCCESS, "访问成功");
	public static StatusObject COMMON_STATUS_ERROR = new StatusObject(StatusCode.CODE_ERROR,
			"访问错误，错误码");
	public static StatusObject COMMON_STATUS_IN = new StatusObject(StatusCode.CODE_ERROR_PARAMETER,
			"用户已存在");
	public static StatusObject COMMON_STATUS_NO = new StatusObject(StatusCode.CODE_ERROR_PROGRAM,
			"用户不存在");
	public static StatusObject COMMON_STATUS_ERROR_PARAMETER = new StatusObject(StatusCode.CODE_ERROR_PARAMETER,
			"参数错误，错误码");
	public static StatusObject COMMON_STATUS_EXIST_OPERATION = new StatusObject(StatusCode.CODE_ERROR_EXIST_OPERATION,
			"已操作，错误码");
	public static StatusObject COMMON_STATUS_ERROR_PASSWORD_OR_PHONE = new StatusObject(
			StatusCode.CODE_ERROR_PASSWORD_OR_PHONE, "帐号或密码错误");
}
