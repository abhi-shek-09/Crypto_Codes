public class DSS {
    public static int inverse(int a, int m){
        int x;
        for(x =1; x<m; x++){
            if(((a%m)*(x%m))%m == 1) break;
        }
        return x;
    }
    public static void main(String[] args) {
        int p = 7;
        int q = 3;
        int power = (int)((p-1)/q);
        int h = 2;
        int x = 2;

        int g = (int)(Math.pow(h, power)%p);
        int ya = (int)(Math.pow(g, x)%p);

        int k = 2;
        int m = 3;
        int r = (int)((Math.pow(g, k)%p)%q);
        int inv = inverse(k, q);
        int s = (inv*(m + x*r))%q;
        int w = inverse(s, q);
        int u1 = (m*w)%q;
        int u2 = (r*w)%q;

        int v = (int)(((  (((Math.pow(g, u1))%p)%q)  * (((Math.pow(ya, u2))%p)%q))%p)%q);
        System.out.println(v);
        System.out.println(r);
    }
}
