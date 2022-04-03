Feature: Gorest test
#  https://gorest.co.in/

  Scenario: 1 - Post pet with id 1
    Given user sends "postPetRQ" request with following parameters
      | id  | idCategoty | nameCategory | name    | photoUrl |  idTag | nameTag         | status    |
      | 1   | 1          | dog          | Hunter  | Url      |  1     | golden_retriver | available |

    When user sends POST "postPetRQ" request

    Then "postPetRS" code is "200"
    And name of pet in "postPetRS" is "Hunter"
  Scenario: 2 - Get pet with id 1

    Given user has "getPetByIdRQ" request with id "1"

    When user sends "GET" "getPetByIdRQ" request with id "1"

    Then "getPetByIdRS" code is "200"
    And name of pet in "getPetByIdRS" is "Hunter"

  Scenario: 3 - Delete pet with id 1
    Given user has "deletePetByIdRQ" request and id "1"

    When user sends "DELETE" "deletePetByIdRQ" request with id "1"

    Then "deletePetByIdRS" code is "200"
#    And name of pet in "getPetByIdRS" is "Hunter"


#  Scenario: 2 - Getting user by name
#
#    Given user has "getUserByIdRQ" request with id "3977"
#
#    When user sends "GET" "getUserByIdRQ" request
#
#    Then "getUserByIdRS" code is "200"
#    And user name contains expected "Anal Kaniyar"
#
#  Scenario: 3 - Creating user
#
#    Given user has "createUserRQ" request with following parameters
#      | email              | name | status | gender |
#      | email111@gmail.com | test | active | male   |
#
#    When user sends "POST" "createUserRQ" request
#
#    And "createUserRS" code is "201"
#    And user has "getUserByIdRQ" request with id from "createUserRS" response
#    And user sends "GET" "getUserByIdRQ" request
#    Then "getUserByIdRS" code is "200"
#    And user name contains expected 'test'
#
#  Scenario: 4 - Deleting user
#    Given user has "createUserRQ" request with following parameters
#      | email              | name | status | gender |
#      | email111@gmail.com | test | active | male   |
#
#    When user sends "POST" "createUserRQ" request
#    And user has "deleteUserRQ" request with id from "createUserRS" response
#    And user sends "DELETE" "deleteUserRQ" request
#
#    Then "deleteUserRS" code is "204"
