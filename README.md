How to write a maven plugin
===============

## Step three, generating and compiling sources ##
Great, now that we have generated a resource, lets generate some sources!
In some cases it is handy to generate code, and use it in our project. One common example is generating code
based on wsdl and xsd files, for easier interaction with WebServices. 

Your new task is to modify BuildtimeMojo to generate a java file that is compiled when the project using the mojo is compiled.

To match the test created for this task the class should be called *Generated*, have no package, and implement java.io.Closeable.

When you have made the required changes to *BuildtimeMojo.java*, you can run ```mvn install -P run-its``` to run a test verifying that 
executing the plugin has the desired effect.

If you are stuck you may want to check out the branch *step-3-solution* where a solution proposal exists. 

## What you need to know ##
One solution would be to generate the code and put it in *src/main/java*, but this is not ideal. 

When compiling the project's sources, maven looks in the project source directory, which by default is 
*src/main/java* for a Java project. 
When building, Maven does by default not look anywhere else for sources, but we can easily tell it that we want to
include some source files.

In order to tell Maven that we want to add sources we have to get hold of the project the plugin is running in.
Because *maven-plugin-api* is only used when building the plugin we need *maven-core* to get access to runtime information. We include it as a dependency by adding:

    <dependency>
        <groupId>org.apache.maven</groupId>
        <artifactId>maven-core</artifactId>
        <version>3.2.2</version>
    </dependency>

To get the current project injected into our mojo, add the following field:

    @Component
    private MavenProject project;

MavenProject has a method *addCompileSourceRoot(path)*, that allows us to tell Maven to look there for sources to compile.
It supports both absolute paths, and paths relative to the projects base directory i.e. the folder containing the pom.

A convension when generating sources is to put them in *target/generated-sources/something*. 