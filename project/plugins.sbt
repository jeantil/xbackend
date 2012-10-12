resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Typesafe repository snap" at "http://repo.typesafe.com/typesafe/snapshots/"

resolvers += Resolver.url("Typesafe Ivy Snapshots Repository", url("https://typesafe.artifactoryonline.com/typesafe/ivy-snapshots/"))(Resolver.ivyStylePatterns)

addSbtPlugin("play" % "sbt-plugin" % "2.1-SNAPSHOT")
