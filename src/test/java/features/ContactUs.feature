Feature: Contact us
	User should be able to send inquiry
	@contact
	Scenario: User sends niquiry
	Given The user opens home page
	When User clicks 'contact us' link
	And User fills in inquiry form
	Then User should see inquiry sent successfully