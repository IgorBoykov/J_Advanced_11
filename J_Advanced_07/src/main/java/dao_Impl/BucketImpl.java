package dao_Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import Connection.ConnectionUtils;
import Dao.BucketDao;
import domain.Bucket;

public class BucketImpl implements BucketDao {

	private static String READ_ALL = "select * from bucket";
	private static String CREATE = "insert into bucket(`user_id`, `product_id`, `puchase_date`) values (?,?,?)";
	private static String READ_BY_ID = "select * from bucket where id =?";
	private static String DELETE_BY_ID = "delete from bucket where id=?";
	
	private static Logger LOGGER = Logger.getLogger(BucketImpl.class);

	private Connection connection;
	private PreparedStatement preparedStatement;

	public BucketImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		connection = ConnectionUtils.openConnection();
	}

	@Override
	public Bucket create(Bucket bucket) {
		try {
			preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, bucket.getUser_id());
			preparedStatement.setInt(2, bucket.getProduct_id());
			preparedStatement.setDate(3, new Date(bucket.getPuchase_date().getTime()));
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			bucket.setId(rs.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return bucket;
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;
		try {
			preparedStatement = connection.prepareStatement(READ_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			result.next();

			Integer bucket_id = result.getInt("id");
			Integer user_id = result.getInt("user_id");
			Integer product_id = result.getInt("product_id");
			java.util.Date puchase_date = result.getDate("puchase_date");

			bucket = new Bucket(bucket_id, user_id, product_id, puchase_date);

		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return bucket;
	}

	@Override
	public Bucket update(Bucket t) {
		throw new IllegalStateException("there is no update for bucket");
	}

	@Override
	public void delete(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_BY_ID);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}

	}

	@Override
	public List<Bucket> readAll() {
		List<Bucket> bucketRecords = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(READ_ALL);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Integer bucket_id = result.getInt("id");
				Integer user_id = result.getInt("user_id");
				Integer product_id = result.getInt("product_id");
				java.util.Date puchase_date = result.getDate("puchase_date");
				bucketRecords.add(new Bucket(bucket_id, user_id, product_id, puchase_date));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}

		return bucketRecords;
	}

}
