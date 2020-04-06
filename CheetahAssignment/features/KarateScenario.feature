@example
Feature: Flickr GET Recent

  Background:
    * url demoBaseUrl ='https://www.flickr.com/services/rest/'

  Scenario: Flickr get recents method
    Given param method = 'flickr.photos.getRecent'
    And param api_key = 'xxxxxxxxxxxxxxxxx'
    And param format = 'json'
    And param nojsoncallback = '1'
    When method get
    * def statusCode = responseStatus
    And print statusCode
    Then status 200
    And print 'Print response'
    * def photo0 = response.photos.photo[0]
    And print photo0
    * def id = photo0.id
    And match photo0.id == id
    # read json
    * def someJson = read('sample.json')
    And print someJson.employees[0]
    * match someJson.employees[0] == {"firstName": "aaa","lastName": "bbb"}


