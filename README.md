# Java High-Level REST Client

> This is a simple application to understand how we can use high-level REST client in order to store and retrieve data from Elasticsearch.

Steps to follow to run this application:

- Open the terminal.
- Clone the project ```git clone git@github.com:DivyaDua/high-level-rest-client.git```.
- ```cd high-level-rest-client```.
- Execute ```sbt clean compile```.
- Run the elasticsearch on your localhost, and kibana to visualise the data in elasticsearch indices.
- Run the main class ```ElasticProcessor``` to execute CRUD operations.

You can now run the commands in Kibana to interact with the data stored in indices-

- By executing ```GET /_cat/indices?v```, you can list all the indices. You can see ```user-index``` index is being created.
- By executing ```GET /user-index/_search { "query": { "match_all": {}  }}```, you can list out all the data in users-index.




