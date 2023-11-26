//package ua.training.quotes.persistence.user;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import ua.training.quotes.model.User;
//
//public class UserJdbcRepository implements UserResource {
//
//	private NamedParameterJdbcTemplate repository;
//
//	private BeanPropertyRowMapper<User> mapper;
//
//	private static final String getAllUsersSql = "SELECT * FROM USER";
//	private static final String getUserSql = "SELECT * FROM USER WHERE username=:username";
//
//	private static final String addUserSql = "INSERT INTO USER (username, passwd, NON_LOCKED, PASSWORD_NON_EXPIRED, authorities) VALUES (:username, :passwd, :nonLocked, :passwordNonExpired, :authorities)";
//
//
//	public UserJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
//		this.repository = namedParameterJdbcTemplate;
//		mapper = new BeanPropertyRowMapper<>(User.class);
//	}
//
//	@Override
//	public void addUser(User user) {
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("username", user.getUsername());
//		params.put("passwd", user.getPasswd());
//		params.put("nonLocked", user.nonLocked());
//		params.put("passwordNonExpired", user.passwordNonExpired());
//		params.put("authorities", user.getAuthorities());
//		try{
//			repository.update(addUserSql, params);
//		}
//		catch (DuplicateKeyException e) {
//			throw new IllegalArgumentException("User with username '" + user.getUsername() +
//					"' already exists");
//		}
//	}
//
//	@Override
//	public User getUser(String username) {
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("username", username);
//		try{
//			return repository.queryForObject(getUserSql, params, mapper);
//		}
//		catch (EmptyResultDataAccessException e) {
//			throw new NoSuchElementException("User with username " + username + " not found.");
//		}
//	}
//
//	@Override
//	public HashSet<User> getAllUsers() {
//
//		List<User> users = repository.query(getAllUsersSql, mapper);
//
//		Iterator<User> i = users.iterator();
//		HashSet<User> result = new HashSet<>();
//		while(i.hasNext()){
//			result.add(i.next());
//		}
//		return result;
//	}
//
//}
