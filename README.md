How to write a maven plugin
===============

## Step two, generating resources ##
Sometimes it is nice to know when our artifact was generated. 
So now your task is to make our mojo generate a build.properties, containing a property «build.timestamp» with the time when the plugin was run.
The file should be available for the consuming project with getClass().getResourceAsStream("build.properties")

When you have made the required changes to *BuildtimeMojo.java*, you can run ```mvn install -P run-its``` to run a test verifying that 
executing the plugin has the desired effect.

If you are stuck you may want to check out the branch *step-2-solution* where a solution proposal exists. 

## What you need to know ##
When building Maven use the *target* folder as a work folder. Everything that ends up in the final artifact goes through this folder.
An important subfolder is the *classes* folder. This is where all resources, and all compiled classes are stored before they are 
put into the final jar/war. 
This means that if you put anything into this folder, it will end up on the classpath.

Now that we have created the beginning of our own version of [Maven Resources Plugin](http://maven.apache.org/plugins/maven-resources-plugin/) we can move on 
to step three by executing ```git checkout step-3 -f```