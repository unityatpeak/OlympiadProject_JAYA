package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entity.OlympiadForm;
import exception.OlympiadException;

public interface OlympiadService {
	
	
	public String addParticipants(OlympiadForm participants) throws OlympiadException, ClassNotFoundException, IOException, SQLException;
	public List<OlympiadForm> retriveAll()throws OlympiadException, ClassNotFoundException, IOException, SQLException;
	public long getFee(String registrationNumber) throws ClassNotFoundException, IOException, SQLException, OlympiadException;
	
	public String cancellation(String registrationNumber) throws ClassNotFoundException, IOException, SQLException, OlympiadException;
	
}