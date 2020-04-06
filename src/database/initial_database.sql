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

-- Use char(1) for sex, 'f', 'm' for female and male identity
CREATE TABLE ALL_USER
    (username varchar(20) PRIMARY KEY,
    pwd varchar(20) NOT NULL,
    email varchar(30) NOT NULL,
    sex char(1),
    CONSTRAINT user_unique UNIQUE (username, email));

grant select on ALL_USER to public;

CREATE TABLE Appointment
     (aid INTEGER PRIMARY KEY,
     Appointment_date date,
     type varchar(25));

grant select on Appointment to public;

CREATE TABLE Doctor
    (demail varchar(20) PRIMARY KEY,
    name varchar(20));

grant select on Doctor to public;

CREATE TABLE ReviewContent
    (username varchar(20) DEFAULT 'someone',
    aid INTEGER DEFAULT -2,
    demail varchar(20) ,
    user_comment VARCHAR(100),
    rating INTEGER,
    PRIMARY KEY (username, aid, demail),
    FOREIGN KEY (username) REFERENCES ALL_USER(username) ON DELETE CASCADE,
    FOREIGN KEY (aid) REFERENCES Appointment(aid) ON DELETE CASCADE,
    FOREIGN KEY (demail) REFERENCES Doctor(demail) ON DELETE CASCADE);

grant select on ReviewContent to public;

CREATE TABLE ReviewDetails
    (rid INTEGER PRIMARY KEY,
    username varchar(20) DEFAULT 'someone',
    aid INTEGER DEFAULT -2,
    demail varchar(20),
    numlikes INTEGER,
    review_date date,
    visible number,
    FOREIGN KEY (username) REFERENCES ALL_USER(username) ON DELETE CASCADE,
    FOREIGN KEY (aid) REFERENCES Appointment(aid) ON DELETE CASCADE,
    FOREIGN KEY (demail) REFERENCES Doctor(demail) ON DELETE CASCADE);

grant select on ReviewDetails to public;

-- -- acceptingPatients: 1 -> True, 0 -> false
-- CREATE TABLE FamilyDoctor
--    (did INTEGER PRIMARY KEY,
--    acceptingPatients number,
--    FOREIGN KEY (did) REFERENCES Doctor(did) ON DELETE CASCADE);

-- grant select on FamilyDoctor to public;

CREATE TABLE SpecialistDoctor
    (demail varchar(20) PRIMARY KEY,
    FOREIGN KEY (demail) REFERENCES Doctor(demail)ON DELETE CASCADE);

    grant select on SpecialistDoctor to public;

CREATE TABLE Specialization
    (specname varchar(20),
    subspec varchar(20),
    PRIMARY KEY (specname, subspec));

grant select on Specialization to public;

