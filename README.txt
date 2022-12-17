Relea Florin Andrei 324CA

Pachetul input contine clasele necesare pentru citirea din fisierele Json

In pachetul pages:

- clasa page  este o clasa abstracta care defineste comportamentul unei pagini,
iar metoda changeStatus actualizeaza informatile unei pagini
- clasele Login, Movies, Register, HomepageAutentificated, HomapageUnautentificated,
seeDetails, Upgrades reprezinta efectiv paginile in care poate naviga programul
cu actiunile lor
- clasa Monitor monitorizeaza activitatea pagini : userul curent, lista de filme, activitatile
care s-au realizat prin boolean autentificated, moviePage, seeDetailsMovie, login, register,
upgradePage care sunt actualizate prin metoda changeStatus a fiecarei pagini

In pachetul utils:

- Database reprezinta baza de date care retine useri inregistrati si lista de
filme disponibile
- Movie si User clase care descriu un film/user
- Helper clasa care realizeaza activitatile de changePage sau onPage
- OutputPrinter clasa care se ocupa de afisare. In functie de caz printMessage afiseaza 
fie un mesaj de eroare fie al unei actiuni realizate cu succes.
