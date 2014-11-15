import io.gatling.sbt.GatlingPlugin

val main = project
  .in(file("."))
  .enablePlugins(GatlingPlugin)

libraryDependencies ++= Seq(
	"io.gatling.highcharts" % "gatling-charts-highcharts" % "2.0.2" % "test",
	"io.gatling" % "test-framework" % "1.0" % "test"
)

