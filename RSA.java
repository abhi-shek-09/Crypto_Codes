//p q inputs
//n
//phi
//choose e such that e and phi are co prime
//select message m
//d is such that d*e mod phi = 1

//c = m^e mod n
//d = c^d mod n
public class RSA {
    public static int gcd(int a, int b){
        if(b == 0) return a;

        return gcd(b, a%b);
    }
    public static void main(String[] args) {
        int m = 28;
        int p = 3, q = 11;
        int n = p*q;
        int phi = (p-1) * (q-1);
        int e = 1;

        for(int i=2; i<phi; i++){
            if(gcd(i, phi) == 1){ // remember this
                e = i;
                break;
            }
        }

        int d = 0;
        while(d*e % phi != 1){ // remember this
            d+=1;
        }

        int c = (int)(Math.pow(m, e) % n);
        int dec = (int)(Math.pow(c, d) % n);

        System.out.println(m + " " + c + " " + dec);

    }
}
