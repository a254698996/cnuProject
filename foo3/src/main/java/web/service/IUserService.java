package web.service;

import java.io.Serializable;

import web.entity.User;

public interface IUserService<T, PK extends Serializable> extends IService<T, PK> {

	public User getUserAllInfo(User user);

	public User getUserRolesAndPermissions(String userName);

}
