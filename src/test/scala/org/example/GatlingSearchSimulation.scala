package org.example

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GatlingSearchSimulation extends Simulation {

  val github = "https://github.com"

  val httpProtocol = http
    .baseURL(github)
    //.inferHtmlResources(BlackList( """.*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader( "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader( "gzip, deflate")
    .acceptLanguageHeader( "en-US,en;q=0.5")
    .connection( "keep-alive")
    .doNotTrackHeader( "1")
    .userAgentHeader( "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:32.0) Gecko/20100101 Firefox/32.0")

  val userJourney = scenario("Searching Gatling")
    .exec(http("github-home").get("/"))
    .pause(2)
    .exec(http("search-gatling").get("/search?utf8=✓&q=gatling"))
    .pause(1)
    .exec(http("gatling-on-github").get("/gatling/gatling"))

  setUp(
    userJourney.inject(
      nothingFor(1.second),
      atOnceUsers(1),
      rampUsers(5) over(10 seconds),
      constantUsersPerSec(1) during(10 seconds)
    )
  ).protocols(httpProtocol)
}