reliability
===========

Reliability and stability project code. Includes all services, RO Monitoring tool+visualization, Atom Notifications and reliability calculation algorithm.

This is an Eclipse Java project that includes everything needed to be executed (except for the server).

In Stability/WebContent one may find the html for the main page (visual.html) together older versions and sets of sample traces that have been developed during the project. Stability/WebContent/images stores all the images used in the application and Stability/WebContent/WEB-INF/lib has all the required libraries.

Stability/src gathers all the packages and the code that has been implemented. At /main one can find all the REST and ATOM webservices. The objects used in the webservices are defined at /rdfReliability and at /atom. The algorithms in charge of the calculations of Reliability and Stability are performed at /storageEvaluation, which at the same time is the package with the classes in charge of loading the quality traces. /checklistStorage has the classes that call to the plataform in order to run rhe checklist against every available RO (has to run every 24h) and saving the results in xml files for each RO. 

The basic idea of the code is: 1) Run Checklists daily, 2) Store XML Results, 3) Make the services available 4) Load the XML Results when needed, 5) Build and calculate the complete Reliability Trace, 6) Return the final data (ATOM, JSON or XML). 8)[OPTIONAL] Visualize the data at the web application.
