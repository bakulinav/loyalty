CONTRIBUTING
============

**TBD**


Task description
----------------

**Title**

All issues have title with component token to improve searching and distinguish
 task in a list. Loyalty separates to following modules
 - API: Loyalty API component
 - ENG: Loyalty Rules Engine

**Description**

To provide unified look for all tasks in Github issues, all task descriptions
should have following structure:

 * **Description** - motivation part of description.
 * **Need to be done** - list of steps need to be done to complete a task

Example:

```
**Description**

As soon Loyalty system is a decomposed to modules with specific functions like processing facts
(*Rules Engine*) we need to create a separate module to provide public API for clients can connect to it,
send own shopping cart (or other container) and get processed entity back with applied rules.

**Need to be done**

 * create separate maven module based on Spring Boot and having dependency to Spring Web component;
 * create endpoint to accept `ShoppingCart` object in JSON for processing
 ...
 ```