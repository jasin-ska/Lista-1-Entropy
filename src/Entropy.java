import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Entropy {
    public static void main(String[] args) {
        String path = args[0];
        if(args.length != 1) {
            System.out.println("Usage: java Entropy <path>");
            return;
        }

        try {
            byte[] byteArray = Files.readAllBytes(Paths.get(path));

            Map<BytePair, Integer> bytePairsCount = new HashMap<>(); // counting byte pairs
            Map<Byte, Integer> bytesCount = new HashMap<>(); // counting bytes
            byte prev = 0;
            for(byte b : byteArray) {
                if(!bytesCount.containsKey(b)) {
                    bytesCount.put(b, 1);
                }
                else {
                    bytesCount.put(b, bytesCount.get(b) + 1);
                }
                if(!bytePairsCount.containsKey(new BytePair(b, prev))) {
                    bytePairsCount.put(new BytePair(b, prev), 1);
                }
                else {
                    bytePairsCount.put(new BytePair(b, prev), bytePairsCount.get(new BytePair(b, prev))+1);
                }
                prev = b;
            }

            float HYX = 0;
            for(BytePair bp : bytePairsCount.keySet()) {
                float pxy = (float)bytePairsCount.get(bp)/byteArray.length;
                float px = (float)bytesCount.get(bp.getCurrByte())/byteArray.length;
                HYX -= pxy*(Math.log(pxy/px)/Math.log(2));
            }

            float HX = 0;
            for(byte b : bytesCount.keySet()) {
                float pb = (float)bytesCount.get(b)/byteArray.length;
                HX-=pb*(Math.log(pb)/Math.log(2));
            }

            System.out.println("H(X) = " + HX); // entropy
            System.out.println("H(Y|X) = " + HYX); // conditional entropy


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
