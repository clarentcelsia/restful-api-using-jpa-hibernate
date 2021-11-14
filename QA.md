Q&A

1. Kenapa jika menggunakan Jpa tidak melakukan scanning component? 
2. Apa itu JSON Header vs JSON Body?
   A: Header in JSON, there are 2 commonly are Authorization and content-type:application/json.
      This header define the type of your web is.

Testing (Sanity, Smoke, Regression)
https://www.linkedin.com/pulse/sanity-smoke-regression-testing-zepri-togatorop?trk=portfolio_article-card_title

INFO:
-
- Restful API Using JPA (DAO), Hibernate (ORM), PostgreSQL (DB), Spring Framework
- Bagian testing menggunakan JUnit5 
- Proses testing dengan memulai server menggunakan random port.
- Proses testing seharusnya menggunakan mock database (database in memory only [e.g h2] ) bukan database asli yg disave di harddrive langsung. 
https://stackoverflow.com/questions/35707469/how-can-i-mock-db-connection-in-spring-boot-for-testing-purpose.

p.s. Lihat 'Access Database and Web Server (API) Using JDBC Template'

More deets: @Query and @Modifying https://www.baeldung.com/spring-data-jpa-modifying-annotation
