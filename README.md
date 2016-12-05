# _Surfboard Review Website_

#### _Allows users to keep track of surfboards and review them._

#### By _**Joanna Anderson and Andy Malkin**_

## Description

_This application is a customized surfboard review website that allows users to add/edit/delete new surfboards and review them._

## Installation Requirements

1. Go to [my GitHub](https://github.com/jsaerom/surfboard-review)
2. Click Clone or download and choose either the Open in Desktop option or Download Zip option
**OR**
1. Open Terminal
2. Command `$ git clone https://github.com/jsaerom/surfboard-review`

In PSQL:

1. CREATE DATABASE surfboard_review;
2. CREATE TABLE boards (id serial PRIMARY KEY, name varchar, imgurl varchar, boardtype varchar, thickness int, price int, length int, fin int, tail varchar, companyid int, width int);
3. CREATE TABLE companies (id serial PRIMARY KEY, name varchar, location varchar, imgurl varchar, website varchar, description varchar);
4. Run the following command: $ psql media < media.sql

## Technologies Used

* _Java_
* _Spark_
* _HTML_
* _Velocity Template Engine_
* _CSS_
* _Bootstrap_

### License

Copyright (c) 2016 **_Joanna Anderson_**
