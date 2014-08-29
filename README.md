How to write a maven plugin
===============

## Step two ##
Some times it is nice to know when our artifact was generated. 
So now your task is to make our mojo generate a build.properties, containing a property «build.timestamp» with the time when the plugin was run.
The file should be available for the consuming project with getClass().getResourceAsStream("build.properties")