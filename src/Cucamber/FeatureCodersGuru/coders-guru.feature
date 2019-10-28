Feature: Register user in to "Coders Guru" page

  Scenario Outline: user registers on site

    Given opened page CodersGuru
    Given user email inserted in to site
    Given user name <Name>
    Given user second name <SecondName>
    Given user password <Password>
    Given user repeats password <Password>
    Given user city <City>
    Given user postcode <Postcode>
    Given user street <Street>
    Given user home number
    Given user opt-in
    Then form is properly send
    Then close browser

    Examples:
      | Name  | SecondName | Password | City     | Postcode | Street |
      | Harry | Smith      | qwerty   | New York | 55-555    | Wall Street 23 |
