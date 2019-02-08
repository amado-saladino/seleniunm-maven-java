Feature: User Registration
	User should be able to register in our website
	
	Scenario Outline: User Registration successfully
	Given The user is at the home page
	When User clicks register link
	And User entered "<firstname>" , "<lastname>"
	Then User should see Registration success page
	
	Examples:
 	 | firstname | lastname |
 	 | ahmed | mohamed |
 	 | Hamed | ahmed |