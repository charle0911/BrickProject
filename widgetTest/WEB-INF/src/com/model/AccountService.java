package com.model; 

public class AccountService
{
	private ServiceInterface service = new ServiceInterface();
	public AccountService() 
	{  
	}

/**============================  Return the json data for the client  ============================**/
	public String accountAuthentication(String account, String password)
	{
		return service.accountAuthentication(account, password);
	}
/**=====================  add the user account to database  after register   =====================**/
	public String setRegisterData(String account, String password)
	{
		return service.setRegisterData(account, password);
	}


}

	 
