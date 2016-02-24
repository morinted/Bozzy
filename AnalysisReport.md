# Analysis Report Template

The point of this document is to capture your understanding of the requirements, and how you plan to address them. This document is used to capture the agreement between you and your customer of what will be built. It is also intended to be a useful reference for your project team.

Not all of the following sections will be relevant for every project. For example, not every project will have a user interface, or a database.

## Background (1 paragraph)

Briefly give enough background so that a person outside your team reading this document would understand why this document exists and what it is used for.

## Requirements (1 page)

A numbered list of the main requirements for this project with a brief definition of each requirement (often a sentence will suffice, no more than a short paragraph).  Requirements should be grouped so that similar requirements are grouped together.  The point of this section is to list and identify requirements.  It is not necessary to include a myriad of details that may evolve over time, as long as the main requirement has been identified.

## Example Data and Test Cases ( 2-3 paragraphs)

Identify the data that the customer will be providing, or you will be building to test and verify the system throughout the project lifecycle. If it is not currently available, identify the process and schedule by which it will be made available (and highlight it as a risk in your project status report that needs to be managed).  

Please note that you and your customer cannot have possibly come to a common understanding of the requirements, unless you have agreed upon the test cases and sample data that will be used to verify requirements have been met.  It is essential that you start building up these test cases and sample data early.

Identify two or three critical scenarios and define example data for them that will be used to start work on test cases.  You do not have to provide test cases in this document, but you should use the scenarios and example data to illustrate your analysis of the requirements throughout this document.

Use Case Model or Functional Features of System (2-3 paragraphs per feature or use case)

Define WHAT functionality the system will provide. Ideally this would be organized around the major use cases the system would support. This can also be organized around the major functions or features of the system. In either case, your system should have somewhere in the range of 3-10 of these use cases or major features. Include an updated use case diagram.

For each use case or feature:

Identify the requirements that are addressed by it, the actors it is relevant to, and any preconditions or restrictions.

Define clearly the "normal" flow of interactions with the system. Identify the variations on this normal flow that are possible, and in particular what "exceptions" must be handled.

Illustrate the normal flow (and variations) with an example using the sample data from one or more of your critical scenarios. (If the illustration will be shown in your user interface mockups, simply reference which screen shot should be looked at)

## Non-Functional Features (1 paragraph per feature)

Some requirements are non-functional. E.g. the system must be scalable and support a 24*7 usage. Or the system must be usable by novice users. For each of these, describe your strategy for addressing them:

 a) from a design point of view (how do you plan to design and build the system to address the requirement) and

b) from a testing point of view (how do you plan to verify that the requirement is met).

## High Level Architecture (2-3 Pages)

Draw a package diagram (or possibly an updated deployment diagram) that shows the key components of your system (e.g. web server, database, user interface) and what third party technologies you will use or be dependent on (e.g. IIS, MySql, PHP etc.). For each component there should be a brief statement of what its role is in supporting the requirements of the system. Illustrate your architecture by drawing two or three high level interaction diagrams that show how the components interact for your critical scenarios and example data. (If you are using a UML tool like Rational Rose you will need to create a facade class to represent each of your components).

## User Interface Mockup (2-3 pages)

Screen shots or actual GUI or HTML pages that illustrate the look and feel of the user interface for your critical scenarios. Make sure you use your example data.

## Database Schemas and File Formats (1 page)

If your data will be stored persistently either in a database or a file, define the relevant database schemas or file formats. Illustrate by showing how your example data will be stored.

## Algorithms (2-3 paragraphs per algorithm)

If your system will be dependent on any significant algorithms (pattern recognition, image processing, game strategy, matching problem etc.) then identify them here. Briefly outline the problem that needs to be solved, the alternatives considered, and the algorithm selected. Illustrate the algorithm with an example using the example data.

 
