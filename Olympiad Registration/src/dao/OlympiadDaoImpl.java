package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import db.OlympiadDB;
import entity.OlympiadForm;
import exception.OlympiadException;

public class OlympiadDaoImpl implements OlympiadDao {
	Logger logger = Logger.getRootLogger();

	public OlympiadDaoImpl() {
		PropertyConfigurator.configure("Resources/log4j.properties");

	}

	// method to get all the details from participants

	@Override
	public String addParticipants(OlympiadForm participants)
			throws OlympiadException, ClassNotFoundException, IOException, SQLException {
		Connection connection = OlympiadDB.getConnection();

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Statement st = connection.createStatement();
		String registrationNumber = null;
		int queryResult = 0;
		try {

			pst = connection.prepareStatement(QueryMapper.INSERT_QUERY);

			pst.setString(1, participants.getName());
			pst.setString(2, participants.getDob());
			pst.setString(3, participants.getPhoneNumber());
			pst.setString(4, participants.getInstitution());
			pst.setString(5, participants.getField());

			queryResult = pst.executeUpdate();

			resultSet = st.executeQuery("select * from participant_details");

			if (resultSet.next()) {
				registrationNumber = resultSet.getString(1);

			}
			if (queryResult == 0) {
				logger.error("Insertion failed ");
				throw new OlympiadException("Inserting donor details failed ");

			} else {
				logger.info("Participant  details added successfully:");
				return registrationNumber;
			}
		}

		catch (SQLException sql) {

			sql.printStackTrace();
			logger.error(sql.getMessage());
			throw new OlympiadException("Tehnical problem occured refer log");

		} finally {
			try {

				pst.close();
				connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				logger.error(sqlException.getMessage());
				throw new OlympiadException("Error in closing db connection");

			}
		}

	}

	// method to generate the fee for the particular field

	@Override
	public long getFee(String registrationNumber)
			throws ClassNotFoundException, IOException, SQLException, OlympiadException {
		Connection connection = OlympiadDB.getConnection();

		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Statement st = connection.createStatement();
		long fee = 0;
		try {
			pst = connection.prepareStatement(QueryMapper.GET_FEE_QUERY);
			pst.setString(1, registrationNumber);
			pst.executeUpdate();
			resultSet = pst.executeQuery();
			while (resultSet.next()) {

				fee = resultSet.getInt(2);
			}

		} catch (Exception oe) {

			// logger.error(oe.getMessage());
			throw new OlympiadException(oe.getMessage());

		} finally {
			try {

				pst.close();
				connection.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				logger.error(sqlException.getMessage());
				throw new OlympiadException("Error in closing db connection");

			}
		}
		return fee;
	}

	// method to show all the details of the participants

	@Override
	public List<OlympiadForm> retriveAllDetails()
			throws OlympiadException, ClassNotFoundException, IOException, SQLException {
		Connection connection = OlympiadDB.getConnection();
		List<OlympiadForm> list = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Statement st = connection.createStatement();

		resultSet = st.executeQuery(QueryMapper.RETRIVE_ALL_QUERY);

		list = new ArrayList<>();
		while (resultSet.next()) {
			OlympiadForm count = new OlympiadForm();
			count.setRegistrationNumber(resultSet.getString(1));
			count.setName(resultSet.getString(2));
			count.setDob(resultSet.getString(3));
			count.setPhoneNumber(resultSet.getString(4));
			count.setInstitution(resultSet.getString(5));
			count.setField(resultSet.getString(6));
			list.add(count);

		}

		return list;

	}

	// method for cancellation
	@Override
	public String cancellation(String registrationNumber) throws ClassNotFoundException, IOException, SQLException {
		OlympiadForm olympiadForm = new OlympiadForm();
		Connection connection = OlympiadDB.getConnection();
		String cancelParticipation;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Statement st = connection.createStatement();
		try {
			pst = connection.prepareStatement(QueryMapper.Deletion_Query);

			pst.setString(1, registrationNumber);
			pst.executeQuery();
			resultSet=pst.executeQuery(QueryMapper.Cancellation_Query);

			if (resultSet.next()) {
				cancelParticipation = olympiadForm.setName(resultSet.getString(1));;
				return cancelParticipation;
			}

			

		} catch (Exception oe) {
			System.out.println(oe);
		} finally {
			// try
			// {
			resultSet.close();
			pst.close();
			connection.close();
			// }
			// catch (SQLException e)
			// {
			//
			// System.out.println(e);
			// }
		}
		return null;

	}

}
