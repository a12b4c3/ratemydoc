insert into ALL_USER values('1001', 'kewlguy96', 'password', 'table123@u.ca', 'm');
 
insert into ALL_USER values('1002', 'princeandrew', 'wordpass', 'flwr@u.ca', 'f');

insert into ALL_USER values('1003', 'edgelord999', 'pa$$word', 'evilman@a.ca', 'm');

insert into ALL_USER values('1004', 'pm_me_ur_dr', 'passw0rd', 'lol@gmayle.ca', 'm');

insert into ALL_USER values('1005', 'droneluvr', 'p4ssword', 'drones@lol.ca', 'f');

insert into Appointment values('100000001', TO_DATE('2020-02-27','YYYY-MM-DD'), 'checkup');
insert into Appointment values('100000002', TO_DATE('2020-02-25','YYYY-MM-DD'), 'followup');
insert into Appointment values('100000003', TO_DATE('2020-02-27','YYYY-MM-DD'), 'diagnosis');
insert into Appointment values('100000004', TO_DATE('2020-02-23','YYYY-MM-DD'), 'checkup');
insert into Appointment values('100000005', TO_DATE('2020-02-23','YYYY-MM-DD'), 'followup');

-- insert into Doctor values('2000000001', 'Gregory House', 'ghouse@princet.com');
-- insert into Doctor values('2000000002', 'Perry Cox', 'pcox@sacredh.com');
-- insert into Doctor values('2000000003', 'Meredith Grey', 'mgrey@seattleg.com');
-- insert into Doctor values('2000000004', 'Dr. Pepper', 'drpepper@fizzy.com');
-- insert into Doctor values('2000000005', 'Doogie Howser', 'dhowser@thedoog.com');
insert into Doctor values('2000000006', 'Elliot Reid', 'ereid@sacredh.com');
insert into Doctor values('2000000007', 'John Dorian', 'jd@sacredh.com');
insert into Doctor values('2000000008', 'Cristina Yang', 'cyang@seattleg.com');
insert into Doctor values('2000000009', 'Lisa Cuddy', 'lcuddles@princet.com');
insert into Doctor values('2000000010', 'John Zoidberg', 'jzoidberg@planex.com');

-- ReviewContent
insert into ReviewContent values('1001', '100000001',  '2000000007', 'this doctor was the greatest. He was so patient and loving i love him.', '5');
insert into ReviewContent values('1002', '100000002',  '2000000006', 'The devil, rude and doesnt know anything.', '1');
insert into ReviewContent values('1003', '100000003',  '2000000008', 'the worst. he lives at 123 cresc. ave. vancouver!', '1');
insert into ReviewContent values('1004', '100000004',  '2000000010', 'they ran out of candy in reception, what kind of clinic runs out of candy?! -3*', '2');
insert into ReviewContent values('1005', '100000005',  '2000000009', 'he will prescribe ANYTHING, he was so amazing! I got drugz4dayz. A++++', '5');

-- ReviewDetails

insert into ReviewDetails values('8000000001', '1001', '100000001', '2000000007', '10', TO_DATE('2020-03-03','YYYY-MM-DD'), '1');
insert into ReviewDetails values('8000000002', '1002', '100000002', '2000000006', '3', TO_DATE('2020-03-10','YYYY-MM-DD'), '1');
insert into ReviewDetails values('8000000003', '1003', '100000003', '2000000008', '2', TO_DATE('2020-03-27','YYYY-MM-DD'), '0');
insert into ReviewDetails values('8000000004', '1004', '100000004', '2000000010', '8', TO_DATE('2020-03-13','YYYY-MM-DD'), '1');
insert into ReviewDetails values('8000000005', '1005', '100000005', '2000000009', '13', TO_DATE('2020-03-16','YYYY-MM-DD'), '1');

-- -- FamilyDoctor
-- insert into FamilyDoctor values('2000000001', '1');
-- insert into FamilyDoctor values('2000000002', '0');
-- insert into FamilyDoctor values('2000000003', '1');
-- insert into FamilyDoctor values('2000000004', '0');
-- insert into FamilyDoctor values('2000000005', '1');

-- SpecialistDoctor
insert into SpecialistDoctor values('2000000006');
insert into SpecialistDoctor values('2000000007');
insert into SpecialistDoctor values('2000000008');
insert into SpecialistDoctor values('2000000009');
insert into SpecialistDoctor values('2000000010');

-- Specialization
insert into Specialization values('Pediatric', 'Oncology');
insert into Specialization values('Pediatric', 'Genetics');
insert into Specialization values('Psychiatry', 'Learning');
insert into Specialization values('Dermatology', 'Immunodermatology');
insert into Specialization values('Cardiology', 'Nuclear');
insert into Specialization values('Otolaryngologist', 'Cosmetic');

-- SpecializesIn
insert into SpecializesIn values('2000000006', 'Pediatric', 'Oncology');
insert into SpecializesIn values('2000000007', 'Otolaryngologist', 'Cosmetic');
insert into SpecializesIn values('2000000008', 'Psychiatry', 'Learning');
insert into SpecializesIn values('2000000009', 'Dermatology', 'Immunodermatology');
insert into SpecializesIn values('2000000010', 'Cardiology', 'Nuclear');

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
insert into Hospital values('300000004', 'Doogie’s House', '3920 Faek Cresc', 'R4C 0S1');
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
insert into IsEmployedAt values('300000001', 'Psychiatry', '2000000008');

-- insert into IsEmployedAt values('300000002', 'Pediatry', '2000000002');
insert into IsEmployedAt values('300000004', 'Pediatry', '2000000006');	
insert into IsEmployedAt values('300000002', 'ENT', '2000000007');

-- insert into IsEmployedAt values('300000003', 'Dermatology', '2000000001');	
insert into IsEmployedAt values('300000003', 'Dermatology', '2000000009');

-- insert into IsEmployedAt values('300000004', 'Dermatology', '2000000005');

-- insert into IsEmployedAt values('300000005', 'Cardiology', '2000000004');
insert into IsEmployedAt values('300000005', 'Cardiology', '2000000010');
-- Admin
insert into Admin values('00001', 'alee', '123456');	
insert into Admin values('00002', 'ali', '234567');
insert into Admin values('00003', 'rnguyen', '345678');
-- insert into Admin values('00004', 'ksanson', '456789');
-- insert into Admin values('00005', 'bsanders', '567890');

--monitors
insert into Monitors values('00001', '8000000003');
insert into Monitors values('00001', '8000000004');
insert into Monitors values('00002', '8000000001');
insert into Monitors values('00003', '8000000002');
insert into Monitors values('00003', '8000000005');