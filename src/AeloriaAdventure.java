import java.util.*;

public class AeloriaAdventure {
    private static int honor = 0;
    private static int reputation = 0;
    private static int karma = 0;
    private static List<String> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    static void showTitle() {
        System.out.println(" ▄▄▄      ▓█████  ██▓     ▒█████   ██▀███   ██▓ ▄▄▄      \n" +
                           "▒████▄    ▓█   ▀ ▓██▒    ▒██▒  ██▒▓██ ▒ ██▒▓██▒▒████▄    \n" +
                           "▒██  ▀█▄  ▒███   ▒██░    ▒██░  ██▒▓██ ░▄█ ▒▒██▒▒██  ▀█▄  \n" +
                           "░██▄▄▄▄██ ▒▓█  ▄ ▒██░    ▒██   ██░▒██▀▀█▄  ░██░░██▄▄▄▄██ \n" +
                           " ▓█   ▓██▒░▒████▒░██████▒░ ████▓▒░░██▓ ▒██▒░██░ ▓█   ▓██▒\n" +
                           " ▒▒   ▓▒█░░░ ▒░ ░░ ▒░▓  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░░▓   ▒▒   ▓▒█░\n" +
                           "  ▒   ▒▒ ░ ░ ░  ░░ ░ ▒  ░  ░ ▒ ▒░   ░▒ ░ ▒░ ▒ ░  ▒   ▒▒ ░\n" +
                           "  ░   ▒      ░     ░ ░   ░ ░ ░ ▒    ░░   ░  ▒ ░  ░   ▒   \n" +
                           "      ░  ░   ░  ░    ░  ░    ░ ░     ░      ░        ░  ░");
    }



    public static void main(String[] args) {
        showTitle();
        System.out.println("Üdvözöllek Aeloria világában!");
        System.out.println("Egykor békés világ volt ez, tele csodákkal és misztériummal. Azonban Zerath, az istenek királya eltűnt, és a világot káosz lepte el. Te vagy az utolsó reménysugár, aki képes lehet helyreállítani az egyensúlyt.");
        System.out.println("A te történeted most kezdődik...\n");

        startAdventure();
    }

    private static void startAdventure() {
        System.out.println("Egy titokzatos erdő közepén ébredsz, emlékeid homályosak...");
        displayStatus();
        System.out.println("1. Felfedezed az erdőt\n2. Elindulsz a távolban látható falu felé");

        int choice = getPlayerChoice(2);
        if (choice == 1) {
            exploreForest();
        } else {
            goToVillage();
        }
    }

