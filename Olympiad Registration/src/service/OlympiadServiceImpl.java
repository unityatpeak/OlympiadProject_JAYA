package service;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.OlympiadDao;
import dao.OlympiadDaoImpl;
import entity.OlympiadForm;
import exception.OlympiadException;

public class OlympiadServiceImpl implements OlympiadService {
	
	OlympiadDao olympiadDao=null;

	
	//method to add details
	@Override
	public String addParticipants(OlympiadForm participants) throws OlympiadException, ClassNotFoundException, IOException, SQLException {
		olympiadDao=new OlympiadDaoImpl();
		String participantsSeq=olympiadDao.addParticipants(participants);
		return participantsSeq;
	}

	
	
	
	//method to generate fee
	@Override
	public long getFee(String registrationNumber) throws ClassNotFoundException, IOException, SQLException, OlympiadException {
		olympiadDao=new OlympiadDaoImpl();
		long fee = olympiadDao.getFee(registrationNumber);
		return fee;
	}
	
	
	
	
	//method to get all the details
	@Override
	public List<OlympiadForm> retriveAll() throws OlympiadException, ClassNotFoundException, IOException, SQLException {
		olympiadDao=new OlympiadDaoImpl();
		List<OlympiadForm> participantList=null;
	participantList=olympiadDao.retriveAllDetails();
		return participantList;
		
	}
	
	
	
	//method for cancellation
	
	@Override
	public String cancellation(String registrationNumber) throws ClassNotFoundException, IOException, SQLException, OlympiadException {
		olympiadDao=new OlympiadDaoImpl();
		String participantsSeq;
		participantsSeq=olympiadDao.cancellation(registrationNumber);
		
		return participantsSeq;
	}

	
	
	
	
	
	
	
	//method for validation
	public boolean detailValidation(OlympiadForm details) throws OlympiadException
	{
	
		List<String> validationErrors=new ArrayList<String>();
		
		
		
		//Validating Participant name
		if(!(isValidName(details.getName())))
		{
			validationErrors.add("Participant name should be in alphabets and minimum 3 characters long");
			
		}
		
		//Validating date of birth
		if(!(isValidDob(details.getDob())))
		{
			validationErrors.add("participation date must be in the given format");
		}
		
		
		
		//Validating phone number
		if(!(isValidPhoneNumber(details.getPhoneNumber())))
		{
			validationErrors.add("participation phone number must be of 10 digits");
		}
		
		if(!(isValidInstitution(details.getInstitution())))
		{
			validationErrors.add("Institution must be according to the validation");
		
		}
		
		if(!(validationErrors.isEmpty()))
		{
			System.err.println(validationErrors+" ");
			return false;
		}
		return true;
	}
	
	

	
	//methods for validating Name
	public boolean isValidName(String name)
	{
		
			Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
			Matcher nameMatcher=namePattern.matcher(name);
			return nameMatcher.matches();
		
	}
	
	
	                   //method for validating date of birth
     public boolean isValidDob(String dob)
     {
	      
    	 Pattern dobPattern=Pattern.compile("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$");
	      Matcher dobMatcher=dobPattern.matcher(dob);
		return dobMatcher.matches();
	       
     }
     
     
                        //method for validating phone number
     public boolean isValidPhoneNumber(String phoneNumber)
     {
    	      Pattern phonePattern=Pattern.compile("^[1-9]{1}[0-9]{9}$");
 		      Matcher phoneMatcher=phonePattern.matcher(phoneNumber);
 		      return phoneMatcher.matches();
      
     }
	
     
                       //method for validating institute name
     private boolean isValidInstitution(String institution) {
 		
 		return (institution.length() > 6);
 	}


     
     
                       //method for validating registration number
	public boolean validateRegistrationNumber(String registrationNumber) {
		Pattern regNumberPattern=Pattern.compile("[0-9]{1,4}");
        Matcher  regNumberMatcher =  regNumberPattern.matcher(registrationNumber);
		
		if(regNumberMatcher.matches())
			return true;
		else
			return false;	
	}




	



	
	
	
	

}
