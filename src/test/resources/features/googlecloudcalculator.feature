Feature: I want to use google cloud calculator and perform calculation

  Background: Browser is open and maximized

  Scenario Outline: Search for google cloud calculator, fill the form and receive result on generated email
    Given the user opens google cloud website
    When the user clicks on the search button
    And search for <calculator>
    Then clicks on the first result
    Then inputs the number of instances <instances>
    Then sets machine type to e2 standard 8
    Then inputs the number of nodes <nodes>
    Then checks "Add GPU" checkbox
    Then sets the number of GPU-s to 4
    Then sets the GPU type to Nvidia Tesla V100
    Then sets the local SSD storage to 24x375GB
    Then sets the datacenter location to Frankfurt
    Then sets the commited usage to 1 year
    And clicks add to estimate button
    Then stores the calculated total price
    Then clicks on email estimation button
    Then opens a new tab and navigates to email generation page
    Then copies the generated email
    Then switches to calculator tab
    Then pasts the email into the field
    Then clicks on send email button
    Then switches to email tab
    And opens the received email
    Then compares the monthly cost with stored total price
    Examples:
      | calculator                               | instances | nodes |
      | Google Cloud Platform Pricing Calculator | 4         | 1     |
      | Cloud Platform Pricing Calculator        | 3         | 2     |