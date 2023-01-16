Relea Florin Andrei 324CA

Pachetul input contine clasele necesare pentru citirea din fisierele Json

In pachetul pages:

- clasa page  este o clasa abstracta care defineste comportamentul unei pagini,
iar metodele: changeStatus actualizeaza informatile unei pagini, actionOnPage care executa
actiunea de pe o pagina, checkMoveOn verifica daca se poate muta pe pagina aceea
- clasele Login, Movies, Register, HomepageAutentificated, HomapageUnautentificated,
seeDetails, Upgrades reprezinta efectiv paginile in care poate naviga programul
cu actiunile lor
- clasa Monitor monitorizeaza activitatea pagini : userul curent, lista de filme, activitatile
care s-au realizat prin boolean autentificated, moviePage care sunt actualizate prin metoda
changeStatus a fiecarei pagini
- pageFactory care genereaza o noua pagina

In pachetul utils:

- Database reprezinta baza de date care retine useri inregistrati si lista de
filme disponibile
- Movie si User clase care descriu un film/user
- OutputPrinter clasa care se ocupa de afisare. In functie de caz printMessage afiseaza 
fie un mesaj de eroare fie al unei actiuni realizate cu succes, fie recomandarile.
- Notification clasa care descrie o notificare
- Subscriber - clasa abstract folosita pentru Observer Pattern
- Invoker executa comenzile si retine o lista a acestora
- Helper genereaza comenzile si le trimite la Invoker, afiseaza daca e cazul o recomandare de film

In pachetul actions:
- interfata Action descrie functionalitatea generala a unei comenzi
- ActionFactory genereaza o actiune specifica

Design Patterns folositi:
- Singleton pattern pentru Monitor si Database
- Factory pentru Page si Action
- Observer pentru a realiza notificarea de ADD :
   -- subiectul este baza de date, care retine printr-un hashmap subscreberi pentru fiecare gen
   -- useri sunt observatorii care sunt notificati prin metoda addMovie din Database
-CommandPattern pentru implementarea comenzilor de changePage, OnPage, database si back si retinerea
lor intr-o lista a comenzilor efectuate