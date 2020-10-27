package Dao;

import domain.User;
import shared.AbstractCrud;

public interface UserDao extends AbstractCrud<User> {
	User getUserByEmail(String email);

}
