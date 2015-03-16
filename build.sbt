import io.gatling.sbt.GatlingPlugin

val main = project
  .in(file("."))
  .enablePlugins(GatlingPlugin)

libraryDependencies ++= Seq(
	"io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.4" % "test",
	"io.gatling"            % "gatling-test-framework"    % "2.1.4" % "test"
)