CREATE TABLE SpecializesIn
    (demail varchar(20),
    specname varchar(20),
    subspec varchar(20),
    PRIMARY KEY(demail, specname, subspec),
    FOREIGN KEY (demail) REFERENCES SpecialistDoctor(demail) ON DELETE CASCADE,
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

CREATE TABLE IsEmployedAt
    (hid INTEGER,
    dname varchar(20),
    demail varchar(20),
    PRIMARY KEY (hid, dname, demail),
    FOREIGN KEY (hid) REFERENCES Hospital(hid) ON DELETE CASCADE,
    FOREIGN KEY (dname,hid) REFERENCES Department(dname,hid) ON DELETE CASCADE,
    FOREIGN KEY (demail) REFERENCES Doctor(demail) ON DELETE CASCADE);

grant select on IsEmployedAt to public;

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



insert into ALL_USER values('kewlguy96', 'password', 'table123@u.ca', 'm');
 
insert into ALL_USER values('princeandrew', 'wordpass', 'flwr@u.ca', 'f');

insert into ALL_USER values('edgelord999', 'pa$$word', 'evilman@a.ca', 'm');

insert into ALL_USER values('pm_me_ur_dr', 'passw0rd', 'lol@gmayle.ca', 'm');

insert into ALL_USER values('droneluvr', 'p4ssword', 'drones@lol.ca', 'f');

insert into Appointment values('10000001', TO_DATE('2020-02-27','YYYY-MM-DD'), 'checkup');
insert into Appointment values('10000002', TO_DATE('2020-02-25','YYYY-MM-DD'), 'followup');
insert into Appointment values('10000003', TO_DATE('2020-02-27','YYYY-MM-DD'), 'diagnosis');
insert into Appointment values('10000004', TO_DATE('2020-02-23','YYYY-MM-DD'), 'checkup');
insert into Appointment values('10000005', TO_DATE('2020-02-23','YYYY-MM-DD'), 'followup');

-- insert into Doctor values('2000000001', 'Gregory House', 'ghouse@princet.com');
-- insert into Doctor values('2000000002', 'Perry Cox', 'pcox@sacredh.com');
-- insert into Doctor values('2000000003', 'Meredith Grey', 'mgrey@seattleg.com');
-- insert into Doctor values('2000000004', 'Dr. Pepper', 'drpepper@fizzy.com');
-- insert into Doctor values('2000000005', 'Doogie Howser', 'dhowser@thedoog.com');
insert into Doctor values('ereid@sacredh.com', 'Elliot Reid');
insert into Doctor values('jd@sacredh.com', 'John Dorian');
insert into Doctor values('cyang@seattleg.com', 'Cristina Yang');
insert into Doctor values('lcuddles@princet.com', 'Lisa Cuddy');
insert into Doctor values('jzoidberg@planex.com', 'John Zoidberg');

-- ReviewContent
insert into ReviewContent values('kewlguy96', '10000001', 'jd@sacredh.com', 'this doctor was the greatest. He was so patient and loving i love him.', '5');
insert into ReviewContent values('princeandrew', '10000002', 'ereid@sacredh.com', 'The devil, rude and doesnt know anything.', '1');
insert into ReviewContent values('edgelord999', '10000003', 'cyang@seattleg.com', 'the worst. he lives at 123 cresc. ave. vancouver!', '1');
insert into ReviewContent values('pm_me_ur_dr', '10000004', 'jzoidberg@planex.com', 'they ran out of candy in reception, what kind of clinic runs out of candy?! -3*', '2');
insert into ReviewContent values('droneluvr', '10000005', 'lcuddles@princet.com', 'he will prescribe ANYTHING, he was so amazing! I got drugz4dayz. A++++', '5');

-- ReviewDetails

insert into ReviewDetails values('80001', 'kewlguy96', '10000001', 'jd@sacredh.com', '10', TO_DATE('2020-03-03','YYYY-MM-DD'), '1');
insert into ReviewDetails values('80002', 'princeandrew', '10000002', 'ereid@sacredh.com', '3', TO_DATE('2020-03-10','YYYY-MM-DD'), '1');
insert into ReviewDetails values('80003', 'edgelord999', '10000003', 'cyang@seattleg.com', '2', TO_DATE('2020-03-27','YYYY-MM-DD'), '0');
insert into ReviewDetails values('80004', 'pm_me_ur_dr', '10000004', 'jzoidberg@planex.com', '8', TO_DATE('2020-03-13','YYYY-MM-DD'), '1');
insert into ReviewDetails values('80005', 'droneluvr', '10000005', 'lcuddles@princet.com', '13', TO_DATE('2020-03-16','YYYY-MM-DD'), '1');

-- -- FamilyDoctor
-- insert into FamilyDoctor values('2000000001', '1');
-- insert into FamilyDoctor values('2000000002', '0');
-- insert into FamilyDoctor values('2000000003', '1');
-- insert into FamilyDoctor values('2000000004', '0');
-- insert into FamilyDoctor values('2000000005', '1');

-- SpecialistDoctor
insert into SpecialistDoctor values('ereid@sacredh.com');
insert into SpecialistDoctor values('jd@sacredh.com');
insert into SpecialistDoctor values('cyang@seattleg.com');
insert into SpecialistDoctor values('lcuddles@princet.com');
insert into SpecialistDoctor values('jzoidberg@planex.com');

-- Specialization
insert into Specialization values('Pediatric', 'Oncology');
insert into Specialization values('Pediatric', 'Genetics');
insert into Specialization values('Psychiatry', 'Learning');
insert into Specialization values('Dermatology', 'Immunodermatology');
insert into Specialization values('Cardiology', 'Nuclear');
insert into Specialization values('Otolaryngologist', 'Cosmetic');

-- SpecializesIn
insert into SpecializesIn values('ereid@sacredh.com', 'Pediatric', 'Oncology');
insert into SpecializesIn values('jd@sacredh.com', 'Otolaryngologist', 'Cosmetic');
insert into SpecializesIn values('cyang@seattleg.com', 'Psychiatry', 'Learning');
insert into SpecializesIn values('lcuddles@princet.com', 'Dermatology', 'Immunodermatology');
insert into SpecializesIn values('jzoidberg@planex.com', 'Cardiology', 'Nuclear');

-- HospitalLocation
insert into HospitalLocation values('M9B 8C7', 'Toronto', 'ON');    
insert into HospitalLocation values('V1A 2B3', 'Vancouver', 'BC');
insert into HospitalLocation values('V1T 2B5', 'Vancouver', 'BC');
insert into HospitalLocation values('R4C 0S1', 'Winnipeg', 'MB');
insert into HospitalLocation values('V9X 8Y7', 'Vancouver', 'BC');
-- Hospital
insert into Hospital values('300000001', 'Princeton Plainsboro', '1234 Fake St', 'M9B 8C7');    
insert into Hospital values('300000002', 'Sacred Heart', '1234 Faux St', 'V1A 2B3');
insert into Hospital values('300000003', 'Seattle Grace', '4321 Fayk Ave', 'V1T 2B5');
insert into Hospital values('300000004', 'Doogies House', '3920 Faek Cresc', 'R4C 0S1');
insert into Hospital values('300000005', 'Planet Express', '9210 Feyk Dr', 'V9X 8Y7');
-- Department
-- insert into Department values('Pediatry', '300000001');

-- insert into Department values('Gynecology', '300000001');

-- insert into Department values('Gynecology', '300000002');

-- insert into Department values('Gynecology', '300000003');

-- insert into Department values('Cardiology', '300000005');
insert into Department values('Psychiatry', '300000001');
insert into Department values('Pediatry', '300000002');
insert into Department values('ENT', '300000002');
insert into Department values('Dermatology', '300000003');
insert into Department values('Pediatry', '300000004');
insert into Department values('Cardiology', '300000005');
-- IsEmployedAt
-- insert into IsEmployedAt values('300000001', 'Psychiatry', '2000000003');
insert into IsEmployedAt values('300000001', 'Psychiatry', 'cyang@seattleg.com');

-- insert into IsEmployedAt values('300000002', 'Pediatry', '2000000002');
insert into IsEmployedAt values('300000004', 'Pediatry', 'ereid@sacredh.com');  
insert into IsEmployedAt values('300000002', 'ENT', 'jd@sacredh.com');

-- insert into IsEmployedAt values('300000003', 'Dermatology', '2000000001');   
insert into IsEmployedAt values('300000003', 'Dermatology', 'lcuddles@princet.com');

-- insert into IsEmployedAt values('300000004', 'Dermatology', '2000000005');

-- insert into IsEmployedAt values('300000005', 'Cardiology', '2000000004');
insert into IsEmployedAt values('300000005', 'Cardiology', 'jzoidberg@planex.com');
-- Admin
insert into Admin values('00001', 'alee', '123456');    
insert into Admin values('00002', 'ali', '234567');
insert into Admin values('00003', 'rnguyen', '345678');
-- insert into Admin values('00004', 'ksanson', '456789');
-- insert into Admin values('00005', 'bsanders', '567890');

--monitors
insert into Monitors values('00001', '80003');
insert into Monitors values('00001', '80004');
insert into Monitors values('00002', '80001');
insert into Monitors values('00003', '80002');
insert into Monitors values('00003', '80005');
















