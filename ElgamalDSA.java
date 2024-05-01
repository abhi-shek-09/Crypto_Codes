public class ElgamalDSA {
    public static int modInverse(int A, int M){   
        int X;
        for (X = 1; X < M; X++)
            if (((A % M) * (X % M)) % M == 1)
                break;

        return X;
    }
    public static void main(String[] args) {
        int q = 19;
        int al = 10;
        int xa = 16;
        int ya = (int)(Math.pow(al, xa) % q);

        int m = 4;
        int hm = 14;

        int k = 5;
        int s1 = (int)(Math.pow(al, k)%q);
        System.out.println(modInverse(k, q-1));
        int ll = (hm-xa*s1);
        int s2;
        if(ll>0){
            s2 = (modInverse(k, q-1) * ll)%(q-1);
        }
        else{
            ll *= -1;
            ll = ll%(q-1);
            s2 = (modInverse(k, q-1) * ll)%(q-1);
            s2 = q-1-s2;
        } 
        System.out.println(s1 + " " + s2);



        int v1 = (int)(Math.pow(al, hm)%q);
        int v2 = ((int)(Math.pow(ya, s1)%q) * (int)(Math.pow(s1, s2)%q))%q;
        System.out.println(v1 + " " + v2);
    }
}
