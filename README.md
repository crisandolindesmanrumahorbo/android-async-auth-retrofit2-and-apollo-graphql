After you installed them successfully, open your CMD and run “npm install -g apollo” to install apollo-codegen globally.
After this is done, use it to get your introspection file.
To do that, run the command: 

```apollo schema:download --endpoint=http://serverAddress/graphql schema.json.```