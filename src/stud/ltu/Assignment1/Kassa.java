package stud.ltu.Assignment1;
/*************************************************************************
 *  Syfte: Programmet hanterar beräkningar av vilken växel som ska ges tillbaka
 *  till en kund givet en viss köpesumma och en viss betald summa. Systemet tillåter
 *  hantering av flera parallella valutasystem.
 *
 *  Programmet skrevs som en del i Systemvetenskapsutbildningen på Luleå Tekniska Högskola(LTU)
 *  inom kursen D0019N - Programutveckling med Java. Uppgift 1.1.
 *
 *  @author Carl Granström
 *
 *  Datum: 2018-11-07
 *
 *  Saker att utforska 1: inte tillåta dubblerade valörer i Kassa, använd en collection
 *  som kan kolla efter dubletter snabbt. Annars kan vi modifiera den sorterade arrayen
 *  genom att iterera genom hela arrayen och kolla om värdet är == det föregående värdet.
 *
 *  Alternativt använda array.contains() i en loop. Ska fundera vad som är snabbast.
 *
 *  Å andra sidan skapar inte dubblerade valörer något vidare problem då dessa helt enkelt
 *  kommer hoppas över i beräkningarna då delningen av den kvarvarande summan gör att
 *  delningen över den dubblerade valören ger 0, och då hoppas valören också över i utskriften.
 *
 *  Saker att utforska 2: Använd en eller två stacks för att lagra betalningarna.
 *  Använd Stack.peek()? för att returnera den mest nyliga betalningen.
 *
 *  Saker att utforska 3: Förbättra felhanteringen med exception catching
 *
 *  Saker att utforska 4: Tillåt negativa betalningar och/eller ångrande av betalningar i
 *  systemet. Skapligt enkelt att implementera.
 *
 *************************************************************************/


import java.util.*;

public class Kassa {

    private int[] valorer;    //int-array som ska hålla valörerna för att skapa kassor med olika valörkombinationer
    private int antalVal;     //objektunikt värde med antalet valörer
    private List<Betalning> betList = new ArrayList<Betalning>(); //Skapa en lista att lagra betalningarna i

    //konstruktor som tar in en array och sorterar den för att sedan lagra den i kassaobjektet
    Kassa(int[] array){
        // sortera valörerna i stigande ordning
        valorer = array;                //lagra input-array i kassa-objektet
        Arrays.sort(valorer);           //sortera arrayen från lo->hi
        this.antalVal = array.length;   //räkna ut antalet valörer i den nya kassan och lagra detta(för indexering)
    }

    /**
     * Skapa en ny betalning och lagra i kassan
     */
    public void regBetal(){
        Betalning b = new Betalning();  //skapa den nya betalningen
        b.setBetalat(betalInput());     //lagra inbetalad summa i betalningen
        b.setKostnad(kostnadInput());   //lagra kostnaden för köpet i betalningen
        b.beraknaVaxel();               //skapa och lagra valör-array
        betList.add(b);                 //lagra betalnings-objektet i kassans Betalning-lista
    }

    //Efterfråga information om betalningen
    private int betalInput(){
        System.out.println("Vilken summa har erhållits som betalning?");
        int betalning = getLegalIntInput();
        return betalning;
    }

    //Efterfråga information om kostnaden för köpet
    private int kostnadInput(){
        System.out.println("Totalkostnad för köpet?");
        int kostnad = getLegalIntInput();
        return kostnad;
    }

    private int getLegalIntInput() {
        int sentinel = -1; //sentinel value
        while (sentinel <= 0) {
            try {
                Scanner input = new Scanner(System.in); //Skapa ny Scanner som läser data från standard-input
                sentinel = input.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println(e);
            }
            if (sentinel <= 0) {
                System.out.println("Vänligen ange ett positivt heltal: ");
                }
        }
        return sentinel;
    }

    /**
     * En privat klass som kan registrera flera betalningsobjekt på en viss kassa.
     * */
    private class Betalning{
        private int betalat;
        private int kostnad;
        private int[] vaxelAntal;
        /*Om det inte finns någon 1-valuta är det inte säkert att rätt växel kan batalas ut,
         denna överblivna växel lagras i kvarSumma*/
        private int kvarSumma;

