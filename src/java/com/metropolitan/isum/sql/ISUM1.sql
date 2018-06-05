DROP TABLE IF EXISTS `student`;
CREATE TABLE student (  
          broj_indexa int (10) PRIMARY KEY,  
          ime varchar(30),  
          prezime varchar(30),  
          username varchar(10),  
          password varchar(10),  
          email varchar(30),  
		  skolska_godina varchar(30),
          smer varchar(15)
        
);  

DROP TABLE IF EXISTS `predavac`;
CREATE TABLE predavac (  
          id int (10) PRIMARY KEY,  
          ime varchar(30),  
          prezime varchar(30),  
          username varchar(10),  
          password varchar(10),  
          email varchar(30),  
		 
          predmet varchar(30)
        
);  

DROP TABLE IF EXISTS `predmet`; 
CREATE TABLE predmet (  
          sifra_predmeta int(10) PRIMARY KEY,  
         
          naziv_predmeta varchar(25) NOT NULL,
		  predavac varchar(30) NOT NULL,
		  
		   
		  ESPB INT(2) NOT NULL
);  
 
 
DROP TABLE IF EXISTS `predmetne_obaveze`; 
CREATE TABLE predmetne_obaveze (  
          sifra_predmeta int(10) PRIMARY KEY,  
         
          naziv_predmeta varchar(25) NOT NULL,
		  predavac varchar(30) NOT NULL,
		  
		   broj_poena_zadaci INT(2) NOT NULL,
	       broj_poena_testovi INT(2) NOT NULL,
		   broj_poena_projekat INT(2) NOT NULL
		   prisustvo int(2) NOT NULL,
		   ocena int (2) NOT NULL
);  
 