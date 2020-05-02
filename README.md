# ReelRecords
Description: native android application purposed to create a film database search with local storage.

LocalDB.java contains a local database which stored data during runtime. Here you will find the storage of a user's 
username and password along with the results from a REST API call recieved for a query made using HTTP. Insertion and deletion methods are included in here for modifying internal information. 

MainActivity.java contains the initial load screen for the system along with username and password verification. Here is where the user has the option whether to log in to their existing account or to make a new account in the system. 

SignUpActivity.java contains linked TextView which allow the user to enter a username and password along with a password confirmation to verify the user's selection. Upon a successful account creation the user is sent back to MainActivity.java.

MySingleton.java contains a standalone RequestQueue handler that allows HTTP Requests to be made from any script in the project. 

QueryActivity.java contains buttons linked to a TextView which concatenates a query to a url in order to pull the queried information from the online database. Also here is where the asyncronous method lies which takes care of the actual HTTP request. When a JSON payload is recieved here it is stored in LocalDB.java.

ResultsActivity.java contains an inflater which loads up data stored in a ArrayList<>() and puts it into list_item.XML via the ArrayAdapter class. When an item is clicked the user may save that specific query to their own personal queue. 

SavedActivity.java contains an inflater which loads up data stored in a ArrayList<>() and puts it into list_item.XML via the ArrayAdapter class. A user may click the "DELETE" button here to remove an item from their saved queue. 

