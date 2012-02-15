Play Java Application
=====================

This Play Java Web Application is designed as part of a software presentation

Design follows the "Readme Driven Development" (via) Tom Preston-Werner 
and "Behaviour Driven Development" style. This application is designed 
to run from a Heroku cloud platform(Paas)

Design
------

## Actor is:
  - admin (a special type of web user)
  - superuser (a particular type of web user)
  - user (a type of web user)

Requirements
------------

## Story is:
  * a superuser can register himself
  * a superuser can add an event 
  * a user can register to any event
  * a superuser can do http://en.wikipedia.org/wiki/Moderation
  * a superuser can list events, audiences, repports.
  *  

## RESTify


 * GET /events
 * GET /events/add

 * POST /event/register/{user}


Dependencies
------------

Java  and the play framework working

  * - play -> crud
  * - play -> secure

Testing
-------

To run test

    $ play auto-test

Run
---

To run the application local

    $ play run


