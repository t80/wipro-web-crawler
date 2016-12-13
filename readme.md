# Wipro Web Crawler 

## Environment Assumptions

1. Maven & Java 8 is available
2. The application would be run in a single thread

## Build and running instructions

1. In the terminal navigate to "crawler" directory and run "mvn clean package"
2. Run "java -jar target/site-mapper.jar"
3. At the command prompt enter a url including protocol, E.g http://www.bbc.co.uk.

## Omissions due to time constraints
 
1. Logging
2. Thorough parameter validation
3. Acceptance tests
4. Consideration of parallel mapping of links/pages
5. Production of a JSON/XML representation of the site map


