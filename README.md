## Power Consumption Statistics Generator


#### SUMMARY

The central idea is that a software system an analyst can use to
draw conclusions about energy consumption over time has to be built. An
analyst would like to know the answers to questions such as:\
`(a)`what time of day is the laundry room consumption maximum?\
`(b)` which period of the year/month is the minimum/maximum heating consumption?\
`(c)` energy consumption in the kitchen, changes depending on the day of the
week, etc.\
The data come from a [Kaggle data set on electricity consumption](https://www.kaggle.com/uciml/electric-power-consumption-data-set) of a house
for 3 years, sampled every second.
<br><br>


#### CONTENTS

* src: the source code
* uml: UML diagrams of each class separately as well as for the whole program
* resources: data input examples and program output examples
<br><br>


#### HOW TO USE

`(1)` Clone the repo into an eclipse workspace folder.\
`(2)` In eclipse, create a new java project with the same name as the above folder (!)\
`(3)` Eclipse should be able to put everything together on it's own that way (except for tests)
<br><br>

Alternatively the program can run on any terminal as long as Java 14 or 15 is installed in the system:\
`(1)` Navigate to the src folder (in the terminal)\
`(2)` Compile: javac client/*.java\
`(3)` And run: java client.MainUI
<br><br>


#### NOTES ABOUT FILE LOAD:

Input file examples can be found in the resources folder\
When asked for input file path, an absolute file path is required (e.g. E:\folder\text.txt)\
Acceptable file types are: .txt and .tsv\
File type extension must be included\
Acceptable delimeters are: ;, tab, space)\
Expected number of columns for each line: 9
<br><br>


#### NOTE ABOUT REPORT CREATION: 

When asked for a directory, an absolute directory path is required (e.g. E:\folder)\
And afterwards, a file name alone is required (file extension is not required) (e.g. MyFile)
<br><br>


#### ABOUT TESTS:

`(1)` In order for tests to run successfully JUnit 4 or JUnit 5 must be imported into the eclipse project\
`(2)` File paths in the classes of the test package must be updated to match that of TestInput folder location in the specific system
