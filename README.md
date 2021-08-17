# Weather-Simulation
A model of temperature, wind speed, precipitation, snowfall, and snow depth for each state within the United States. The historic and current weather data is gathered from the NCDC API, while future weather is predicted using linear regression. You can simulate the effects of hurricanes and the Yellowstone Supervolcano on weather in the US. 


The program simulates weather data for each state within the United States

Historical and current information from each state is gathered from the National Centers for Environmental Information (NCEI) API and serialzied using Gson
https://www.ncei.noaa.gov/support/access-data-service-api-user-documentation

The data for each state is generalized to the largest city within the state (ie. Washington state is represented by Seattle)

Weather data in the future is predicted through linear regression. For every year prior to the date being predicted, a line of best fit is drawn centered on the predicted date. The error for each year is calculated compared to the current year, and the year with the least error will be used to model this year's unknown data. 

Users can select any date from 2011 to the present for data they want to view

Users can also simulate extreme weather events, such as the eruption of the Yellowstone supervolcano as well as East and Gulf Coast hurricanes
