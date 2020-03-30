-- drop table ALL_USER;
-- drop table ReviewContent;
-- drop table Monitors;
-- drop table FamilyDoctor;
-- drop table SpecializesIn;
-- drop table IsEmployedAt;
-- drop table HospitalLocation;
-- drop table Admin;
-- drop table ReviewDetails;
-- drop table SpecialistDoctor;
-- drop table Specialization;
-- drop table Department;
-- drop table Hospital;
-- drop table Appointment;
-- drop table Doctor;
drop table FAMILYDOCTOR cascade constraints;
drop table APPOINTMENT cascade constraints;
drop table ALL_USER cascade constraints;
drop table ISEMPLOYEDAT cascade constraints;
drop table DEPARTMENT cascade constraints;
drop table ADMIN cascade constraints;
drop table SPECIALIZESIN cascade constraints;
drop table SPECIALIZATION cascade constraints;
drop table HOSPITALLOCATION cascade constraints;
drop table HOSPITAL cascade constraints;
drop table SPECIALISTDOCTOR cascade constraints;
drop table DOCTOR cascade constraints;
drop table REVIEWDETAILS cascade constraints;
drop table REVIEWCONTENT cascade constraints;
drop table MONITORS cascade constraints;

-- Use char(1) for sex, 'f', 'm' for female and mele identity
CREATE TABLE ALL_USER
    (USER_ID INTEGER PRIMARY KEY,
    username varchar(20) NOT NULL,
    pwd varchar(20) NOT NULL,
    email varchar(30) NOT NULL,
    sex char(1),
    CONSTRAINT user_unique UNIQUE (username, email));

grant select on ALL_USER to public;
-- considering use data instead of varchar(40)
CREATE TABLE Appointment
     (aid INTEGER PRIMARY KEY,
     Appointment_date date,
     type varchar(20));

grant select on Appointment to public;

CREATE TABLE Doctor
    (did INTEGER PRIMARY KEY,
    name varchar(20),
    email varchar(20)  NOT NULL,
    CONSTRAINT email_unique UNIQUE (email));

grant select on Doctor to public;

-- I think it's better to delet the review when delete user
-- on delete set dafualt is not supported on oracle
-- changed on delete for user and appointment
CREATE TABLE ReviewContent
    (USER_ID INTEGER DEFAULT -1,
    aid INTEGER DEFAULT -2,
    did INTEGER,
    user_comment VARCHAR(100),
    rating INTEGER,
    PRIMARY KEY (USER_ID, aid, did),
    FOREIGN KEY (USER_ID) REFERENCES ALL_USER(USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (aid) REFERENCES Appointment(aid) ON DELETE CASCADE,
    FOREIGN KEY(did) REFERENCES Doctor(did) ON DELETE CASCADE);

grant select on ReviewContent to public;

-- boolean is not supported in oracle, so I changed visible type to number 
-- 1 -> True, 0 -> false
-- changed on delete for user and appointment
CREATE TABLE ReviewDetails
    (rid INTEGER PRIMARY KEY,
    USER_ID INTEGER DEFAULT -1,
    aid INTEGER DEFAULT -2,
    did INTEGER,
    numlikes INTEGER,
    review_date date,
    visible number,
    FOREIGN KEY (USER_ID) REFERENCES ALL_USER(USER_ID) ON DELETE CASCADE,
    FOREIGN KEY (aid) REFERENCES Appointment(aid) ON DELETE CASCADE,
    FOREIGN KEY (did) REFERENCES Doctor(did) ON DELETE CASCADE);

grant select on ReviewDetails to public;

-- -- acceptingPatients: 1 -> True, 0 -> false
-- CREATE TABLE FamilyDoctor
--    (did INTEGER PRIMARY KEY,
--    acceptingPatients number,
--    FOREIGN KEY (did) REFERENCES Doctor(did) ON DELETE CASCADE);

-- grant select on FamilyDoctor to public;

CREATE TABLE SpecialistDoctor
    (did INTEGER PRIMARY KEY,
    FOREIGN KEY (did) REFERENCES Doctor(did)ON DELETE CASCADE);

    grant select on SpecialistDoctor to public;

CREATE TABLE Specialization
    (specname varchar(20),
    subspec varchar(20),
    PRIMARY KEY (specname, subspec));

grant select on Specialization to public;

CREATE TABLE SpecializesIn
    (did INTEGER,
    specname varchar(20),
    subspec varchar(20),
    PRIMARY KEY(did, specname, subspec),
    FOREIGN KEY (did) REFERENCES SpecialistDoctor(did) ON DELETE CASCADE,
    FOREIGN KEY (specname, subspec) REFERENCES Specialization(specname, subspec) ON DELETE CASCADE);

grant select on SpecializesIn to public;

CREATE TABLE HospitalLocation
    (postalcode VARCHAR(8) PRIMARY KEY,
    province VARCHAR(10),
    city VARCHAR(20));

grant select on HospitalLocation to public;

CREATE TABLE Hospital
    (hid INTEGER PRIMARY KEY,
    hname varchar(30),
    address varchar(100),
    postalcode VARCHAR(8),
    FOREIGN KEY (postalcode) REFERENCES HospitalLocation(postalcode) ON DELETE CASCADE);

grant select on Hospital to public;


CREATE TABLE Department
    (dname varchar(20),
    hid INTEGER,
    PRIMARY KEY(dname, hid),
    FOREIGN KEY(hid) REFERENCES Hospital(hid) ON DELETE CASCADE);

grant select on Department to public;
-- changed " FOREIGN KEY (dname) REFERENCES Department(dname) ON DELETE CASCADE,"
-- to " FOREIGN KEY (dname,hid) REFERENCES Department(dname,hid) ON DELETE CASCADE"
-- because dname + hid are the primary key of department
-- we can't just use one of them
CREATE TABLE IsEmployedAt
    (hid INTEGER,
    dname varchar(20),
    did INTEGER,
    PRIMARY KEY (hid, dname, did),
    FOREIGN KEY (hid) REFERENCES Hospital(hid) ON DELETE CASCADE,
    FOREIGN KEY (dname,hid) REFERENCES Department(dname,hid) ON DELETE CASCADE,
    FOREIGN KEY (did) REFERENCES Doctor(did) ON DELETE CASCADE);

grant select on IsEmployedAt to public;

-- CREATE TABLE Department
-- (dname STRING,
--  hid INTEGER,
--  PRIMARY KEY(dname, hid),
--  FOREIGN KEY(hid) REFERENCES Hospital(hid) ON DELETE CASCADE
-- )

CREATE TABLE Admin
    (adid INTEGER PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    pwd VARCHAR(20) NOT NULL,
 CONSTRAINT admin_unique UNIQUE (username));

grant select on Admin to public;

CREATE TABLE Monitors
    (adid INTEGER,
    rid INTEGER,
    PRIMARY KEY (adid, rid),
    FOREIGN KEY (adid) REFERENCES Admin(adid) ON DELETE CASCADE,
    FOREIGN KEY (rid) REFERENCES ReviewDetails(rid) ON DELETE CASCADE);

grant select on Monitors to public;





