    private static void exploreForest() {
        System.out.println("Az erdő mélyén egy rejtélyes ládát találsz. Kinyitod? (igen/nem)");
        displayStatus();
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("igen")) {
            System.out.println("A ládában egy varázskardot találsz, ami növeli a becsületedet!");
            inventory.add("Varázskard");
            honor += 10;
        } else {
            System.out.println("Tovább mész, de kihagytál egy lehetőséget...");
        }
        encounterWolf();
    }

    private static void goToVillage() {
        System.out.println("A faluba érve egy idős asszony megkér, hogy segíts neki vízért menni.");
        displayStatus();
        System.out.println("1. Segítesz neki\n2. Figyelmen kívül hagyod és tovább mész");

        int choice = getPlayerChoice(2);
        if (choice == 1) {
            System.out.println("Az asszony hálából megáld téged, karma növekszik!");
            karma += 10;
        } else {
            System.out.println("A falusiak szúrós tekintettel néznek rád, hírneved csökken.");
            reputation -= 5;
        }
        villageMarket();
    }

    private static void encounterWolf() {
        System.out.println("Egy farkas támad rád! Küzdesz vagy futsz? (küzdesz/futsz)");
        displayStatus();
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("küzdesz")) {
            if (inventory.contains("Varázskard")) {
                System.out.println("A varázskard segítségével legyőzöd a farkast, hírneved nő!");
                reputation += 10;
            } else {
                System.out.println("Fegyver nélkül küzdesz, de alig éled túl a harcot.");
                honor += 5;
            }
        } else {
            System.out.println("Elfutsz, de a becsületed csorbul, de a karmád feljebb javult.");
            honor -= 5;
            karma += 10;
        }
        mysteriousPrison();
    }

    private static void villageMarket() {
        System.out.println("A faluban egy titokzatos kereskedő varázstárgyakat árul.");
        displayStatus();
        System.out.println("1. Veszel egy mágikus gyűrűt (karma +10)\n2. Veszel egy gyógyitalt (élet +20)\n3. Nem vásárolsz semmit");

        int choice = getPlayerChoice(3);
        if (choice == 1) {
            System.out.println("A mágikus gyűrű felerősíti a karmádat.");
            inventory.add("Mágikus gyűrű");
            karma += 10;
        } else if (choice == 2) {
            System.out.println("A gyógyital megmenthet egy nehéz csatában.");
            inventory.add("Gyógyital");
        } else {
            System.out.println("Úgy döntesz, nem költesz a kereskedőnél.");
        }
        mysteriousPrison();
    }


    private static void mysteriousPrison() {
        System.out.println("Egy csapda ajtó alatt beszakad a föld, és egy sötét börtönben találod magad.");
        displayStatus();
        System.out.println("Az őr közli: \"Csak akkor engedlek ki, ha helyesen válaszolsz kettő találós kérdésre!\"");
        int correctAnswers = 0;

        correctAnswers += askQuestion("Mi az: reggel négy lábon jár, délben kettőn, este háromon?", "ember");
        correctAnswers += askQuestion("Mi az: mindig jön, de sosem érkezik meg?", "holnap");

        if (correctAnswers >= 2) {
            System.out.println("Az őr elismerően bólint és kienged a börtönből. Azonban lesben rádtámadnak ha ő legyőzöd akkor már ott leszel a győzelem kapujában.");
            swordBattle();
        } else {
            System.out.println("Az őr csalódottan néz rád, és nem enged el. Próbáld újra!");
            mysteriousPrison();
        }
    }

    private static void swordBattle() {
        System.out.println("Azonban egy  hatalmas szörny bukkan elő, és küzdelemre kényszerít téged!");
        displayStatus();
        System.out.println("Három vágási forma közül választhatsz:");
        System.out.println("1. Ferde vágás\n2. Függőleges vágás\n3. Oldalsó vágás");

        int correctMove = new Random().nextInt(3) + 1; // A helyes mozdulat generálása
        int playerChoice = getPlayerChoice(3);

        if (playerChoice == correctMove) {
            System.out.println("Sikerült eltalálnod a szörny gyenge pontját! Tovább mehetsz.");
            reputation += 10;
            reachTemple();
        } else {
            System.out.println("A támadás nem volt elég hatékony, és a szörny megölt téged.");
            System.out.println("A kaland véget ért. Próbáld újra!");
            swordBattle();
        }


    }


    private static void reachTemple() {
        System.out.println("Egy ősi templom előtt állsz. Az ajtó zárva van, csak akkor nyílik ki, ha helyesen válaszolsz 3 találós kérdésre.");
        displayStatus();
        int correctAnswers = 0;

        correctAnswers += askQuestion("Mi az, amit ha megtöltesz, könnyebb lesz?", "léggömb");
        correctAnswers += askQuestion("Mi az, ami addig van tele, amíg ki nem üríted?", "gödör");
        correctAnswers += askQuestion("Mi az, ami olyan könnyű, hogy mégis szinte senki sem tudja megtartani?", "levegő");


        if (correctAnswers >= 3) {
            System.out.println("Az ajtó kitárul, belépsz a templomba...");
            finalBoss();
        } else {
            System.out.println("Nem sikerült helyesen válaszolni, az ajtó zárva marad.");
        }
    }

    private static void finalBoss() {
        System.out.println("A templomban egy hatalmas démon vár rád, ez az utolsó próba!");
        displayStatus();
        System.out.println("1. Küzdesz a varázskarddal\n2. Használod a gyógyitalt a túlélésért\n3. Bízol az istenek segítségében");

        int choice = getPlayerChoice(3);
        if (choice == 1 && inventory.contains("Varázskard")) {
            System.out.println("A varázskard erejével legyőzöd a démont, hírneved az egekbe szökik!");
        } else if (choice == 2 && inventory.contains("Gyógyital")) {
            System.out.println("A gyógyital segítségével túléled a harcot és legyőzöd a démont!");
        } else if (karma >= 10) {
            System.out.println("A jó karmád miatt az istenek segítséget küldenek, és legyőzöd a démont!");
        } else if (honor >= 10) {
            System.out.println("A becsületed erőt ad, és győzöl a démon felett!");
        } else if (reputation >= 10) {
            System.out.println("A hírneved vonzza a szövetségeseket, akik segítenek legyőzni a démont!");
        } else {
            System.out.println("Nem voltál elég felkészült, a démon legyőz téged... Sajnos nem sikerült helyreállítanod a világ rendjét.");
        }
        System.out.println("A kaland véget ért! Aeloria világa megmenekült. Köszönjük, hogy játszottál.");
    }

    private static int askQuestion(String question, String correctAnswer) {
        System.out.println(question);
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Helyes válasz!");
            return 1;
        } else {
            System.out.println("Helytelen válasz.");
            return 0;
        }
    }

    private static int getPlayerChoice(int options) {
        int choice = -1;
        while (choice < 1 || choice > options) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Érvénytelen választás. Próbáld újra.");
            }
        }
        return choice;
    }

    private static void displayStatus() {
        System.out.println("=========================");
        System.out.println("Becsület: " + honor);
        System.out.println("Hírnév: " + reputation);
        System.out.println("Karma: " + karma);
        System.out.println("Tárgyak: " + (inventory.isEmpty() ? "Nincsenek tárgyak" : inventory));
        System.out.println("=========================");
    }
}