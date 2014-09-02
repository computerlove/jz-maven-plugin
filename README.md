How to write a maven plugin
===============

## Step one ##
A Maven archetype exists for creating the skeleton of Maven plugins, we will start by executing the following on 
the commandline:

```$ mvn archetype:generate
          -DgroupId=sample.plugin
          -DartifactId=hello-maven-plugin
          -Dversion=1.0-SNAPSHOT
          -DarchetypeGroupId=org.apache.maven.archetypes
          -DarchetypeArtifactId=maven-archetype-plugin
          -B```

You may change groupId, artifactId, and version to values of your choosing.

After pressing enter when prompted, we now have a simple Mojo with a simple test.
Open the project in your favourite IDE, and run mvn install.

__pom.xml__

The version of maven-plugin-api is a bit old in the archetype, so bump it to version 3.2.1.
We can see that the dependency maven-plugin-annotations has provided scope. This is not because the jar will be present 
at runtime, but because the annotations are only used at build-time.

In the build section of the pom we find maven-plugin-plugin. This is the plugin responsible for processing the annotations on
our classes, and generate the appropriate meta-information used for using the plugin in other projects.
In the plugin configuration we declare that we want our plugin to be run with «hello-maven-plugin:mojo», and we want to 
generate the [plugin descriptor](http://maven.apache.org/ref/3.2.3/maven-plugin-api/plugin.html), as well as generate a help-mojo. The help-mojo basically prints information inferred from 
the plugin descriptor.

The last element of our generated pom is a profile for running integration tests. 
In src/it we find settings.xml, declaring some of our environment, and a folder simple-it.
simple-it contains a pom.xml which declares a project executing our plugin, and verify.groovy that simply
asserts that the file created by our mojo exists.

__MyMojo__

This is our mojo( **M**aven plain **O**ld **J**ava **O**bject). There are many like it, but this one is mine!

What makes this class a mojo is the fact that it extends [AbstractMojo](http://maven.apache.org/ref/3.2.1/maven-plugin-api/apidocs/org/apache/maven/plugin/AbstractMojo.html), and that it is annotated with [@Mojo](https://maven.apache.org/plugin-tools/maven-plugin-annotations/apidocs/org/apache/maven/plugins/annotations/Mojo.html).

AbstractMojo provides a execute() method. which is where we do our magic. It also gives us access to a logger, through the getLog()-method.

@Mojo requires only one parameter, name. «name="touch"» declares that we want to run our plugin with «mvn hello-maven-plugin:touch», or 
like the configuration in src/it/simple-it/pom.xml.
The parameter defaultPhase specifies in which phase the mojo should run in if none is provided. 
In our it-pom it is declared that the mojo shall run in the validate phase. Removing this line from the pom makes the 
plugin run in the default phase, process-sources. If phase is removed from both pom and annotation makes the plugin not run at all. 

The last element in our generated mojo is [@Parameter](https://maven.apache.org/plugin-tools/maven-plugin-annotations/apidocs/org/apache/maven/plugins/annotations/Parameter.html).
The main parameter for @Parameter is «property», which declares the name of the property by which it should be used when configuring the plugin.
The defaultValue parameter provides what value should be used when the parameter is not provided by configuration. 
 It supports using [expressions](http://maven.apache.org/ref/3.2.3/maven-core/apidocs/org/apache/maven/plugin/PluginParameterExpressionEvaluator.html) 
 for values known by Maven.
 
Ok, lets go to step two, and do somthing more than creating an empty file by executing ```git checkout step-2 -f``` 

## Useful resources ##
* [Mojo API Specification](http://maven.apache.org/developers/mojo-api-specification.html)
* [Guide to Developing Java Plugins](http://maven.apache.org/guides/plugin/guide-java-plugin-development.html)
* [Maven Plugin Plugin](http://maven.apache.org/plugin-tools/maven-plugin-plugin/)
* [Maven Plugin Tool for Annotations](http://maven.apache.org/plugin-tools/maven-plugin-tools-annotations/index.html)