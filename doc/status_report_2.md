# Status Report

## Project: Bozzy

## Iteration 2

## Implementation Status

The main GUI wireframe for our application has been implemented, which consists of the main window with a menu bar (File, Edit, Manage, About), a pane on the left for filter options, and a centre pane for the table that displays the dictionary entries. Currently the table has four columns: Stroke, Translation, Word Count, and Stroke Count.

Upon opening the application, data from dictionary.json is automatically loaded into the table. Using the table, you can sort on Stroke, Translation, Word Count, or Stroke Count, as well as rearrange the columns in a preferred order. Note that rearrangement is not saved upon closing and reopening the application.

The following is a sample screenshot to illustrate our progress:

![Bozzy application]()

## Highlights

We met with our customer Mirabai every week for at least one hour during this iteration. During these customer meetings we were able to produce and demonstrate two new releases of the stenography dictionary editor, which was an invaluable way of creating a user experience for our customer and receiving feedback from the customer's interaction with the system. At this time Mirabai is very impressed and pleased with the application after commenting that the application is good, smooth, and responsive. Mirabai also provided us with tips and ideas for the application, such as creating and deleting dictionary entries, merging dictionaries, creating a blank dictionary, and colour coding strokes.  

## Risks or Issues List

| Date Entered | Risk or Issue          | Description                                                                                                                                                                                                          | Resolution                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Status |
|--------------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| Feb 4, 2016  | User Experience        | We need to create a user experience for our customer and walk through storyboards with them                                                                                                                          | For the last few weeks we have been accomplishing weekly sprints revolving around our weekly customer meeting. At the end of every sprint we have a potentially viable version of our application for release, which we immediately send to our customer during our customer meeting. During the customer meeting we're able to walk through of the changes we've made with our customer and get feedback from our Customer's direct interaction with the application, thus creating an invaluable user experience. | Closed |
| Feb 4, 2016  | Request Pull           | Pull request feedback needs to be faster                                                                                                                                                                             | Instead of one person reviewing everyone's code, we've changed it so that everyone can review another team member's code and can accept or decline a pull request. This ensures at least one team members reviews someone's code before merging the code, and also keeps progress moving more quickly.                                                                                                                                                                                                              | Closed |
| Mar 5, 2016  | Team meeting room      | We need to find a consistent and appropriate place to hold weekly team meetings for several hours (1-5 hours) to code and talk with our customer                                                                     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Open   |
| Mar 5, 2016  | Depending on a feature | Mirabai has considered working with features that don't exist in Plover yet. For raw input of the stroke (STKPWHR style), we don't have to worry because Plover could have a raw output mode (which it doesn't yet). |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Open   |

## Tasks in Progress or Completed This Iteration

| Task Name                 | Description                                                                                                                                                                                        | Who Worked on it | Complete    |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------|-------------|
| Setup environment         | Choosing the technologies and environment, testing the development environment, and then writing instructions to team members on how to setup the chosen development environment.                  | Ian              | Complete    |
| Read/write to file system | Writing code to write string to file system, read string from file system.                                                                                                                         | Erica            | Complete    |
| Mockups                   | Creating Mockups of Bozzy's main screen, desktop application.                                                                                                                                      | Erica            | Complete    |
| Storyboards               | Using the Mockups, create storyboards for user tasks such as add, remove, assign priorities, filter, sort, convert, backup, build, and edit entries on dictionaries. Also showing changes on save. | Sophie           | Complete    |
| Use cases                 | From user tasks, create use case diagram and user stories.                                                                                                                                         | Ted              | Complete    |
| Conversion script         | Writing up conversion script between RTF and JSON.                                                                                                                                                 | Ted              | Complete    |
| GUI wireframe             | Writing code for Bozzy user interface.                                                                                                                                                             | Ian              | Complete    |
| GUI table                 | Must complete a working graph prototype displaying a setof strokes and translations                                                                                                                | Ian              | Complete    |
| Compiling a release       | Add assembly sbt plugin for building fat jars                                                                                                                                                      | Ian              | Complete    |
| Search by Translation     | A working prototype of the function which will filter througha list of words to match the input parameter, the word theuser is searching for                                                       | Sophie           | In Progress |
| Back up of Dictionaries   | Read and write from the file system with some dummy data in order to simulate a back up of the dictionaries inalphabetical order                                                                   | Ian              | In Progress |

## Tasks to be worked on next Iteration

| Task Name      	| Description                    	                               |  Who worked on it   	|
|---                     	|---	                                                                       |---	                        |
|   Add Dictionaries	|   Implement the functionality of adding at least two new dictionaries into the application to enlarge our sample data 	|  Ted  	|
|  File directory prototype  	|  Create a dictionary file directory prototype for the application	|   Ian  	|
| MVC design pattern	|  Implement system modules as a MVC design pattern. Decide whether we should access dictionary entries from dictionary files themselves, example from main.json, or from a list structure which holds the entries from main.json 	| Sophie  	|
|  Artwork  	|   Looking into artwork, logo, icons, and colours for Bozzy application	|   Erica  	|