        Betalning(){
            betalat = 0;
            kostnad = 0;
            vaxelAntal = new int[antalVal];
        }

        //Detta är bara ett test av throw-funktionen, egentligen bättre att hantera felet istället för att avsluta
        public void setBetalat(int betalat){
            if (betalat == 0){
                throw new IllegalArgumentException("Man måste betala för att få växel!");
            } else if (betalat < 0) {
                throw new IllegalArgumentException("Vi hanterar inte i-o-u:s här");
            } else {
                this.betalat = betalat;
            }
        }

        private int getBetalat(){
            return this.betalat;
        }

        public void setKostnad(int kostnad){
            this.kostnad = kostnad;
        }

        private int getKostnad(){
            return this.kostnad;
        }

        /**
         * Metod f&ouml;r att ber&auml;kna antal av varje val&ouml;r som ska &aring;terbetalas som v&auml;xel.
         */
        private void beraknaVaxel(){
            int summa = beraknaVaxelSumma();
            if (summa >= 0) {
                //Beräkna antal av varje valör som ska betalas tillbaka som växel lagra i array[lo->hi]
                for (int i = antalVal; i > 0; i--) {  //iterera igenom valörarrayen bakifrån då största valören står sist
                    this.vaxelAntal[i - 1] = summa / valorer[i - 1]; //räkna ut hur många av valören som ska lagras
                    summa = summa % valorer[i - 1]; //räkna ut resten(remainder) och sätt summan till den
                }
            }
            /*
             * Om inga 1-valörer finns kan det vara växel som inte kan betalas ut,
             * vi lagrar detta i betalningens variabel kvarSumma
             */
            this.kvarSumma = summa;
        }

        /**
         * Metod för att beräkna summan som ska återbetalas som växel.
         * Ger felmeddelande om inte hela summan betalats.
         */
        private int beraknaVaxelSumma(){
            return (this.getBetalat() - this.getKostnad());  //beräkna och returnera återbetalningssumman
        }

        //Skriv ut lämplig data från Betalnings-objektet
        private void print(){
            System.out.println("Betalat: " + betalat);
            System.out.println("Kostnad: " + kostnad);
            System.out.println("Växel totalt: " + beraknaVaxelSumma());
            if (kvarSumma >= 0) {
                printVal();
                System.out.println("Växel som ej kan betalas ut: " + kvarSumma);
            } else {
                System.out.println("Betalningen är för liten, det återstår att betala: " + Math.abs(kvarSumma));
            }
        }

        //Skriv ut valörerna och antal av varje valör
        private void printVal(){
            int valNum = 0;
            System.out.println("Ge tillbaka växel:");
            for (int i : vaxelAntal){
                //Visa endast de valörer som ska betalas ut
                if (i > 0) {
                    System.out.println("Valör " + valorer[valNum] + " x " + i);
                }
                valNum++;
            }
        }
    }

    /**
     * Entry point. Observera att två olika kassor med olika valörer skapas och testas, vilket kan får programmet
     * att upplevas lite rörigt. Med bara en kassa blir det snyggare. Ta gärna bort och testa med en kassa.
     */
    public static void main(String[] args) {

        int[] a = {20, 1000, 1, 500, 50, 2, 200, 100}; //Test med osorterad array
        int[] b = {25, 2300, 4, 500, 50, 200, 105}; //Test med osorterad array och konstiga valörer
        //Skapa nya Kassor med valöruppsättningen från array a och b, här anropas Kassa
        Kassa k1 = new Kassa(a);
        Kassa k2 = new Kassa(b);
        //Registrera ny betalning på kassa k1 och k2, det är här själva logiken för Betalning anropas
        k1.regBetal();
        k2.regBetal();
        //Hämta betalningarna ur Kassornas respektive lagrade betalningar
        Betalning betal = k1.betList.get(0);
        Betalning betal2 = k2.betList.get(0);
        //åberopa Betalning-objektens print-metod för att skriva ut all information
        betal.print();
        betal2.print();
    }

}