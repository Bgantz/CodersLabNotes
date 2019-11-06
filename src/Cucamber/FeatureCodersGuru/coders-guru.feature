Feature: Register user in to "Coders Guru" page

  Scenario Outline: user registers on site

    Given opened page CodersGuru
    Given user email is <Email>
    Given user name is <Name>
    Given user second name is <SecondName>
    Given user password is <Password>
    Given user repeats password is <Password>
    Given user city is <City>
    Given user postcode is <Postcode>
    Given user street is <Street>
    Given user home number
    Given user opt-in
    Then form is properly sent
    Then close browser

    Examples:
      |Email | Name  | SecondName | Password | City     | Postcode | Street |
      | harry@mailinator.com | Harry | Smith      | qwerty   | New York | 55-555    | Wall Street 23 |
      | Melody@mailinator.com | Melody | Ohara     | qwerty123! | Chicago | 00-111   | Żółkiewskiego 49 |
