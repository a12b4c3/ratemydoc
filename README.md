╦═╗┌─┐┌┬┐┌─┐╔╦╗┬ ┬╔╦╗┌─┐┌─┐ ╔═╗╔═╗
╠╦╝├─┤ │ ├┤ ║║║└┬┘ ║║│ ││   ║  ╠═╣
╩╚═┴ ┴ ┴ └─┘╩ ╩ ┴ ═╩╝└─┘└─┘o╚═╝╩ ╩



Leave a review:
	USER/PASS is autheticated first, before creating new review.

	To leave a review, requires..
	1. username
	2. password
	3. appointment type
	4. appointment date
	5. doctor email address
	6. small review
	7. overall rating
	
	RESPONSE CODES:
	000 - SUCCESS
	100 - WRONG_CRED
	200 - WRONT_FORMAT
		eg. wrong date, email address format



Edit a review:
	USER/PASS is authenticated, then compared against the valid rID provided. If USER/PASS
	wrote the review, then an sql update is made. Only review can be updated, nothing
	related to doctor/appointment can be updated by the user after the review is created.

	To edit a review, requires..
	1. username
	2. password
	3. small review (this will replace whatever is already in the system).
	4. overall rating (this will replace whatever is already in the system).

	RESPONSE CODES:
	000 - SUCCESS
	100 - WRONG_CRED
	105 - WRONG_rID
		eg. could be rID doesnt exist, user did not create the original review, etc.
	800 - UNEXPECT_ARG
		eg. Appointments, and doctors must be default values, and will not be updated.

		
		
Lookup a review:
	The app should allow lookup for name, location, specialization, hospital, and filter by stars. 
	
	To look up a review, ...
	1. no fields filled out = get 3 most recent reviews in the system.
	2. Doctor Name (Always FIRSTNAME LASTNAME) eg. Gregory House, Diane Nguyen
	3. Doctor Location (Name of a city) eg. Vancouver, Richmond, Surrey
	4. Doctor Specialization eg. Pediatrician, Oncologist
	5. Only show doctors above (eg 3 stars) Show doctors who have received only 3* reviews and above.
	
	* When no results are found, will just return no results.
	* Total number of results should be counted and returned.
	
	RESPONSE CODES:
	000 - SUCCESS
	



Delete a review:

	To delete a review, enter a valid admin Username, Password, and ReviewID to delete.

	
	RESPONSE CODES:
	100 - WRONG_CRED
		(eg. not admin credential trying to delete a review)
	105 - WRONG_rID
		(eg. trying to delete a rID which does not exist)
