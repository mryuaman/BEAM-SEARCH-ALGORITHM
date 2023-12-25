package beam;

import java.util.*;

public class beam {

	//BEAMSEARCH ADINDA BİR METOT OLUŞTURDUK.BU METOT BEAM SEARCH ALGORİTMAMIZI TEMSİL EDİYOR.
	//BEAMWIDTH VE MAXLENGTH İSMİNDE İKİ PARAMETRE VAR. BU METOT BAŞLANGIÇTA BOŞ DURUMDA. BEAMNODE İLE DE ÖNCELİK KUYRUĞU OLUŞTURUYOR.
	//DAHA SONRA KUYRUKTAN ÇEKEREK SIRALAMA OLUŞTURUYOR.
	
    public static List<String> beamSearch(int beamWidth, int maxLength) {
        List<String> results = new ArrayList<>();
        PriorityQueue<BeamNode> beam = new PriorityQueue<>(Comparator.comparingDouble(a -> -a.score));
        beam.offer(new BeamNode("", 1.0));

        while (!beam.isEmpty() && results.size() < beamWidth) {
            BeamNode current = beam.poll();
            if (current.sequence.length() >= maxLength) {
                results.add(current.sequence);
            } else {
                List<BeamNode> nextNodes = generateNextNodes(current);
                for (BeamNode node : nextNodes) {
                    beam.offer(node);
                }
            }
        }
        return results;
    }
//GENERATENEXTNODES ADINDA BİR BAŞKA METOT VAR. BU METOT, VERİLEN BİR BEAMNODE ÜZERİNDEN YENİ DURUMLARI OLUŞTUYOR.
//RASTGELE KELİME DİZİSİ OLAN POSSİBLEWORDS KULLANILARAK HER BİR KELİME İÇİN RASTGELE BİR SKOR HESAPLANIYOR VE YENİ BİR BEAMNODE 
//OLUŞTURULMASI SAĞLANIYOR.

    public static List<BeamNode> generateNextNodes(BeamNode current) {
        List<BeamNode> nextNodes = new ArrayList<>();
        Random random = new Random();
        String[] possibleWords = {"mandalina", "karpuz", "armut", "elma", "portakal", "kiraz"};
        for (String word : possibleWords) {
            double score = random.nextDouble();
            BeamNode newNode = new BeamNode(current.sequence + " " + word, current.score * score);
            nextNodes.add(newNode);
        }
        return nextNodes;
    }
//BEAMNODE ADINDA BİR İÇ İÇE SINIF BULUNUYOR. BU SINIF, HER BİR DURUMU VE O DURUMUN BİR SKORUNU İÇERİYOR. 
//SEQUENCE KELİME DİZİSİNİ, SCORE İSE O DURUMUN SKORUNU TEMSİL EDECEK.

    static class BeamNode {
        String sequence;
        double score;

        BeamNode(String sequence, double score) {
            this.sequence = sequence;
            this.score = score;
        }
    }
//EN SON KISIMDA MAİN METODU BULUNUYOR. BU METOT, BAŞLANGIÇ PARAMETRELERİNİ (BEAMWİDTH VE MAXLENGTH) BELİRLER,
//BEAMSEARCH METODUYLA SONUÇLARI ALIR VE EKRANA YAZDIRIR.
    
    public static void main(String[] args) {
        int beamWidth = 5;
        int maxLength = 3;
        List<String> results = beamSearch(beamWidth, maxLength);
        System.out.println("Beam Search Sonuçları:");
        for (String result : results) {
            System.out.println(result);
        }
    }
}
