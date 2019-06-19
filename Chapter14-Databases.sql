--List of tenants who are renting more than one apartment.

SELECT TenantName
FROM Tenants 
INNER JOIN 
    ( SELECT TenantID 
    FROM AptTenants
    GROUP BY TenantID
    HAVING count(*) > 1) C
ON Tenants.TenantID = C.TenantID

-------------------

--List of all buildings and the number of open requests.
--(Requests with status = "Open")

SELECT Buildings.*, ISNULL(Count, 0) as 'Count'
FROM Buildings
LEFT JOIN
    ( SELECT Apartments.BuildingID, count(*) as 'Count'
      FROM Requests INNER JOIN Apartments
      ON Requests.AptID = Apartments.AptID
      WHERE Requests.Status = 'Open'
      GROUP BY Apartments.BuildingID) ReqCounts
ON ReqCounts.BuildingID = Buildings.BuildingID

-------------------

--Query that sets all requests for all apartments in building #11 to 'closed'

UPDATE Requests
SET Status = 'Closed'
WHERE AptID = (SELECT AptID FROM Apartments WHERE BuildingID = 11)

--------------------

--Different types of joins.
--Explain how they differ.
--Why certain types are better in certain conditions.

JOIN --Combines results of two tables with at least one field that can be matched.

INNER JOIN --Results of only data where the criteria matches.
--OUTER JOIN: Contains inner join data + can also contain data not matching in the other table.
	LEFT OUTER JOIN / LEFT JOIN -- Contains all record from the left table. If not in right table, will return NULL for that data.
	RIGHT OUTER JOIN / RIGHT JOIN -- Contains all record from the right table. If not in left table, will return NULL for that data.
	FULL OUTER JOIN --Combination of both left and right outer join results.

--------------------

--What is denormalization?
--Pros & Cons

--Normalization: Stores data only once in the database even if its used in multiple tables. Primary key/Foreign key is used and references are made.
--Denormalization: Duplicates of the same data is used and stored on those tables that use the fields.

--Pros of Denormalization: 
  --Faster processing time, does not require expensive JOINs.
  --Retrieval queries are usually simpler (uses less tables)
--Cons of Denormalization:
  --Takes up data space since duplicates are stored.
  --Updates and inserts are more expensive (need to do multiples of them)

-------------------

--Draw an ER diagram for a db with companies, people, and professionals (people who work for companies).

COMPANY  		<WorksAt>		PROFESSIONALS 	______Is_____> 	PERSON
CompanyID		1..*			Degree							Name
CompanyName		DateOfJoining	Experience						Gender
Address			Salary											ID
																DOB
																Phone
																Address

-------------------

--Imagine a simple db storing info for students' grades.
--Design what the db might look like
--Provide a SQL query to return a list of the honor roll students (top 10%, sorted by their GPA)

STUDENTS
Name
StudentID

COURSE
CourseName
CourseID

COURSEENROLLMENT
StudentID
CourseID
Grade

--Microsoft SQL Server--
DECLARE @GPACutoff float;
SET @GPACutoff = (SELECT min(GPA) as 'GPAMin' FROM(
	 SELECT TOP 10 PERCENT AVG(CourseEnrollment.Grade) AS GPA
	 FROM CourseEnrollment
	 GROUP BY CourseEnrollment.StudentID
	 ORDXER BY GPA desc) Grades);

SELECT Name, GPA
FROM Students
WHERE (SELECT AVG(CourseEnrollment.Grade) AS GPA, CourseEnrollment.StudentID
	   FROM CourseEnrollment
	   GROUP BY CourseEnrollment.StudentID
	   HAVING AVG(CourseEnrollment.Grade) >= @GPACutoff) TopTenStudents
INNER JOIN Students ON TopTenStudents.StudentaID = Student.StudentID




















