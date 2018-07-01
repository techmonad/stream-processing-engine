name := """stream-processing-engine"""

version := "1.0"

scalaVersion := "2.11.11"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % "2.3.1",
  "org.apache.spark" %% "spark-streaming-kafka-0-10-assembly" % "2.3.1",
  "org.json4s" %% "json4s-native" % "3.4.0",
  "org.elasticsearch" %% "elasticsearch-spark-20" % "6.3.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0" artifacts(Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp")),
  "org.scalatest" %% "scalatest" % "3.0.0"
)
