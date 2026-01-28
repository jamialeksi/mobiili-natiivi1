Viikko 1 lisäys
Toteutin tässä projektissa yksinkertaisen tehtävälistasovelluksen androidille. Sovelluksen perustana toimii Task, data class, joka kuvaa yhtä tehtävää ja sisältää perusasiat kuten otsikon, kuvauksen, eräpäivän ja tiedon siitä, onko tehtävä valmis vai ei. Mukana on myös mockTasks lista, jossa on viisi esimerkkitehtävää, jotta sovellus näyttää sisältöä heti käynnistyessään.
Domain-puolelle toteutin neljä Kotliniin perustuvaa funktiota: addTask, toggleDone, filterByDone ja sortByDueDate. Ne käsittelevät tehtävälistaa eri tavoilla, kuten lisäävät uuden tehtävän, vaihtavat valmiustilaa, suodattavat tehtäviä ja järjestävät ne eräpäivän mukaan.
Käyttöliittymä on rakennettu HomeScreen näkymään Composen peruskomponenteilla, kuten Column, Row, Text ja Modifier. Näkymä näyttää mock datasta muodostuvan tehtävälistan sekä napit, joilla käyttäjä voi suodattaa listaa tai järjestää sen eräpäivän mukaan.
Sovelluksen kaikki perustoiminnot toimivat.
Sovelluksen voi ajaa Android Studiossa emulaattorilla tai vaihtoehtoisesti voit asentaa sovelluksen myös APK tiedostosta, joka löytyy projektin releases osiosta.

Viikon 2 lisäys
Compose on tilanhallintaa jossa ui päivittyy automaattisesti tilan muuttuessa. Tässä toteutuksessa tehtävälistaa hallitaan viewmodelissa mutableStateOf muuttujalla, jolloin ui reagoi muutoksiin ilman erillisiä kutsuja. Remember sopii paremmin pieniin hetkellisiin muutoksiin, kun taas viewmodel säilyttää datan vaikka sivu muuttuisi.
