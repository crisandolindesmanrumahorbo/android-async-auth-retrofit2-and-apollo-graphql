After you installed them successfully, open your CMD and run “npm install -g apollo” to install apollo-codegen globally.
After this is done, use it to get your introspection file.
To do that, run the command: 

```apollo schema:download --endpoint=http://serverAddress/graphql schema.json.```

when you done with your graphql file (VehicleQuery.graphql in this project), rebuild the project.
You can open tab Build then choose Rebuild to generate your graphql method.
Open your graphql method on path apps-build-generated-source-apollo-debug-service

reference
- retrofit: https://www.journaldev.com/13639/retrofit-android-example-tutorial
- graphql: https://android.jlelse.eu/implementing-apollo-client-in-android-studio-2d018fb36cd9
