CREATE DATABASE IF NOT EXISTS patient DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE patient;

CREATE TABLE tbl_appointment (
  appointment_id int(10) NOT NULL,
  patient_id int(10) NOT NULL,
  dr_id int(10) NOT NULL,
  schedule_date date NOT NULL,
  schedule_shift varchar(20) NOT NULL
);


CREATE TABLE tbl_billing (
  billing_id int(10) NOT NULL,
  appointment_id int(10) NOT NULL,
  amount float(10,2) NOT NULL,
  payment_mode varchar(10) NOT NULL
);


CREATE TABLE tbl_checkup (
  checkup_id int(10) NOT NULL,
  appointment_id int(10) NOT NULL,
  diagnosis varchar(50) NOT NULL,
  blood_pressure float(5,2) NOT NULL,
  weight float(5,2) NOT NULL
);


CREATE TABLE tbl_doctor (
  dr_id int(10) NOT NULL,
  dr_name varchar(30) NOT NULL,
  dr_charge float(10,2) NOT NULL
);



CREATE TABLE tbl_patient (
  patient_id int(10) NOT NULL,
  patient_name varchar(30) NOT NULL,
  patient_dob date DEFAULT NULL,
  patient_gender varchar(10) DEFAULT NULL,
  patient_address varchar(30) DEFAULT NULL,
  patient_email varchar(30) NOT NULL,
  patient_password varchar(30) NOT NULL
);


ALTER TABLE tbl_appointment
  ADD PRIMARY KEY (appointment_id);

ALTER TABLE tbl_billing
  ADD PRIMARY KEY (billing_id);

ALTER TABLE tbl_checkup
  ADD PRIMARY KEY (checkup_id);


ALTER TABLE tbl_doctor
  ADD PRIMARY KEY (dr_id);


ALTER TABLE tbl_patient
  ADD PRIMARY KEY (patient_id);
COMMIT;
