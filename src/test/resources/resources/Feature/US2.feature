Feature: Refund item

Scenario: 2
	#Then I am on "Home" page
	Then I am on "Google" page
	Then I enter "selenium" in the "Search box"
	Then I enter "selenium" in the "Google Search"
	Given I launch "Chrome" browser
	Then I navigate to "https://www.facebook.com" URL
	Given I select "1" from "day" dropdown
	Given I select "5" from "month" dropdown
	Given I select "20" from "year" dropdown
	Then I wait for 3 Seconds

