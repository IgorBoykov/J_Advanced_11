package service;

import domain.User;
import shared.AbstractCrud;

public interface UserService extends AbstractCrud<User> {
	User getUserByEmail(String email);

}
