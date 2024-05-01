public class El{
    public static int inverse(int a, int m){
        int i;
        for(i=1; i<m; i++){
            if(((a%m) * (i%m))%m == 1) break;
        }
        return i;
    }
    public static void main(String[] args){
        int q = 17;
        int al = 11;
        int xa = 6;
        int ya = (int)(Math.pow(al, xa)%q);
        
        int m = 7;
        int k = 5;

        int c1 = (int)(Math.pow(al, k)%q);
        int K = (int)(Math.pow(ya, k)%q);

        int c2 = (K * m)%q;

        int K_d = (int)(Math.pow(c1, xa)%q);
        int dec = (inverse(K_d, q) * c2)%q;
        System.out.println(m + " " + c1 + " " + c2 + " " + dec);
    }
}