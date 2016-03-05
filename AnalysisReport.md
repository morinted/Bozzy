# Analysis Report Template

The point of this document is to capture your understanding of the requirements, and how you plan to address them. This document is used to capture the agreement between you and your customer of what will be built. It is also intended to be a useful reference for your project team.

Not all of the following sections will be relevant for every project. For example, not every project will have a user interface, or a database.



## Background (1 paragraph)

This document is an analysis report for the Bozzy stenography dictionary editor. A team of four students have taken on this project in hopes to improve the world's first free, open source stenography engine, Plover.  This document will provide a brief overview of both the functional and non-functional requirements, example data, test cases and user interface mock-ups. An explanation of the high level architecture, database schemas and file formats will also be given.


## Requirements (1 page)

Functional Requirements:

|           |                                                                    |
|-----------|--------------------------------------------------------------------|
|FR 1.1     |The system shall enable the user to add a dictionary.               |                 
|FR 1.2     |The system shall add the dictionary to the list of dictionaries.    |                 
|FR 1.3     |The system shall add the corresponding dictionary entries to the main table.|         
|           |                                                                    |                 
|FR 2.1     |The system shall enable the user to remove a dictionary.            |                 
|FR 2.2     |The system shall display a dialog window to ask if the user wants to remove the dictionary.|
|FR 2.3     |The system shall remove the dictionary from the list of dictionaries|                 
|FR 2.4     |The system shall remove the corresponding dictionary entries from the main table|     
|           |                                                                    |                 
|FR 3.1     |The system shall assign a default priority of n to the newly added dictionary.   n represents the nth dictionary added to the editor. Newly added dictionaries will be by default lowest priority.|
|FR 3.2     |The system shall adjust the priorities of the dictionaries with a lower priority when a dictionary is removed.|
|FR 3.3     |The system shall enable users to change the priority of the dictionaries.|            
|           |                                                                    |
|FR 4.1     |The system shall filter the main table by translation.              |
|FR 4.2     |The system shall filter the main table by stroke.                   |
|           |                                                                    |
|FR 5.1     |The system shall sort the dictionaries by translation.              |
|FR 5.2     |The system shall sort the dictionaries by stroke.                   |
|FR 6.1     |The system shall display changes which have been made to the dictionary. |
|FR 6.2     |The system shall save changes.                                      |
|FR 6.3     |The system shall back up the selected dictionary to the selected directory.|
|FR 6.4     |The system shall back up all of the dictionaries to the selected directory.|
|           |                                                                    |
|FR 7.1     |The system shall convert the dictionary format from RTF to JSON.    |
|FR 7.2     |The system shall convert the dictionary format of JSON to RTF.      |
|           |                                                                    |
|FR 8.1     |The system shall accept a URL to build a dictionary.                |
|FR 8.2     |The system shall accept a block of text to build a dictionary.      |
|FR 8.3     |The system shall scan the text of the URL page for all words currently not in the dictionary.|
|FR 8.4     |The system shall scan the block of text for all words currently not in the dictionary.|
|FR 8.5     |The system shall display a list of words to add to the current dictionary.|
|FR 8.6     |The system shall add the new word to the dictionary.                |

####Non-Functional Requirements

|           |                                                                    |
|-----------|--------------------------------------------------------------------|
|NFR1.1     |The system shall run on Windows operating system.                   |
|NFR1.2     |The system shall run on Mac Operating system.                       |
|NFR1.3     |The system shall be interoperable with Plover.                      |
|           |                                                                    |
|NFR2       |The system shall store data of 140,000 dictionary entries.          |
|NFR3       |The system shall take no longer than 3 hours to learn for a novice user.|
|NFR4       |The system shall be available 95% of the time.                      |

## Example Data and Test Cases ( 2-3 paragraphs)

### Data

The data we will be using to test and verify the system are the following dictionaries:

- stened.rtf (a dictionary provided by our customer, RTF format)
- magnum.rtf (a dictionary our team member owns a copy of, RTF format)
- dictionary.json (Plover default open source dictionary, JSON format)

### Critical scenarios with example data

#### Add dictionaries scenario

**The user launches the application.**

- The main window appears.

**The user selects add dictionary icon.**

- The add dictionary window appears.

**The user clicks browse to browse through file directory**

- The file directory window appears.

**The user selects plover default dictionary 'dictionary.json' from files.**

- The directory for 'dictionary.json' file is shown in text box.

**The user clicks okay in the add dictionary window.**

- Table in the main window is populated with dictionary.json entries:

