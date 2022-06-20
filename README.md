### reference
- retrofit: https://www.journaldev.com/13639/retrofit-android-example-tutorial
- graphql: https://android.jlelse.eu/implementing-apollo-client-in-android-studio-2d018fb36cd9

#### Step 1: Adding the required dependencies.
Create or open an Android project and navigate to “/<projectName>/app/” to open your apps build.gradle file.
Paste the following line of code on top of the file.

``apply plugin: 'com.apollographql.android``

add dependencies:

````
      implementation 'com.apollographql.apollo:apollo-runtime:2.2.3'
      implementation "com.apollographql.apollo:apollo-android-support:2.2.3"
  
      implementation 'com.squareup.okhttp3:okhttp:4.5.0'
  
      implementation ('com.squareup.retrofit2:retrofit:2.1.0') {
          // exclude Retrofit’s OkHttp dependency module and define your own module import
          exclude module: 'okhttp'
      }
      implementation 'com.google.code.gson:gson:2.8.5'
      implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
  
      dependencies {
          compileOnly 'org.projectlombok:lombok:1.18.12'
          annotationProcessor 'org.projectlombok:lombok:1.18.12'
      }
````

#### Step 2: Make folder child graphql folder same as java 
In the /main directory, create a folder called “graphql” after that create another folder inside of /graphql with the same name as your subfolder of /java.

#### Step 3:
After you installed them successfully, open your CMD and run ``npm install -g apollo`` to install apollo-codegen globally.
After this is done, use it to get your introspection file.
To do that, run the command: 

```apollo schema:download --endpoint=http://serverAddress/graphql schema.json.```
or
```apollo schema:download --endpoint=http://localhost:8081/graphql schema.json```

before this, step 4 before
when you done with your graphql file (VehicleQuery.graphql in this project), rebuild the project.
You can open tab Build then choose Rebuild to generate your graphql method.
Open your graphql method on path apps-build-generated-source-apollo-debug-service.

you can go to server on this site: https://github.com/crisandolindesmanrumahorbo/spring-jwt-auth-and-graphql

#### Step 4: Inserting a query.
add (on this project) VehicleQuery.graphql on graphql/com/eform/graphqlpractice and put some query graphql
schema.json ignored

Now we are ready to let the magic happen.
Go on “Build” and click “Rebuild Project”. This might take a while because Apollo is actually generating a class that we can use later to run our query.
In case you want to add more queries or mutations, just make another file with a meaningful name, the file ending of “.graphql” and insert the needed query or mutation. Don’t forget to build the project so the necessary classes are created.
The created classes are located under /build/source/apollo/classes/<packageName>

#### Step 5: Creating our Apollo Connector
Now our base is good to go, and we are ready to create our connector which connects our application to our endpoint.

First, we import the ApolloClient and OkHttpClient, which helps us send requests to our endpoint.
Then we initialize a variable for our endpoint.
In this case, I have a local server running and instead of using “localhost:5000/graphql”, I use “10.0.2.2:5000/graphql” because “localhost” won’t work if you use the Android emulator.
Obviously, your endpoint might look different from mine.
In the next step, we build a function with the name “ setupApollo” which returns an ApolloClient. In this function, we create our OkHttpClient and use it to build our ApolloClient which we directly return.
That's it, that's our client and now we’re ready to query data. Yay.
Step 6: Querying data.
Now that we have our ApolloClient we can finally use it to create a query function for our restaurant.
In this example, I will query in my MainActivity, but you should use different classes for that for the sake of cleaner code.

So let’s create a function called “queryVehicle” and give it a parameter of type String which will be used for our vehicle id.
In the function we call the ApolloConnector.setupApollo() function from our ApolloConnector class and create our query.
After we called the “.builder()”-method, we can now access the fields of our query and forward our vehicle parameter.
After that, we need to call the build()-method and apply a callback function.
Like the name already suggests onResponse() will be called if we get a response from the server. Our data is delivered as a parameter, and we can access it by using “response.data().vehicle”.
Be sure you gave the necessary permissions in the AndroidManifest to use internet:

```
//...
      <uses-permission android:name="android.permission.INTERNET" />
      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
      
      <Application 
        //...
        
```
