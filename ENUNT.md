Creati:

Tabela **persoane**:
- cnp - primary key
- nume
- varsta

Clasa **Persoana**
- nume 
- varsta
- cnp 
- constructor cu cei 3 membri
- getteri 
- toString?
	
Clasa care actioneaza intre db si Persoana
- metoda care face conexiunea la db si o salveaza in variabila statica
- metoda care insereaza un row in db (INSERT)
- metoda care updateaza un row in db (UPDATE)
- metoda care sterge un row in db (DELETE)
- metoda care extrage toate rows din db (SELECT)
- metoda care afiseaza rows din db 
- pentru a va ajuta in test, puteti crea si o metoda selectPersoana, care cauta o persoana dupa cnp

Clasa de test
- deschide o sg conexiune la db
- cate un test pt fiecare operatiune CRUD
- inchide conexiune