| Stroke       | Translation | Words | Strokes |
|--------------|-------------|-------|---------|
| #*E          | {>}{&e}     | 1     | 1       |
| #*EU         | {>}{&i}     | 1     | 1       |
| #*U          | {>}{&u}     | 1     | 1       |
|      ...     |     ...     |  ...  |   ...   |
| PHOUPB/TAPB  | mountain    | 1     | 2       |
| PHOUPB/TEUPB | mountain    | 1     | 2       |
|      ...     |     ...     |  ...  |   ...   |
| WUT          | but         | 1     | 1       |
| WUZ          | was         | 1     | 1       |
| WUZ/KWREU    | wuzzy       | 1     | 2       |

## Convert dictionaries scenario

**The user selects Manage>Convert from menu.**

- The convert dictionary window appears.

**The user sets the following convert properties**

- selects dictionary to convert: 'dictionary.json'
- Converting to: RTF
- Output Location: 'C:\Documents\Dictionaries'

**The users clicks convert**

- The file system opens up a window at the directory location of the converted dictionary 'C:\Documents\Dictionaries\dictionary.rtf'.

## Edit Dictionaries + See changes before save

**From the main window, the user selects an existing dictionary entry from the table**

- The entry becomes editable after selection

| Stroke       | Translation | Words | Strokes |
|--------------|-------------|-------|---------|
| PHOUPB/TAPB  | `mountain`    | 1     | 2       |

**The user changes text 'mountain' to 'mountainous' and clicks away**

- The entry is updated to the changed text

| Stroke       | Translation | Words | Strokes |
|--------------|-------------|-------|---------|
| PHOUPB/TAPB  | `mountainous`    | 1     | 2       |

**The user selects File>Save from menu**

- The Save window appears and the following information is displayed.
   - changes made since last save:
      - modified: dictionary.json
      - + PHOUPB/TAPB , mountainous,  1, 2
      - - PHOUPB/TAPB , mountain,  1, 2

**The user clicks save**

- Changes are saved to dictionary.

## Use Case Model or Functional Features of System (2-3 paragraphs per feature or use case)

Define WHAT functionality the system will provide. Ideally this would be organized around the major use cases the system would support. This can also be organized around the major functions or features of the system. In either case, your system should have somewhere in the range of 3-10 of these use cases or major features. Include an updated use case diagram.

For each use case or feature:

Identify the requirements that are addressed by it, the actors it is relevant to, and any preconditions or restrictions.

Define clearly the "normal" flow of interactions with the system. Identify the variations on this normal flow that are possible, and in particular what "exceptions" must be handled.

Illustrate the normal flow (and variations) with an example using the sample data from one or more of your critical scenarios. (If the illustration will be shown in your user interface mockups, simply reference which screen shot should be looked at)

## Non-Functional Features (1 paragraph per feature)

Some requirements are non-functional. E.g. the system must be scalable and support a 24*7 usage.
Or the system must be usable by novice users. For each of these, describe your strategy for addressing them:

a) from a design point of view (how do you plan to design and build the system to address the requirement) and

b) from a testing point of view (how do you plan to verify that the requirement is met).

### Performance

The system must be able to load several thousand dictionary entries and display entries in a table. Largest example data dictionary we have has roughtly 140,000 dictionary entries.

The system must be usable for novice users.

The system must be reliable when it comes to backing up dictionaries.

## Design

The system must be maintainable and verifiable for future use in the open steno project community. One of the big issues is lack of documentation, which makes it hard for people to feel like continuing a project since so much effort is needed in understanding the application

## Adaption

The system must be portable on windows and max osx computers

They system must be interoperable with other steno applications

## High Level Architecture (2-3 Pages)

Draw a package diagram (or possibly an updated deployment diagram) that shows the key components of your system (e.g. web server, database, user interface) and what third party technologies you will use or be dependent on (e.g. IIS, MySql, PHP etc.). For each component there should be a brief statement of what its role is in supporting the requirements of the system. Illustrate your architecture by drawing two or three high level interaction diagrams that show how the components interact for your critical scenarios and example data. (If you are using a UML tool like Rational Rose you will need to create a facade class to represent each of your components).

## User Interface Mockup (2-3 pages)

Screen shots or actual GUI or HTML pages that illustrate the look and feel of the user interface for your critical scenarios. Make sure you use your example data.

## Database Schemas and File Formats (1 page)

If your data will be stored persistently either in a database or a file, define the relevant database schemas or file formats. Illustrate by showing how your example data will be stored.

## Algorithms (2-3 paragraphs per algorithm)

If your system will be dependent on any significant algorithms (pattern recognition, image processing, game strategy, matching problem etc.) then identify them here. Briefly outline the problem that needs to be solved, the alternatives considered, and the algorithm selected. Illustrate the algorithm with an example using the example data.
