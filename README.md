╦═╗┌─┐┌┬┐┌─┐╔╦╗┬ ┬╔╦╗┌─┐┌─┐ ╔═╗╔═╗
╠╦╝├─┤ │ ├┤ ║║║└┬┘ ║║│ ││   ║  ╠═╣
╩╚═┴ ┴ ┴ └─┘╩ ╩ ┴ ═╩╝└─┘└─┘o╚═╝╩ ╩

This application provides an interface to write and query for doctor reviews. 

Leave a review:
	USER/PASS is autheticated first, before creating new review. A unique review ID will be displayed when the review is succesfully created.

	To leave a review, requires..
	1. username
	2. password
	3. appointment type
	4. appointment date
	5. doctor email address
	6. small review comment
	7. overall rating



Edit a review:
	USER/PASS is authenticated, then compared against the valid rID provided. If USER/PASS
	wrote the review, then an sql update is made. Only review can be updated, nothing
	related to doctor/appointment can be updated by the user after the review is created.

	To edit a review, requires..
	1. username
	2. password
	3. small review (this will replace whatever is already in the system).
	4. overall rating (this will replace whatever is already in the system).

		
		
Lookup a review:
	The app should allow lookup for name, location, specialization, hospital, and filter by stars. 
	
	To look up a review, ...
	1. no fields filled out = display all reviews in the database.
	2. Doctor Name (Always FIRSTNAME LASTNAME) eg. Gregory House, Diane Nguyen
	3. Display doctor in results with name or email
	4. Doctor Specialization eg. Pediatrician, Oncologist
	5. With average rating above (eg. 3 stars) Show doctors who have an average rating above 3*.
	
	* When no results are found, will just return no results.
	* Total number of results should be counted and returned.



Lookup most reviewed doctor:

	The doctors that have been reviewed by all users in the database will be displayed. 
	This operation will not look at any of the fields as in normal review lookup.
	User can still choose to display doctor name or email in the results.



Total review count:

	The total number of reviews stored in the database will be displayed in the review lookup result box.
	No other user input is required.


Delete a review:

	To delete a review, enter a valid admin Username, Password, and ReviewID to delete.
	The deleted review ID will not show up in the list of monitored reviews of an admin.



Monitor a review:

	By default, enter a valid admin username and password to display a list of review IDs 
	that the admin is monitoring in the review lookup result box.
	If a valid ReviewID is also specified, then the review will be added to the list of monitored reviews 
	of the admin in the database.
	



