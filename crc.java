import java.io.*;
class crc{
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] data;
        int[] div;
        int[] divisor;
        int[] rem;
        int[] crc;
        int data_bits, divisor_bits, tot_length;
        System.out.print("Enter number of data bits : ");
        data_bits = Integer.parseInt(br.readLine());
        data = new int[data_bits];
        System.out.println("Enter data bits (space separated): ");
        String[] dataInput = br.readLine().trim().split("\\s+");
        for (int i = 0; i < data_bits; i++) {
            data[i] = Integer.parseInt(dataInput[i]);
        }
        divisor_bits = 17;
        divisor = new int[]{1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};
        tot_length = data_bits + divisor_bits - 1;
        div = new int[tot_length];
        rem = new int[tot_length];
        crc = new int[tot_length];
        /* CRC GENERATION */
        for (int i = 0; i < data.length; i++)
            div[i] = data[i];
        System.out.print("Dividend (after appending 0's): ");
        for (int i = 0; i < div.length; i++)
            System.out.print(div[i]);
        System.out.println();
        for (int j = 0; j < div.length; j++) {
            rem[j] = div[j];
        }
        rem = divide(divisor, rem);
        for (int i = 0; i < div.length; i++) // append dividend and remainder
        {
            crc[i] = (div[i] ^ rem[i]);
        }
        System.out.println();
        System.out.println("CRC code : ");
        for (int i = 0; i < crc.length; i++)
            System.out.print(crc[i]);
        /* ERROR DETECTION */
        System.out.println();
        System.out.println("Enter CRC code of " + tot_length + " bits (space separated): ");
        String[] crcInput = br.readLine().trim().split("\\s+");
        for (int i = 0; i < crc.length; i++) {
            crc[i] = Integer.parseInt(crcInput[i]);
        }
        for (int j = 0; j < crc.length; j++) {
            rem[j] = crc[j];
        }
        rem = divide(divisor, rem);
        boolean error = false;
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                System.out.println("Error detected!");
                error = true;
                break;
            }
        }
        if (!error) System.out.println("No Error detected.");
        System.out.println("THANK YOU :)");
    }
    static int[] divide(int divisor[], int rem[]) {
        int cur = 0;
        while (true) {
            for (int i = 0; i < divisor.length; i++)
                rem[cur + i] = (rem[cur + i] ^ divisor[i]);
            while (cur < rem.length && rem[cur] == 0 && cur != rem.length - 1)
                cur++;
            if ((rem.length - cur) < divisor.length)
                break;
        }
        return rem;
    }
}
