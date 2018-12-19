package client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import entity.OlympiadForm;
import exception.OlympiadException;
import service.OlympiadService;
import service.OlympiadServiceImpl;

public class OlympiadMain {
	
	
	
	static Scanner sc = new Scanner(System.in);
	static OlympiadService olympiadService=null;
	static OlympiadServiceImpl olympiadServiceImpl=null;
	static Logger logger = Logger.getRootLogger();

	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, OlympiadException 
	{
		PropertyConfigurator.configure("Resources/log4j.properties");
		OlympiadForm olympiadForm=null;
		String registrationNumber=null;
		int option=0;
		while(true) 
		{
			
			
			
			//form layout
			System.out.println("          Olympiad Registration            ");
			System.out.println("__________________________________________________________________");
			System.out.println("      1.Add participants details:");
			System.out.println("      2.Enter registration number to get the particular fee:");
			System.out.println("      3.Participant information:");
			System.out.println("      4.cancel participation:");
			System.out.println("__________________________________________________________________");
			System.out.println("Select an Option:");
			
			option=sc.nextInt();
			switch(option)
			{
			
			
			
			
			
			case 1:
				while(olympiadForm==null)
				{
					olympiadForm=submitParticipationDetails();
					
				}
				try 
				{
					olympiadService=new OlympiadServiceImpl();
					registrationNumber=olympiadService.addParticipants(olympiadForm);
					System.out.println("Registration number is "+registrationNumber);
					
					System.out.println("successfully registered");
					
				}
				catch(OlympiadException oe)
				{
					System.out.println("Error"+oe.getMessage());
				}
				finally 
				{
					registrationNumber=null;
					olympiadService=null;
					olympiadForm=null;
				}
				break;
				
				
				
				
				
				
			case 2:
				olympiadService=new OlympiadServiceImpl();
				olympiadServiceImpl = new OlympiadServiceImpl();
				registrationNumber = null;
				long fee=0;
				System.out.println("enter registration number to get fees ");
				registrationNumber = sc.next();
				if(olympiadServiceImpl.validateRegistrationNumber(registrationNumber))
				{
					fee = olympiadService.getFee(registrationNumber);
					System.out.println(" fee for exam is :"+fee);
				}
				else
				{
					System.out.println("enter valid registration number.....");
				}
				break;
				
				
				
				
				
				
			case 3:
				olympiadService=new OlympiadServiceImpl();
				try 
				{
					 List<OlympiadForm> participantList=new ArrayList<OlympiadForm>();
					participantList = olympiadService.retriveAll();

					if (participantList != null) 
					{
						Iterator<OlympiadForm> i = participantList.iterator();
						 while (i.hasNext()) 
						 {
							System.out.println(i.next());
						 }
					} 
					else 
					{
						System.out.println("there is not participant");
					}

				}

				catch (OlympiadException e) 
				{

					System.out.println("Error  :" + e.getMessage());
				}

				break;
				
				
				
				
				
				
				
				
			case 4:
				String olympName;
				System.out.println("enter registration number:");
				registrationNumber=sc.next();
				try {
					olympiadService=new OlympiadServiceImpl();
					olympName=olympiadService.cancellation(registrationNumber);
					 System.out.println("Participant ,"+olympName+" has been successfully removed");
				}
				finally
				{
					registrationNumber=null;
					olympiadService=null;
					olympiadForm=null;
				}
				break;
				default:
			}
			
		}
	}


	
	
	
	
	
	
	private static OlympiadForm submitParticipationDetails()
	{
		OlympiadForm olympiadForm=new OlympiadForm();
		System.out.println("ENTER DETAILS");
		
		System.out.println("Enter name (length>3):");
		olympiadForm.setName(sc.next());
		
		System.out.println("\nEnter date of birth (dd/mm/yyyy):");
		olympiadForm.setDob(sc.next());
		
		System.out.println("\nEnter phone Number:");
		olympiadForm.setPhoneNumber(sc.next());
		
		System.out.println("enter Insttution:");
		olympiadForm.setInstitution(sc.next());
		
		
		System.out.println("FIELDS ARE:");
		System.out.println("1. MATHS");
		System.out.println("2. SCIENCE");
		System.out.println("3. JAVA");
		System.out.println("4. HTML");
		System.out.println("select field:");
		olympiadForm.setField(sc.next());
		
		
		
	olympiadServiceImpl = new OlympiadServiceImpl();
		try 
		{
			if(olympiadServiceImpl.detailValidation(olympiadForm))
		return olympiadForm;
		
		} 
		catch (OlympiadException olympiadException)
		{
			
		System.err.println("Invalid data:");
			System.err.println(olympiadException.getMessage() + " \n Try again..");
			System.exit(0);

		}
		
		
		return null;
		}
	}


