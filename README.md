# Exercise: Journal Terminal App with JDBC and PostgreSQL
## Objective
Create a command-line journal application using Java JDBC to interact with a PostgreSQL database. The application should allow users to add new journal entries, view a list of all entries, and display details of a specific entry by ID.
Requirements

## 1. Database Setup:
1. Create a PostgreSQL database named journal_db.
2. Create a table named journal_entries with the following columns:  
> id (serial primary key)  
> title (varchar)  
> content (text)  

## Journal Terminal App:
1. Use JDBC to connect to the PostgreSQL database.
2. Implement a loop to handle user input with the following options:
3. Add a new journal entry: with title and content
4. List all journal entries with ID, title, and character count of the content.
5. Show details of a specific entry by ID.

#### Exmaple menu
```bash
Journal App started
Select Mode
(1) Add Entry
(2) Show List
(3) Show Entry
(0) Exit
> 
```