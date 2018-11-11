/*************************************************************************
 *  Syfte: Programmet hanterar beräkningar av vilken växel som ska ges tillbaka
 *  till en kund givet en viss köpesumma och en viss betald summa. Systemet tillåter
 *  hantering av flera parallella valutasystem.
 *
 *  Programmet skrevs som en del i Systemvetenskapsutbildningen på Luleå Tekniska Högskola(LTU)
 *  inom kursen D0019N - Programutveckling med Java. Uppgift 1.1.
 *
 *  Författare: Carl Granström
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
 *  Saker att utforska 4: TIllåt negativa betalningar och/eller ångrande av betalningar i
 *  systemet. Skapligt enkelt att implementera.
 *
 *************************************************************************/

package stud.ltu.Assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Kassa {

    private int[] valorer;    //int-array som ska hålla valörerna för att skapa kassor med olika valörkombinationer
    private int antalVal;     //objektunikt värde med antalet valörer

    //Skapa en lista att lagra betalningarna i
    private List<Betalning> betList = new ArrayList<Betalning>();

    //Skapa ny Scanner som läser data från standard-input, oklart om det är en bra idé att deklararera den i klassen
    private Scanner input = new Scanner(System.in);

    /**
     * Konstruktorn tar en int-array med valörer som indata
     */
    Kassa(int[] array){ //konstruktor som tar in en array och sorterar den för att sedan lagra den i kassaobjektet
        // sortera valörerna i stigande ordning
        valorer = array;                //lagra input-array i kassa-objektet
        Arrays.sort(valorer);           //sortera arrayen från lo->hi
        this.antalVal = array.length;   //räkna ut antalet valörer i den nya kassan och lagra detta(för indexering)
    }

    public void regBetal(int betalning, int kostnad){  //Skapa en ny betalning och lagra i kassan
        Betalning b = new Betalning();  //skapa den nya betalningen
        b.setBetalat(betalning);        //lagra inbetalad summa i betalningen
        b.setKostnad(kostnad);          //lagra kostnaden för köpet i betalningen
        b.beraknaVaxel();               //skapa och lagra valör-array
        betList.add(b);                 //lagra betalnings-objektet i kassans Betalning-lista
    }

    //Efterfråga information om betalningen
    private int betalInput(){
        System.out.println("Vilken summa har erhållits som betalning?");
        int betalning = input.nextInt();

        if (betalning > 0){     //tänker mig att det kanske finns en bättre lösning på det här
            return betalning;
        } else {
            System.out.println("Betalningen måste överstiga 0, vänligen försök igen!");
        }
        return this.betalInput();  //oklart om det här är så smart...
    }

    //Efterfråga information om kostnaden för köpet
    private int kostnadInput(){
        System.out.println("Totalkostnad för köpet?");
        int kostnad = input.nextInt();

        if (kostnad > 0){
            return kostnad;
        } else {
            System.out.println("Vi ger väl inte bort våra varor? Vänligen försök igen");
        }

        return this.kostnadInput();
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
            vaxelAntal = new int[antalVal];
        }

        public void setBetalat(int betalat){
            if (betalat == 0){
                throw new IllegalArgumentException("Man måste betala mer än kostnaden för att få växel!");
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
         * Metod för att beräkna antal av varje valör som ska återbetalas som växel.
         */
        private void beraknaVaxel(){

            int summa = beraknaVaxelSumma();

            ////Beräkna antal av varje valör som ska betalas tillbaka som växel lagra i array[lo->hi]
            for (int i = antalVal; i > 0; i--){  //iterera igenom valörarrayen bakifrån då största valören står sist
                this.vaxelAntal[i - 1] = summa / valorer[i - 1]; //räkna ut hur många av valören som ska lagras
                summa = summa % valorer[i - 1]; //räkna ut resten(remainder) och sätt summan till den
            }

            /**
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

            //ge felmeddelande om hela kostnaden ej är betalad
            if (this.getBetalat() < this.getKostnad()) {
                throw new IllegalArgumentException("Hela beloppet ej betalat!"); //Test av exceptions
            }

            return (this.getBetalat() - this.getKostnad());  //beräkna och returnera återbetalningssumman
        }

        //Skriv ut lämplig data från Betalnings-objektet
        private void print(){
            System.out.println("Betalat: " + betalat);
            System.out.println("Kostnad: " + kostnad);
            System.out.println("Växel totalt: " + beraknaVaxelSumma());
            printVal();
            System.out.println("Växel som ej kan betalas ut: " + kvarSumma);
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

    public static void main(String[] args) {

        int[] a = {20, 1000, 1, 500, 50, 2, 200, 100}; //Test med osorterad array
        //Skapa ny Kassa med valöruppsättningen från array a
        Kassa k1 = new Kassa(a);
        //Efterfråga summa för kostnaden
        int kostnad = k1.kostnadInput();
        //Efterfråga summa för betalningen
        int betalning = k1.betalInput();
        //Registrera ny betalning på kassa k1
        k1.regBetal(betalning, kostnad);
        //Hämta betalningen ur Kassans lagrade betalningar, bör eventuellt skötas med en Stack istället, men orka.
        Betalning betal = k1.betList.get(0);
        //åberopa Betalning-objektets print-metod för att skriva ut all information
        betal.print();
    }

}